package it.polimi.ingsw.cards;

import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Stock;

public abstract class Tool extends Card{
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

    public abstract boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value);



    @Override
    public String toString() {
        return "Tool{" +
                "name= " + name + '\'' +
                ", effect= " + effect + '\'' +
                ", accessed= " + accessed +
                '}'+'\n';
    }
}
