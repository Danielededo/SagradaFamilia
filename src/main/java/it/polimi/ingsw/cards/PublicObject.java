package it.polimi.ingsw.cards;

import it.polimi.ingsw.game.Player;

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
        return "PublicObject{" +
                "name=" + super.getName()+
                ", effect='" + effect + '\'' +
                '}';
    }
    public abstract int calcola_punteggio(Player player);
}
