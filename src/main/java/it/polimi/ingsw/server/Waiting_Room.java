package it.polimi.ingsw.server;

import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Waiting_Room {
    private ArrayList<Player> players;
    private Hub server;
    private Match match;
    private Controller c;
    private static int timer_waiting;


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Waiting_Room(Hub server, Controller controller,int timer_waiting) {
        this.server=server;
        this.c = controller;
        this.timer_waiting=timer_waiting;
        players=new ArrayList<Player>();
    }

    public void addPlayer(String player) throws RemoteException, InterruptedException {
        if (status_verify()) {
            Player player1=new Player(player);
            players.add(player1);
        }
    }

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

    public void attesa_partita() throws InterruptedException, RemoteException {
        for (int i=timer_waiting;i>0;i--){
            Thread.sleep(500);
            server.notifyObserver("Timer");
            Thread.sleep(500);
            if (players.size()>1&&players.size()<4){
            server.notifyObserver(""+i);
            System.out.print("\r"+i);
            }else {
                i=0;
            }
        }
        server.notifyObserver("Timer stop");
        System.out.print("\r");
        if(players.size()==1){
            try {
                server.notifyObserver("Solo");
                //server.notifyObserver("Attendi che uno o più giocatori partecipino alla partita");
            } catch (RemoteException e) {}
        }else if(players.size()==2){
            c.match=new Match(players.get(0),players.get(1));
            server.getSetupGame().cancel();
            server.t.cancel();
            server.setStart(true);
            try {
                c.setMatch();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (players.size()==3) {
            c.match=new Match(players.get(0),players.get(1),players.get(2));
            server.getSetupGame().cancel();
            server.setStart(true);
            try {
                c.setMatch();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (players.size()==4){
            c.match=new Match(players.get(0),players.get(1),players.get(2),players.get(3));
            server.getSetupGame().cancel();
            server.setStart(true);
            try {
                c.setMatch();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
