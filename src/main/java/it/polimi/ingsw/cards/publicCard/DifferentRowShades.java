package it.polimi.ingsw.Cards.PublicCard;

import it.polimi.ingsw.Cards.PublicObject;
import it.polimi.ingsw.Game.Player;

public class DifferentRowShades extends PublicObject {
    public DifferentRowShades() {
        setName("Different Shades - Row");
        setValue(3);
        setPunteggio(5);
        setEffect("Rows without repeated shades");
    }

    private int numberrow(Player player) {
        int cont=0;
        int cont1=0;
        for (int i=0;i<4;i++){
            int[] array={1,2,3,4,5,6};
            for (int j=0;j<5;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    array[player.getWindow().getSlot(i,j).getDice().getFace()-1]=0;
                }
            }
            for (int z=0;z<6;z++) {
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

    public int calcola_punteggio(Player player) {
        return numberrow(player)*getPunteggio();
    }
}
