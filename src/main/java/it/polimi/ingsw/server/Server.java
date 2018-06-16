package it.polimi.ingsw.server;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.rmi.ServerInt;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.utils.DisconnectionThread;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server implements ServerInt {
    static int PORT;
    private Controller controller;
    private Waiting_Room room;
    private ArrayList<ClientInt> listofobserver = new ArrayList<ClientInt>();
    private Registry registry;
    private boolean start=false;
    public Timer timer=new Timer();
    public TimerTurn setupGame;
    public DisconnectionThread thread;
    private boolean endRound=false;
    Map<String,String> o=new HashMap<>();

    public void setStart(boolean start) {
        this.start = start;
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
            setupGame=new TimerTurn(this);
            controller=new Controller(this,start);
            this.room=new Waiting_Room(this,controller);
            stub = (ServerInt) UnicastRemoteObject.exportObject(this, 0);
            registry= LocateRegistry.createRegistry(PORT);
            registry.rebind(server_name,stub);
            timer.scheduleAtFixedRate(thread,0,500);
            Timer t=new Timer();
            t.scheduleAtFixedRate(setupGame,0,1000);
            System.err.println(server_name + " pronto");
            while (gone) {
            }
        }catch (Exception e){
            System.err.println("Server exception:   " + e.toString());
            e.printStackTrace();
        }
    }

    public TimerTurn getSetupGame() {
        return setupGame;
    }

    public void removeObserver(ClientInt o) throws RemoteException {
        listofobserver.remove(o);
    }


    void notifyObserver(String arg) throws RemoteException {
         for (int i=0;i<listofobserver.size();i++) {
             try {
                 if(!start || (start && controller.match.getPlayers().get(i).isConnected()))
                    listofobserver.get(i).update(arg);
             } catch (RemoteException e){}
         }
    }


    public boolean addObserver(ClientInt o) throws RemoteException {
        if (loginconnection(o)) {
            if(start){
                int i=0;
                for(Player p: controller.match.getPlayers()) {
                    if (p.getNickname().equals(o.getNickname())) {
                        i=controller.match.getPlayers().indexOf(p);
                    }
                }
                if(!endRound)
                    thread.cancel();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                listofobserver.set(i,o);
                controller.match.getPlayers().get(i).setConnected(true);
                System.out.println(o.getNickname()+" riconnesso");
                if(!endRound) {
                    thread = new DisconnectionThread(this);
                    timer.schedule(thread, 0, 500);
                }
                return true;
            }else{
                listofobserver.add(o);
                try {
                    room.addPlayer(o.getNickname());
                }catch (InterruptedException e){}
                catch (UnmarshalException e){}
                return true;
            }
        }else
            return false;
        }

    void notify(ClientInt o,String arg) throws RemoteException{
        try {
            if(!start || (start && controller.match.getPlayers().get(listofobserver.indexOf(o)).isConnected()))
                o.update(arg);
        } catch (RemoteException e) {}
    }

    void notifyOthers(ClientInt o,String arg)throws RemoteException{
        for(ClientInt c:listofobserver){
            try{
                if((start && !c.equals(o) && controller.match.getPlayers().get(listofobserver.indexOf(c)).isConnected()) || (!start && !c.equals(o))){
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
                    if(o.getPassword().equals(this.o.get(nick))){
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

    public Waiting_Room getRoom() {
        return room;
    }

    public boolean isStart() {
        return start;
    }

    public void vericaconnessione() throws RemoteException {
        int i=0,j=0;
        if (start) {
            for(Player p:controller.match.getPlayers()){
                if(p.isConnected())
                    j++;
            }
            if(j==1 && !controller.isRank()){
                for(Player p:controller.match.getPlayers())
                    if(p.isConnected()) {
                        start=false;
                        j = controller.match.getPlayers().indexOf(p);
                        notify(listofobserver.get(j),Colour.RED.escape()+"Hai vinto, non ci sono altri giocatori connessi"+Colour.RESET);
                        System.out.println(p.getNickname()+ " ha vinto dato che non ci sono altri giocatori connessi");
                        notify(listofobserver.get(j),"disconnettiti");
                        System.exit(0);
                    }
            }
        }
        for (ClientInt c:listofobserver){
            try {
                if((start && controller.match.getPlayers().get(i).isConnected()) || !start)
                    c.verifyconnection();
            }catch (ConnectException e){
                if(!this.start) {
                    System.out.println(room.getPlayers().get(i).getNickname()+" disconnesso");
                    notifyOthers(c,room.getPlayers().get(i).getNickname()+" disconnesso");
                    room.getPlayers().remove(i);
                    removeObserver(c);
                }

                else{
                    if(controller.match.getPlayers().get(i).isConnected()) {
                        controller.match.getPlayers().get(i).setConnected(false);
                        System.out.println(controller.match.getPlayers().get(i).getNickname() + " disconnesso");
                    }
                }
            }
            i++;
        }
    }
}
