package it.polimi.ingsw.rete;

import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements ServerInt{
    static int PORT=8080;
    protected Match match;
    private Server obj;
    private Waiting_Room room=new Waiting_Room(obj);
    private ArrayList<ClientInt> listofobserver = new ArrayList<ClientInt>();
    private Registry registry;


    public void controll() throws RemoteException {
        String b;
        for(int i=0;i<listofobserver.size();i++) {
            b=listofobserver.get(i).getNickname();
            try {
                listofobserver.get(i).setupPlayer();
            } catch (Exception e) {
                System.out.println(b + " has been disconnected");
                removeObserver(listofobserver.get(i));
                room.deleteplayer(b);
                System.out.println(room.toString());
                i--;
            }
        }
    }

    public void start_server(){
        try{
            String server_name="Sagrada server";
            obj =new Server();
            ServerInt stub = (ServerInt) UnicastRemoteObject.exportObject(obj,0);
            registry= LocateRegistry.createRegistry(PORT);
            registry.rebind(server_name,stub);
            System.err.println(server_name + " ready");
            while (true){

            }
        }catch (Exception e){
            System.err.println("Server exception:   " + e.toString());
            e.printStackTrace();
        }
    }

    public void setMatch(Match match) {
        this.match = match;
    }


    public void removeObserver(ClientInt o) throws RemoteException {
        listofobserver.remove(o);
    }


    public void notifyObserver(String arg) throws RemoteException {
        for (ClientInt c:listofobserver){
            c.update(arg);
        }
    }


    public void addObserver(ClientInt o) throws RemoteException {
        listofobserver.add(o);
        loginconnection(listofobserver.indexOf(o));
    }

    public void notify(ClientInt o,String arg) throws RemoteException{
        o.update(arg);
    }

    public void loginconnection(int index) throws RemoteException {
        String name=null;
        int i=0;
        String nick = null;
        while (i!=2){
            nick=listofobserver.get(index).setupconnection();
            for (Player p: room.getPlayers()){
                if (p.getNickname().equals(nick)){
                    notify(listofobserver.get(index),"Nickname già usato da un altro giocatore... try again ->");
                    i=1;
                }
            }
            if (i==1) i=0;
            else if (i==0) i=2;
        }
        System.out.println(nick + " connected");
        room.addPlayer(nick);
    }
}
