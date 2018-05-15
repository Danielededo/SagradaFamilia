package it.polimi.ingsw.cards.publicCard;

import it.polimi.ingsw.cards.PublicObject;
import it.polimi.ingsw.game.Player;

public class DarkShades extends PublicObject {
    public DarkShades() {
        setName("Dark Shades");
        setValue(7);
        setPunteggio(2);
        setEffect("Set of 5 & 6 everywhere");
    }

    private int numberofset(Player player){
        int cont1=0,cont2=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==5) cont1++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==6) cont2++;
                }
            }
        }
        if (cont1<=cont2) return cont1;
        else return cont2;
    }

    public int calcola_punteggio(Player player) {
        return numberofset(player)*getPunteggio();
    }
}
