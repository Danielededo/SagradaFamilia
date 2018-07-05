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
import java.util.Random;

public class ControllerG {
    protected Match match;
    private Hub hub;
    private TimerTurn timerTurn;
    private int timer_t,timer_window;
    private boolean dicehand_done=false, toolhand_done=false;
    private boolean endRound=false;
    private boolean rank=false;
    private static String separator="\n---------------------------------------------------------------------------------------------";

    public ControllerG(Hub hub, int timer_window, int timer_t) {
        this.hub = hub;
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
            hub.o.put(match.getPlayers().get(i).getNickname(), hub.getListofobserver().get(i).getPassword());
        }
        System.out.println(hub.o);

        hub.notifyObserver("Public");
        JSONArray pubb = new JSONArray();
        for(PublicObject p: match.getPublictarget()){
            pubb.put(p.getName());
        }
        hub.notifyObserver(pubb.toString());

        hub.notifyObserver("Tool");
        JSONArray tool = new JSONArray();
        for(Tool p: match.getTool()){
            tool.put(p.getName());
        }

        hub.notifyObserver(tool.toString());


        for (ClientInt c: hub.getListofobserver()){
            int client_index= hub.getListofobserver().indexOf(c);
            ArrayList<GlassWindow> windows=match.getScheme().extractGlass();
            if (match.getPlayers().get(client_index).isConnected()){
                TimerTurn schemetimer=new TimerTurn(c, hub);
                try {
                    hub.notify(c, "Privato");
                    hub.notify(c, "" + match.getPlayers().get(client_index).getPrivatetarget().getName());


                    for(GlassWindow p: windows){
                        hub.notify(c, "Scheme");
                        hub.notify(c, updateWindow(p).toString());
                    }

                    hub.notify(c, "Scheme done");

                    hub.timer.schedule(schemetimer,1000*timer_window);

                    int scheme;
                    scheme = selection(5,1,client_index) - 1;

                    hub.notify(c, "Timer scelta stop");
                    this.match.getPlayers().get(client_index).setWindow(windows.get(scheme));

                    hub.notifyOthers(c, "Adv");
                    hub.notifyOthers(c, updateAdversary(match.getPlayers().get(client_index)).toString());


                    System.out.println(c.getNickname() + " ha scelto " + windows.get(scheme).getName());
                } catch (ConnectException | UnmarshalException e) {
                    match.getPlayers().get(client_index).setConnected(false);
                    System.out.println(match.getPlayers().get(client_index).getNickname()+" disconnesso");
                    Random r=new Random();
                    this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                    System.out.println(match.getPlayers().get(client_index).getNickname() + " ha scelto " + match.getPlayers().get(client_index).getWindow().getName());
                    hub.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
                } finally {
                    schemetimer.cancel();
                }
            }else {
                Random r=new Random();
                this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                System.out.println(match.getPlayers().get(client_index).getNickname() + " ha scelto " + match.getPlayers().get(client_index).getWindow().getName());
                hub.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
            }
        }


        while(this.match.getRound()!=11){
            round();
        }
        rank=true;
        hub.notifyObserver("PARTITA TERMINATA");
        this.match.endMatch();
        hub.notifyObserver(this.match.ranking());
        try {
            Thread.sleep(1000*20);
        } catch (InterruptedException ignored) {
        }
        hub.notifyObserver("disconnettiti");
        hub.start=false;
        hub.thread.cancel();
        timerTurn.cancel();
        hub.terminateHub();
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
            if (hub.start){

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
        hub.notifyObserver(Colour.GREEN.escape()+"IL "+match.getRound()+"° ROUND è TERMINATO"+Colour.RESET);
        hub.thread.cancel();
        Thread.sleep(2000);
        match.endRound();

        //hub.notifyObserver("ROUNDTRACK");
        //hub.notifyObserver(dicePacking(match.getRoundTrackList(match.getRound())).toString());





        if(match.getRound()!=11) {
            hub.getListofobserver().add(hub.getListofobserver().get(0));
            hub.getListofobserver().remove(0);
        }
        hub.thread=new DisconnectionThread(hub);
        hub.timer.schedule(hub.thread,0,500);
        endRound=false;
    }

    public void handleTurn(Round round,int z,int k,int t)throws RemoteException{
        timerTurn=new TimerTurn(hub.getListofobserver().get(k), hub);
        try {
            if (round.getTurns().get(z).getOneplayer().isConnected()) {
                dicehand_done=false;
                toolhand_done=false;
                int cont_turn=1;

                hub.timer.schedule(timerTurn,1000*timer_t);

                if (!round.getTurns().get(z).getOneplayer().isMissednextturn()){
                    do {
                        JSONArray ris = dicePacking(match.getStock().getDicestock());
                        hub.notifyObserver("DRAFT");
                        hub.notifyObserver(ris.toString());

                        hub.notifyObserver("DRAFT END");
                        hub.notifyOthers(hub.getListofobserver().get(k), round.getTurns().get(z).getOneplayer().getNickname() + "sta eseguendo il suo turno.");
                        hub.notify(hub.getListofobserver().get(k),"E' il tuo turno. " + "Round: "+match.getRound()+"; Turno: " + t);

                        hub.notifyOthers(hub.getListofobserver().get(k), Constants.ADV_RELOAD);
                        hub.notifyOthers(hub.getListofobserver().get(k), updateAdversary(match.getPlayers().get(k)).toString());

                        if (dicehand_done && toolhand_done){
                            hub.notify(hub.getListofobserver().get(k), Constants.PASS);
                        }
                        else if(!dicehand_done && toolhand_done) {
                            hub.notify(hub.getListofobserver().get(k), Constants.MENU_D);
                        }else if(dicehand_done && !toolhand_done){
                            hub.notify(hub.getListofobserver().get(k), Constants.MENU_T);
                        }else{
                            hub.notify(hub.getListofobserver().get(k), Constants.MENU_W);
                        }
                        int menu;
                        menu = selection(Constants.MENU + 4,Constants.MENU + 1, k) - Constants.MENU;

                        switch (menu){
                            case 1:{
                                hub.notifyObserver(Constants.SHIFT);
                                hub.notify(hub.getListofobserver().get(k), "Il tuo turno è terminato.");
                                hub.notifyOthers(hub.getListofobserver().get(k), hub.getListofobserver().get(k).getNickname()+" ha terminato il suo turno");
                                timerTurn.cancel();
                                cont_turn=0;
                                break;
                            }
                            case 2:{
                                dicehand(k,z,round);
                                break;
                            }
                            case 3:{
                                tool_hand(k,z,round);
                                break;
                            }
                        }
                        hub.notifyObserver(Constants.CLEAN_DRAFT);
                    } while (cont_turn!=0);
                }else {
                    hub.notify(hub.getListofobserver().get(k),"Hai usato la carta utensile Tenaglia a Rotelle nel tuo primo turno perciò salti il turno corrente");
                    hub.notifyOthers(hub.getListofobserver().get(k), hub.getListofobserver().get(k).getNickname()+" salta il turno per l'uso della carta utensile Tenaglia a Rotelle");
                    round.getTurns().get(z).getOneplayer().setMissednextturn(false);
                }
            }
            else{
                hub.notifyOthers(hub.getListofobserver().get(k), Constants.DISCONNECTED);
                hub.notifyOthers(hub.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname());
            }
        } catch (UnmarshalException | ConnectException e) {
            round.getTurns().get(z).getOneplayer().setConnected(false);
            System.out.println(round.getTurns().get(z).getOneplayer().getNickname()+" disconnesso");
            hub.notifyOthers(hub.getListofobserver().get(k), Constants.DISCONNECTED);
            hub.notifyOthers(hub.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname());
            timerTurn.cancel();
            return;
        } catch (InterruptedException ignored) {
        }
    }

    public void dicehand(int k,int z,Round round) throws RemoteException, InterruptedException {
        int index_draft, choice, row, column;

        hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_DIE);
        index_draft = selection(match.getStock().getDicestock().size() + Constants.F_SLOT,Constants.F_DIE + 1, k) - Constants.F_DIE;
        hub.notify(hub.getListofobserver().get(k), Constants.ON_DIE_CLICKED);


        hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_TAS);
        choice = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
        row = rowRefactor(choice);
        column = coloumnRefactor(choice);

        match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(), round.getTurns().get(z).getOneplayer().getWindow().getSlot(row, column), match.getStock().getDicestock().get(index_draft - 1));


        if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()){
            hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
            hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

            match.getStock().getDicestock().remove(index_draft - 1);
            match.getStock().getDieMap().remove(index_draft);

            dicehand_done=true;
        } else {
            hub.notify(hub.getListofobserver().get(k), "ERROR");
            hub.notify(hub.getListofobserver().get(k), match.getRules().getError());
        }
    }

    public void tool_hand(int k,int z,Round round)throws RemoteException{
        int index;

        hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_TOOL);

        index = selection( Constants.CLICK_TOOL + 3, Constants.CLICK_TOOL, k) - Constants.CLICK_TOOL;
        hub.notify(hub.getListofobserver().get(k),Constants.ON_SLOT_CLICKED);

        match.getTool().get(index).setPlayer(round.getTurns().get(z).getOneplayer());

        if (!tool_selection(k,z,round,match.getTool().get(index))){
            hub.notify(hub.getListofobserver().get(k),"ERROR");
            hub.notify(hub.getListofobserver().get(k),match.getTool().get(index).getError());

        }else {

            hub.notify(hub.getListofobserver().get(k),Constants.TOOL_RIGHT_USE);
            hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
            hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());
            toolhand_done=true;

            hub.notifyOthers(hub.getListofobserver().get(k), hub.getListofobserver().get(k).getNickname()+" ha usato la carta utensile "+match.getTool().get(index).getName());
        }
    }

    public boolean tool_selection(int k, int z, Round round, Tool tool) throws RemoteException {
        ArrayList<Die> dice=new ArrayList<>();
        ArrayList<Slot> slots=new ArrayList<>();
        try {
            switch (tool.getName()){
                case "Pinza Sgrossatrice":{
                    int index_draft, piumeno;

                    hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_DIE);
                    index_draft=selection(match.getStock().getDicestock().size() + Constants.F_DIE,Constants.F_DIE + 1, k) - Constants.F_DIE;
                    hub.notify(hub.getListofobserver().get(k), Constants.ON_DIE_CLICKED);


                    hub.notify(hub.getListofobserver().get(k), "Piumeno");
                    hub.notify(hub.getListofobserver().get(k),"Ora seleziona 0 se vuoi decrementare il valore o 1 se vuoi incrementare il valore");

                    piumeno = selection(2,0, k);

                    dice.add(match.getStock().getDicestock().get(index_draft));
                    return tool.effect(dice,match,slots,piumeno);
                }
                case "Pennello per Eglomise": {
                    int index1, index2;
                    int row1,column1,row2,column2;

                    hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_FROM_SCHEME);
                    index1 = selection(Constants.S_SLOT, Constants.F_SLOT , k) - Constants.F_SLOT;
                    row1 = rowRefactor(index1);
                    column1 = coloumnRefactor(index1);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                    hub.notify(hub.getListofobserver().get(k), Constants.WHERE_ON_SCHEME);
                    index2 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                    row2 = rowRefactor(index2);
                    column2 = coloumnRefactor(index2);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                    Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                    Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                    slots.add(slot1);
                    slots.add(slot2);

                    return tool.effect(dice,match,slots,0);
                }
                case "Alesatore per lamina di rame": {
                    int index1, index2;
                    int row1,column1,row2,column2;

                    hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_FROM_SCHEME);
                    index1 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                    row1 = rowRefactor(index1);
                    column1 = coloumnRefactor(index1);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                    hub.notify(hub.getListofobserver().get(k),Constants.WHERE_ON_SCHEME);
                    index2 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                    row2 = rowRefactor(index2);
                    column2 = coloumnRefactor(index2);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                    Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                    Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                    slots.add(slot1);
                    slots.add(slot2);

                    return tool.effect(dice,match,slots,0);
                }
                case "Lathekin": {
                    int index1, index2, index3, index4;
                    int row1,column1,row2,column2,row3,column3,row4,column4;

                    hub.notify(hub.getListofobserver().get(k),"PRIMO DADO");
                    hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_FROM_SCHEME);
                    index1 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                    row1 = rowRefactor(index1);
                    column1 = coloumnRefactor(index1);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                    hub.notify(hub.getListofobserver().get(k), Constants.WHERE_ON_SCHEME);
                    index2 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                    row2 = rowRefactor(index2);
                    column2 = coloumnRefactor(index2);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());


                    hub.notify(hub.getListofobserver().get(k), "SECONDO DADO");
                    hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_FROM_SCHEME);
                    index3 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                    row3 = rowRefactor(index3);
                    column3 = coloumnRefactor(index3);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                    hub.notify(hub.getListofobserver().get(k),Constants.WHERE_ON_SCHEME);
                    index4 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                    row4 = rowRefactor(index4);
                    column4 = coloumnRefactor(index4);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1));
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2));
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3));
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4));

                    return tool.effect(dice,match,slots,0);
                }
                case "Taglierina circolare": {
                    int index_draft,index_roundtrackList,index_roundtrackDie;

                    if (match.getRound()>1) {
                        hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_DIE);
                        index_draft=selection(match.getStock().getDicestock().size() + Constants.F_DIE,Constants.F_DIE + 1, k);

                        hub.notify(hub.getListofobserver().get(k),"Ora inserisci il numero del round e l'indice del dado scelto");

                        index_roundtrackList=selection(match.getRound(),1,k)-1;
                        index_roundtrackDie=selection(match.getRoundTrackList(index_roundtrackList).size(),0,k);

                        dice.add(match.getStock().getDicestock().get(index_draft - 1));
                        dice.add(match.getRoundTrackList(index_roundtrackList).get(index_roundtrackDie));

                        return tool.effect(dice,match,slots,index_roundtrackList);
                    }else {
                        hub.notify(hub.getListofobserver().get(k), "ERROR");
                        hub.notify(hub.getListofobserver().get(k),"Non ci sono dadi sul tracciato dei round");
                    }
                    return false;
                }
                case "Pennello per Pasta Salda": {
                    int index_draft;

                    hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_DIE);
                    index_draft = selection(match.getStock().getDicestock().size() + Constants.F_DIE,Constants.F_DIE + 1, k) - Constants.F_DIE;
                    hub.notify(hub.getListofobserver().get(k), Constants.ON_DIE_CLICKED);

                    dice.add(match.getStock().getDicestock().get(index_draft - 1));
                    return tool.effect(dice,match,slots,0);
                }
                case "Martelletto": {
                    if (round.getTurns().get(z).getOneplayer().getContTurn()==2){
                        if (!dicehand_done)
                            return tool.effect(dice,match,slots,0);
                        else {
                            hub.notify(hub.getListofobserver().get(k), "ERROR");
                            hub.notify(hub.getListofobserver().get(k),"Hai già posizionato un dado perciò non puoi usare questa carta");
                            return false;
                        }
                    }
                    hub.notify(hub.getListofobserver().get(k), "ERROR");
                    hub.notify(hub.getListofobserver().get(k),"Non è il tuo secondo turno");
                    return false;
                }
                case "Tenaglia a Rotelle": {
                    int index_draft, index_tas, row,column;
                    if (!dicehand_done){
                        hub.notify(hub.getListofobserver().get(k), "ERROR");
                        hub.notify(hub.getListofobserver().get(k),"Per usare questa carta devi prima posizionare un dado");
                        return false;
                    }
                    hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_DIE);
                    index_draft = selection(match.getStock().getDicestock().size() + Constants.F_DIE,Constants.F_DIE + 1,k) - Constants.F_DIE;
                    hub.notify(hub.getListofobserver().get(k), Constants.ON_DIE_CLICKED);

                    hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_TAS);
                    index_tas = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                    row = rowRefactor(index_tas);
                    column = coloumnRefactor(index_tas);
                    hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                    hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column));
                    dice.add(match.getStock().getDicestock().get(index_draft));

                    return tool.effect(dice,match,slots,0);
                }
                case "Riga in Sughero": {
                    int index_draft, index_tas, row, column;

                    if (!dicehand_done) {
                        hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_DIE);
                        index_draft = selection(match.getStock().getDicestock().size() + Constants.F_DIE,Constants.F_DIE + 1, k) - Constants.F_DIE;
                        hub.notify(hub.getListofobserver().get(k),Constants.ON_DIE_CLICKED);

                        hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_TAS);
                        index_tas = selection(Constants.S_SLOT,Constants.F_SLOT, k) - Constants.F_SLOT;
                        row = rowRefactor(index_tas);
                        column = coloumnRefactor(index_tas);
                        hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                        hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column));
                        dice.add(match.getStock().getDicestock().get(index_draft - 1));
                        if (tool.effect(dice,match,slots,0)){
                            dicehand_done=true;
                            match.getStock().getDicestock().remove(index_draft);
                            //hub.notify(hub.getListofobserver().get(k),"Dado posizionato correttamente");
                            return true;
                        }else return false;
                    }else {
                        hub.notify(hub.getListofobserver().get(k), "ERROR");
                        hub.notify(hub.getListofobserver().get(k),"Hai già posizionato un dado");}
                    return false;
                }
                case "Tampone Diamantato": {
                    int index_draft;
                    hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_DIE);
                    index_draft = selection(match.getStock().getDicestock().size() + Constants.F_DIE,Constants.F_DIE + 1, k) - Constants.F_DIE;
                    hub.notify(hub.getListofobserver().get(k), Constants.ON_DIE_CLICKED);

                    dice.add(match.getStock().getDicestock().get(index_draft - 1));
                    return tool.effect(dice,match,slots,0);
                }
                case "Diluente per Pasta Salda": {
                    int index_draft, index_tas, value, row, column;
                    if (!dicehand_done) {
                        hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_DIE);
                        index_draft = selection(match.getStock().getDicestock().size() + Constants.F_DIE, Constants.F_DIE, k) - Constants.F_DIE;
                        hub.notify(hub.getListofobserver().get(k),Constants.ON_DIE_CLICKED);

                        Die die = match.getStock().getDicestock().get(index_draft - 1);
                        dice.add(die);
                        if (tool.effect(dice,match,slots,0)){
                            Die d = match.getSack().extractdie();

                            hub.notify(hub.getListofobserver().get(k),d+" ora seleziona la faccia da dare al dado");
                            value = selection(7,0,k);
                            d.setFace(value);

                            hub.notify(hub.getListofobserver().get(k), Constants.CHOOSE_TAS);
                            index_tas = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                            row = rowRefactor(index_tas);
                            column = coloumnRefactor(index_tas);
                            hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                            hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                            Slot slot=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column);
                            match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(),slot,d);

                            if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()){
                                dicehand_done=true;
                                return true;
                            }else {
                                hub.notify(hub.getListofobserver().get(k), "Dado nella riserva");
                                match.getStock().getDicestock().add(die);
                                match.getSack().adddie(d);
                                return true;
                            }
                        }
                    }else hub.notify(hub.getListofobserver().get(k),"Hai già posizionato un dado");
                    return false;
                }
                case "Taglierina Manuale": {
                    if (match.getRound()!=1) {
                        int index1, index2, index3, index4;
                        int row1,column1,row2,column2,row3,column3,row4,column4;
                        hub.notify(hub.getListofobserver().get(k),"Vuoi muovere 1 o 2 dadi?");
                        int move = selection(3,1,k);

                        hub.notify(hub.getListofobserver().get(k), "PRIMO DADO");
                        hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_FROM_SCHEME);
                        index1 = selection(Constants.S_SLOT,Constants.F_SLOT, k) - Constants.F_SLOT;
                        row1 = rowRefactor(index1);
                        column1 = coloumnRefactor(index1);
                        hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                        hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                        hub.notify(hub.getListofobserver().get(k),Constants.WHERE_ON_SCHEME);
                        index2 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                        row2 = rowRefactor(index2);
                        column2 = coloumnRefactor(index2);
                        hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                        hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());


                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1));
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2));
                        if(move==2){
                            hub.notify(hub.getListofobserver().get(k), "SECONDO DADO");
                            hub.notify(hub.getListofobserver().get(k),Constants.CHOOSE_FROM_SCHEME);
                            index3 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                            row3 = rowRefactor(index3);
                            column3 = coloumnRefactor(index3);
                            hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                            hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                            hub.notify(hub.getListofobserver().get(k),Constants.WHERE_ON_SCHEME);
                            index4 = selection(Constants.S_SLOT, Constants.F_SLOT, k) - Constants.F_SLOT;
                            row4 = rowRefactor(index4);
                            column4 = coloumnRefactor(index4);
                            hub.notify(hub.getListofobserver().get(k), Constants.SCHEME_RELOAD);
                            hub.notify(hub.getListofobserver().get(k), updateWindow(match.getPlayers().get(k).getWindow()).toString());

                            slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3));
                            slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4));
                            return tool.effect(dice,match,slots,0);
                        }else return tool.effect(dice,match,slots,0);
                    }else {
                        hub.notify(hub.getListofobserver().get(k), "ERROR");
                        hub.notify(hub.getListofobserver().get(k),"Non puoi usare questa carta nel 1° round perchè non ci sono dadi sul tracciato dei round");
                        return false;
                    }
                }
            }
            return false;
        } catch (UnmarshalException e) {
            return false;
        }
    }

    public int selection(int max,int min, int k)throws RemoteException{
        int index;
        do {
            index = hub.getListofobserver().get(k).selection_int();
        } while (index >= max || index < min);
        return index;
    }



    public String menu(){
        return match.toStringRoundTrack()+"\nRiserva: "+match.getStock().toString()+"\nScegli cosa fare : \n0: fine turno; \n1: posiziona un dado dalla riserva;" +
                "\n2: usa una carta utensile:\n"+match.toolcardsString();

    }

    public boolean isStart() {
        return hub.start;
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
        change.put("name", hub.getListofobserver().get(k).getNickname());
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
