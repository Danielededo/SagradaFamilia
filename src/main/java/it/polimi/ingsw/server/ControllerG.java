package it.polimi.ingsw.server;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Round;
import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.utils.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

public class ControllerG {
    protected Match match;
    private Hub server;
    private TimerTurn timerTurn;
    private int timer_t,timer_window;
    private boolean dicehand_done=false, toolhand_done=false;
    private boolean endRound=false;
    private boolean rank=false;
    private static String separator="\n---------------------------------------------------------------------------------------------";

    public ControllerG(Hub server,int timer_window,int timer_t) {
        this.server = server;
        this.timer_t=timer_t;
        this.timer_window=timer_window;
    }

    public TimerTurn getTimerTurn() {
        return timerTurn;
    }

    public boolean isRank() {
        return rank;
    }

    public void setMatch() throws RemoteException, InterruptedException {
        for (int i=0;i<match.getnumberPlayers();i++) {
            server.o.put(match.getPlayers().get(i).getNickname(),server.getListofobserver().get(i).getPassword());
        }
        System.out.println(server.o);

        server.notifyObserver("Public");
        JSONArray pubb = new JSONArray();
        for(PublicObject p: match.getPublictarget()){
            pubb.put(p.getName());
        }
        server.notifyObserver(pubb.toString());

        server.notifyObserver("Tool");
        JSONArray tool = new JSONArray();
        for(Tool p: match.getTool()){
            tool.put(p.getName());
        }

        server.notifyObserver(tool.toString());


        for (ClientInt c:server.getListofobserver()){
            int client_index=server.getListofobserver().indexOf(c);
            ArrayList<GlassWindow> windows=match.getScheme().extractGlass();
            if (match.getPlayers().get(client_index).isConnected()){
                TimerTurn schemetimer=new TimerTurn(c,server);
                try {
                    server.notify(c, "Privato");
                    server.notify(c, "" + match.getPlayers().get(client_index).getPrivatetarget().getName());


                    for(GlassWindow p: windows){
                        server.notify(c, "Scheme");
                        server.notify(c, updateWindow(p).toString());
                    }

                    server.notify(c, "Scheme done");

                    server.timer.schedule(schemetimer,1000*timer_window);
                    boolean wait = true;
                    int scheme = 6;
                    while(wait) {
                        Thread.sleep(1000);
                        scheme = selection(5,1,client_index) - 1;
                        if (scheme >= 0 && scheme < 4)
                            wait = false;
                    }
                    server.notify(c, "Timer scelta stop");
                    this.match.getPlayers().get(client_index).setWindow(windows.get(scheme));

                    server.notifyOthers(c, "Adv");
                    server.notifyOthers(c, updateAdversary(match.getPlayers().get(client_index)).toString());


                    System.out.println(c.getNickname() + " ha scelto " + windows.get(scheme).getName());
                } catch (ConnectException | UnmarshalException e) {
                    match.getPlayers().get(client_index).setConnected(false);
                    System.out.println(match.getPlayers().get(client_index).getNickname()+" disconnesso");
                    Random r=new Random();
                    this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                    System.out.println(match.getPlayers().get(client_index).getNickname() + " ha scelto " + match.getPlayers().get(client_index).getWindow().getName());
                    server.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
                } finally {
                    schemetimer.cancel();
                }
            }else {
                Random r=new Random();
                this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                System.out.println(match.getPlayers().get(client_index).getNickname() + " ha scelto " + match.getPlayers().get(client_index).getWindow().getName());
                server.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
            }
        }


        while(this.match.getRound()!=11){
            round();
        }
        rank=true;
        server.notifyObserver("PARTITA TERMINATA");
        this.match.endMatch();
        server.notifyObserver(this.match.ranking());
        try {
            Thread.sleep(1000*20);
        } catch (InterruptedException ignored) {
        }
        server.notifyObserver("disconnettiti");
        server.start=false;
        server.thread.cancel();
        timerTurn.cancel();
        server.terminateHub();
    }

    public void attend(){
        boolean c=true;
        while (c){

        }
    }

    public void round() throws RemoteException, InterruptedException {
        Round round= new Round(match);
        int k=0,t=1,j;
        for(int z=0; z<2*match.getnumberPlayers();z++){
            if (server.start){

                handleTurn(round,z,k,t);
                if(z>match.getnumberPlayers()-1)
                    k--;
                if(z<match.getnumberPlayers()-1)
                    k++;
                if(z==match.getnumberPlayers()-1){
                    for(Player p:match.getPlayers()){
                        p.setContTurn(2);
                    }
                    t=2;
                }
            }else attend();
        }
        endRound=true;
        server.notifyObserver(Colour.GREEN.escape()+"IL "+match.getRound()+"° ROUND è TERMINATO"+Colour.RESET);
        server.thread.cancel();
        Thread.sleep(2000);
        match.endRound();

        server.notifyObserver("ROUNDTRACK");
        server.notifyObserver(dicePacking(match.getRoundTrackList(match.getRound() - 1)).toString());





        if(match.getRound()!=11) {
            server.getListofobserver().add(server.getListofobserver().get(0));
            server.getListofobserver().remove(0);
        }
        server.thread=new DisconnectionThread(server);
        server.timer.schedule(server.thread,0,500);
        endRound=false;
    }

    public void handleTurn(Round round,int z,int k,int t)throws RemoteException{
        timerTurn=new TimerTurn(server.getListofobserver().get(k),server);
        try {
            if (round.getTurns().get(z).getOneplayer().isConnected()) {
                dicehand_done=false;
                toolhand_done=false;
                int cont_turn=1;

                server.timer.schedule(timerTurn,1000*timer_t);

                if (!round.getTurns().get(z).getOneplayer().isMissednextturn()){
                    do {
                        JSONArray ris = dicePacking(match.getStock().getDicestock());
                        server.notifyObserver("DRAFT");
                        server.notifyObserver(ris.toString());

                        server.notifyObserver("DRAFT END");
                        server.notifyOthers(server.getListofobserver().get(k), round.getTurns().get(z).getOneplayer().getNickname() + "sta eseguendo il suo turno.");
                        server.notify(server.getListofobserver().get(k),"E' il tuo turno. " + "Round: "+match.getRound()+"; Turno: " + t);

                        server.notifyOthers(server.getListofobserver().get(k),"Adv place");
                        server.notifyOthers(server.getListofobserver().get(k), updateAdversary(match.getPlayers().get(k)).toString());

                        if (dicehand_done && toolhand_done){
                            server.notify(server.getListofobserver().get(k), "PASS");
                        }
                        else if(!dicehand_done && toolhand_done) {
                            server.notify(server.getListofobserver().get(k), "MENU die");
                        }else if(dicehand_done && !toolhand_done){
                            server.notify(server.getListofobserver().get(k), "MENU tool");
                        }else{
                            server.notify(server.getListofobserver().get(k), "MENU whole");
                        }
                        int menu = -1;
                        boolean wait = true;

                        while(wait){
                            Thread.sleep(1000);
                            menu = selection(3,0, k);
                            if(menu >= Constants.MENU){
                                wait = false;
                            }
                        }
                        menu = menu - Constants.MENU;

                        switch (menu){
                            case 1:{
                                server.notifyObserver("Shift");
                                server.notify(server.getListofobserver().get(k), "Il tuo turno è terminato.");
                                server.notifyOthers(server.getListofobserver().get(k),server.getListofobserver().get(k).getNickname()+" ha terminato il suo turno");
                                timerTurn.cancel();
                                cont_turn=0;
                                break;
                            }
                            case 2:{
                                dicehand(k,z,round);
                                break;
                            }
                            case 3:{
                                tool_hand(k,z,round,cont_turn);
                                break;
                            }
                        }
                        server.notifyObserver("Remove die");
                    } while (cont_turn!=0);
                }else {
                    server.notify(server.getListofobserver().get(k),"Hai usato la carta utensile Tenaglia a Rotelle nel tuo primo turno perciò salti il turno corrente");
                    server.notifyOthers(server.getListofobserver().get(k),server.getListofobserver().get(k).getNickname()+" salta il turno per l'uso della carta utensile Tenaglia a Rotelle");
                    round.getTurns().get(z).getOneplayer().setMissednextturn(false);
                }
                server.notifyObserver("Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
            }
            else{
                server.notifyOthers(server.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname()+" salta il turno perchè è disconnesso\n"
                        +"Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
            }
        } catch (UnmarshalException | ConnectException e) {
            round.getTurns().get(z).getOneplayer().setConnected(false);
            System.out.println(round.getTurns().get(z).getOneplayer().getNickname()+" disconnesso");
            server.notifyOthers(server.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname()+" salta il turno perchè è disconnesso\n"
                    +"Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
            timerTurn.cancel();
            return;
        } catch (InterruptedException ignored) {
        }
    }

    public void dicehand(int k,int z,Round round) throws RemoteException, InterruptedException {
        int index_draft = -1, row, column;
        server.notify(server.getListofobserver().get(k), "Scegli un dado.");

        boolean wait = true;

        while(wait){
            Thread.sleep(1000);
            index_draft = selection(match.getStock().getDicestock().size()+1,0, k);
            if(index_draft < Constants.F_SLOT && index_draft > Constants.F_DIE){
                wait = false;
            }
        }
        server.notify(server.getListofobserver().get(k), "DIE OK");


        server.notify(server.getListofobserver().get(k), "Ora scegli una casella della tua vetrata.");
        wait = true;
        int choice = -1;
        while(wait){
            Thread.sleep(1000);
            choice = selection(20, 0, k);
            if(choice > Constants.F_SLOT && choice <= Constants.S_DIE){
                wait = false;
            }
        }

        row = rowRefactor(choice - Constants.F_SLOT);
        column = coloumnRefactor(choice - Constants.F_SLOT);
        index_draft = index_draft - Constants.F_DIE;
        //int indexdie=match.getStock().getDicestock().indexOf(match.getStock().getDieMap().get(index_draft - Constants.F_DIE));

        match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(), round.getTurns().get(z).getOneplayer().getWindow().getSlot(row, column), match.getStock().getDicestock().get(index_draft - 1));

        if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()){
            server.notify(server.getListofobserver().get(k), "Dado piazzato correttamente");
            server.notify(server.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

            /*server.notifyOthers(server.getListofobserver().get(k),"Adv place");
               server.notifyOthers(server.getListofobserver().get(k), updateAdversary(match.getPlayers().get(k)).toString());*/

            /*choice = choice - Constants.F_SLOT;
               server.notifyOthers(server.getListofobserver().get(k), updateTassel(k, index_draft, choice).toString());*/

            match.getStock().getDicestock().remove(index_draft - 1);
            match.getStock().getDieMap().remove(index_draft-Constants.F_DIE);

            dicehand_done=true;
        } else {
            server.notify(server.getListofobserver().get(k), "ERROR");
            server.notify(server.getListofobserver().get(k), match.getRules().getError());
        }
    }

    public void tool_hand(int k,int z,Round round,int cont_turn)throws RemoteException{
        int cont=1;
        int index;
        server.notify(server.getListofobserver().get(k),"Scegli una carta utensile dalla lista tramite il suo indice: ");
        index=selection(4,1,k);
        server.notify(server.getListofobserver().get(k),"La tua scelta è: "+match.getTool().get(index-1));
        match.getTool().get(index-1).setPlayer(round.getTurns().get(z).getOneplayer());
        if (!tool_selection(k,z,round,match.getTool().get(index-1),cont_turn)){
            server.notify(server.getListofobserver().get(k),match.getTool().get(index-1).getError());
        }else {
            server.notify(server.getListofobserver().get(k),"Operazione completata");
            toolhand_done=true;
            server.notifyOthers(server.getListofobserver().get(k),server.getListofobserver().get(k).getNickname()+" ha usato la carta utensile "+match.getTool().get(index-1).getName());
        }
    }

    public boolean tool_selection(int k, int z, Round round, Tool tool, int cont_turn) throws RemoteException {
        ArrayList<Die> dice=new ArrayList<>();
        ArrayList<Slot> slots=new ArrayList<>();
        try {
            switch (tool.getName()){
                case "Pinza Sgrossatrice":{
                    int index_draft,piumeno=-1;
                    boolean b;
                    server.notify(server.getListofobserver().get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    server.notify(server.getListofobserver().get(k),"la tua scelta è "+match.getStock().getDicestock().get(index_draft));
                    server.notify(server.getListofobserver().get(k),"Ora seleziona 0 se vuoi decrementare il valore o 1 se vuoi incrementare il valore");
                    do {
                        try {
                            piumeno = server.getListofobserver().get(k).selection_int();
                        }catch (InputMismatchException e){
                            piumeno=10;
                        }
                    } while (piumeno!=0&&piumeno!=1);
                    dice.add(match.getStock().getDicestock().get(index_draft));
                    return tool.effect(dice,match,slots,piumeno);
                }
                case "Pennello per Eglomise": {
                    int row1,column1,row2,column2;
                    server.notify(server.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale prendere il dadoe");
                    row1=selection(4,0,k);
                    column1=selection(5,0,k);
                    server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                    row2=selection(4,0,k);
                    column2=selection(5,0,k);
                    Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                    Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                    slots.add(slot1);
                    slots.add(slot2);
                    return tool.effect(dice,match,slots,0);
                }
                case "Alesatore per lamina di rame": {
                    int row1,column1,row2,column2;
                    server.notify(server.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale prendere il dadoe");
                    row1=selection(4,0,k);
                    column1=selection(5,0,k);
                    server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                    row2=selection(4,0,k);
                    column2=selection(5,0,k);
                    Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                    Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                    slots.add(slot1);
                    slots.add(slot2);
                    return tool.effect(dice,match,slots,0);
                }
                case "Lathekin": {
                    int row1,column1,row2,column2,row3,column3,row4,column4;
                    server.notify(server.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il primo dado");
                    row1=selection(4,0,k);
                    column1=selection(5,0,k);
                    server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il primo dado");
                    row2=selection(4,0,k);
                    column2=selection(5,0,k);
                    server.notify(server.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il secondo dado");
                    row3=selection(4,0,k);
                    column3=selection(5,0,k);
                    server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il secondo dado");
                    row4=selection(4,0,k);
                    column4=selection(5,0,k);
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1));
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2));
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3));
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4));
                    return tool.effect(dice,match,slots,0);
                }
                case "Taglierina circolare": {
                    int index_draft,index_roundtrackList,index_roundtrackDie;
                    if (match.getRound()>1) {
                        server.notify(server.getListofobserver().get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                        index_draft=selection(match.getStock().getDicestock().size(),0,k);
                        server.notify(server.getListofobserver().get(k),"la tua scelta è "+match.getStock().getDicestock().get(index_draft));
                        server.notify(server.getListofobserver().get(k),"Ora inserisci il numero del round e l'indice del dado scelto");
                        index_roundtrackList=selection(match.getRound(),1,k)-1;
                        index_roundtrackDie=selection(match.getRoundTrackList(index_roundtrackList).size(),0,k);
                        dice.add(match.getStock().getDicestock().get(index_draft));
                        dice.add(match.getRoundTrackList(index_roundtrackList).get(index_roundtrackDie));
                        return tool.effect(dice,match,slots,index_roundtrackList);
                    }else server.notify(server.getListofobserver().get(k),"Non ci sono dadi sul tracciato dei round");
                    return false;
                }
                case "Pennello per Pasta Salda": {
                    int index_draft;
                    server.notify(server.getListofobserver().get(k),"Scgli un dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    dice.add(match.getStock().getDicestock().get(index_draft));
                    return tool.effect(dice,match,slots,0);
                }
                case "Martelletto": {
                    if (round.getTurns().get(z).getOneplayer().getContTurn()==2){
                        if (!dicehand_done)
                            return tool.effect(dice,match,slots,0);
                        else {
                            server.notify(server.getListofobserver().get(k),"Hai già posizionato un dado perciò non puoi usare questa carta");
                            return false;
                        }
                    }
                    server.notify(server.getListofobserver().get(k),"Non è il tuo secondo turno");
                    return false;
                }
                case "Tenaglia a Rotelle": {
                    int index_draft,row,column;
                    if (!dicehand_done){
                        server.notify(server.getListofobserver().get(k),"Per usare questa carta devi prima posizionare un dado");
                        return false;
                    }
                    server.notify(server.getListofobserver().get(k),tool.getPlayer().getWindow()+"\nPuoi selezionare un altro dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size()+1,0,k);
                    if (index_draft==match.getStock().getDicestock().size()){return false;}
                    server.notify(server.getListofobserver().get(k), "\nLa tua scelta è: " + match.getStock().getDicestock().get(index_draft).toString() +
                            "\nScegli la casella della tua tua carta schema dove posizionare il dado, rispettivamente riga e colonna: ");
                    row=selection(4,0,k);
                    column=selection(5,0,k);
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column));
                    dice.add(match.getStock().getDicestock().get(index_draft));
                    if (tool.effect(dice,match,slots,0)){
                        cont_turn=0;
                        return true;
                    }else return false;
                }
                case "Riga in Sughero": {
                    int index_draft,row,column;
                    if (!dicehand_done) {
                        server.notify(server.getListofobserver().get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                        index_draft=selection(match.getStock().getDicestock().size()+1,0,k);
                        if(index_draft==match.getStock().getDicestock().size()){return false;}
                        server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dalla quale prendere il dado");
                        row=selection(4,0,k);
                        column=selection(5,0,k);
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column));
                        dice.add(match.getStock().getDicestock().get(index_draft));
                        if (tool.effect(dice,match,slots,0)){
                            dicehand_done=true;
                            match.getStock().getDicestock().remove(index_draft);
                            cont_turn=0;
                            server.notify(server.getListofobserver().get(k),"DieG posizionato correttamente");
                            return true;
                        }else return false;
                    }else {server.notify(server.getListofobserver().get(k),"Hai già posizionato un dado");}
                    return false;
                }
                case "Tampone Diamantato": {
                    int index_draft;
                    server.notify(server.getListofobserver().get(k),"Scegli un dado dalla riserva al quale invertire la faccia:\n"+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    dice.add(match.getStock().getDicestock().get(index_draft));
                    return tool.effect(dice,match,slots,0);
                }
                case "Diluente per Pasta Salda": {
                    int index_draft,value,row,column;
                    boolean loop;
                    if (!dicehand_done) {
                        server.notify(server.getListofobserver().get(k),"Scegli un dado dalla riserva da rimettere nel sacchetto:\n"+match.getStock().toString());
                        index_draft=selection(match.getStock().getDicestock().size(),0,k);
                        Die die=match.getStock().getDicestock().get(index_draft);
                        dice.add(die);
                        if (tool.effect(dice,match,slots,0)){
                            Die d = match.getSack().extractdie();
                            server.notify(server.getListofobserver().get(k),d+" ora seleziona la faccia da dare al dado");
                            value=selection(7,0,k);
                            d.setFace(value);
                            server.notify(server.getListofobserver().get(k),d+"\nAdesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                            row=selection(4,0,k);
                            column=selection(5,0,k);
                            Slot slot=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column);
                            match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(),slot,d);
                            if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()){
                                dicehand_done=true;
                                return true;
                            }else {
                                match.getStock().getDicestock().add(die);
                                match.getSack().adddie(d);
                                return false;
                            }
                        }
                    }else server.notify(server.getListofobserver().get(k),"Hai già posizionato un dado");
                    return false;
                }
                case "Taglierina Manuale": {
                    if (match.getRound()!=1) {
                        int row1,column1,row2,column2,row3,column3,row4,column4;
                        server.notify(server.getListofobserver().get(k),"Vuoi muovere 1 o 2 dadi?");
                        int move=selection(4,1,k);
                        server.notify(server.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il primo dado");
                        row1=selection(4,0,k);
                        column1=selection(5,0,k);
                        server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il primo dado");
                        row2=selection(4,0,k);
                        column2=selection(5,0,k);
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1));
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2));
                        if(move==2){
                            server.notify(server.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il secondo dado");
                            row3=selection(4,0,k);
                            column3=selection(5,0,k);
                            server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il secondo dado");
                            row4=selection(4,0,k);
                            column4=selection(5,0,k);
                            slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3));
                            slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4));
                            return tool.effect(dice,match,slots,0);
                        }else return tool.effect(dice,match,slots,0);
                    }else {
                        server.notify(server.getListofobserver().get(k),"Non puoi usare questa carta nel 1° round perchè non ci sono dadi sul tracciato dei round");
                        return false;
                    }
                }
            }
            return false;
        } catch (UnmarshalException e) {
            cont_turn=0;
            return false;
        }
    }

    public int selection(int max,int min, int k)throws RemoteException{
        int index;
        index = server.getListofobserver().get(k).selection_int();
        return index;
    }



    public String menu(){
        return match.toStringRoundTrack()+"\nRiserva: "+match.getStock().toString()+"\nScegli cosa fare : \n0: fine turno; \n1: posiziona un dado dalla riserva;" +
                "\n2: usa una carta utensile:\n"+match.toolcardsString();

    }

    public boolean isStart() {
        return server.start;
    }

    public int rowRefactor(int i){
        if(i <= 4 && i >= 0){
            return 0;
        }else if(i <= 9 && i >= 5){
            return 1;
        }else if(i <= 14 && i >= 10){
            return 2;
        }else if(i <= 19 && i >= 15){
            return 3;
        }else return -1;
    }

    public int coloumnRefactor(int i){
        if(i == 0 || i == 5 || i == 10 || i == 15){
            return 0;
        }else if(i == 1 || i == 6 || i == 11 || i == 16){
            return 1;
        }else if(i == 2 || i == 7 || i == 12 || i == 17){
            return 2;
        }else if(i == 3 || i == 8 || i == 13 || i == 18){
            return 3;
        }else if(i == 4 || i == 9 || i == 14 || i == 19){
            return 4;
        }else return -1;
    }

    public JSONObject updateTassel(int k, int index_draft, int choice) throws RemoteException {
        JSONObject change = new JSONObject();
        change.put("name", server.getListofobserver().get(k).getNickname());
        change.put("face", match.getStock().getDicestock().get(index_draft - 1).getFace() + "");
        change.put("color", match.getStock().getDicestock().get(index_draft - 1).getDicecolor().name());
        change.put("pos", choice);
        return change;
    }

    public JSONObject updateWindow(GlassWindow provv){
        JSONObject def = new JSONObject();
        def.put("name", provv.getName());
        def.put("diff", provv.getDifficulty());
        JSONArray change = new JSONArray();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                JSONObject obj = new JSONObject();
                if(provv.getSlot(i,j).isOccupate()){
                    obj.put("face", provv.getSlot(i,j).getDice().getFace() + "");
                    obj.put("color", provv.getSlot(i,j).getDice().getDicecolor().name());
                }else{
                    obj.put("face", provv.getSlot(i,j).getValue() + "");
                    obj.put("color", provv.getSlot(i,j).getSlotcolour().name());
                }
                change.put(obj);
            }
        }
        def.put("glass",change);
        return def;
    }

    public JSONObject updateAdversary(Player p){
        JSONObject def = new JSONObject();
        def.put("player", p.getNickname());
        JSONObject glass = updateWindow(p.getWindow());
        def.put("glasswindow", glass);
        return def;
    }
     public JSONArray dicePacking(ArrayList<Die> list){
         JSONArray ris = new JSONArray();
         for(Die d: list){
             JSONObject provv = new JSONObject();
             provv.put("Face", d.getFace());
             provv.put("Color", d.getDicecolor().name());
             ris.put(provv);
         }
         return ris;
     }
}
