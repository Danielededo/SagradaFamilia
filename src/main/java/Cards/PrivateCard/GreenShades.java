package Cards.PrivateCard;

import Dice.Colour;
import Cards.PrivateObject;

public class GreenShades extends PrivateObject{
    public GreenShades() {
        super.setValue(3);
        super.setName("Green Shades");
        super.setColor(Colour.GREEN);
        super.setEffect("Sum of values on all green dice");
    }
}
