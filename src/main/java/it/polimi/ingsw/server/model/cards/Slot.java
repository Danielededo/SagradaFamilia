package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;

public class Slot {
    private int line;
    private int column;
    private int value;
    private Colour slotcolour;
    private Die dice;
    private boolean occupate = false;
    private boolean exist;

    public Slot() {
    }

    public Slot(int line, int column, int value, Colour slotcolour) {
        this.line = line;
        this.column = column;
        this.value = value;
        this.slotcolour = slotcolour;
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

    public boolean exists(){ return exist; }
    public void setExists(boolean exists) { this.exist = exists; }

    /**
     * This method sets the Die passed as parameter to die attribute, only if boolean 'occupate'
     * is false and sets it true if dice passed is not equals to null
     * @param dice is a Die that will be assign to the slot
     */
    public void setDie(Die dice) {
        if(!isOccupate()){
            this.dice = dice;
            if(dice!=null)
                this.setOccupate(true);
        }
    }

    public Die getDice() {
        return dice;
    }

    /**
     * Sets boolean 'occupate' to false in order to allow the placement of a Die
     */
    public void removeDie(){
        this.setOccupate(false);
    }

    @Override
    public String toString() {
        String escape=this.slotcolour.escape();
        if (!isOccupate()) {
            if (value != 0) return "("+line+","+column+") "+escape + "[" + value + "]" + Colour.RESET;
            else return "("+line+","+column+") "+escape + "[ " + "]" + Colour.RESET;
        }else return "("+line+","+column+") "+escape+"["+dice.toString()+escape+"]"+Colour.RESET;
    }
}
