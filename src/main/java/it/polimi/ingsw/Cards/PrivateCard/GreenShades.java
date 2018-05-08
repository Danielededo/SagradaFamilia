package it.polimi.ingsw.Cards.PrivateCard;

import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Cards.PrivateObject;

public class GreenShades extends PrivateObject{
    public GreenShades() {
        super.setValue(3);
        super.setName("Green Shades");
        super.setColor(Colour.GREEN);
        super.setEffect("Sum of values on all green dice");
    }
}
