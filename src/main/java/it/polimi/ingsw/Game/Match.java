package it.polimi.ingsw.Game;

import it.polimi.ingsw.Cards.PublicObject;

import java.util.ArrayList;

public class Match {
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<PublicObject> publictarget=new ArrayList<PublicObject>();
    private int round=0;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getnumberPlayers() {
        return players.size();
    }

    private void addPlayer(Player player){
        this.players.add(player);
    }

    public Match(Player a, Player b) {
        addPlayer(a);
        addPlayer(b);
    }

    public Match(Player a, Player b, Player c) {
        addPlayer(a);
        addPlayer(b);
        addPlayer(c);
    }

    public Match(Player a, Player b,Player c,Player d) {
        addPlayer(a);
        addPlayer(b);
        addPlayer(c);
        addPlayer(d);
    }

    public void changePlayer(){
        players.add(players.get(0));
        players.remove(0);
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }
}
