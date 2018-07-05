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

    public void terminatehub(Hub hub){
        hubs.remove(hub);
    }

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

    @Override
    public void ping() throws RemoteException {
        return;
    }
}
