package it.polimi.ingsw.rete;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Colour;
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
        notifyObserver(match.getGlassWindowPlayers()+ Colour.RED.escape()+"---------------------------------------------------------------------------------------------\nTHE GAME BEGIN"+Colour.RESET);
        while(match.getRound()!=11){
            round();
        }
        notifyObserver("THE MATCH IS OVER");
        match.fineMatch();
        notifyObserver(match.ranking());
    }

    public void round()throws RemoteException{
        int cont_turn=1;
        final boolean[] timer_scaduto = {false};
        Timer timer = new Timer();
        Round round= new Round(match);
        int k=0,t=1;
        for(int z=0; z<2*match.getnumberPlayers();z++){
            notifyOthers(listofobserver.get(k),"Wait your turn\nIt's "+listofobserver.get(k).getNickname()+"'s turn\nDraft pool: "+match.getStock().toString());
            notify(listofobserver.get(k),"It's your turn "+listofobserver.get(k).getNickname()+"\nRound: "+match.getRound()+"; Turn "+t+"\n"+
                    "Your scheme card: "+round.getTurns().get(z).getOneplayer().getWindow().toString()+"\nDraft pool: "+match.getStock().toString()+"\n"+menu());
            int menu;
            do {
                do {
                    try {
                        menu=listofobserver.get(k).selection_int();
                    }catch (InputMismatchException e){
                        menu=3;
                    }
                    if (menu<0||menu>2) notify(listofobserver.get(k),"Try again...");
                }while (menu>2||menu<0);
                switch (menu){
                    case 0:{
                        notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" has skipped his turn");
                        cont_turn=0;
                        break;
                    }
                    case 1:{
                        dicehand(k,z,round);
                        break;
                    }
                    case 2:{
                        tool_hand(k,z,round);
                        cont_turn=1;
                        break;
                    }
                }
            } while (cont_turn!=0);
            notifyObserver(listofobserver.get(k).getNickname()+"'s scheme card, after this turn "+round.getTurns().get(z).getOneplayer().getWindow().toString()+
                    "\n---------------------------------------------------------------------------------------------");
            if(z>match.getnumberPlayers()-1)
                k--;
            if(z<match.getnumberPlayers()-1)
                k++;
            if (z==match.getnumberPlayers()-1)
                t=2;
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
            do {
                try {
                    index_draft = listofobserver.get(k).selection_int();
                }catch (InputMismatchException e){
                    index_draft=10;
                }
            } while (index_draft >= match.getStock().getDicestock().size() || index_draft < 0);
            notify(listofobserver.get(k), "\nYour choice is: " + match.getStock().getDicestock().get(index_draft).toString() +
                    "\nChoose the slot of your scheme card where you want to place the die, respectively row and column: ");
            do {
                try {
                    row = listofobserver.get(k).selection_int();
                }catch (InputMismatchException e){
                    row=10;
                }
            } while (row >= 4 || row < 0);
            do {
                try {
                    column = listofobserver.get(k).selection_int();
                }catch (InputMismatchException e){
                    column=10;
                }
            } while (column >= 5 || column < 0);
            match.getRules().diePlacing(round.getTurns().get(z).getOneplayer(), round.getTurns().get(z).getOneplayer().getWindow().getSlot(row, column), match.getStock().getDicestock().get(index_draft));
            if (round.getTurns().get(z).getOneplayer().getWindow().getSlot(row,column).isOccupate()) {
                notify(listofobserver.get(k), "Die placed correctly");
                notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" has placed the die "+match.getStock().getDicestock().get(index_draft)+" in his slot ("+row+","+column+")");
                match.getStock().getDicestock().remove(index_draft);
                cont = 0;
            } else
                notify(listofobserver.get(k), match.getRules().getError());
        }
    }

    public void tool_hand(int k,int z,Round round)throws RemoteException{
        int cont=1;
        int index;
        notify(listofobserver.get(k),"Choose a tool card from list by its value: \n"+match.toolcardsString());
        do {
            try {
                index = listofobserver.get(k).selection_int();
            }catch (InputMismatchException e){
                index=10;
            }
        } while (index >= 4 || index < 1);
        notify(listofobserver.get(k),"your choiche is "+match.getTool().get(index-1));
        notifyOthers(listofobserver.get(k),listofobserver.get(k).getNickname()+" ha usato la carta tool "+match.getTool().get(index-1).getName());
        while (cont!=0){
            match.getTool().get(index-1).setPlayer(round.getTurns().get(z).getOneplayer());
            if (!tool_selection(k,match.getTool().get(index-1))){
                notify(listofobserver.get(k),match.getTool().get(index-1).getError());
            }else {
                notify(listofobserver.get(k),"Operation completed");
                cont=0;
            }
        }
    }

    public boolean tool_selection(int k, Tool tool) throws RemoteException {
        switch (tool.getName()){
            case "Pinza Sgrossatrice":{
                int index_draft,piumeno=-1;
                boolean b;
                notify(listofobserver.get(k),"Choose a die from draft pool: "+match.getStock().toString());
                do {
                    try {
                        index_draft = listofobserver.get(k).selection_int();
                    }catch (InputMismatchException e){
                        index_draft=10;
                    }
                } while (index_draft >= match.getStock().getDicestock().size() || index_draft < 0);
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
            case "Pennello per Eglomise": break;
            case "Alesatore per lamina di rame": break;
            case "Lathekin": break;
            case "Taglierina circolare": break;
            case "Pennello per Pasta Salda": break;
            case "Martelletto": break;
            case "Tenaglia a Rotelle": break;
            case "Riga in Sughero": break;
            case "Tampone Diamantato": break;
            case "Diluente per Pasta Salda": break;
            case "Taglierina Manuale": break;
        }
        return false;
    }

    public String menu(){
        return "Choose what to do : \n0: skip turn; \n1: place a die from draft pool;" +
                "\n2: use a tool card;";

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
