package it.polimi.ingsw.server;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.utils.Constants;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.util.*;
import java.util.stream.Collectors;

public class Hub {
    private Server server;
    private ControllerG controller;
    private Waiting_Room room;
    private ArrayList<ClientInt> listofobserver = new ArrayList<ClientInt>();
    Boolean start;
    public Timer timer=new Timer();
    Timer t=new Timer();
    public TimerTurn setupGame;
    public DisconnectionThread thread;
    private boolean endRound=false;
    Map<String,String> o=new HashMap<>();
    private int timer_window,timer_t,timer_waiting;


    public Hub(Server server,int timer_t,int timer_waiting,int timer_window) {
        this.start = false;
        this.server=server;
        this.timer_waiting=timer_waiting;
        this.timer_window=timer_window;
        this.timer_t=timer_t;
        thread=new DisconnectionThread(this);
        setupGame=new TimerTurn(this);
        controller=new ControllerG(this,this.timer_window,this.timer_t);
        this.room=new Waiting_Room(this, controller,this.timer_waiting);
        timer.scheduleAtFixedRate(thread,0,500);
        t.scheduleAtFixedRate(setupGame,0,1000);
    }

    public Hub(boolean start) {
        this.start=start;
    }


    public ArrayList<ClientInt> getListofobserver() {
        return listofobserver;
    }

    public TimerTurn getSetupGame() {
        return setupGame;
    }

    /**
     * @param o is removed from the list of client in game
     * @throws RemoteException called when connection is lost
     */
    public void removeObserver(ClientInt o) throws RemoteException {
        listofobserver.remove(o);
    }

    public Server getServer() {
        return server;
    }

    /**
     * Add a client to list of observer client connected to the game
     * @param o client to be connected to current hub
     * @return true if adding was made, else in other case
     * @throws RemoteException called when connection is lost
     */
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
                notify(o,"Sei stato riconnesso");
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
            o.setNickerr(true);
            return false;
    }

    /**
     * This method is used to notify change on every client in the listofOberver
     * @param arg is the parameter to send to client
     * @throws RemoteException called when connection is lost
     */
    void notifyObserver(String arg) throws RemoteException {
        try {
            for (int i=0;i<listofobserver.size();i++) {
                if(!start || (start && controller.match.getPlayers().get(i).isConnected()))
                    listofobserver.get(i).update(arg);
            }
        }catch (RemoteException e){}
        catch (ConcurrentModificationException e){}
    }

    /**
     * This method is used to notify change on specified client
     * @param o is the client to notify
     * @param arg is the parameter to send to client
     * @throws RemoteException called when connection is lost
     */
    void notify(ClientInt o,String arg) throws RemoteException{
        try {
            if(!start || (start && controller.match.getPlayers().get(listofobserver.indexOf(o)).isConnected()))
                o.update(arg);
        } catch (RemoteException e) {}
    }

    /**
     * It used to notify change all client except one
     * @param o is the client to exclude from notification
     * @param arg is the parameter to send to client
     * @throws RemoteException called when connection is lost
     */
    void notifyOthers(ClientInt o,String arg)throws RemoteException{
        try{
            for(ClientInt c:listofobserver){
                if((start && !c.equals(o) && controller.match.getPlayers().get(listofobserver.indexOf(c)).isConnected()) || (!start && !c.equals(o)))
                    notify(c,arg);
            }
        }catch (ConcurrentModificationException e){}
        catch (ConnectException e){}
    }

    /**
     * This method is called by addobserver to verify if client has the right requirement
     * @param o is the client to be added to match
     * @return true if requirement are right, false in other case
     * @throws RemoteException called when connection is lost
     */
    public boolean loginconnection(ClientInt o) throws RemoteException {
        final int list=4;
        int i=0;
        String nick = null;
        while (i!=2){
            nick=o.getNickname();
            for (Player p: room.getPlayers()){
                if(start && p.getNickname().equals(nick) && !p.isConnected()){
                    if(o.getPassword().equals(this.o.get(nick))){
                        return true;
                    }
                }else if (p.getNickname().equals(nick)){
                    o.update("Questo nickname è già stato usato da un altro giocatore");
                    return false;
                }
            }
            if (i==1) i=0;
            else if (i==0) i=2;
        }
        if(o.getServerIp().equals("127.0.0.1"))
            System.out.println(nick + " connesso localmente");
        else
            System.out.println(nick + " connesso da remoto");
        notify(o, "welcome");
        notify(o, "Benvenuto " + nick);
        for (Player p:room.getPlayers()){
            notify(o, "connesso");
            notify(o, p.getNickname() + " è pronto per giocare.");
        }
        notifyOthers(o, "connesso");
        notifyOthers(o, nick + " è pronto per giocare.");
        return true;
    }

    public Waiting_Room getRoom() {
        return room;
    }

    public boolean isStart() {
        return start;
    }

    /**
     * This method is called when match is end. It's used to terminate this game and eliminate hub
     */
    public void terminateHub(){
        try {
            thread.cancel();
            setupGame.cancel();
            t.purge();
            controller.getTimerTurn().cancel();
        } catch (NullPointerException e) {}
        finally {
            server.terminatehub(this);
            this.controller=new ControllerG(this,timer_window,timer_t);
        }
    }

    /**
     * DisconnectionThread called this method to verify if client on list is alive,
     * in other case it remove it or set to player boolean disconnected to true
     * @throws RemoteException called when connection is lost
     */
    public void connection_verify() throws RemoteException {
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
                        thread.cancel();
                        j = controller.match.getPlayers().indexOf(p);
                        notify(listofobserver.get(j),Constants.WINNER);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {}
                        //notify(listofobserver.get(j), Colour.RED.escape()+"Hai vinto, non ci sono altri giocatori connessi"+Colour.RESET);
                        notify(listofobserver.get(j),"disconnettiti");
                        System.out.println(p.getNickname()+ " ha vinto dato che non ci sono altri giocatori connessi");
                        List<String> list=controller.match.getPlayers().stream().map(Player::getNickname).collect(Collectors.toList());
                        for (String name: list) {
                            server.getMatches().remove(name);
                        }
                        controller.match.setRound(11);
                        terminateHub();
                        return;
                    }
            }
        }
        for (ClientInt c:listofobserver){
            try {
                if((start && controller.match.getPlayers().get(i).isConnected()) || !start)
                    c.connected();
            }catch (RemoteException e){
                if(!this.start) {
                    System.out.println(room.getPlayers().get(i).getNickname()+" disconnesso");
                    notifyOthers(c,"Left");
                    notifyOthers(c,room.getPlayers().get(i).getNickname()+" disconnesso");
                    room.getPlayers().remove(i);
                    removeObserver(c);
                }
                else{
                    if(controller.match.getPlayers().get(i).isConnected()) {
                        notifyOthers(c, Constants.DISCONNECTED);
                        notifyOthers(c,controller.match.getPlayers().get(i).getNickname());
                        controller.match.getPlayers().get(i).setConnected(false);
                        System.out.println(controller.match.getPlayers().get(i).getNickname() + " disconnesso");
                    }
                }
            }
            i++;
        }
    }
}
