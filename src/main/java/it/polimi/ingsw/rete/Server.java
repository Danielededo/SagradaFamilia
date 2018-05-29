package it.polimi.ingsw.rete;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Round;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Timer;

public class Server implements ServerInt{
    static int PORT;
    protected Match match;
    private Server obj;
    private Waiting_Room room;
    private ArrayList<ClientInt> listofobserver = new ArrayList<ClientInt>();
    private Registry registry;
    private boolean start=false;
    private boolean dicehand_done=false;

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
                System.out.println(b + " has been disconnected");
                room.deleteplayer(b);
                removeObserver(listofobserver.get(i));
                System.out.println(room.toString());
                i--;
            }
        }
    }

    public void start_server(){
        boolean gone=true;
        try{
            Properties defaultProps = new Properties();
            FileInputStream in = new FileInputStream("src/main/resources/Connection");
            defaultProps.load(in);
            PORT= Integer.parseInt(defaultProps.getProperty("Port"));
            in.close();
            String server_name="Sagrada server";
            obj =new Server();
            obj.room=new Waiting_Room(obj);
            ServerInt stub = (ServerInt) UnicastRemoteObject.exportObject(obj,0);
            registry= LocateRegistry.createRegistry(PORT);
            registry.rebind(server_name,stub);
            System.err.println(server_name + " ready");
            while (gone) {

            }
        }catch (Exception e){
            System.err.println("Server exception:   " + e.toString());
            e.printStackTrace();
            gone=false;
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

    public void setMatch(Match match) throws RemoteException {
        setStart(true);
        final int time=15;
        this.match = match;
        notifyObserver("Tool cards are: \n"+match.toolcardsString()+
                "\nServer -> Public targets are: \n"+match.publictargetString());
        notifyObserver("The game is starting... ");
        int i=0,j=0;
        try {
            Thread.sleep(1000*time);
        }
        catch (Exception e) {}
        for (ClientInt c:listofobserver){
            c.update("Your private target is "+match.getPlayers().get(i).getPrivatetarget().toString());
            ArrayList<GlassWindow> windows=match.getScheme().extractGlass();
            notify(c,"\n"+ windows.toString());
            do {
                String a = c.setupgame();
                if (getNames(windows).contains(a)) {
                    this.match.getPlayers().get(i).setWindow(windows.get(getNames(windows).indexOf(a)));
                    System.out.println(c.getNickname() + " has chosen " + a);
                    j=1;
                } else
                    c.update("Please try again");
            }while (j==0);
            i++;
            j=0;
        }
        notifyObserver(match.getGlassWindowPlayers()+ Colour.RED.escape()+"---------------------------------------------------------------------------------------------\nTHE MATCH BEGINS"+Colour.RESET);
        while(match.getRound()!=11){
            round();
        }
        notifyObserver("THE MATCH IS OVER");
        match.fineMatch();
        notifyObserver(match.ranking());
    }

    public void round()throws RemoteException{
        final boolean[] timer_scaduto = {false};
        Timer timer = new Timer();
        Round round= new Round(match);
        int k=0,t=1;
        for(int z=0; z<2*match.getnumberPlayers();z++){
            dicehand_done=false;
            int cont_turn=1;
            notifyOthers(listofobserver.get(k),"Wait your turn\nIt's "+listofobserver.get(k).getNickname()+"'s turn\nDraft pool: "+match.getStock().toString());
            notify(listofobserver.get(k),"It's your turn "+listofobserver.get(k).getNickname()+"\nRound: "+match.getRound()+"; Turn "+t+"\n"+
                    "Your scheme card: "+round.getTurns().get(z).getOneplayer().getWindow().toString()+"\nDraft pool: "+match.getStock().toString());
            int menu;
            if (!round.getTurns().get(z).getOneplayer().isMissednext_turn()){
                do {
                    if (dicehand_done){
                        notify(listofobserver.get(k),"you can only end your turn or use a tool card");
                    }
                    notify(listofobserver.get(k),menu());
                    menu=selection(3,0,k);
                    switch (menu){
                        case 0:{
                            notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" has ended his turn");
                            cont_turn=0;
                            break;
                        }
                        case 1:{
                            if (!dicehand_done) dicehand(k,z,round);
                            else notify(listofobserver.get(k),"You have already placed a die in this turn");
                            break;
                        }
                        case 2:{
                            tool_hand(k,z,round,cont_turn);
                            break;
                        }
                    }
                } while (cont_turn!=0);
            }else {
                notify(listofobserver.get(k),"You have used tool card Tenaglia a Rotelle in your first turn so skip this turn");
                notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" skip his turn due to use of tool card Tenaglia a Rotelle");
                round.getTurns().get(z).getOneplayer().setMissednext_turn(false);
            }
            notifyObserver("After this turn, "+round.getTurns().get(z).getOneplayer().toStringpublic()+
                    "\n---------------------------------------------------------------------------------------------");
            if(z>match.getnumberPlayers()-1)
                k--;
            if(z<match.getnumberPlayers()-1)
                k++;
            if (z==match.getnumberPlayers()-1){
                for (Player p:match.getPlayers()){
                    p.setContTurn(2);
                }
                t=2;
            }
        }
        notifyObserver("THE "+match.getRound()+" ROUND IS OVER");
        match.fineRound();
        listofobserver.add(listofobserver.get(0));
        listofobserver.remove(0);
    }

    public void dicehand(int k,int z,Round round)throws RemoteException{
        int cont=1;
        while(cont!=0) {
            int index_draft, row, column;
            notify(listofobserver.get(k), "Choose a die through its index");
            index_draft=selection(match.getStock().getDicestock().size(),0,k);
            notify(listofobserver.get(k), "\nYour choice is: " + match.getStock().getDicestock().get(index_draft).toString() +
                    "\nChoose the slot of your scheme card where you want to place the die, respectively row and column: ");
            row=selection(4,0,k);
            column=selection(5,0,k);
            match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(), round.getTurns().get(z).getOneplayer().getWindow().getSlot(row, column), match.getStock().getDicestock().get(index_draft));
            if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()) {
                notify(listofobserver.get(k), "Die placed correctly\n"+round.getTurns().get(z).getOneplayer().getWindow());
                notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" has placed the die "+match.getStock().getDicestock().get(index_draft)+" in his slot ("+row+","+column+")");
                match.getStock().getDicestock().remove(index_draft);
                cont = 0;
                dicehand_done=true;
            } else
                notify(listofobserver.get(k), match.getRules().getError());
        }
    }

    public void tool_hand(int k,int z,Round round,int cont_turn)throws RemoteException{
        int cont=1,placed=1;
        int index;
        notify(listofobserver.get(k),"Choose a tool card from list by its value:");
        index=selection(4,1,k);
        notify(listofobserver.get(k),"your choiche is "+match.getTool().get(index-1));
        notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" has used tool card "+match.getTool().get(index-1).getName());
        while (cont!=0&&placed!=0){
            match.getTool().get(index-1).setPlayer(round.getTurns().get(z).getOneplayer());
            if (!tool_selection(k,z,round,match.getTool().get(index-1),cont_turn,placed)){
                notify(listofobserver.get(k),match.getTool().get(index-1).getError());
                if (!match.getTool().get(index-1).isUsed()) placed=0;
            }else {
                notify(listofobserver.get(k),"Operation completed");
                cont=0;
            }
        }
    }

    public boolean tool_selection(int k, int z, Round round, Tool tool,int cont_turn,int placed) throws RemoteException {
        switch (tool.getName()){
            case "Pinza Sgrossatrice":{
                int index_draft,piumeno=-1;
                boolean b;
                notify(listofobserver.get(k),"Choose a die from draft pool: "+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size(),0,k);
                notify(listofobserver.get(k),"your choice is "+match.getStock().getDicestock().get(index_draft));
                notify(listofobserver.get(k),"Now select 0 if you want to increase the value or 1 if you want to decrease the value");
                do {
                    try {
                        piumeno = listofobserver.get(k).selection_int();
                    }catch (InputMismatchException e){
                        piumeno=10;
                    }
                } while (piumeno!=0&&piumeno!=1);
                if (piumeno==1)b=false;
                else b=true;
                return tool.effect(match.getStock().getDicestock().get(index_draft),null,b,match,match.getStock(),null,null,null,null,0);
            }
            case "Pennello per Eglomise": {
                int row1,column1,row2,column2;
                notify(listofobserver.get(k),"Insert row and column respectively of the slot from which you want to move the die");
                row1=selection(4,0,k);
                column1=selection(5,0,k);
                notify(listofobserver.get(k),"Now insert row and column respectively of the slot where you want to put the die");
                row2=selection(4,0,k);
                column2=selection(5,0,k);
                Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                return tool.effect(null,null,false,match,match.getStock(),slot1,slot2,null,null,0);
            }
            case "Alesatore per lamina di rame": {
                int row1,column1,row2,column2;
                notify(listofobserver.get(k),"Insert row and column respectively of the slot from which you want to move the die");
                row1=selection(4,0,k);
                column1=selection(5,0,k);
                notify(listofobserver.get(k),"Now insert row and column respectively of the slot where you want to put the die");
                row2=selection(4,0,k);
                column2=selection(5,0,k);
                Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                return tool.effect(null,null,false,match,match.getStock(),slot1,slot2,null,null,0);
            }
            case "Lathekin": {
                int row1,column1,row2,column2,row3,column3,row4,column4;
                notify(listofobserver.get(k),"Insert row and column respectively of the slot from which you want to move the first die");
                row1=selection(4,0,k);
                column1=selection(5,0,k);
                notify(listofobserver.get(k),"Now insert row and column respectively of the slot where you want to put the first die");
                row2=selection(4,0,k);
                column2=selection(5,0,k);
                notify(listofobserver.get(k),"Insert row and column respectively of the slot from which you want to move the second die");
                row3=selection(4,0,k);
                column3=selection(5,0,k);
                notify(listofobserver.get(k),"Now insert row and column respectively of the slot where you want to put the second die");
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
                notify(listofobserver.get(k),"Choose a die from draft pool: "+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size(),0,k);
                notify(listofobserver.get(k),"your choice is "+match.getStock().getDicestock().get(index_draft));
                notify(listofobserver.get(k),match.toStringRoundTrack()+"\nNow insert round number and then the index of the chosen die");
                index_roundtrackList=selection(match.getRound(),1,k)-1;
                index_roundtrackDie=selection(match.getRoundTrackList(index_roundtrackList).size()-1,0,k);
                Die d=match.getRoundTrackList(index_roundtrackList).get(index_roundtrackDie);
                return tool.effect(match.getStock().getDicestock().get(index_draft),d,false,match,match.getStock(),null,null,null,null,index_roundtrackList);
            }
            case "Pennello per Pasta Salda": {
                int index_draft;
                notify(listofobserver.get(k),"Choose a die from draft pool: "+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size(),0,k);
                return tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0);
            }
            case "Martelletto": {
                if (!dicehand_done)
                    return tool.effect(null,null,false,match,match.getStock(),null,null,null,null,0);
                else {
                    notify(listofobserver.get(k),"You've already placede a die so you can't use this card");
                    placed=0;
                    return false;
                }
            }
            case "Tenaglia a Rotelle": {
                int index_draft,row,column;
                while (!dicehand_done){
                    notify(listofobserver.get(k),"To use this card you must place a die before");
                    placed=0;
                    return false;
                }
                notify(listofobserver.get(k),tool.getPlayer().getWindow()+"\nYou can select another die from draft pool: "+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size(),0,k);
                notify(listofobserver.get(k), "\nYour choice is: " + match.getStock().getDicestock().get(index_draft).toString() +
                        "\nChoose the slot of your scheme card where you want to place the die, respectively row and column: ");
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
                    notify(listofobserver.get(k),"Choose a die from draft pool: "+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    notify(listofobserver.get(k),"Insert row and column respectively of the slot where you want to put the die");
                    row=selection(4,0,k);
                    column=selection(5,0,k);
                    Slot slot=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column);
                    if (tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),slot,null,null,null,0)){
                        dicehand_done=true;
                        match.getStock().getDicestock().remove(index_draft);
                        cont_turn=0;
                        notify(listofobserver.get(k),"Die placed correctly");
                        return true;
                    }else return false;
                }else {notify(listofobserver.get(k),"You have already placed a die");placed=0;}
                return false;
            }
            case "Tampone Diamantato": {
                int index_draft;
                notify(listofobserver.get(k),"Choose a die from draft pool to invert the face:\n"+match.getStock().toString());
                index_draft=selection(match.getStock().getDicestock().size(),0,k);
                return tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0);
            }
            case "Diluente per Pasta Salda": {
                int index_draft,value,row,column;
                boolean loop;
                if (!dicehand_done) {
                    notify(listofobserver.get(k),"Choose a die from draft pool to put in sack:\n"+match.getStock().toString());
                    index_draft=selection(match.getStock().getDicestock().size(),0,k);
                    if (tool.effect(match.getStock().getDicestock().get(index_draft),null,false,match,match.getStock(),null,null,null,null,0)){
                        Die d = match.getSack().extractdie();
                        do {
                            notify(listofobserver.get(k),d+" now select the face you want to put in this die");
                            value=selection(7,0,k);
                            d.setFace(value);
                            notify(listofobserver.get(k),d+"\nNow insert row and column respectively of the slot where you want to put the die");
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
                }else notify(listofobserver.get(k),"You have already placed a die");
                return false;
            }
            case "Taglierina Manuale": {
                int row1,column1,row2,column2,row3,column3,row4,column4;
                notify(listofobserver.get(k),"Do you want to move 1 or 2 dice?");
                int move=selection(3,1,k);
                notify(listofobserver.get(k),"Insert row and column respectively of the slot from which you want to move the first die");
                row1=selection(4,0,k);
                column1=selection(5,0,k);
                notify(listofobserver.get(k),"Now insert row and column respectively of the slot where you want to put the first die");
                row2=selection(4,0,k);
                column2=selection(5,0,k);
                Slot slot1=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row1,column1);
                Slot slot2=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row2,column2);
                if(move==2){
                    notify(listofobserver.get(k),"Insert row and column respectively of the slot from which you want to move the second die");
                    row3=selection(4,0,k);
                    column3=selection(5,0,k);
                    notify(listofobserver.get(k),"Now insert row and column respectively of the slot where you want to put the second die");
                    row4=selection(4,0,k);
                    column4=selection(5,0,k);
                    Slot slot3=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row3,column3);
                    Slot slot4=round.getTurns().get(z).getOneplayer().getWindow().getSlot(row4,column4);
                    return tool.effect(null,null,false,match,match.getStock(),slot1,slot3,slot2,slot4,0);
                }else return tool.effect(null,null,false,match,match.getStock(),slot1,null,slot2,null,0);
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
            if (index<min||index>=max) notify(listofobserver.get(k),"Try again...");
        } while (index >= max || index < min);
        return index;
    }

    public String menu(){
        return "Choose what to do : \n0: end turn; \n1: place a die from draft pool;" +
                "\n2: use a tool card:\n"+match.toolcardsString();

    }


    public void removeObserver(ClientInt o) throws RemoteException {
        listofobserver.remove(o);
    }


    public void notifyObserver(String arg) throws RemoteException {
         for (ClientInt c:listofobserver) {
             c.update(arg);
         }
    }


    public boolean addObserver(ClientInt o) throws RemoteException {
        if (loginconnection(o)) {
            listofobserver.add(o);
            room.addPlayer(o.getNickname());
            return true;
        } else
            return false;
        }

    public void notify(ClientInt o,String arg) throws RemoteException{
        o.update(arg);
    }

    public void notifyOthers(ClientInt o,String arg)throws RemoteException{
        for(ClientInt c:listofobserver){
            if(!c.equals(o))
                c.update(arg);
        }
    }

    public boolean loginconnection(ClientInt o) throws RemoteException {
        String name=null;
        final int list=4;
        int i=0;
        String nick = null;
        while (i!=2){
            nick=o.setupconnection();
            for (Player p: room.getPlayers()){
                if (p.getNickname().equals(nick)){
                    notify(o,"This nickname has already been used");
                    i=1;
                }
            }
            if (i==1) i=0;
            else if (i==0) i=2;
        }
        if(start){
            System.out.println(nick+" tried to access");
            notify(o,"Match already begun");
            return false;
        }
        if(listofobserver.size()<list) {
            if(o.getServerIp().equals("127.0.0.1"))
                System.out.println(nick + " locally connected");
            else
                System.out.println(nick + " connected remotely");
            notify(o, "Welcome " + nick);
            String string = "Waiting room: ";
            for (Player p:room.getPlayers()){
                string+=p.getNickname()+" ; ";
            }
            notify(o,string);
            notifyOthers(o, nick+ " connected");
            return true;
        }
        else{
            System.out.println(nick+ " tried to join unsuccessfully");
            notify(o,"Reached the maximum limit of players" );
            return false;
        }
    }
}
