package it.polimi.ingsw.Game;


import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;




public class Rules {
    public GlassWindow diePlacing(GlassWindow current, Slot choice, Die selected){
        boolean mayI = rules(current, choice, selected);

        if(!mayI){
            System.out.print("Chose another slot.");
            return current;
        }

        current.getSlot(choice.getLine(),choice.getColumn()).setDie(selected);

        return current;
    }


    public boolean rules(GlassWindow current, Slot choice, Die selected){
        boolean tracker;
        boolean primoRound = false;
        Colour cSlot = choice.getSlotcolour();
        Colour cDie = selected.getDicecolor();
        int value = choice.getValue();
        int face = selected.getFace();
        int riga = choice.getLine();
        int colonna = choice.getColumn();

        //inserire controllo primo round



        tracker = occupiedSlot(choice);

        if (!tracker) {
            System.out.print("Slot occupied.");
            return false;
        };

        tracker = colourCheck(cSlot, cDie);

        if (!tracker) {
            System.out.print("You can't place a " + cDie + "die on a " + cSlot + " slot.");
            return false;
        };

        tracker = numberCheck(value, face);

        if (!tracker){
            System.out.print("You can't place this die on a slot with a " + value + " value.");
            return false;
        };

        tracker = neighboursCheck(current, colonna, riga, cDie, face);

        if (!tracker) {
            System.out.print("There aren't any dice next to this slot.");
            return false;
        };

        return true;

    }



    public boolean occupiedSlot(Slot choice) {
        boolean i;
        if ((i = choice.isOccupate()) == false)
            return true;
        else
            return false;
    }



    public boolean neighboursCheck(GlassWindow current, int colonna, int riga, Colour die, int face) {
        boolean diagOccupate = true;
        boolean ortOccupate = false;

        if(!current.getSlot( riga - 1, colonna -1).isOccupate())
        {if(!current.getSlot(riga - 1, colonna + 1).isOccupate())
            if(!current.getSlot(riga + 1, colonna -1).isOccupate())
                if(!current.getSlot(riga + 1, colonna + 1).isOccupate())
                    diagOccupate = false;
        };


        if(current.getSlot(riga, colonna - 1).isOccupate()) {
            if (current.getSlot(riga, colonna - 1).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one");
                return false;
            };
            if (current.getSlot(riga, colonna - 1).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one");
                return false;
            };
            ortOccupate = true;
        };


        if(current.getSlot(riga - 1, colonna).isOccupate()) {
            if (current.getSlot(riga - 1, colonna).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one");
                return false;
            };
            if (current.getSlot(riga - 1, colonna).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one");
                return false;
            };
            ortOccupate = true;
        };


        if(current.getSlot(riga + 1, colonna).isOccupate()) {
            if (current.getSlot(riga + 1, colonna).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one");
                return false;
            };
            if (current.getSlot(riga + 1, colonna).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one");
                return false;
            };
            ortOccupate = true;
        };


        if(current.getSlot(riga, colonna + 1).isOccupate()) {
            if (current.getSlot(riga, colonna + 1).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one");
                return false;
            };
            if (current.getSlot(riga, colonna + 1).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one");
                return false;
            };
            ortOccupate = true;
        };

        if(diagOccupate == true || ortOccupate == true)
            return true;
        else
            return false;
    }



    public boolean colourCheck(Colour slot, Colour die) {
        if (slot == Colour.WHITE || slot == die)
            return true;
        else return false;
    }

    public boolean numberCheck(int slot, int die) {
        if (slot == 0 || slot == die)
            return true;
        else return false;
    }


    //public boolean first round
}








