package it.polimi.ingsw.rete;

import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;

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
        players=new ArrayList<Player>(0);
    }

    public int numberPlayers() {
        return players.size();
    }

    public void addPlayer(String player) {
        if (status_verify()) {
            Player player1=new Player(player);
            players.add(player1);
            if (this.players.size()==2)
                attesa_partita();
        }else System.out.println("Max giocatori");
    }

    public void deleteplayer(String player) {
        players.remove(player);
    }


    public boolean status_verify(){
        if (players.size()<4) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Waiting_Room{" +
                "players=" + players +
                '}';
    }

    public void attesa_partita(){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int n = 1;
            @Override
            public void run() {
                System.out.println(n);
                if (++n == 61) {
                    timer.cancel();
                    if (players.size()<2){
                        System.out.println("A player has been disconnected wait until another connect himself");
                        attesa_partita();
                    }
                    if (players.size()==2){
                        match=new Match(players.get(0),players.get(1));
                        server.setMatch(match);
                    }else if (players.size()==3) {
                        match=new Match(players.get(0),players.get(1),players.get(2));
                        server.setMatch(match);
                    }else if (players.size()==4){
                        match=new Match(players.get(0),players.get(1),players.get(2),players.get(3));
                        server.setMatch(match);
                    }
                }
            }
        },1000,1000);
    }
}
