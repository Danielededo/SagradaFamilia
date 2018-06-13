package it.polimi.ingsw.server.model.cards.privateCard;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.cards.PrivateObject;

public class YellowShades extends PrivateObject {
    public YellowShades() {
        super.setValue(2);
        super.setName("Yellow Shades");
        super.setColor(Colour.YELLOW);
        super.setEffect("Sum of values on all yellow dice");
    }
}
