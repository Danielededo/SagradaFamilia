package it.polimi.ingsw.Cards.PublicCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.PublicObject;
import java.util.ArrayList;
import java.lang.*;

public class DifferentRowShades extends PublicObject {
    public DifferentRowShades() {
        setName("Different Shades - Row");
        setValue(3);
        setPunteggio(5);
        setEffect("Rows without repeated shades");
    }

    private int numberrow(GlassWindow scheme) {
        int cont=0;
        int cont1=0;
        for (int i=0;i<4;i++){
            int[] array={1,2,3,4,5,6};
            for (int j=0;j<5;j++){
                if (scheme.getSlot(i,j).isOccupate()){
                    array[scheme.getSlot(i,j).getDice().getFace()-1]=0;
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

    public int calcola_punteggio(GlassWindow scheme) {
        return numberrow(scheme)*getPunteggio();
    }
}