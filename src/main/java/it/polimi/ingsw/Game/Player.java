package it.polimi.ingsw.Game;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.PrivateObject;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String nickname;
    private PrivateObject privatetarget;
    private GlassWindow window;
    private int score;
    private int marker;
    private int contTurn;
    private boolean missednext_turn=false;

    public boolean isMissednext_turn() {
        return missednext_turn;
    }

    public void setMissednext_turn(boolean missednext_turn) {
        this.missednext_turn = missednext_turn;
    }

    public void setMarker(int marker) {
        this.marker = marker;
    }

    public int getMarker() {

        return marker;
    }

    public int getContTurn() {
        return contTurn;
    }

    public void setContTurn(int contTurn) {
        this.contTurn = contTurn;
    }

    public void setPrivatetarget(PrivateObject privatetarget) {
        this.privatetarget = privatetarget;
    }

    public void setWindow(GlassWindow window){
        this.window=window;
        this.marker=window.getDifficulty();
    }

    public void setWindow(ArrayList<GlassWindow> window) {
        Random random=new Random();
        int i=random.nextInt(window.size());
        this.window = window.get(i);
        this.marker=window.get(i).getDifficulty();
    }

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public PrivateObject getPrivatetarget() {
        return privatetarget;
    }

    public GlassWindow getWindow() {
        return window;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", privatetarget=" + privatetarget +
                ", window=" + window +
                ", score=" + score +
                ", marker=" + marker +
                '}';
    }
}
