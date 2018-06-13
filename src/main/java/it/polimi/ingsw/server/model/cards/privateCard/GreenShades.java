package it.polimi.ingsw.server.model.cards.privateCard;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.cards.PrivateObject;

public class GreenShades extends PrivateObject{
    public GreenShades() {
        super.setValue(3);
        super.setName("Green Shades");
        super.setColor(Colour.GREEN);
        super.setEffect("Sum of values on all green dice");
    }
}
