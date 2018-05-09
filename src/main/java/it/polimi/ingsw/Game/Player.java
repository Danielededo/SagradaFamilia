package it.polimi.ingsw.Game;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.PrivateObject;

public class Player {
    private String nickname;
    private PrivateObject privatetarget;
    private GlassWindow window;
    private int score;
    private int marker;


    public void setPrivatetarget(PrivateObject privatetarget) {
        this.privatetarget = privatetarget;
    }

    public void setWindow(GlassWindow window) {
        this.window = window;
        this.marker=window.getDifficulty();
        this.marker=window.getDifficulty();
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
}
