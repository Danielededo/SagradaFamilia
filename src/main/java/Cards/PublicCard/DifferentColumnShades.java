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

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
