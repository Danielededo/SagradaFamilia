package Cards;

import Dice.Colour;
import Dice.Die;

public class Slot {
    private int line;
    private int column;
    private int value;
    private Colour slotcolour;
    private Die dice;
    private boolean occupate = false;

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getValue() {
        return value;
    }

    public Colour getSlotcolour() {
        return slotcolour;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSlotcolour(Colour slotcolour) {
        this.slotcolour = slotcolour;
    }

    public void setOccupate(boolean occupate) {
        this.occupate = occupate;
    }

    public boolean isOccupate() {
        return occupate;
    }

    public void setDie(Die dice) {
        if(!isOccupate()){
            if(this.getSlotcolour()==Colour.WHITE && getValue()==0) {
                this.setOccupate(true);
                this.dice = dice;
            }
            else {
                if (this.getSlotcolour() == dice.getDicecolor()&& getSlotcolour()!=Colour.WHITE ){
                    this.setOccupate(true);
                    this.dice = dice;
                }
                if (this.getValue() != 0 && this.getValue()==dice.getFace()){
                    this.setOccupate(true);
                    this.dice = dice;
                }
            }
        }
    }
}
