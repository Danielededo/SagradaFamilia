package Game;

import Cards.PublicObject;

import java.util.ArrayList;

public class Match {
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<PublicObject> publictarget=new ArrayList<PublicObject>();

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
}
