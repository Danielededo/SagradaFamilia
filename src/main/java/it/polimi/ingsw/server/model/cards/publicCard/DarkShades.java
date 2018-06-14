package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.model.game.Player;

public class DarkShades extends PublicObject {
    public DarkShades() {
        setName("Sfumature Scure");
        setValue(7);
        setPunteggio(2);
        setEffect("Set di 5 & 6 ovunque");
    }

    private int numberofset(Player player){
        int cont1=0,cont2=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
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
