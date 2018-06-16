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

    public int calcola_punteggio(Player player) {
        return numberrow(player)*getPunteggio();
    }
}
