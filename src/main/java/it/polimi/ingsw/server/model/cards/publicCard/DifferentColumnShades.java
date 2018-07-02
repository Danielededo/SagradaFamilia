package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.model.game.Player;

public class DifferentColumnShades extends PublicObject {
    public DifferentColumnShades() {
        setName("Sfumature diverse - Colonna");
        setValue(4);
        setPunteggio(4);
        setEffect("Colonne senza sfumature ripetute");
    }

    /**
     * Calculate number of column with every dice value
     * @param player from which to take the scheme card
     * @return int cont number of column
     */
    private int numbercolumn(Player player){
        int cont=0;
        int cont1=0;
        for (int i=0;i<height;i++){
            int[] array={1,2,3,4,5,6};
            for (int j=0;j<width;j++){
                if (player.getWindow().getSlot(j,i).isOccupate()){
                    array[player.getWindow().getSlot(j,i).getDice().getFace()-1]=0;
                }
            }
            for (int z=0;z<height+1;z++) {
                if (array[z]!=0) {
                    cont1++;
                }
            }
            if (cont1==2) {
                cont++;
            }
            cont1=0;
        }
        return cont;
    }

    /**
     * Calculate score of player scheme card in @param
     * @param player that need to calculate score
     * @return int column number multiplied by score of this scheme card
     */
    public int calcola_punteggio(Player player) {
        return numbercolumn(player)*getPunteggio();
    }
}
