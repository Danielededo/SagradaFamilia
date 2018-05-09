package it.polimi.ingsw.Cards.PublicCard;

import it.polimi.ingsw.Cards.PublicObject;
import it.polimi.ingsw.Game.Player;

public class MediumShades extends PublicObject {
    public MediumShades() {
        setName("Medium Shades");
        setValue(6);
        setPunteggio(2);
        setEffect("Set of 3 & 4 everywhere");
    }

    private int numberofset(Player player){
        int cont1=0,cont2=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==3) cont1++;
                    if (player.getWindow().getSlot(i,j).getDice().getFace()==4) cont2++;
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

