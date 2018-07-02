package it.polimi.ingsw.server;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.server.model.cards.*;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Round;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.utils.Constants;
import it.polimi.ingsw.server.utils.DisconnectionThread;

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
    private boolean dicehand_done=false, toolhand_done=false;
    private boolean endRound=false;
    private boolean rank=false;
    private static String separator="\n---------------------------------------------------------------------------------------------";

    public ControllerG(Hub server) {
        this.server = server;
    }

    public TimerTurn getTimerTurn() {
        return timerTurn;
    }

    public boolean isRank() {
        return rank;
    }

    public void setMatch() throws RemoteException, InterruptedException {
        final int sec=60;
        for (int i=0;i<match.getnumberPlayers();i++) {
            server.o.put(match.getPlayers().get(i).getNickname(),server.getListofobserver().get(i).getPassword());
        }
        System.out.println(server.o);
        //Thread.sleep(2000);

        for(PublicObject p: match.getPublictarget()){
            server.notifyObserver("Public");
            server.notifyObserver("" + p.getName());
        }

        for(Tool p: match.getTool()){
            server.notifyObserver("Tool");
            server.notifyObserver("" + p.getName());
        }



        for (ClientInt c:server.getListofobserver()){
            int client_index=server.getListofobserver().indexOf(c);
            ArrayList<GlassWindow> windows=match.getScheme().extractGlass();
            if (match.getPlayers().get(client_index).isConnected()){
                TimerTurn schemetimer=new TimerTurn(c,server);
                try {
                    server.notify(c, "Privato");
                    server.notify(c, "" + match.getPlayers().get(client_index).getPrivatetarget().getName());


                    server.notify(c, "Scheme");
                    for(GlassWindow p: windows){
                        server.notify(c, "Glasswindow");
                        server.notify(c, p.getName());
                    }
                    server.notify(c, "Scheme done");

                    //server.notify(c,"\n"+ match.getScheme().schemechoice(windows)+"\nScegli la tua carta schema tramite il suo indice");
                    server.timer.schedule(schemetimer,1000*sec);
                    boolean wait = true;
                    int scheme = 6;
                    while(wait) {
                        Thread.sleep(1000);
                        scheme = selection(5,1,client_index)-1;
                        if (scheme > 0 && scheme < 5)
                            wait = false;
                    }
                    server.notify(c, "Timer scelta stop");
                    this.match.getPlayers().get(client_index).setWindow(windows.get(scheme));
                    server.notifyOthers(c, "Avversario nome");
                    server.notifyOthers(c, match.getPlayers().get(client_index).getNickname());
                    server.notifyOthers(c, "Avversario vetrata");
                    server.notifyOthers(c,match.getPlayers().get(client_index).getWindow().getName());


                    System.out.println(c.getNickname() + " ha scelto " + windows.get(scheme).getName());
                } catch (ConnectException e) {
                    match.getPlayers().get(client_index).setConnected(false);
                    System.out.println(match.getPlayers().get(client_index).getNickname()+" disconnesso");
                    Random r=new Random();
                    this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                    System.out.println(match.getPlayers().get(client_index).getNickname() + " ha scelto " + match.getPlayers().get(client_index).getWindow().getName());
                    server.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
                } catch (UnmarshalException e){
                    match.getPlayers().get(client_index).setConnected(false);
                    System.out.println(match.getPlayers().get(client_index).getNickname()+" disconnesso");
                    Random r=new Random();
                    this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                    System.out.println(match.getPlayers().get(client_index).getNickname() + " ha scelto " + match.getPlayers().get(client_index).getWindow().getName());
                    server.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
                }finally {
                    schemetimer.cancel();
                }
            }else {
                Random r=new Random();
                this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                System.out.println(match.getPlayers().get(client_index).getNickname() + " ha scelto " + match.getPlayers().get(client_index).getWindow().getName());
                server.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
            }
        }

        server.notifyObserver("START");

        //server.notifyObserver(match.getGlassWindowPlayers()+separator+"\n"+ Colour.RED.escape()+"LA PARTITA HA INIZIO"+Colour.RESET);




        while(this.match.getRound()!=11){
            round();
        }
        rank=true;
        server.notifyObserver("PARTITA TERMINATA");
        this.match.endMatch();
        server.notifyObserver(this.match.ranking());
        try {
            Thread.sleep(1000*20);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        if(match.getRound()!=11) {
            server.getListofobserver().add(server.getListofobserver().get(0));
            server.getListofobserver().remove(0);
        }
        server.thread=new DisconnectionThread(server);
        server.timer.schedule(server.thread,0,500);
        endRound=false;
    }

    public void handleTurn(Round round,int z,int k,int t)throws RemoteException{
        final int sec=300;
        timerTurn=new TimerTurn(server.getListofobserver().get(k),server);
        try {
            if (round.getTurns().get(z).getOneplayer().isConnected()) {
                dicehand_done=false;
                toolhand_done=false;
                int cont_turn=1;

                server.notifyOthers(server.getListofobserver().get(k), round.getTurns().get(z).getOneplayer().getNickname() + "sta eseguendo il suo turno.");
                server.notify(server.getListofobserver().get(k),"E' il tuo turno. " + "Round: "+match.getRound()+"; Turno: " + t);


                int menu = -1;
                server.timer.schedule(timerTurn,1000*sec);

                if (!round.getTurns().get(z).getOneplayer().isMissednext_turn()){
                    do {
                        if (dicehand_done && toolhand_done){
                            //server.notify(server.getListofobserver().get(k),"Puoi solo terminare il tuo turno ");
                            server.notify(server.getListofobserver().get(k), "PASS");
                        }
                        else
                        if(!dicehand_done && toolhand_done) {
                            //server.notify(server.getListofobserver().get(k),"Puoi solo posizionare un dado o terminare il turno");
                            server.notify(server.getListofobserver().get(k), "MENU die");
                        }else if(dicehand_done && !toolhand_done){
                            //server.notify(server.getListofobserver().get(k),"Puoi solo usare una carta utensile o terminare il turno");
                            server.notify(server.getListofobserver().get(k), "MENU tool");
                        }else{
                            server.notify(server.getListofobserver().get(k), "MENU whole");
                        }

                        for (Die d: match.getStock().getDicestock()) {
                            server.notifyObserver("Draft face");
                            server.notifyObserver(d.getFace() + "");
                            server.notifyObserver("Draft color");
                            server.notifyObserver(d.getDicecolor().name());
                        }

                        server.notifyObserver("Draft end");

                        boolean wait = true;

                        while(wait){
                            Thread.sleep(1000);
                            menu = selection(3,0, k);
                            if(menu >= 5){
                                wait = false;
                            }
                        }

                        switch (menu){
                            case 5:{
                                server.notifyObserver("Shift");
                                server.notify(server.getListofobserver().get(k), "Il tuo turno è terminato.");
                                server.notifyOthers(server.getListofobserver().get(k),server.getListofobserver().get(k).getNickname()+" ha terminato il suo turno");
                                timerTurn.cancel();
                                cont_turn=0;
                                break;
                            }
                            case 6:{
                                //if (!dicehand_done)
                                //server.notify(server.getListofobserver().get(k), "DIE");
                                dicehand(k,z,round);
                                //else server.notify(server.getListofobserver().get(k),"Hai già posizionato un dado in questo turno");
                                break;
                            }
                            case 7:{
                                //if(!toolhand_done)
                                    tool_hand(k,z,round,cont_turn);
                                //else
                                //    server.notify(server.getListofobserver().get(k),"Hai già usato una carta utensile in questo turno");
                                break;
                            }
                        }
                    } while (cont_turn!=0);
                }else {
                    server.notify(server.getListofobserver().get(k),"Hai usato la carta utensile Tenaglia a Rotelle nel tuo primo turno perciò salti il turno corrente");
                    server.notifyOthers(server.getListofobserver().get(k),server.getListofobserver().get(k).getNickname()+" salta il turno per l'uso della carta utensile Tenaglia a Rotelle");
                    round.getTurns().get(z).getOneplayer().setMissednext_turn(false);
                }
                server.notifyObserver("Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
            }
            else{
                server.notifyOthers(server.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname()+" salta il turno perchè è disconnesso\n"
                        +"Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
            }
        } catch (UnmarshalException e) {
            round.getTurns().get(z).getOneplayer().setConnected(false);
            System.out.println(round.getTurns().get(z).getOneplayer().getNickname()+" disconnesso");
            server.notifyOthers(server.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname()+" salta il turno perchè è disconnesso\n"
                    +"Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
            timerTurn.cancel();
            return;
        }catch (ConnectException e){
            round.getTurns().get(z).getOneplayer().setConnected(false);
            System.out.println(round.getTurns().get(z).getOneplayer().getNickname()+" disconnesso");
            server.notifyOthers(server.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname()+" salta il turno perchè è disconnesso\n"
                    +"Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
            timerTurn.cancel();
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dicehand(int k,int z,Round round) throws RemoteException, InterruptedException {
        int cont=1;
        while(cont!=0) {
            int index_draft = -1, row, column;
            server.notify(server.getListofobserver().get(k), "Scegli un dado.");

            boolean wait = true;

            while(wait){
                Thread.sleep(1000);
                index_draft = selection(match.getStock().getDicestock().size()+1,0, k);
                if(index_draft > Constants.F_DIE){
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
                if(choice > Constants.F_SLOT){
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


                server.notifyObserver("Remove die");


                server.notifyOthers(server.getListofobserver().get(k), "Adv place");
                server.notifyOthers(server.getListofobserver().get(k), server.getListofobserver().get(k).getNickname());
                server.notifyOthers(server.getListofobserver().get(k), "Placed face");
                server.notifyOthers(server.getListofobserver().get(k), match.getStock().getDicestock().get(index_draft - 1).getFace() + "");
                server.notifyOthers(server.getListofobserver().get(k), "Placed face");
                server.notifyOthers(server.getListofobserver().get(k), match.getStock().getDicestock().get(index_draft - 1).getDicecolor().name());
                server.notifyOthers(server.getListofobserver().get(k), "Tassel place");
                choice = choice - Constants.F_SLOT;
                server.notifyOthers(server.getListofobserver().get(k), choice + "");


                match.getStock().getDicestock().remove(index_draft - 1);
                match.getStock().getDieMap().remove(index_draft-Constants.F_DIE);

                cont = 0;
                dicehand_done=true;
            } else {
                    server.notify(server.getListofobserver().get(k), "ERROR");
                    server.notify(server.getListofobserver().get(k), match.getRules().getError());
            }
        }
    }

    public void tool_hand(int k,int z,Round round,int cont_turn)throws RemoteException{
        int cont=1,placed=1;
        int index;
        server.notify(server.getListofobserver().get(k),"Scegli una carta utensile dalla lista tramite il suo indice: ");
        index=selection(4,1,k);
        server.notify(server.getListofobserver().get(k),"La tua scelta è: "+match.getTool().get(index-1));
        while (cont!=0&&placed!=0){
            match.getTool().get(index-1).setPlayer(round.getTurns().get(z).getOneplayer());
            if (!tool_selection(k,z,round,match.getTool().get(index-1),cont_turn,placed)){
                server.notify(server.getListofobserver().get(k),match.getTool().get(index-1).getError());
                if (!match.getTool().get(index-1).isUsed()) placed=0;
            }else {
                server.notify(server.getListofobserver().get(k),"Operazione completata");
                toolhand_done=true;
                server.notifyOthers(server.getListofobserver().get(k),server.getListofobserver().get(k).getNickname()+" ha usato la carta utensile "+match.getTool().get(index-1).getName());
                cont=0;
            }
        }
    }

    public boolean tool_selection(int k, int z, Round round, Tool tool, int cont_turn, int placed) throws RemoteException {
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
                    if (piumeno==0)b=false;
                    else b=true;
                    return tool.effect(match.getStock().getDicestock().get(index_draft),null,b,match,match.getStock(),null,null,null,null,0);
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
                    return tool.effect(null,null,false,match,match.getStock(),slot1,slot2,null,null,0);
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
                    return tool.effect(null,null,false,match,match.getStock(),slot1,slot2,null,null,0);
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
                    Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                    Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                    Slot slot3=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3);
                    Slot slot4=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4);
                    return tool.effect(null,null,false,match,match.getStock(),slot1,slot2,slot3,slot4,0);
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
                        Die d=match.getRoundTrackList(index_roundtrackList).get(index_roundtrackDie);
                        return tool.effect(match.getStock().getDicestock().get(index_draft),d,false,match,match.getStock(),null,null,null,null,index_roundtrackList);
                    }else server.notify(server.getListofobserver().get(k),"Non ci sono dadi sul tracciato dei round");
                    return false;
                }
                case "Pennello per Pasta Salda": {
                    int index_draft;
                    server.notify(server.getListofobserver().get(k),"Scgli un dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    return tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0);
                }
                case "Martelletto": {
                    if (round.getTurns().get(z).getOneplayer().getContTurn()==2){
                        if (!dicehand_done)
                            return tool.effect(null,null,false,match,match.getStock(),null,null,null,null,0);
                        else {
                            server.notify(server.getListofobserver().get(k),"Hai già posizionato un dado perciò non puoi usare questa carta");
                            placed=0;
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
                        placed=0;
                        return false;
                    }
                    server.notify(server.getListofobserver().get(k),tool.getPlayer().getWindow()+"\nPuoi selezionare un altro dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size()+1,0,k);
                    if (index_draft==match.getStock().getDicestock().size()){placed=0;return false;}
                    server.notify(server.getListofobserver().get(k), "\nLa tua scelta è: " + match.getStock().getDicestock().get(index_draft).toString() +
                            "\nScegli la casella della tua tua carta schema dove posizionare il dado, rispettivamente riga e colonna: ");
                    row=selection(4,0,k);
                    column=selection(5,0,k);
                    Slot slot=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column);
                    if (tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),slot,null,null,null,0)){
                        cont_turn=0;
                        return true;
                    }else return false;
                }
                case "Riga in Sughero": {
                    int index_draft,row,column;
                    if (!dicehand_done) {
                        server.notify(server.getListofobserver().get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                        index_draft=selection(match.getStock().getDicestock().size()+1,0,k);
                        if(index_draft==match.getStock().getDicestock().size()){placed=0;return false;}
                        server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dalla quale prendere il dado");
                        row=selection(4,0,k);
                        column=selection(5,0,k);
                        Slot slot=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column);
                        if (tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),slot,null,null,null,0)){
                            dicehand_done=true;
                            match.getStock().getDicestock().remove(index_draft);
                            cont_turn=0;
                            server.notify(server.getListofobserver().get(k),"DieG posizionato correttamente");
                            return true;
                        }else return false;
                    }else {server.notify(server.getListofobserver().get(k),"Hai già posizionato un dado");placed=0;}
                    return false;
                }
                case "Tampone Diamantato": {
                    int index_draft;
                    server.notify(server.getListofobserver().get(k),"Scegli un dado dalla riserva al quale invertire la faccia:\n"+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    return tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0);
                }
                case "Diluente per Pasta Salda": {
                    int index_draft,value,row,column;
                    boolean loop;
                    if (!dicehand_done) {
                        server.notify(server.getListofobserver().get(k),"Scegli un dado dalla riserva da rimettere nel sacchetto:\n"+match.getStock().toString());
                        index_draft=selection(match.getStock().getDicestock().size(),0,k);
                        if (tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0)){
                            Die d = match.getSack().extractdie();
                            server.notify(server.getListofobserver().get(k),d+" ora seleziona la faccia da dare al dado");
                            value=selection(7,0,k);
                            d.setFace(value);
                            do {
                                server.notify(server.getListofobserver().get(k),d+"\nAdesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                                row=selection(4,0,k);
                                column=selection(5,0,k);
                                Slot slot=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column);
                                match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(),slot,d);
                                if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()){
                                    dicehand_done=true;
                                    return true;
                                }else loop=false;
                            } while (!loop);
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
                        Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                        Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                        if(move==2){
                            server.notify(server.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il secondo dado");
                            row3=selection(4,0,k);
                            column3=selection(5,0,k);
                            server.notify(server.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il secondo dado");
                            row4=selection(4,0,k);
                            column4=selection(5,0,k);
                            Slot slot3=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3);
                            Slot slot4=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4);
                            return tool.effect(null,null,false,match,match.getStock(),slot1,slot3,slot2,slot4,0);
                        }else return tool.effect(null,null,false,match,match.getStock(),slot1,null,slot2,null,0);
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
        if(i <= 5 && i >=1){
            return 0;
        }else if(i <= 10 && i >= 6){
            return 1;
        }else if(i <= 15 && i >= 11){
            return 2;
        }else if(i <= 20 && i >= 16){
            return 3;
        }else return -1;
    }

    public int coloumnRefactor(int i){
        if(i == 1 || i ==6 || i == 11 || i == 16){
            return 0;
        }else if(i == 2 || i == 7 || i == 12 || i == 17){
            return 1;
        }else if(i == 3 || i == 8 || i == 13 || i == 18){
            return 2;
        }else if(i == 4 || i == 9 || i == 14 || i == 19){
            return 3;
        }else if(i == 5 || i == 10 || i == 15 || i == 20){
            return 4;
        }else return -1;
    }
}
