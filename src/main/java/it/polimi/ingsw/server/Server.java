package it.polimi.ingsw.server;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.rmi.ServerInt;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements ServerInt {
    static int PORT;
    private ArrayList<Hub> hubs=new ArrayList<Hub>();
    private HashMap<String,Hub> matches=new HashMap();
    private Registry registry;
    private ArrayList<Boolean> start=new ArrayList<Boolean>();
    private boolean endRound=false;

    public void start_server(String arg){
        boolean gone=true;
        try{
            String server_name;
            ServerInt stub;
            PORT= Integer.parseInt(arg);
            server_name = "Sagrada server";
            InetAddress address=InetAddress.getLocalHost();
            String IP=address.getHostAddress();
            System.setProperty("java.rmi.game.hostname",IP);
            stub = (ServerInt) UnicastRemoteObject.exportObject(this, 0);
            registry= LocateRegistry.createRegistry(PORT);
            registry.rebind(server_name,stub);
            System.err.println(server_name + " pronto");
            while (gone) {
            }
        }catch (Exception e){
            System.err.println("Server exception:   " + e.toString());
            e.printStackTrace();
        }
    }

    public HashMap<String, Hub> getMatches() {
        return matches;
    }

    public void setStart(Boolean start,int index) {
        this.start.set(index,start);
    }

    public void terminatehub(Hub hub){
        int i=hubs.indexOf(hub);
        start.remove(i);
        hubs.remove(hub);
        hub=null;
    }

    public boolean addObserver(ClientInt o) throws RemoteException {
        String nick=o.setupconnection();
        if(matches.containsKey(nick)){
            return matches.get(nick).addObserver(o);
        }else{
            int cont = 0;
            for (int i=0;i<start.size();i++){
                if (start.get(i)) cont++;
                else i=start.size();
            }
            if (hubs.size()==0||cont==start.size()){
                start.add(false);
                Hub hub=new Hub(start.get(start.size()-1),hubs.size(),this);
                hubs.add(hub);
                if (hub.addObserver(o))return true;
            }else if (cont<start.size()-1){
                Hub hub=new Hub(start.get(cont),hubs.size(),this);
                hubs.add(hub);
                if (hub.addObserver(o))return true;
            } else if (hubs.get(cont).addObserver(o)) return true;
        }
        return false;
    }

    @Override
    public void ping() throws RemoteException {
        return;
    }
}
