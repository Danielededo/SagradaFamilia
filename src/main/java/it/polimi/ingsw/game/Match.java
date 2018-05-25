package it.polimi.ingsw.game;

import it.polimi.ingsw.cards.*;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.dice.Sack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class Match {
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<PublicObject> publictarget=new ArrayList<PublicObject>();
    private ArrayList<Tool> toolcards=new ArrayList<Tool>();
    private Stock stock= new Stock();
    private Sack sack= new Sack();
    private Scheme scheme = new Scheme();
    private PubObj pubObj = new PubObj();
    private PrivObj privObj= new PrivObj();
    private ToolCards tool= new ToolCards();
    private Rules rules = new Rules();
    private ArrayList<Die> roundTrack= new ArrayList<Die>();
    private int round=1;

    @Override
    public String toString() {
        return "Match{\n" +
                "players= " + players +
                ",\npublictarget= " + publictarget +
                ",\ntoolcards= " + toolcards +
                ",\nstock= " + stock +
                ", roundTrack= " + roundTrack +
                ", round= "+ round+
                '}'+"\n";
    }

    public void partita(Match match){
        System.out.println(match.toString());
        while(round!=11){
            Round round= new Round(match);
            for(int i=0; i<2*getnumberPlayers();i++){
                round.getTurns().get(i).doTurn(match,round,i);

            }
            fineRound();
        }
        fineMatch();
    }

    public Rules getRules() {
        return rules;
    }

    public String getGlassWindowPlayers(){
        String a="\n";
        for(int i=0; i<getnumberPlayers();i++){
            a +=getPlayers().get(i).getNickname()+"'s "+getPlayers().get(i).getWindow().toString()+"\n";
        }
        return a;
    }

    public void fineRound(){
        //this.roundTrack.set(this.round-1,getStock().extract_die(0));
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

    public String classifica(){
        String classifica="";
        String escape= Colour.RED.escape();
        Collections.sort(players, Comparator.comparingInt(Player::getScore));
        for(Player p:players){
            if(players.indexOf(p)==0)
                classifica+=escape+players.indexOf(p)+1+"° classificato: "+p.getNickname()+Colour.RESET+"\n";
            else
                classifica+=players.indexOf(p)+1+"° classificato: "+p.getNickname()+"\n";
        }
        return classifica;
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


    public void setTool() {
        int i = 0;
        int a=-1;
        ToolCards t = new ToolCards();
        do {
            Random numero = new Random();
            Tool inserire = t.creatingTool((numero.nextInt(12) + 1));
            if (!getNamesTool().contains(inserire.getName())) {
                toolcards.add(inserire);
            } else {
                i--;
            }
            i++;
        } while (i < 3);
    }

    public ArrayList<String> getNamesTool(){
        ArrayList<String> names=new ArrayList<String>();
        for(Tool t:toolcards)
            names.add(t.getName());
        return names;
    }

    public void setPrivateObject(){
        for(int i=0;i<getnumberPlayers();i++){
            this.players.get(i).setPrivatetarget(this.privObj.extractPrivObj());
        }
    }

    public Match(Player a, Player b) {
        addPlayer(a);
        addPlayer(b);
        cardAssignment();
    }

    public Match(Player a, Player b, Player c) {
        addPlayer(a);
        addPlayer(b);
        addPlayer(c);
        cardAssignment();
    }

    public Match(Player a, Player b,Player c,Player d) {
        addPlayer(a);
        addPlayer(b);
        addPlayer(c);
        addPlayer(d);
        cardAssignment();
    }

    private void changePlayer(){
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

    public ArrayList<Tool> getTool() { return toolcards; }

    private void calculatescore(){
        for(int i=0; i<getnumberPlayers(); i++){
           players.get(i).setScore(players.get(i).getPrivatetarget().calculate_score(players.get(i)));
           players.get(i).setScore(publictarget.get(0).calcola_punteggio(players.get(i)));
           players.get(i).setScore(publictarget.get(1).calcola_punteggio(players.get(i)));
           players.get(i).setScore(publictarget.get(2).calcola_punteggio(players.get(i)));
           players.get(i).setScore(players.get(i).getMarker());
           players.get(i).setScore(players.get(i).getWindow().calculateEmptySlot());
        }
    }

    public ArrayList<Die> getRoundTrack() {
        return roundTrack;
    }

    public void setRoundTrack(ArrayList<Die> roundTrack) {
        this.roundTrack = roundTrack;
    }

    private void cardAssignment(){
        setPrivateObject();
        setPublictarget();
        setTool();
    }
}
