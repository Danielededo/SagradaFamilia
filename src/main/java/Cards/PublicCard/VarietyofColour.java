package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class VarietyofColour extends PublicObject {
    public VarietyofColour() {
        setName("Variety of Color");
        setValue(10);
        setPunteggio(4);
        setEffect("Set of dice of every color everywhere");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return 0;
    }
}
