package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.game.Player;

public abstract class PublicObject extends Card{
    private String effect;
    private int punteggio;
    protected final int width=4;
    protected final int height=5;

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String toString() {
        String escape = Colour.RED.escape();
        return  "Name: " + escape+super.getName()+Colour.RESET+
                " effect: " + effect +"\n";
    }
    public abstract int calcola_punteggio(Player player);
}
