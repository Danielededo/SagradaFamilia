package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;
import Dice.Colour;

import java.util.ArrayList;

public class DifferentColumnsColors extends PublicObject {
    public DifferentColumnsColors() {
        setName("Different Colors - Column");
        setValue(2);
        setPunteggio(5);
        setEffect("Columns without repeated colors");
    }

    public int numerocolonne(GlassWindow scheme){
        int cont=0;
        ArrayList<Colour> colori = new ArrayList<Colour>();
        for (int i=0;i<5;i++){
            colori.add(Colour.RED);
            colori.add(Colour.BLUE);
            colori.add(Colour.YELLOW);
            colori.add(Colour.GREEN);
            colori.add(Colour.PURPLE);
            for (int j=0;j<4;j++){
                if (scheme.getSlot(j,i).isOccupate() &&
                        colori.contains(scheme.getSlot(j,i).getDice().getDicecolor())){
                    colori.remove(scheme.getSlot(j,i).getDice().getDicecolor());
                }
            }
            if (colori.size()==1) {
                cont++;
            }
            colori.clear();
        }
        return cont;
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return numerocolonne(scheme)*getPunteggio();
    }
}
