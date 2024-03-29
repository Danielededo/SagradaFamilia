package it.polimi.ingsw.server;

import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Waiting_Room {
    private ArrayList<Player> players;
    private Hub hub;
    private Match match;
    private ControllerG c;
    private static int timer_waiting;


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Waiting_Room(Hub hub, ControllerG controller, int timer_waiting) {
        this.hub = hub;
        this.c = controller;
        this.timer_waiting=timer_waiting;
        players=new ArrayList<Player>();
    }

    /**
     * Hub call this method to add a player to game connected by name to client
     * @param player is the name of current player that try to connect
     * @throws RemoteException
     * @throws InterruptedException
     */
    public void addPlayer(String player) throws RemoteException, InterruptedException {
        if (status_verify()) {
            Player player1=new Player(player);
            players.add(player1);
        }
    }

    /**
     * @return If there is less then four players return true, false in other case
     */
    public boolean status_verify(){
        if (players.size()<4) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Waiting_Room{" +
                "players=" + players+
                '}';
    }

    /**
     * Is used to put in stand-by client until match begun
     * @throws InterruptedException
     * @throws RemoteException called when connection is lost
     */
    public void waiting_match() throws Exception {
        for (int i=timer_waiting;i>0;i--){
            Thread.sleep(500);
            hub.notifyObserver("Timer");
            hub.notifyObserver(i+"");
            Thread.sleep(500);
            System.out.print("\r"+i);
            if (players.size()<=1 || players.size()==4){
                i=0;
            }
        }
        hub.notifyObserver("Timer stop");
        System.out.print("\r");
        if(players.size()==1){
            try {
                hub.notifyObserver("Solo");
                //hub.notifyObserver("Attendi che uno o più giocatori partecipino alla partita");
            } catch (RemoteException e) {}
        }else if(players.size()==2){
            c.match=new Match(players.get(0),players.get(1));
            hub.getSetupGame().cancel();
            hub.t.cancel();
            hub.start=true;
            try {
                c.setMatch();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (players.size()==3) {
            c.match=new Match(players.get(0),players.get(1),players.get(2));
            hub.getSetupGame().cancel();
            hub.t.cancel();
            hub.start=true;
            try {
                c.setMatch();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (players.size()==4){
            c.match=new Match(players.get(0),players.get(1),players.get(2),players.get(3));
            hub.getSetupGame().cancel();
            hub.t.cancel();
            hub.start=true;
            try {
                c.setMatch();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
