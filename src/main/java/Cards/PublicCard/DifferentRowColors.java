package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;
import Dice.Colour;

import java.util.ArrayList;

public class DifferentRowColors extends PublicObject {
    public DifferentRowColors() {
        setName("Different Colors - Row");
        setValue(1);
        setPunteggio(6);
        setEffect("Rows without repeated colors");
    }

    public int numerorighe(GlassWindow scheme) {
        int cont=0;
        ArrayList<Colour> colori = new ArrayList<Colour>();
        for (int i=0;i<4;i++){
            colori.add(Colour.RED);
            colori.add(Colour.BLUE);
            colori.add(Colour.YELLOW);
            colori.add(Colour.GREEN);
            colori.add(Colour.PURPLE);
            for (int j=0;j<5;j++){
                if (scheme.getSlot(i,j).isOccupate() &&
                        colori.contains(scheme.getSlot(i,j).getDice().getDicecolor())){
                    colori.remove(scheme.getSlot(i,j).getDice().getDicecolor());
                }
            }
            if (colori.size()==0) {
                cont++;
            }
            colori.clear();
        }
        return cont;
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return numerorighe(scheme)*getPunteggio();
    }
}
