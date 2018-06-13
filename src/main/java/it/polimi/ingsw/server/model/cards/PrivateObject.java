package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.game.Player;

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
        String escape = Colour.RED.escape();
        return escape+super.getName()+ Colour.RESET+
                ", effect: " + effect;
    }

    public int calculate_score(Player player){
        int sum=0;
        final int width=4;
        final int height=5;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()==true &&
                        player.getWindow().getSlot(i,j).getDice().getDicecolor()==this.color) {
                    sum += player.getWindow().getSlot(i, j).getDice().getFace();
                }
            }
        }
        return sum;
    }

}