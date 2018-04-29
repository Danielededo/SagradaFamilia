package Cards;

import Dice.Colour;

public class PrivateObject extends Card{
    private Colour color;
    private String effect;

    public Colour getColor() {
        return color;
    }

    public void setColor(Colour color) {
        this.color = color;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "PrivateObject{" +
                "name=" + super.getName()+
                ", color=" + color +
                ", effect='" + effect + '\'' +
                '}';
    }
}
