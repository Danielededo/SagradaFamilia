package it.polimi.ingsw.server;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Round;
import it.polimi.ingsw.utils.Colour;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.InputMismatchException;
import java.util.Random;

public class Controller {
    protected Match match;
    private Hub hub;
    private TimerTurn timerTurn;
    private int timer_t;
    private int timer_window;
    private boolean dicehand_done=false,toolhand_done=false;
    private boolean endRound=false;
    private boolean rank=false;
    boolean turn=false;
    private static String separator="\n---------------------------------------------------------------------------------------------";

    public Controller(Hub hub, int timer_window, int timer_t) {
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

    /**
     * This method deals with managing game and called other method to carry it out
     * @throws RemoteException called when connection is lost
     * @throws InterruptedException
     */
    public void setMatch() throws Exception {
        int i=0;
        for(Player p: match.getPlayers()){
            hub.getServer().getMatches().put(p.getNickname(), hub);
            hub.o.put(p.getNickname(), hub.getListofobserver().get(i).getPassword());
            i++;
        }
        final int time=10;
        System.out.println(hub.o);
        Thread.sleep(2000);
        hub.notifyObserver("Le carte utensili sono: "+match.toolcardsString()+
                "\nServer -> Le carte obiettivo pubblico sono: "+match.publictargetString());
        hub.notifyObserver("La partita sta per iniziare... ");
        try {
            Thread.sleep(2000*time);
        }
        catch (Exception e) {}
        try {
            for (ClientInt c: hub.getListofobserver()){
                if (hub.start) {
                    int client_index= hub.getListofobserver().indexOf(c);
                    ArrayList<GlassWindow> windows=match.getScheme().extractGlass();
                    if (match.getPlayers().get(client_index).isConnected()){
                        TimerTurn schemetimer=new TimerTurn(c, hub);
                        turn=true;
                        try {
                            hub.notify(c,"Il tuo obiettivo privato è "+match.getPlayers().get(client_index).getPrivatetarget().toString());
                            hub.notify(c,"\n"+ match.getScheme().schemechoice(windows)+"\nScegli la tua carta schema tramite il suo indice");
                            hub.timer.schedule(schemetimer,1000*timer_window);
                            hub.notifyOthers(c,c.getNickname()+" sta scegliendo la propria carta schema");
                            int scheme=selection(5,1,client_index)-1;
                            this.match.getPlayers().get(client_index).setWindow(windows.get(scheme));
                            if (client_index!= hub.getListofobserver().size()-1)
                                hub.notify(c,"Attendi che gli altri giocatori selezionino la propria carta schema");
                        } catch (ConnectException | UnmarshalException e) {
                            match.getPlayers().get(client_index).setConnected(false);
                            System.out.println(match.getPlayers().get(client_index).getNickname()+" disconnesso");
                            Random r=new Random();
                            this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                            hub.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
                        } finally {
                            schemetimer.cancel();
                        }
                    }else {
                        Random r=new Random();
                        this.match.getPlayers().get(client_index).setWindow(windows.get(r.nextInt(windows.size())));
                        hub.notifyObserver("A "+match.getPlayers().get(client_index).getNickname()+" è stata assegnata casualmente la carta schema non essendo connesso");
                    }
                }
            }
        } catch (ConcurrentModificationException ignored) {}
        while(this.match.getRound()<11 && hub.isStart()){
            round();
        }
        try {
            timerTurn.cancel();
            hub.timer.cancel();
        }catch (NullPointerException ignored){}
        if (hub.start) {
            rank=true;
            hub.notifyObserver("PARTITA TERMINATA");
            this.match.endMatch();
            hub.notifyObserver(this.match.ranking());
            hub.thread.cancel();
            hub.timer.cancel();
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException ignored) {
            }
            for(Player p: match.getPlayers())
                hub.getServer().getMatches().remove(p.getNickname());
            for(ClientInt c: hub.getListofobserver())
                hub.notify(c,"disconnettiti");
            hub.start=false;
            try {
                hub.terminateHub();
            } catch (NullPointerException ignored) {}
        }
    }

    /**
     * Round deals with managing round during match. There is 10 round
     * @throws RemoteException called when connection is lost
     * @throws InterruptedException
     */
    public void round() throws RemoteException, InterruptedException,Exception {
        if(match.getRound()==1)
            hub.notifyObserver(match.getGlassWindowPlayers()+separator+"\n"+Colour.RED.escape()
                    +"LA PARTITA HA INIZIO"+Colour.RESET);
        Round round= new Round(match);
        int k=0;
        int t=1;
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
            }else return;
        }
        if (hub.start){
            endRound=true;
            hub.notifyObserver(Colour.GREEN.escape()+"IL "+match.getRound()+"° ROUND è TERMINATO"+Colour.RESET+
                    "\nServer -> Le carte obiettivo pubblico sono: "+match.publictargetString());

            //hub.thread.cancel();
            Thread.sleep(2000);
            match.endRound();
            if(match.getRound()!=11) {
                hub.getListofobserver().add(hub.getListofobserver().get(0));
                hub.getListofobserver().remove(0);
            }
            hub.thread=new DisconnectionThread(hub);
            //hub.timer.schedule(hub.thread,0,500);
            endRound=false;
        }
    }

    /**
     * Turn is the center of the game. Every player has to turn in every round and this method is used to do this
     * @param round is the reference of the current round in model
     * @param k is the reference to listofobserver index that do this turn
     * @param z is the reference to players index in match that do this turn
     * @param t is the reference to turn if it second or first
     * @throws RemoteException called when connection is lost
     */
    private void handleTurn(Round round, int z, int k, int t)throws RemoteException,Exception{
        try {
            timerTurn=new TimerTurn(hub.getListofobserver().get(k), hub);
            try {
                if (round.getTurns().get(z).getOneplayer().isConnected()) {
                    dicehand_done=false;
                    toolhand_done=false;
                    int cont_turn=1;
                    hub.notifyOthers(hub.getListofobserver().get(k),"Aspetta il tuo turno\n"+round.getTurns().get(z).getOneplayer().getNickname()+" sta eseguendo il suo turno\nRiserva: "+match.getStock().toString());
                    hub.notify(hub.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname()+" è il tuo turno"+Colour.GREEN.escape()+"\nRound: "+match.getRound()+"; Turno: "+t+Colour.RESET);
                    int menu;
                    if (!round.getTurns().get(z).getOneplayer().isMissednextturn()){
                        hub.timer.schedule(timerTurn,1000*timer_t);
                        do {
                            hub.notify(hub.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().toString());
                            if (dicehand_done && toolhand_done){
                                hub.notify(hub.getListofobserver().get(k),"Puoi solo terminare il tuo turno ");
                            }
                            else
                            if(!dicehand_done && toolhand_done)
                                hub.notify(hub.getListofobserver().get(k),"Puoi solo posizionare un dado o terminare il turno");
                            else if(dicehand_done && !toolhand_done)
                                hub.notify(hub.getListofobserver().get(k),"Puoi solo usare una carta utensile o terminare il turno");
                            hub.notify(hub.getListofobserver().get(k),menu());
                            menu=selection(3,0,k);
                            switch (menu){
                                case 0:{
                                    hub.notifyOthers(hub.getListofobserver().get(k), hub.getListofobserver().get(k).getNickname()+" ha terminato il suo turno");
                                    timerTurn.cancel();
                                    hub.timer.purge();
                                    cont_turn=0;
                                    break;
                                }
                                case 1:{
                                    if (!dicehand_done) dicehand(k,z,round);
                                    else hub.notify(hub.getListofobserver().get(k),"Hai già posizionato un dado in questo turno");
                                    break;
                                }
                                case 2:{
                                    if(!toolhand_done)
                                        tool_hand(k,z,round,cont_turn);
                                    else
                                        hub.notify(hub.getListofobserver().get(k),"Hai già usato una carta utensile in questo turno");
                                    break;
                                }
                            }
                        } while (cont_turn!=0);
                    }else {
                        hub.notify(hub.getListofobserver().get(k),"Hai usato la carta utensile Tenaglia a Rotelle nel tuo primo turno perciò salti il turno corrente");
                        hub.notifyOthers(hub.getListofobserver().get(k), hub.getListofobserver().get(k).getNickname()+" salta il turno per l'uso della carta utensile Tenaglia a Rotelle");
                        round.getTurns().get(z).getOneplayer().setMissednextturn(false);
                    }
                    hub.notifyObserver("Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
                }
                else{
                    hub.notifyOthers(hub.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname()+" salta il turno perchè è disconnesso\n"
                            +"Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
                }
            } catch (UnmarshalException | ConnectException e) {
                round.getTurns().get(z).getOneplayer().setConnected(false);
                System.out.println(round.getTurns().get(z).getOneplayer().getNickname()+" disconnesso");
                hub.notifyOthers(hub.getListofobserver().get(k),round.getTurns().get(z).getOneplayer().getNickname()+" salta il turno perchè è disconnesso\n"
                        +"Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+separator);
                timerTurn.cancel();
                hub.timer.purge();
                return;
            }
        }catch (NullPointerException ignored){}
    }

    /**
     * dicehand allows to client to put die in his scheme card
     * @param k is the reference to listofobserver index that do this turn
     * @param z is the reference to players index in match that do this turn
     * @param round is the reference of the current round in model
     * @throws RemoteException called when connection is lost
     */
    private void dicehand(int k, int z, Round round)throws RemoteException{
        int index_draft, row, column;
        hub.notify(hub.getListofobserver().get(k), "Scegli un dado attraverso il suo indice");
        index_draft=selection(match.getStock().getDicestock().size(),0,k);
        hub.notify(hub.getListofobserver().get(k), "\nLa tua scelta è: " + match.getStock().getDicestock().get(index_draft).toString() +
                "\nScegli la casella della tua carta schema dove posizionare il dado, rispettivamente inserisci riga e colonna: ");
        row=selection(4,0,k);
        column=selection(5,0,k);
        if(match.getPlayers().get(k).getWindow().getSlot(row,column).isOccupate()){
            hub.notify(hub.getListofobserver().get(k),"C'è già un dado su questo slot");
            return;
        }else{
        match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(), round.getTurns().get(z).getOneplayer().getWindow().getSlot(row, column), match.getStock().getDicestock().get(index_draft));
        if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()) {
            hub.notify(hub.getListofobserver().get(k), "Dado piazzato correttamente");
            hub.notifyOthers(hub.getListofobserver().get(k), hub.getListofobserver().get(k).getNickname()+" ha piazzato il dado "+
                    match.getStock().getDicestock().get(index_draft)+" nella sua casella ("+row+","+column+")");
            match.getStock().getDicestock().remove(index_draft);
            dicehand_done=true;
        } else
            hub.notify(hub.getListofobserver().get(k), Colour.GREEN.escape()+match.getRules().getError()+Colour.RESET);
        }
    }

    /**
     * toolhand allows to client to use a toolcard
     * @param k is the reference to listofobserver index that do this turn
     * @param z is the reference to players index in match that do this turn
     * @param round is the reference of the current round in model
     * @param cont_turn it used to end tool_hand
     * @throws RemoteException called when connection is lost
     */
    private void tool_hand(int k, int z, Round round, int cont_turn)throws RemoteException{
        int index;
        hub.notify(hub.getListofobserver().get(k),"Scegli una carta utensile dalla lista tramite il suo indice: ");
        index=selection(4,1,k);
        hub.notify(hub.getListofobserver().get(k),"La tua scelta è: "+match.getTool().get(index-1));
        match.getTool().get(index-1).setPlayer(round.getTurns().get(z).getOneplayer());
        if (match.getTool().get(index-1).tokencontroller()){
            if (!tool_selection(k,z,round,match.getTool().get(index-1),cont_turn)){
                hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+match.getTool().get(index-1).getError()+Colour.RESET);
            }else {
                hub.notify(hub.getListofobserver().get(k),"Operazione completata");
                toolhand_done=true;
                hub.notifyOthers(hub.getListofobserver().get(k), hub.getListofobserver().get(k).getNickname()+" ha usato la carta utensile "+match.getTool().get(index-1).getName());
            }
        }else hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non hai abbastanza segnalini favore"+Colour.RESET);
    }

    /**
     * toolselection allows to client to use a specified toolcard in match
     * @param k is the reference to listofobserver index that do this turn
     * @param z is the reference to players index in match that do this turn
     * @param round is the reference of the current round in model
     * @param tool is the current toolcard used by player
     * @param cont_turn is used to end tool_hand
     * @return true if effect of toolcard is done, false in other case
     * @throws RemoteException called when connection is lost
     */
    private boolean tool_selection(int k, int z, Round round, Tool tool, int cont_turn) throws RemoteException {
        ArrayList<Die> dice=new ArrayList<>();
        ArrayList<Slot> slots=new ArrayList<>();
        try {
            switch (tool.getName()){
                case "Pinza Sgrossatrice":{
                    int index_draft,piumeno;
                    hub.notify(hub.getListofobserver().get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    hub.notify(hub.getListofobserver().get(k),"la tua scelta è "+match.getStock().getDicestock().get(index_draft));
                    hub.notify(hub.getListofobserver().get(k),"Ora seleziona 0 se vuoi decrementare il valore o 1 se vuoi incrementare il valore");
                    do {
                        try {
                            piumeno = hub.getListofobserver().get(k).selection_int();
                        }catch (InputMismatchException e){
                            piumeno=10;
                        }
                    } while (piumeno!=0&&piumeno!=1);
                    dice.add(match.getStock().getDicestock().get(index_draft));
                    return tool.effect(dice,match,slots,piumeno);
                }
                case "Pennello per Eglomise": {
                    if (match.getRules().getCont(tool.getPlayer())>1){
                        int row1,column1,row2,column2;
                        hub.notify(hub.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale prendere il dado");
                        row1=selection(4,0,k);
                        column1=selection(5,0,k);
                        hub.notify(hub.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                        row2=selection(4,0,k);
                        column2=selection(5,0,k);
                        Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                        Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                        slots.add(slot1);
                        slots.add(slot2);
                        return tool.effect(dice,match,slots,0);
                    }
                    hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non ci sono abbastanza dadi sulla tua carta schema"+Colour.RESET);
                    return false;
                }
                case "Alesatore per lamina di rame": {
                    if (match.getRules().getCont(tool.getPlayer())>1){
                        int row1,column1,row2,column2;
                        hub.notify(hub.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale prendere il dado");
                        row1=selection(4,0,k);
                        column1=selection(5,0,k);
                        hub.notify(hub.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                        row2=selection(4,0,k);
                        column2=selection(5,0,k);
                        Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                        Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                        slots.add(slot1);
                        slots.add(slot2);
                        return tool.effect(dice,match,slots,0);
                    }
                    hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non ci sono abbastanza dadi sul tracciato"+Colour.RESET);
                    return false;
                }
                case "Lathekin": {
                    if (match.getRules().getCont(tool.getPlayer())>=3){
                        int row1,column1,row2,column2,row3,column3,row4,column4;
                        hub.notify(hub.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il primo dado");
                        row1=selection(4,0,k);
                        column1=selection(5,0,k);
                        hub.notify(hub.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il primo dado");
                        row2=selection(4,0,k);
                        column2=selection(5,0,k);
                        hub.notify(hub.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il secondo dado");
                        row3=selection(4,0,k);
                        column3=selection(5,0,k);
                        hub.notify(hub.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il secondo dado");
                        row4=selection(4,0,k);
                        column4=selection(5,0,k);
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1));
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2));
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3));
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4));
                        return tool.effect(dice,match,slots,0);
                    }
                    hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non ci sono abbastanza dadi sul tracciato"+Colour.RESET);
                    return false;
                }
                case "Taglierina circolare": {
                    int index_draft,index_roundtrackList,index_roundtrackDie;
                    if (match.getRound()>1) {
                        hub.notify(hub.getListofobserver().get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                        index_draft=selection(match.getStock().getDicestock().size(),0,k);
                        hub.notify(hub.getListofobserver().get(k),"la tua scelta è "+match.getStock().getDicestock().get(index_draft));
                        hub.notify(hub.getListofobserver().get(k),"Ora inserisci il numero del round e l'indice del dado scelto");
                        index_roundtrackList=selection(match.getRound(),1,k)-1;
                        index_roundtrackDie=selection(match.getRoundTrackList(index_roundtrackList).size(),0,k);
                        dice.add(match.getStock().getDicestock().get(index_draft));
                        dice.add(match.getRoundTrackList(index_roundtrackList).get(index_roundtrackDie));
                        return tool.effect(dice,match,slots,index_roundtrackList);
                    }else hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non ci sono dadi sul tracciato dei round"+Colour.RESET);
                    return false;
                }
                case "Pennello per Pasta Salda": {
                    int index_draft;
                    hub.notify(hub.getListofobserver().get(k),"Scgli un dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    dice.add(match.getStock().getDicestock().get(index_draft));
                    return tool.effect(dice,match,slots,0);
                }
                case "Martelletto": {
                    if (round.getTurns().get(z).getOneplayer().getContTurn()==2){
                        if (!dicehand_done)
                            return tool.effect(dice,match,slots,0);
                        else {
                            hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Hai già posizionato un dado perciò non puoi usare questa carta"+Colour.RESET);
                            return false;
                        }
                    }
                    hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non è il tuo secondo turno"+Colour.RESET);
                    return false;
                }
                case "Tenaglia a Rotelle": {
                    int index_draft,row,column;
                    if (!dicehand_done || match.getPlayers().get(k).getContTurn()==2){
                        hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non puoi usare questa carta"+Colour.RESET);
                        return false;
                    }
                    hub.notify(hub.getListofobserver().get(k),tool.getPlayer().getWindow()+"\nPuoi selezionare un altro dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    hub.notify(hub.getListofobserver().get(k), "\nLa tua scelta è: " + match.getStock().getDicestock().get(index_draft).toString() +
                            "\nScegli la casella della tua tua carta schema dove posizionare il dado, rispettivamente riga e colonna: ");
                    row=selection(4,0,k);
                    column=selection(5,0,k);
                    slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column));
                    dice.add(match.getStock().getDicestock().get(index_draft));
                    return tool.effect(dice,match,slots,0);
                }
                case "Riga in Sughero": {
                    int index_draft,row,column;
                    if (!dicehand_done) {
                        hub.notify(hub.getListofobserver().get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                        index_draft=selection(match.getStock().getDicestock().size(),0,k);
                        hub.notify(hub.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dalla quale prendere il dado");
                        row=selection(4,0,k);
                        column=selection(5,0,k);
                        slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column));
                        dice.add(match.getStock().getDicestock().get(index_draft));
                        if (tool.effect(dice,match,slots,0)){
                            dicehand_done=true;
                            match.getStock().getDicestock().remove(index_draft);
                            hub.notify(hub.getListofobserver().get(k),"Dado posizionato correttamente");
                            return true;
                        }else return false;
                    }else {
                        hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Hai già posizionato un dado"+Colour.RESET);}
                    return false;
                }
                case "Tampone Diamantato": {
                    int index_draft;
                    hub.notify(hub.getListofobserver().get(k),"Scegli un dado dalla riserva al quale invertire la faccia:\n"+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    dice.add(match.getStock().getDicestock().get(index_draft));
                    return tool.effect(dice,match,slots,0);
                }
                case "Diluente per Pasta Salda": {
                    int index_draft,value,row,column,tokens;
                    if (tool.isAccessed())tokens=2;
                    else tokens=1;
                    if (!dicehand_done) {
                        hub.notify(hub.getListofobserver().get(k),"Scegli un dado dalla riserva da rimettere nel sacchetto:\n"+match.getStock().toString());
                        index_draft=selection(match.getStock().getDicestock().size(),0,k);
                        Die die=match.getStock().getDicestock().get(index_draft);
                        dice.add(die);
                        if (tool.effect(dice,match,slots,0)){
                            Die d = match.getSack().extractdie();
                            hub.notify(hub.getListofobserver().get(k),d+" ora seleziona la faccia da dare al dado");
                            value=selection(7,1,k);
                            d.setFace(value);
                            hub.notify(hub.getListofobserver().get(k),d+"\nAdesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                            row=selection(4,0,k);
                            column=selection(5,0,k);
                            Slot slot=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column);
                            match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(),slot,d);
                            if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()){
                                dicehand_done=true;
                                return true;
                            }else{
                                round.getTurns().get(z).getOneplayer().setMarker(round.getTurns().get(z).getOneplayer().getMarker()+tokens);
                                if (tokens==1)tool.setAccessed(false);
                                match.getStock().getDicestock().add(die);
                                match.getSack().adddie(d);
                                return false;
                            }
                        }
                    }else hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Hai già posizionato un dado"+Colour.RESET);
                    return false;
                }
                case "Taglierina Manuale": {
                    if (match.getRound()!=1) {
                        int row1,column1,row2,column2,row3,column3,row4,column4;
                        hub.notify(hub.getListofobserver().get(k),"Vuoi muovere 1 o 2 dadi?");
                        int move=selection(3,1,k);
                        if ((move==1 && match.getRules().getCont(tool.getPlayer())>=2)||(move==2 && match.getRules().getCont(tool.getPlayer())>=3)) {
                            hub.notify(hub.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il primo dado");
                            row1=selection(4,0,k);
                            column1=selection(5,0,k);
                            hub.notify(hub.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il primo dado");
                            row2=selection(4,0,k);
                            column2=selection(5,0,k);
                            slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1));
                            slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2));
                            if(move==2){
                                hub.notify(hub.getListofobserver().get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il secondo dado");
                                row3=selection(4,0,k);
                                column3=selection(5,0,k);
                                hub.notify(hub.getListofobserver().get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il secondo dado");
                                row4=selection(4,0,k);
                                column4=selection(5,0,k);
                                slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3));
                                slots.add(round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4));
                                return tool.effect(dice,match,slots,0);
                            }else return tool.effect(dice,match,slots,0);
                        }
                        else{
                            hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non puoi usare questa carta perchè non ci sono abbastanza dadi sulla tua vetrata"+Colour.RESET);
                            return false;
                        }
                    }else {
                        hub.notify(hub.getListofobserver().get(k),Colour.GREEN.escape()+"Non puoi usare questa carta nel 1° round perchè non ci sono dadi sul tracciato dei round"+Colour.RESET);
                        return false;
                    }
                }
            }
            return false;
        } catch (UnmarshalException e) {
            return false;
        }
    }

    /**
     * It called when client has to do a choice. It require to insert number to the client
     * @param max is the max of range that client can select
     * @param min is the min of range that client can select
     * @param k is current client that need to put number
     * @return the number selected by client
     * @throws RemoteException called when connection is lost
     */
    private int selection(int max, int min, int k)throws RemoteException{
        int index;
        do {
            try {
                index = hub.getListofobserver().get(k).selection_int();
            }catch (InputMismatchException e){
                index=10000;
            }
            if (index<min||index>=max) hub.notify(hub.getListofobserver().get(k),"Riprova...");
        } while (index >= max || index < min);
        return index;
    }

    private String menu(){
        return match.toStringRoundTrack()+"\nRiserva: "+match.getStock().toString()+"\nScegli cosa fare : \n0: fine turno; \n1: posiziona un dado dalla riserva;" +
                "\n2: usa una carta utensile:\n"+match.toolcardsString();

    }

}

