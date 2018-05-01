package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class DifferentShades extends PublicObject {
    public DifferentShades() {
        setName("Different Shades");
        setValue(8);
        setPunteggio(5);
        setEffect("Set of dice of any value anywhere");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
