package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class DarkShades extends PublicObject {
    public DarkShades() {
        setName("Dark Shades");
        setValue(7);
        setPunteggio(2);
        setEffect("Set of 5 & 6 everywhere");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
