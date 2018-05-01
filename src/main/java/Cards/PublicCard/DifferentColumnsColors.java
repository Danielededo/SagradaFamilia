package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class DifferentColumnsColors extends PublicObject {
    public DifferentColumnsColors() {
        setName("Different Colors - Column");
        setValue(2);
        setPunteggio(5);
        setEffect("Columns without repeated colors");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
