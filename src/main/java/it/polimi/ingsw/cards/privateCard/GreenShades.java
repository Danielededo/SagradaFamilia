package it.polimi.ingsw.cards.privateCard;

import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.cards.PrivateObject;

public class GreenShades extends PrivateObject{
    public GreenShades() {
        super.setValue(3);
        super.setName("Green Shades");
        super.setColor(Colour.GREEN);
        super.setEffect("Sum of values on all green dice");
    }
}
