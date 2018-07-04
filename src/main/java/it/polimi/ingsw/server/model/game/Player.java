package it.polimi.ingsw.server.model.game;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.PrivateObject;

import java.util.List;
import java.util.Random;

public class Player {
    private String nickname;
    private PrivateObject privatetarget;
    private GlassWindow window;
    private int score;
    private int marker;
    private int contTurn;
    private boolean missednextturn =false;
    private boolean connected=true;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public int getScore() {
        return score;
    }

    public boolean isMissednextturn() {
        return missednextturn;
    }

    public void setMissednextturn(boolean missednextturn) {
        this.missednextturn = missednextturn;
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

    public void setWindow(List<GlassWindow> window) {
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
        return  "obiettivo privato: " + privatetarget +
                "\nsegnalini favore: " + marker+
                "\n" + window;

    }

    public String toStringpublic() {
        return  nickname +
                "\nsegnalini favore: " + marker+" "+
                window;

    }
}
