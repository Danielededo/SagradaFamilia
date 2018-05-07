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

    public Slot() {
    }

    public Slot(int value, Colour slotcolour) {
        this.value = value;
        this.slotcolour = slotcolour;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
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

    public Die getDice() {
        return dice;
    }

    public void removeDie(){ this.setOccupate(false); }

    @Override
    public String toString() {
        return "Slot{" +
                "line=" + line +
                ", column=" + column +
                ", value=" + value +
                ", slotcolour=" + slotcolour +
                ", dice=" + dice +
                ", occupate=" + occupate +
                '}';

    }
}
