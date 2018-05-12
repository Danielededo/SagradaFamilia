package it.polimi.ingsw.Game;

import it.polimi.ingsw.Cards.PrivObj;
import it.polimi.ingsw.Cards.PubObj;
import it.polimi.ingsw.Cards.PublicObject;
import it.polimi.ingsw.Cards.Scheme;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Dice.Sack;

import java.util.ArrayList;

public class Match {
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<PublicObject> publictarget=new ArrayList<PublicObject>();
    private Stock stock= new Stock();
    private Sack sack= new Sack();
    private Scheme scheme = new Scheme();
    private PubObj pubObj = new PubObj();
    private PrivObj privObj= new PrivObj();
    private Rules rules = new Rules();
    private ArrayList<Die> roundTrack= new ArrayList<Die>();
    private int round=1;

    public void partita(Match match){
        while(round!=11){
            Round round= new Round(match);
            for(int i=0; i<2*getnumberPlayers();i++){
                round.getTurns().get(i).Hand(match);
            }
            fineRound();
        }
        fineMatch();
    }

    public Rules getRules() {
        return rules;
    }


    public void fineRound(){
        this.roundTrack.set(this.round-1,getStock().extract_die(0));
        setRound(this.round +1);
        getStock().reset_stock();
        if(this.round!=11)
            changePlayer();
    }

    public void setPlayerswindow(){
        for(int i=0; i<getnumberPlayers();i++){
            this.players.get(i).setWindow(this.scheme.extractGlass());
        }
    }
    public Scheme getScheme() {
        return scheme;
    }

    public void fineMatch(){
        calculatescore();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Sack getSack() {
        return sack;
    }

    public Stock getStock() {
        return stock;
    }

    public int getnumberPlayers() {
        return players.size();
    }

    private void addPlayer(Player player){
        this.players.add(player);
    }

    public void setPublictarget() {
        this.publictarget = this.pubObj.extractPubObj();
    }

    public void setPrivateObject(){
        for(int i=0;i<getnumberPlayers();i++){
            this.players.get(i).setPrivatetarget(this.privObj.extractPrivObj());
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

    public ArrayList<Die> getRoundTrack() {
        return roundTrack;
    }

    @Override
    public String toString() {
        return "Match{" +
                "players=" + players +
                ", publictarget=" + publictarget +
                ", stock=" + stock +
                ", sack=" + sack +
                ", roundTrack=" + roundTrack +
                ", round=" + round +
                '}';
    }
}
