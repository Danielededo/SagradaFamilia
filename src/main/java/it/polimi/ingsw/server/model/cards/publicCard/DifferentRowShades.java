package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.model.game.Player;

public class DifferentRowShades extends PublicObject {
    public DifferentRowShades() {
        setName("Sfumature diverse - Riga");
        setValue(3);
        setPunteggio(5);
        setEffect("Righe senza sfumature ripetute");
    }

    /**
     * Calculates the number of row without repeated shades
     * @param player need its scheme card
     * @return int cont number of row
     */
    private int numberrow(Player player) {
        int cont=0;
        int cont1=0;
        for (int i=0;i<width;i++){
            int[] array={1,2,3,4,5,6};
            for (int j=0;j<height;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    array[player.getWindow().getSlot(i,j).getDice().getFace()-1]=0;
                }
            }
            for (int z=0;z<height+1;z++) {
                if (array[z]!=0) {
                    cont1++;
                }
            }
            if (cont1==1) {
                cont++;
            }
            cont1=0;
        }
        return cont;
    }

    /**
     * @param player that need to calculate score
     * @return number of row returned by numberrow method multiplied by score of this scheme card
     */
    public int calcola_punteggio(Player player) {
        return numberrow(player)*getPunteggio();
    }
}
