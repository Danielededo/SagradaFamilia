package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.model.game.Player;

public class DifferentShades extends PublicObject {
    public DifferentShades() {
        setName("Sfumature Diverse");
        setValue(8);
        setPunteggio(5);
        setEffect("Set di dadi di ogni  valore ovunque");
    }

    /**
     * Calculate set of every dice value in player scheme card
     * @param player that need to calculate score
     * @return int the number of occurrences of every different dice whit different value in scheme card
     */
    private int numberofset(Player player){
        int[] cont={0,0,0,0,0,0};
        int min=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==1) cont[0]++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==2) cont[1]++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==3) cont[2]++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==4) cont[3]++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==5) cont[4]++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==6) cont[5]++;
                }
            }
        }
        min=cont[0];
        for (int i=1;i<height+1;i++){
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
