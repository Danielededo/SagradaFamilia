package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Game.Player;

public class Tool {
    private boolean accessed=false;
    private boolean used=false;  //for the payment of the favor token, if use==true the payment has already happened
    private String name;
    private String effect;
    private Player player;

    public void setAccessed(boolean accessed) {
        this.accessed = accessed;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public boolean isAccessed() {
        return accessed;
    }

    public String getEffect() {
        return effect;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {

        return used;
    }
}
