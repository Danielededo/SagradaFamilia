package it.polimi.ingsw.Cards.PublicCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.PublicObject;

public class DarkShades extends PublicObject {
    public DarkShades() {
        setName("Dark Shades");
        setValue(7);
        setPunteggio(2);
        setEffect("Set of 5 & 6 everywhere");
    }

    private int numberofset(GlassWindow scheme){
        int cont1=0,cont2=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                if (scheme.getSlot(i,j).isOccupate()){
                    if (scheme.getSlot(i,j).getDice().getFace()==5) cont1++;
                    if (scheme.getSlot(i,j).getDice().getFace()==6) cont2++;
                }
            }
        }
        if (cont1<=cont2) return cont1;
        else return cont2;
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return numberofset(scheme)*getPunteggio();
    }
}
