package it.polimi.ingsw.server.model.game;

import it.polimi.ingsw.server.model.cards.*;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.dice.Sack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Match {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<PublicObject> publictarget=new ArrayList<>();
    private ArrayList<Tool> toolcards=new ArrayList<>();
    private Stock stock= new Stock();
    private Sack sack= new Sack();
    private Scheme scheme = new Scheme();
    private PubObj pubObj = new PubObj();
    private PrivObj privObj= new PrivObj();
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
        return "Partita{\n" +
                "giocatori= " + players +
                ",\nObiettivo pubblico= " + publictarget +
                ",\nCarte Utensile= " + toolcards +
                ",\nRiserva= " + stock +
                ", Tracciato dei round= " + roundTrack +
                ", round= "+ round+
                '}'+"\n";
    }

    public Rules getRules() {
        return rules;
    }

    public String getGlassWindowPlayers(){
        String a="\n";
        for(int i=0; i<getnumberPlayers();i++){
            a +="La carta schema di "+getPlayers().get(i).getNickname()+" è "+getPlayers().get(i).getWindow().toString()+"\n";
        }
        return a;
    }

    /**
     * This method is used when int 'round' is different to 11, so when the match is in progress. It adds all remained dice from the stock
     * to 'rooundtrack' through method setRoundTrack; increases one int 'round' and clears the stock. Moreover calls method changePlayer
     * that moves player of first position in the arraylist 'players' of the Match to the last position.
     */
    public void endRound(){
        if(getRound()!=11){
            ArrayList<Die> die=new ArrayList<Die>();
            die.addAll(getStock().getDicestock());
            setRoundTrack(die,round-1);
            setRound(this.round +1);
            getStock().reset_stock();
            changePlayer();
        }
    }

    public void setPlayerswindow(){
        for(int i=0; i<getnumberPlayers();i++){
            this.players.get(i).setWindow(this.scheme.extractGlass());
        }
    }
    public Scheme getScheme() {
        return scheme;
    }

    public void endMatch(){
        calculatescore();
    }

    public String ranking(){
        String escape= Colour.RED.escape();
        ArrayList<Player> playerArrayList=new ArrayList<Player>();
        playerArrayList.addAll(players);
        Collections.sort(playerArrayList,(player1,player2)->{
            if(player1.getScore()>player2.getScore())
                return -1;
            else return 1;
        });
        if(playerArrayList.get(0).getScore()==playerArrayList.get(1).getScore()){
            if(getnumberPlayers()>=3 && playerArrayList.get(1).getScore()==playerArrayList.get(2).getScore()){
                if(getnumberPlayers()==4 && playerArrayList.get(2).getScore()==playerArrayList.get(3).getScore()){
                    sortPrivateScore(4,playerArrayList);
                }else
                    sortPrivateScore(3,playerArrayList);
            }else
                sortPrivateScore(2,playerArrayList);
        }
        String ranking="\nLA CLASSIFICA FINALE è:\n";
        for(Player p:playerArrayList){
            if(playerArrayList.indexOf(p)==0)
                ranking+=escape+"1° posto: "+p.getNickname()+", punteggio: "+p.getScore()+Colour.RESET+"\n";
            else
                ranking+=playerArrayList.indexOf(p)+1+"° posto: "+p.getNickname()+", punteggio: "+p.getScore()+"\n";
        }
        return ranking;
    }

    public void sortPrivateScore(int num,ArrayList<Player> p){
        if(num==2){
            if(p.get(0).getPrivatetarget().calculate_score(p.get(0))==p.get(1).getPrivatetarget().calculate_score(p.get(1)))
                sortMarkerScore(2,p);
            if(p.get(0).getPrivatetarget().calculate_score(p.get(0))<p.get(1).getPrivatetarget().calculate_score(p.get(1)))
                Collections.swap(p,0,1);
        }
        if(num==4){
            Collections.sort(p,(player1,player2)->{
                if(player1.getPrivatetarget().calculate_score(player1)>player2.getPrivatetarget().calculate_score(player2))
                    return -1;
                else
                    return 1;
            });
            if(p.get(0).getPrivatetarget().calculate_score(p.get(0))==p.get(1).getPrivatetarget().calculate_score(p.get(1))){
                if(p.get(1).getPrivatetarget().calculate_score(p.get(1))==p.get(2).getPrivatetarget().calculate_score(p.get(2))){
                    if(p.get(2).getPrivatetarget().calculate_score(p.get(2))==p.get(3).getPrivatetarget().calculate_score(p.get(3))){
                        sortMarkerScore(4,p);
                    }else
                        sortMarkerScore(3,p);
                }else
                    sortMarkerScore(2,p);
            }
        }
        if(num==3){
            if(getnumberPlayers()==3) {
                Collections.sort(p, (player1, player2) -> {
                    if (player1.getPrivatetarget().calculate_score(player1) > player2.getPrivatetarget().calculate_score(player2))
                        return -1;
                    else
                        return 1;
                });
                if(p.get(0).getPrivatetarget().calculate_score(p.get(0))==p.get(1).getPrivatetarget().calculate_score(p.get(1))){
                    if(p.get(1).getPrivatetarget().calculate_score(p.get(1))==p.get(2).getPrivatetarget().calculate_score(p.get(2))) {
                        sortMarkerScore(3,p);
                    }
                    else{
                        sortMarkerScore(2,p);
                    }
                }
            }else{
                ArrayList<Player> arrayList=new ArrayList<Player>();
                arrayList.add(p.get(3));
                p.remove(3);
                Collections.sort(p, (player1, player2) -> {
                    if (player1.getPrivatetarget().calculate_score(player1) > player2.getPrivatetarget().calculate_score(player2))
                        return -1;
                    else
                        return 1;
                });
                p.add(arrayList.get(0));
                if(p.get(0).getPrivatetarget().calculate_score(p.get(0))==p.get(1).getPrivatetarget().calculate_score(p.get(1))) {
                    if (p.get(1).getPrivatetarget().calculate_score(p.get(1)) == p.get(2).getPrivatetarget().calculate_score(p.get(2))) {
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
            if(p.get(0).getMarker()<p.get(1).getMarker())
                Collections.swap(p,0,1);
            if(p.get(0).getMarker()==p.get(1).getMarker())
                sortOrderRound(2,p);
        }
        if(i==4){
            Collections.sort(p,(player1,player2)->{
                if(player1.getMarker()>player2.getMarker())
                    return -1;
                else
                    return 1;
            });
            if(p.get(0).getMarker()==p.get(1).getMarker()){
                if(p.get(1).getMarker()==p.get(2).getMarker()){
                    if(p.get(2).getMarker()==p.get(3).getMarker()){
                        sortOrderRound(4,p);
                    }else
                        sortOrderRound(3,p);
                }else
                    sortOrderRound(2,p);
            }
        }
        if(i==3){
            if(getnumberPlayers()==3) {
                Collections.sort(p, (player1, player2) -> {
                    if (player1.getMarker() > player2.getMarker())
                        return -1;
                    else
                        return 1;
                });
                if(p.get(0).getMarker()==p.get(1).getMarker()){
                    if(p.get(1).getMarker()==p.get(2).getMarker()) {
                        sortOrderRound(3,p);
                    }
                    else{
                        sortOrderRound(2,p);
                    }
                }
            }else{
                ArrayList<Player> arrayList=new ArrayList<Player>();
                arrayList.add(p.get(3));
                p.remove(3);
                Collections.sort(p, (player1, player2) -> {
                    if (player1.getMarker() > player2.getMarker())
                        return -1;
                    else
                        return 1;
                });
                p.add(arrayList.get(0));
                arrayList.clear();
                if(p.get(0).getMarker()==p.get(1).getMarker()){
                    if (p.get(1).getMarker() == p.get(2).getMarker()) {
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
                Collections.swap(p,0,1);
        }
        if (j==3){
            if(getnumberPlayers()==3){
                Collections.swap(p,0,2);
            }else{
                ArrayList<Player> players1=new ArrayList<Player>();
                players1.add(players.get(players.indexOf(p.get(3))));
                p.remove(players1.get(0));
                Collections.swap(p,0,2);
                p.add(players1.get(0));
            }
        }
        if(j==4){
            Collections.swap(p,0,3);
            Collections.swap(p,1,2);
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


    /**
     * This method extracts 3 tool cards and assign them to Match
     */
    private void setTool() {
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

    private ArrayList<String> getNamesTool(){
        ArrayList<String> names=new ArrayList<String>();
        for(Tool t:toolcards)
            names.add(t.getName());
        return names;
    }

    /**
     * This method assign casually a private card to each player in arraylist 'players'
     */
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

    /**
     * Moves the player in first position of arraylist 'players' to last position
     */
    private void changePlayer(){
        //players.add(players.get(0));
        //players.remove(0);
        Collections.rotate(players,-1);
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

    /**
     * This method sets for each player in arraylist 'players' the score reached up during the match, calculate through sum of
     * scores of private card, public cards, favor tokens and empty slot.
     */
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

    /**
     * This method calls the methods that setup match's cards
     */
    private void cardAssignment(){
        setPrivateObject();
        setPublictarget();
        setTool();
    }
}
