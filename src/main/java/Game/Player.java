package Game;

import Cards.GlassWindow;
import Cards.PrivateObject;

public class Player {
    private String nickname;
    private PrivateObject privatetarget;
    private GlassWindow window;
    private int marker;


    public void setPrivatetarget(PrivateObject privatetarget) {
        this.privatetarget = privatetarget;
    }

    public void setWindow(GlassWindow window) {
        this.window = window;
        this.marker=window.getDifficulty();
    }

    public Player(String nickname) {
        this.nickname = nickname;
    }


    public GlassWindow getWindow() {
        return window;
    }
}
