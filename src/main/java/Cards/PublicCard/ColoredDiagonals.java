package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class ColoredDiagonals extends PublicObject {
    public ColoredDiagonals() {
        setName("Colored Diagonals");
        setValue(9);
        setEffect("Number of same-colored dice diagonally adjacent");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
