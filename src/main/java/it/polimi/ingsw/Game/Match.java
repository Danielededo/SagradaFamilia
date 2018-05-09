package it.polimi.ingsw.Game;

import it.polimi.ingsw.Cards.PrivObj;
import it.polimi.ingsw.Cards.PubObj;
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

    public void setPublictarget(PubObj pubObj) {
        this.publictarget = pubObj.extractPubObj();
    }

    public void setPrivateObject(PrivObj privObj){
        for(int i=0;i<getnumberPlayers();i++){
            players.get(i).setPrivatetarget(privObj.extractPrivObj());
        }
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

    public ArrayList<PublicObject> getPublictarget() {
        return publictarget;
    }

    public void calculatescore(){
        for(int i=0; i<getnumberPlayers(); i++){
           players.get(i).setScore(players.get(i).getPrivatetarget().calculate_score(players.get(i)));
           players.get(i).setScore(publictarget.get(0).calcola_punteggio(players.get(i)));
           players.get(i).setScore(publictarget.get(1).calcola_punteggio(players.get(i)));
           players.get(i).setScore(publictarget.get(2).calcola_punteggio(players.get(i)));
        }
    }

    @Override
    public String toString() {
        return "Match{" +
                "players=" + players +
                ", publictarget=" + publictarget +
                '}';
    }
}
