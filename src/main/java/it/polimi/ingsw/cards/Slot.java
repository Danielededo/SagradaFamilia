package it.polimi.ingsw.cards;

import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;

public class Slot {
    private int line;
    private int column;
    private int value;
    private Colour slotcolour;
    private Die dice;
    private boolean occupate = false;
    private boolean esiste;

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

    public boolean exists(){ return exists(); }
    public void setExists(boolean exists) { this.esiste = exists; }


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

    public void removeDie(){
        this.setOccupate(false);
    }

    @Override
    public String toString() {
        if (slotcolour==Colour.RED) {
            return "Slot{" +
                    "line=" + line +
                    ", column=" + column +
                    ", value=" + value +
                    ", slotcolour=" + slotcolour +"   "+
                    ", dice=" + dice +
                    ", occupate=" + occupate +
                    '}';
        }else if (slotcolour == Colour.BLUE){
            return "Slot{" +
                    "line=" + line +
                    ", column=" + column +
                    ", value=" + value +
                    ", slotcolour=" + slotcolour +"  "+
                    ", dice=" + dice +
                    ", occupate=" + occupate +
                    '}';
        }else if (slotcolour == Colour.GREEN){
            return "Slot{" +
                    "line=" + line +
                    ", column=" + column +
                    ", value=" + value +
                    ", slotcolour=" + slotcolour +" "+
                    ", dice=" + dice +
                    ", occupate=" + occupate +
                    '}';
        }else if (slotcolour == Colour.WHITE){
            return "Slot{" +
                    "line=" + line +
                    ", column=" + column +
                    ", value=" + value +
                    ", slotcolour=" + slotcolour +" "+
                    ", dice=" + dice +
                    ", occupate=" + occupate +
                    '}';
        }else{
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

}
