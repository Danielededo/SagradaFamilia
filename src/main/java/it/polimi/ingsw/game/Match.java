package it.polimi.ingsw.game;

import it.polimi.ingsw.cards.*;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.dice.Sack;

import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<Die>[] roundTrack=new ArrayList[10];
    private int round=1;

    public String toolcardsString(){
        String string="\n";
        int i=1;
        for (Tool t:toolcards){
            string+=i+"- "+t.toString();
            i++;
        }
        return string;
    }

    public String publictargetString(){
        String string="\n";
        int i=1;
        for (PublicObject t:publictarget){
            string+=i+"- "+t.toString();
            i++;
        }
        return string;
    }

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

    public Rules getRules() {
        return rules;
    }

    public String getGlassWindowPlayers(){
        String a="\n";
        for(int i=0; i<getnumberPlayers();i++){
            a +=getPlayers().get(i).getNickname()+"'s scheme card is "+getPlayers().get(i).getWindow().toString()+"\n";
        }
        return a;
    }

    public void fineRound(){
        ArrayList<Die> die=new ArrayList<Die>();
        die.addAll(getStock().getDicestock());
        setRoundTrack(die,round-1);
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

    public String ranking(){
        String escape= Colour.RED.escape();
        ArrayList<Player> playerArrayList=new ArrayList<Player>();
        for(Player p:players)
            playerArrayList.add(p);
        Collections.sort(players,(player1,player2)->{
            if(player1.getScore()>player2.getScore())
                return -1;
            else return 1;
        });
        if(players.get(0).getScore()==players.get(1).getScore()){
            if(getnumberPlayers()>=3 && players.get(1).getScore()==players.get(2).getScore()){
                if(getnumberPlayers()==4 && players.get(2).getScore()==players.get(3).getScore()){
                    sortPrivateScore(4,playerArrayList);
                }else
                    sortPrivateScore(3,playerArrayList);
            }else
                sortPrivateScore(2,playerArrayList);
        }
        String ranking="\nTHE FINAL RANKING IS:\n";
        for(Player p:players){
            if(players.indexOf(p)==0)
                ranking+=escape+"1° place: "+p.getNickname()+", score: "+p.getScore()+Colour.RESET+"\n";
            else
                ranking+=players.indexOf(p)+1+"° place: "+p.getNickname()+", score: "+p.getScore()+"\n";
        }
        return ranking;
    }

    public void sortPrivateScore(int num,ArrayList<Player> p){
        if(num==2){
            if(players.get(0).getPrivatetarget().calculate_score(players.get(0))==players.get(1).getPrivatetarget().calculate_score(players.get(1)))
                sortMarkerScore(2,p);
            if(players.get(0).getPrivatetarget().calculate_score(players.get(0))<players.get(1).getPrivatetarget().calculate_score(players.get(1)))
                Collections.swap(players,0,1);
        }
        if(num==4){
            Collections.sort(players,(player1,player2)->{
                if(player1.getPrivatetarget().calculate_score(player1)>player2.getPrivatetarget().calculate_score(player2))
                    return -1;
                else
                    return 1;
            });
            if(players.get(0).getPrivatetarget().calculate_score(players.get(0))==players.get(1).getPrivatetarget().calculate_score(players.get(1))){
                if(players.get(1).getPrivatetarget().calculate_score(players.get(1))==players.get(2).getPrivatetarget().calculate_score(players.get(2))){
                    if(players.get(2).getPrivatetarget().calculate_score(players.get(2))==players.get(3).getPrivatetarget().calculate_score(players.get(3))){
                        sortMarkerScore(4,p);
                    }else
                        sortMarkerScore(3,p);
                }else
                    sortMarkerScore(2,p);
            }
        }
        if(num==3){
            if(getnumberPlayers()==3) {
                Collections.sort(players, (player1, player2) -> {
                    if (player1.getPrivatetarget().calculate_score(player1) > player2.getPrivatetarget().calculate_score(player2))
                        return -1;
                    else
                        return 1;
                });
                if(players.get(0).getPrivatetarget().calculate_score(players.get(0))==players.get(1).getPrivatetarget().calculate_score(players.get(1))){
                    if(players.get(1).getPrivatetarget().calculate_score(players.get(1))==players.get(2).getPrivatetarget().calculate_score(players.get(2))) {
                        sortMarkerScore(3,p);
                    }
                    else{
                        sortMarkerScore(2,p);
                    }
                }
            }else{
                ArrayList<Player> arrayList=new ArrayList<Player>();
                arrayList.add(players.get(3));
                players.remove(3);
                Collections.sort(players, (player1, player2) -> {
                    if (player1.getPrivatetarget().calculate_score(player1) > player2.getPrivatetarget().calculate_score(player2))
                        return -1;
                    else
                        return 1;
                });
                players.add(arrayList.get(0));
                if(players.get(0).getPrivatetarget().calculate_score(players.get(0))==players.get(1).getPrivatetarget().calculate_score(players.get(1))) {
                    if (players.get(1).getPrivatetarget().calculate_score(players.get(1)) == players.get(2).getPrivatetarget().calculate_score(players.get(2))) {
                        sortMarkerScore(3,p);
                    } else {
                        sortMarkerScore(2,p);
                    }
                }
            }
        }
    }

    public void sortMarkerScore(int i,ArrayList<Player> p){
        if(i==2){
            if(players.get(0).getMarker()<players.get(1).getMarker())
                Collections.swap(players,0,1);
            if(players.get(0).getMarker()==players.get(1).getMarker())
                sortOrderRound(2,p);
        }
        if(i==4){
            Collections.sort(players,(player1,player2)->{
                if(player1.getMarker()>player2.getMarker())
                    return -1;
                else
                    return 1;
            });
            if(players.get(0).getMarker()==players.get(1).getMarker()){
                if(players.get(1).getMarker()==players.get(2).getMarker()){
                    if(players.get(2).getMarker()==players.get(3).getMarker()){
                        sortOrderRound(4,p);
                    }else
                        sortOrderRound(3,p);
                }else
                    sortOrderRound(2,p);
            }
        }
        if(i==3){
            if(getnumberPlayers()==3) {
                Collections.sort(players, (player1, player2) -> {
                    if (player1.getMarker() > player2.getMarker())
                        return -1;
                    else
                        return 1;
                });
                if(players.get(0).getMarker()==players.get(1).getMarker()){
                    if(players.get(1).getMarker()==players.get(2).getMarker()) {
                        sortOrderRound(3,p);
                    }
                    else{
                        sortOrderRound(2,p);
                    }
                }
            }else{
                ArrayList<Player> arrayList=new ArrayList<Player>();
                arrayList.add(players.get(3));
                players.remove(3);
                Collections.sort(players, (player1, player2) -> {
                    if (player1.getMarker() > player2.getMarker())
                        return -1;
                    else
                        return 1;
                });
                players.add(arrayList.get(0));
                arrayList.clear();
                if(players.get(0).getMarker()==players.get(1).getMarker()){
                    if (players.get(1).getMarker() == players.get(2).getMarker()) {
                        sortOrderRound(3,p);
                    } else {
                        sortOrderRound(2,p);
                    }
                }
            }
        }
    }

    public void sortOrderRound(int j,ArrayList<Player> p){
        if(j==2){
                players.clear();
                players.addAll(p);
                Collections.swap(players,0,1);
        }
        if (j==3){
            if(getnumberPlayers()==3){
                players.clear();
                players.addAll(p);
                Collections.swap(players,0,2);
            }else{
                ArrayList<Player> players1=new ArrayList<Player>();
                players1.add(p.get(p.indexOf(players.get(3))));
                p.remove(players1.get(0));
                players.clear();
                players.addAll(p);
                Collections.swap(players,0,2);
                players.add(players1.get(0));
            }
        }
        if(j==4){
            players.clear();
            players.addAll(p);
            Collections.swap(players,0,3);
            Collections.swap(players,1,2);
        }
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

    public ArrayList<Die>[] getRoundTrack() {
        return roundTrack;
    }

    public ArrayList<Die> getRoundTrackList(int list){
        return roundTrack[list];
    }

    public void setRoundTrack(ArrayList<Die> roundTrack, int round) {
        this.roundTrack[round] = roundTrack;
    }

    public String toStringRoundTrack() {
        String string = "Tracciato dei round:\n";
        for (int i = 0; i <getRound()-1; i++) {
            string += (i+1)+ ". ";
            for (int j=0;j<getRoundTrackList(i).size();j++) {
                string += "("+j+" - "+getRoundTrackList(i).get(j).toString() + ") ";
            }
            string += "\n";
        }
        return string;
    }

    private void cardAssignment(){
        setPrivateObject();
        setPublictarget();
        setTool();
    }
}
