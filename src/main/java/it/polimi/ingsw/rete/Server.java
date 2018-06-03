package it.polimi.ingsw.rete;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Round;
import org.sonarsource.scanner.api.internal.shaded.minimaljson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.InputMismatchException;
import java.util.Timer;

public class Server implements ServerInt{
    static int PORT;
    protected Match match;
    private Waiting_Room room;
    private ArrayList<ClientInt> listofobserver = new ArrayList<ClientInt>();
    private Registry registry;
    private boolean start=false;
    private boolean dicehand_done=false,toolhand_done=false;
    public Timer timer=new Timer();
    public DisconnectionThread thread;
    private ArrayList<String> name_disconnected=new ArrayList<String>();
    private boolean swapdone=true;
    private JsonObject o=new JsonObject();
    private Cryptography trippleDes;

    public ArrayList<String> getName_disconnected() {
        return name_disconnected;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void control() throws RemoteException {
        String b;
        for(int i=0;i<listofobserver.size();i++) {
            try {
                listofobserver.get(i).setupPlayer();
            } catch (Exception e) {
                b=room.getPlayers().get(i).getNickname();
                System.out.println(b + " è stato disconnesso");
                room.deleteplayer(b);
                removeObserver(listofobserver.get(i));
                System.out.println(room.toString());
                i--;
            }
        }
    }

    public ArrayList<ClientInt> getListofobserver() {
        return listofobserver;
    }

    public void start_server(String arg){
        boolean gone=true;
        try{
            String server_name;
            ServerInt stub;
            PORT= Integer.parseInt(arg);
            server_name = "Sagrada server";
            thread=new DisconnectionThread(this);
            this.room=new Waiting_Room(this);
            stub = (ServerInt) UnicastRemoteObject.exportObject(this, 0);
            registry= LocateRegistry.createRegistry(PORT);
            registry.rebind(server_name,stub);
            timer.scheduleAtFixedRate(thread,0,500);
            System.err.println(server_name + " pronto");
            trippleDes=new Cryptography();
            while (gone) {
            }
        }catch (Exception e){
            System.err.println("Server exception:   " + e.toString());
            e.printStackTrace();
        }
    }

    public ArrayList<String> getNames(ArrayList<GlassWindow> windows) {
        ArrayList<String> names= new ArrayList<String>();
        int i=0;
        for(GlassWindow g: windows){
            names.add(windows.get(i).getName());
            i++;
        }
        return names;
    }

    public void setMatch(Match match) throws RemoteException, InterruptedException {
        this.match = match;
        final int time=15;
        for (int i=0;i<match.getnumberPlayers();i++) {
            o.add(match.getPlayers().get(i).getNickname(),trippleDes.decrypt(listofobserver.get(i).getPassword()));
        }
        try (FileWriter file = new FileWriter("src/main/Name_Password.json")) {
            file.write(o.toString());
            System.out.println("\nJSON Object: " + o);
        } catch (IOException e) {}
        notifyObserver("Le carte utensili sono: "+match.toolcardsString()+
                "\nServer -> Le carte obiettivo pubblico sono: "+match.publictargetString());
        notifyObserver("La partita sta per iniziare... ");
        int i=0,j=0;
        try {
            Thread.sleep(1000*time);
        }
        catch (Exception e) {}
        for (ClientInt c:listofobserver){
            try {
                c.update("Il tuo obiettivo privato è "+match.getPlayers().get(i).getPrivatetarget().toString());
                ArrayList<GlassWindow> windows=match.getScheme().extractGlass();
                notify(c,"\n"+ windows.toString());
                do {
                    String a = c.setupgame();
                    if (getNames(windows).contains(a)) {
                        this.match.getPlayers().get(i).setWindow(windows.get(getNames(windows).indexOf(a)));
                        if (listofobserver.indexOf(c)!=listofobserver.size()-1)
                            notify(c,"Attendi che gli altri giocatori selezionino la propria carta schema");
                        System.out.println(c.getNickname() + " has chosen " + a);
                        j=1;
                    } else
                        c.update("Riprova");
                }while (j==0);
                i++;
                j=0;
            } catch (ConnectException e) {
                removeObserver(c);
                notifyObserver("disconnesso client");
            }
        }
        notifyObserver(match.getGlassWindowPlayers()+ "---------------------------------------------------------------------------------------------\n"+Colour.RED.escape()+"LA PARTITA HA INIZIO"+Colour.RESET);
        while(match.getRound()!=11){
            round();
        }
        notifyObserver("PARTITA TERMINATA");
        match.fineMatch();
        notifyObserver(match.ranking());
        try {
            Thread.sleep(1000*20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(ClientInt c:listofobserver)
            c.exit();
        System.exit(0);
    }

    public void round() throws RemoteException, InterruptedException {
        Round round= new Round(match);
        int k=0,t=1;
        for(int z=0; z<2*match.getnumberPlayers();z++){
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
        }
        notifyObserver("IL "+match.getRound()+" IL ROUND è TERMINATO");
        thread.setEnd(true);
        Thread.sleep(2000);
        match.fineRound();
        listofobserver.add(listofobserver.get(0));
        listofobserver.remove(0);
        synchronized (thread){
            thread.setEnd(false);
            thread.notify();
        }
    }

    public void handleTurn(Round round,int z,int k,int t)throws RemoteException{
        if (round.getTurns().get(z).getOneplayer().isConnected()) {
            dicehand_done=false;
            toolhand_done=false;
            int cont_turn=1;
            notifyOthers(listofobserver.get(k),"Aspetta il tuo turno\n"+round.getTurns().get(z).getOneplayer().getNickname()+" sta eseguendo il suo turno\nRiserva: "+match.getStock().toString());
            notify(listofobserver.get(k),round.getTurns().get(z).getOneplayer().getNickname()+" è il tuo turno"+Colour.GREEN.escape()+"\nRound: "+match.getRound()+"; Turno "+t+Colour.RESET);
            int menu;
            if (!round.getTurns().get(z).getOneplayer().isMissednext_turn()){
                do {
                    notify(listofobserver.get(k),round.getTurns().get(z).getOneplayer().toString());
                    if (dicehand_done && toolhand_done){
                        notify(listofobserver.get(k),"Puoi solo terminare il tuo turno ");
                    }
                    else
                    if(!dicehand_done && toolhand_done)
                        notify(listofobserver.get(k),"Puoi solo posizionare un dado o terminare il turno");
                    else if(dicehand_done && !toolhand_done)
                        notify(listofobserver.get(k),"Puoi solo usare una carta utensile o terminare il turno");
                    notify(listofobserver.get(k),menu());
                    menu=selection(3,0,k);
                    switch (menu){
                        case 0:{
                            notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" ha terminato il suo turno");
                            cont_turn=0;
                            break;
                        }
                        case 1:{
                            if (!dicehand_done) dicehand(k,z,round);
                            else notify(listofobserver.get(k),"Hai già posizionato un dado in questo turno");
                            break;
                        }
                        case 2:{
                            if(!toolhand_done)
                                tool_hand(k,z,round,cont_turn);
                            else
                                notify(listofobserver.get(k),"Hai già usato una carta utensile in questo turno");
                            break;
                        }
                    }
                } while (cont_turn!=0);
            }else {
                notify(listofobserver.get(k),"Hai usato la carta utensile Tenaglia a Rotelle nel tuo primo turno perciò salti il turno corrente");
                notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" salta il turno per l'uso della carta utensile Tenaglia a Rotelle");
                round.getTurns().get(z).getOneplayer().setMissednext_turn(false);
            }
            notifyObserver("Alla fine di questo turno, "+round.getTurns().get(z).getOneplayer().toStringpublic()+
                    "\n---------------------------------------------------------------------------------------------");
        }
        else{
            notifyObserver(round.getTurns().get(z).getOneplayer().getNickname()+" salta il turno perchè è disconnesso"+
                    "\n---------------------------------------------------------------------------------------------");
        }
    }

    public void dicehand(int k,int z,Round round)throws RemoteException{
        int cont=1;
        while(cont!=0) {
            int index_draft, row, column;
            notify(listofobserver.get(k), "Scegli un dado attraverso il suo indice");
            index_draft=selection(match.getStock().getDicestock().size()+1,0,k);
            if (index_draft!=match.getStock().getDicestock().size()) {
                notify(listofobserver.get(k), "\nLa tua scelta è: " + match.getStock().getDicestock().get(index_draft).toString() +
                        "\nScegli la casella della tua carta schema dove posizionare il dado, rispettivamente inserisci riga e colonna: ");
                row=selection(4,0,k);
                column=selection(5,0,k);
                match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(), round.getTurns().get(z).getOneplayer().getWindow().getSlot(row, column), match.getStock().getDicestock().get(index_draft));
                if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()) {
                    notify(listofobserver.get(k), "Dado piazzato correttamente");
                    notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" ha piazzato il dado "+match.getStock().getDicestock().get(index_draft)+" nella sua casella ("+row+","+column+")");
                    match.getStock().getDicestock().remove(index_draft);
                    cont = 0;
                    dicehand_done=true;
                } else
                    notify(listofobserver.get(k), match.getRules().getError());
            }else cont=0;
        }
    }

    public void tool_hand(int k,int z,Round round,int cont_turn)throws RemoteException{
        int cont=1,placed=1;
        int index;
        notify(listofobserver.get(k),"Choose a tool card from list by its value:");
        index=selection(4,1,k);
        notify(listofobserver.get(k),"your choiche is "+match.getTool().get(index-1));
        while (cont!=0&&placed!=0){
            match.getTool().get(index-1).setPlayer(round.getTurns().get(z).getOneplayer());
            if (!tool_selection(k,z,round,match.getTool().get(index-1),cont_turn,placed)){
                notify(listofobserver.get(k),match.getTool().get(index-1).getError());
                if (!match.getTool().get(index-1).isUsed()) placed=0;
            }else {
                notify(listofobserver.get(k),"Operation completed");
                toolhand_done=true;
                notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" has used tool card "+match.getTool().get(index-1).getName());
                cont=0;
            }
        }
    }

    public boolean tool_selection(int k, int z, Round round, Tool tool,int cont_turn,int placed) throws RemoteException {
        switch (tool.getName()){
            case "Pinza Sgrossatrice":{
                int index_draft,piumeno=-1;
                boolean b;
                notify(listofobserver.get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size(),0,k);
                notify(listofobserver.get(k),"la tua scelta è "+match.getStock().getDicestock().get(index_draft));
                notify(listofobserver.get(k),"Ora seleziona 0 se vuoi decrementare il valore o 1 se vuoi incrementare il valore");
                do {
                    try {
                        piumeno = listofobserver.get(k).selection_int();
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
                notify(listofobserver.get(k),"nserisci riga e colonna rispettivamente della casella dalla quale prendere il dadoe");
                row1=selection(4,0,k);
                column1=selection(5,0,k);
                notify(listofobserver.get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                row2=selection(4,0,k);
                column2=selection(5,0,k);
                Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                return tool.effect(null,null,false,match,match.getStock(),slot1,slot2,null,null,0);
            }
            case "Alesatore per lamina di rame": {
                int row1,column1,row2,column2;
                notify(listofobserver.get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale prendere il dadoe");
                row1=selection(4,0,k);
                column1=selection(5,0,k);
                notify(listofobserver.get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
                row2=selection(4,0,k);
                column2=selection(5,0,k);
                Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                return tool.effect(null,null,false,match,match.getStock(),slot1,slot2,null,null,0);
            }
            case "Lathekin": {
                int row1,column1,row2,column2,row3,column3,row4,column4;
                notify(listofobserver.get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il primo dado");
                row1=selection(4,0,k);
                column1=selection(5,0,k);
                notify(listofobserver.get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il primo dado");
                row2=selection(4,0,k);
                column2=selection(5,0,k);
                notify(listofobserver.get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il secondo dado");
                row3=selection(4,0,k);
                column3=selection(5,0,k);
                notify(listofobserver.get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il secondo dado");
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
                    notify(listofobserver.get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    notify(listofobserver.get(k),"la tua scelta è "+match.getStock().getDicestock().get(index_draft));
                    notify(listofobserver.get(k),"Ora inserisci il numero del round e l'indice del dado scelto");
                    index_roundtrackList=selection(match.getRound(),1,k)-1;
                    index_roundtrackDie=selection(match.getRoundTrackList(index_roundtrackList).size(),0,k);
                    Die d=match.getRoundTrackList(index_roundtrackList).get(index_roundtrackDie);
                    return tool.effect(match.getStock().getDicestock().get(index_draft),d,false,match,match.getStock(),null,null,null,null,index_roundtrackList);
                }else notify(listofobserver.get(k),"Non ci sono dadi sul tracciato dei round");
                return false;
            }
            case "Pennello per Pasta Salda": {
                int index_draft;
                notify(listofobserver.get(k),"Scgli un dado dalla riserva: "+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size(),0,k);
                return tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0);
            }
            case "Martelletto": {
                if (round.getTurns().get(z).getOneplayer().getContTurn()==2){
                    if (!dicehand_done)
                        return tool.effect(null,null,false,match,match.getStock(),null,null,null,null,0);
                    else {
                        notify(listofobserver.get(k),"Hai già posizionato un dado perciò non puoi usare questa carta");
                        placed=0;
                        return false;
                    }
                }
                notify(listofobserver.get(k),"Non è il tuo secondo turno");
                return false;
            }
            case "Tenaglia a Rotelle": {
                int index_draft,row,column;
                if (!dicehand_done){
                    notify(listofobserver.get(k),"Per usare questa carta devi prima posizionare un dado");
                    placed=0;
                    return false;
                }
                notify(listofobserver.get(k),tool.getPlayer().getWindow()+"\nPuoi selezionare un altro dado dalla riserva: "+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size()+1,0,k);
                if (index_draft==match.getStock().getDicestock().size()){placed=0;return false;}
                notify(listofobserver.get(k), "\nLa tua scelta è: " + match.getStock().getDicestock().get(index_draft).toString() +
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
                    notify(listofobserver.get(k),"Scegli un dado dalla riserva: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size()+1,0,k);
                    if(index_draft==match.getStock().getDicestock().size()){placed=0;return false;}
                    notify(listofobserver.get(k),"Adesso inserisci riga e colonna rispettivamente della casella dalla quale prendere il dado");
                    row=selection(4,0,k);
                    column=selection(5,0,k);
                    Slot slot=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column);
                    if (tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),slot,null,null,null,0)){
                        dicehand_done=true;
                        match.getStock().getDicestock().remove(index_draft);
                        cont_turn=0;
                        notify(listofobserver.get(k),"Dado posizionato correttamente");
                        return true;
                    }else return false;
                }else {notify(listofobserver.get(k),"Hai già posizionato un dado");placed=0;}
                return false;
            }
            case "Tampone Diamantato": {
                int index_draft;
                notify(listofobserver.get(k),"Scegli un dado dalla riserva al quale invertire la faccia:\n"+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size(),0,k);
                return tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0);
            }
            case "Diluente per Pasta Salda": {
                int index_draft,value,row,column;
                boolean loop;
                if (!dicehand_done) {
                    notify(listofobserver.get(k),"Scegli un dado dalla riserva da rimettere nel sacchetto:\n"+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    if (tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0)){
                        Die d = match.getSack().extractdie();
                        notify(listofobserver.get(k),d+" ora seleziona la faccia da dare al dado");
                        value=selection(7,0,k);
                        d.setFace(value);
                        do {
                            notify(listofobserver.get(k),d+"\nAdesso inserisci riga e colonna rispettivamente della casella dove posizionare il dado");
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
                }else notify(listofobserver.get(k),"Hai già posizionato un dado");
                return false;
            }
            case "Taglierina Manuale": {
                if (match.getRound()!=1) {
                    int row1,column1,row2,column2,row3,column3,row4,column4;
                    notify(listofobserver.get(k),"Vuoi muovere 1 o 2 dadi?");
                    int move=selection(4,1,k);
                    notify(listofobserver.get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il primo dado");
                    row1=selection(4,0,k);
                    column1=selection(5,0,k);
                    notify(listofobserver.get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il primo dado");
                    row2=selection(4,0,k);
                    column2=selection(5,0,k);
                    Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                    Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                    if(move==2){
                        notify(listofobserver.get(k),"Inserisci riga e colonna rispettivamente della casella dalla quale vuoi muovere il secondo dado");
                        row3=selection(4,0,k);
                        column3=selection(5,0,k);
                        notify(listofobserver.get(k),"Adesso inserisci riga e colonna rispettivamente della casella dove posizionare il secondo dado");
                        row4=selection(4,0,k);
                        column4=selection(5,0,k);
                        Slot slot3=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3);
                        Slot slot4=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4);
                        return tool.effect(null,null,false,match,match.getStock(),slot1,slot3,slot2,slot4,0);
                    }else return tool.effect(null,null,false,match,match.getStock(),slot1,null,slot2,null,0);
                }else {
                    notify(listofobserver.get(k),"Non puoi usare questa carta nel 1° round perchè non ci sono dadi sul tracciato dei round");
                    return false;
                }
            }
        }
        return false;
    }

    public int selection(int max,int min,int k)throws RemoteException{
        int index;
        do {
            try {
                 index = listofobserver.get(k).selection_int();
            }catch (InputMismatchException e){
                index=10000;
            }
            if (index<min||index>=max) notify(listofobserver.get(k),"Riprova...");
        } while (index >= max || index < min);
        return index;
    }

    public String menu(){
        return match.toStringRoundTrack()+"Riserva: "+match.getStock().toString()+"\nScegli cosa fare : \n0: fine turno; \n1: posiziona un dado dalla riserva;" +
                "\n2: usa una carta utensile:\n"+match.toolcardsString();

    }


    public void removeObserver(ClientInt o) throws RemoteException {
        listofobserver.remove(o);
    }


    public void notifyObserver(String arg) throws RemoteException {
         for (int i=0;i<listofobserver.size();i++) {
             notify(listofobserver.get(i),arg);
         }
    }


    public boolean addObserver(ClientInt o) throws RemoteException {
        if (loginconnection(o)) {
            if(start){
                int i=0;
                for(Player p: match.getPlayers()) {
                    if (p.getNickname().equals(o.getNickname()))
                        i=match.getPlayers().indexOf(p);
                }
                match.getPlayers().get(i).setConnected(true);
                listofobserver.set(i,o);
                return true;
            }else{
                listofobserver.add(o);
                name_disconnected.add(o.getNickname());
                try {
                    room.addPlayer(o.getNickname());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return true;
            }
        }else
            return false;
        }

    public void notify(ClientInt o,String arg) throws RemoteException{
        try {
            o.update(arg);
        } catch (RemoteException e) {}
    }

    public void notifyOthers(ClientInt o,String arg)throws RemoteException{
        for(ClientInt c:listofobserver){
            try{
                if(!c.equals(o)){
                    notify(c,arg);
                }
            }catch (ConcurrentModificationException e){}
            catch (ConnectException e){}
        }
    }

    public boolean loginconnection(ClientInt o) throws RemoteException {
        final int list=4;
        int i=0;
        String nick = null;
        while (i!=2){
            nick=o.setupconnection();
            for (Player p: room.getPlayers()){
                if(start && p.getNickname().equals(nick)){
                    if(trippleDes.decrypt(o.getPassword()).equals(this.o.get(nick).asString())){
                        return true;
                    }
                }else if (p.getNickname().equals(nick)){
                    notify(o,"Questo nickname è già stato usato da un altro giocatore");
                    i=1;
                }
            }
            if (i==1) i=0;
            else if (i==0) i=2;
        }
        if(start){
            System.out.println(nick+" ha tentato di partecipare");
            notify(o,"La partita è già iniziata");
            return false;
        }
        if(listofobserver.size()<list) {
            if(o.getServerIp().equals("127.0.0.1"))
                System.out.println(nick + " connesso localmente");
            else
                System.out.println(nick + " connesso da remoto");
            notify(o, "Benvenuto " + nick);
            String string = "Lista d'attesa: ";
            for (Player p:room.getPlayers()){
                string+=p.getNickname()+" ; ";
            }
            notify(o,string);
            notifyOthers(o, nick+ " connesso");
            return true;
        }
        else{
            System.out.println(nick+ " ha tentato di partecipare ma è stato respinto");
            notify(o,"La partita ha raggiunto il numero massimo di giocatori" );
            return false;
        }
    }

    public void vericaconnessione() throws RemoteException {
            int i=0;
            for (ClientInt c:listofobserver){
                try {
                    c.verifyconnection();
                }catch (ConnectException e){
                    if(!this.start) {
                        System.out.println(room.getPlayers().get(i).getNickname()+" disconnesso");
                        notifyOthers(c,room.getPlayers().get(i).getNickname()+" disconnesso");
                        room.getPlayers().remove(i);
                        name_disconnected.remove(i);
                        removeObserver(c);
                    }
                    else{
                        if(match.getPlayers().get(i).isConnected()){
                            match.getPlayers().get(i).setConnected(false);
                            System.out.println(match.getPlayers().get(i).getNickname() + " disconnesso");
                        }
                    }
                }
                i++;
            }
    }
}
