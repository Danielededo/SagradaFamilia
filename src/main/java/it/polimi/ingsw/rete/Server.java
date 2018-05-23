package it.polimi.ingsw.rete;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Round;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements ServerInt{
    static int PORT=8080;
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
        notifyObserver("Tool cards are: \n"+match.getTool().toString()+
                "\nServer -> Public targets are: \n"+match.getPublictarget());
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
        notifyObserver(match.getGlassWindowPlayers());
        while(match.getRound()!=11){
            Round round= new Round(match);
            for(int z=0; z<match.getnumberPlayers();z++){
                notify(listofobserver.get(z),"It's your Turn\nRound: "+match.getRound()+"; Turn 1\n"+
                "Draft pool: "+match.getStock().getDicestock().toString()+"\n"+menu());
                round.getTurns().get(i).doTurn(match,round,i);

            }
            int z;
            for (z=listofobserver.size()-1;z>=0;z--){
                notify(listofobserver.get(z),"It's your Turn\nRound: "+match.getRound()+"; Turn 2");

            }
            match.fineRound();
        }
        match.fineMatch();
    }

    public String menu(){
        return "Choose what to do : \n0: skip turn; \n1: place a die from draft pool;" +
                "\n2: use a tool card;";

    }


    public void removeObserver(ClientInt o) throws RemoteException {
        listofobserver.remove(o);
    }


    public void notifyObserver(String arg) throws RemoteException {
        for (ClientInt c:listofobserver){
            c.update(arg);
        }
    }


    public boolean addObserver(ClientInt o) throws RemoteException {
        if (loginconnection(o)) {
            listofobserver.add(o);
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
            room.addPlayer(nick);
            return true;
        }
        else{
            System.out.println(nick+ " tried to join unsuccessfully");
            notify(o,"Reached the maximum limit of players" );
            return false;
        }
    }
}
