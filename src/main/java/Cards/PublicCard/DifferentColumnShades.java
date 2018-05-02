package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class DifferentColumnShades extends PublicObject {
    public DifferentColumnShades() {
        setName("Different Shades - Column");
        setValue(4);
        setPunteggio(4);
        setEffect("Columns without repeated shades");
    }


    private int numbercolumn(GlassWindow scheme){
        int cont=0;
        int cont1=0;
        for (int i=0;i<5;i++){
            int[] array={1,2,3,4,5,6};
            for (int j=0;j<4;j++){
                if (scheme.getSlot(j,i).isOccupate()){
                    array[scheme.getSlot(j,i).getDice().getFace()-1]=0;
                }
            }
            for (int z=0;z<6;z++) {
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

    public int calcola_punteggio(GlassWindow scheme) {
        return numbercolumn(scheme)*getPunteggio();
    }
}
