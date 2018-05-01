package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class ClearShades extends PublicObject {
    public ClearShades() {
        setName("Clear Shades");
        setValue(5);
        setPunteggio(2);
        setEffect("Set of 1 & 2 everywhere");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
