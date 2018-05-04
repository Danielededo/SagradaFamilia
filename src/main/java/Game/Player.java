package Game;

import Cards.GlassWindow;
import Cards.PrivateObject;

public class Player {
    private String nickname;
    private PrivateObject privatetarget;
    private GlassWindow window;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPrivatetarget(PrivateObject privatetarget) {
        this.privatetarget = privatetarget;
    }

    public void setWindow(GlassWindow window) {
        this.window = window;
    }

    public Player(String nickname) {
        this.nickname = nickname;
    }
}
