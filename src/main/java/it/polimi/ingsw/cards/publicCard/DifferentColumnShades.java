package it.polimi.ingsw.cards.publicCard;

import it.polimi.ingsw.cards.PublicObject;
import it.polimi.ingsw.game.Player;

public class DifferentColumnShades extends PublicObject {
    public DifferentColumnShades() {
        setName("Different Shades - Column");
        setValue(4);
        setPunteggio(4);
        setEffect("Columns without repeated shades");
    }


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

    public int calcola_punteggio(Player player) {
        return numbercolumn(player)*getPunteggio();
    }
}
