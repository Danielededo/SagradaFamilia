package it.polimi.ingsw.cards;

import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.game.Player;

public class PrivateObject extends Card{
    private Colour color;
    private String effect;

    public Colour getColor() {
        return color;
    }

    public void setColor(Colour color) {
        this.color = color;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "PrivateObject{" +
                "name=" + super.getName()+
                ", color=" + color +
                ", effect='" + effect + '\'' +
                '}';
    }

    public int calculate_score(Player player){
        int sum=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()==true &&
                        player.getWindow().getSlot(i,j).getDice().getDicecolor()==this.color) {
                    sum += player.getWindow().getSlot(i, j).getDice().getFace();
                }
            }
        }
        return sum;
    }

}