package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.game.Player;

public class VarietyofColour extends PublicObject {
    public VarietyofColour() {
        setName("Varietà di Colore");
        setValue(10);
        setPunteggio(4);
        setEffect("Set di dadi di ogni colore ovunque");
    }

    /**
     * Calculate set of every dice colour in player scheme card
     * @param player that need to calculate score
     * @return int the number of occurrences of every different dice whit different colour in scheme card
     */
    private int numberofset(Player player){
        int[] cont={0,0,0,0,0};
        int min=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()== Colour.RED) cont[0]++;
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()== Colour.PURPLE) cont[1]++;
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()== Colour.GREEN) cont[2]++;
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()== Colour.YELLOW) cont[3]++;
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()==Colour.BLUE) cont[4]++;
                }
            }
        }
        min=cont[0];
        for (int i=1;i<cont.length;i++){
            if (cont[i]<min) min=cont[i];
        }
        return min;
    }

    /**
     * Uses numberofset to calculate the score of player
     * @param player that need to calculate score
     * @return int number of set returned from
     * numberofset method multiplied by score of this public card
     */
    public int calcola_punteggio(Player player) {
        return numberofset(player)*getPunteggio();
    }
}
