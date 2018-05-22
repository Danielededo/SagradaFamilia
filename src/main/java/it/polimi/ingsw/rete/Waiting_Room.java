package it.polimi.ingsw.rete;

import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Waiting_Room {
    private ArrayList<Player> players;
    private Server server;
    private Match match;


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Waiting_Room(Server server) {
        this.server=server;
        this.match=server.match;
        players=new ArrayList<Player>();
    }

    public int numberPlayers() {
        return players.size();
    }

    public void addPlayer(String player) throws RemoteException {
        if (status_verify()) {
            Player player1=new Player(player);
            players.add(player1);
            if (this.players.size()==2)
            attesa_partita();
        }else System.out.println("Max giocatori");
    }

    public void deleteplayer(String player) {
        for (Player p:players){
            if(p.getNickname().equals(player)){
                players.remove(p);
                return;
            }
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

    public void attesa_partita(){
        final int time=61;
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int n = 1;
            @Override
            public void run() {
                System.out.println(n);
                if (++n == time) {
                    timer.cancel();
                    try {
                        server.controll();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    if(players.size()==1){
                        try {
                            server.notifyObserver("Wait before a player join");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }else if (players.size()==2){
                        match=new Match(players.get(0),players.get(1));
                        try {
                            server.setMatch(match);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }else if (players.size()==3) {
                        match=new Match(players.get(0),players.get(1),players.get(2));
                        try {
                            server.setMatch(match);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }else if (players.size()==4){
                        match=new Match(players.get(0),players.get(1),players.get(2),players.get(3));
                        try {
                            server.setMatch(match);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },1000,1000);
    }
}
