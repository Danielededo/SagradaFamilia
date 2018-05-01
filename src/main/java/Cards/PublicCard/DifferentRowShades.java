package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class DifferentRowShades extends PublicObject {
    public DifferentRowShades() {
        setName("Different Shades - Row");
        setValue(3);
        setPunteggio(5);
        setEffect("Rows without repeated shades");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
