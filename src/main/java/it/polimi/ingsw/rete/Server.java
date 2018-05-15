package it.polimi.ingsw.rete;

import it.polimi.ingsw.game.Match;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements ServerInt{
    static int PORT=8080;
    protected Match match;
    private Waiting_Room room=new Waiting_Room(this);
    private Registry registry;

    public void start_server(){
        try{
            String server_name="Sagrada server";
            Server obj =new Server();
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


    public boolean login(String player) throws RemoteException {
        try {
            System.out.println(player + " connected");
            room.addPlayer(player);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void show_client_connessi() throws RemoteException {
        String[] remoteObjects=registry.list();
        for (int i=0;i<remoteObjects.length;i++){
            System.out.println(remoteObjects[i]);
        }
    }

    public void logout(String player) throws RemoteException {
        room.deleteplayer(player);
        System.err.println(player + " disconnected");
    }


    public String show_waitingroom() throws RemoteException{
        return room.toString();
    }
}
