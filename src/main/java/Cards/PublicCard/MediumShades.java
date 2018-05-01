package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class MediumShades extends PublicObject {
    public MediumShades() {
        setName("Medium Shades");
        setValue(6);
        setPunteggio(2);
        setEffect("Set of 3 & 4 everywhere");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
