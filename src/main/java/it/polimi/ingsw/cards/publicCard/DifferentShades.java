package it.polimi.ingsw.cards.publicCard;

import it.polimi.ingsw.cards.PublicObject;
import it.polimi.ingsw.game.Player;

public class DifferentShades extends PublicObject {
    public DifferentShades() {
        setName("Different Shades");
        setValue(8);
        setPunteggio(5);
        setEffect("Set of dice of any value anywhere");
    }

    private int numberofset(Player player){
        int[] cont={0,0,0,0,0,0};
        int min=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
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
        for (int i=1;i<6;i++){
            if (cont[i]<min) min=cont[i];
        }
        return min;
    }

    public int calcola_punteggio(Player player) {
        return numberofset(player)*getPunteggio();
    }
}
