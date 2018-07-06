package it.polimi.ingsw.server;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.rmi.ServerInt;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements ServerInt {
    static int PORT;
    private ArrayList<Hub> hubs=new ArrayList<Hub>();
    private HashMap<String,Hub> matches=new HashMap();
    private Registry registry;

    public Server(String PORT) {
        try {
            this.PORT= Integer.parseInt(PORT);
        } catch (NumberFormatException e) {
            System.err.println("Porta inserita non valida");
            System.exit(-1);
        }
    }

    /**
     * This method allows to create a server in RMI protocol
     */
    public void start_server(){
        try{
            boolean gone=true;
            String server_name;
            ServerInt stub;
            server_name = "Sagrada server";
            InetAddress address=InetAddress.getLocalHost();
            String IP=address.getHostAddress();
            System.setProperty("java.rmi.game.hostname",IP);
            stub = (ServerInt) UnicastRemoteObject.exportObject(this, 0);
            registry= LocateRegistry.createRegistry(PORT);
            registry.rebind(server_name,stub);
            System.err.println(server_name + " pronto");
            while(gone){
            }
        }catch (IllegalArgumentException e){
            System.err.println("Porta inserita non valida, il numero di porta va da 0 a 65355");
            System.exit(-1);
        }catch (ExportException e) {
            System.err.println("Porta attualmente in uso");
            System.exit(-1);
        }catch (Exception e){}
    }

    public HashMap<String, Hub> getMatches() {
        return matches;
    }

    /**
     * This method is called when a game is end and cancelled the hub
     * @param hub to be cancelled
     */
    public void terminatehub(Hub hub){
        hubs.remove(hub);
    }

    /**
     * This method is called by client to join in game.
     * Client is connected in an already started hub or to another one by create it
     * @param o is the interface of client that had the connection to client
     * @return true if connection is done correctly or false if it hasn't
     * @throws RemoteException called when connection is lost
     */
    public boolean addObserver(ClientInt o) throws RemoteException {
        String nick=o.setupconnection();
        if(matches.containsKey(nick)){
            return matches.get(nick).addObserver(o);
        }else{
            int cont = 0;
            for (int i=0;i<hubs.size();i++){
                if (hubs.get(i).isStart()) cont++;
                else i=hubs.size();
            }
            if (hubs.size()==0||cont==hubs.size()){
                Hub hub=new Hub(this);
                hubs.add(hub);
                if (hub.addObserver(o))return true;
            }else return hubs.get(cont).addObserver(o);
        }
        return false;
    }

    /**
     * This method is called by client to control if server is still alive
     * @throws RemoteException called when connection is lost
     */
    @Override
    public void ping() throws RemoteException {
        return;
    }
}
