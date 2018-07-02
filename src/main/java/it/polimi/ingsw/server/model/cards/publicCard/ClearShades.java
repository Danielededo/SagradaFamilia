package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.model.game.Player;

public class ClearShades extends PublicObject {
    public ClearShades() {
        setName("Sfumature Chiare");
        setValue(5);
        setPunteggio(2);
        setEffect("Set di 1 & 2 ovunque");
    }

    /**
     * Calculate set of 1 & 2 dice in player scheme card
     * @param player that need to calculate score
     * @return int the lowest number of occurrences between 1 and 2 in scheme card
     */
    private int numberofset(Player player){
        int cont1=0;
        int cont2=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==1) cont1++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==2) cont2++;
                }
            }
        }
        if (cont1<=cont2) return cont1;
        else return cont2;
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
