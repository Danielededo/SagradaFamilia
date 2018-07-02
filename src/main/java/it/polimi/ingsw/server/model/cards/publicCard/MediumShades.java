package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.model.game.Player;

public class MediumShades extends PublicObject {
    public MediumShades() {
        setName("Sfumature Medie");
        setValue(6);
        setPunteggio(2);
        setEffect("Set di 3 & 4 ovunque");
    }

    /**
     * Calculate set of 3 & 4 dice in player scheme card
     * @param player that need to calculate score
     * @return int the lowest number of occurrences between 3 and 4 in scheme card
     */
    private int numberofset(Player player){
        int cont1=0,cont2=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==3) cont1++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==4) cont2++;
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

