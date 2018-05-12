package it.polimi.ingsw.Game;


import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;



public class Rules {
    public GlassWindow diePlacing(Match partita, Player current, Slot choice, Die selected){
        boolean mayI = rules(partita, current, choice, selected);

        if(!mayI){
            System.out.print("Chose another slot.\n");
            return current.getWindow();
        }

        current.getWindow().getSlot(choice.getLine(),choice.getColumn()).setDie(selected);

        return current.getWindow();
    }


    public boolean rules(Match partita, Player current, Slot choice, Die selected){
        boolean tracker;
        boolean primoRound;
        Colour cSlot = choice.getSlotcolour();
        Colour cDie = selected.getDicecolor();
        int value = choice.getValue();
        int face = selected.getFace();
        int riga = choice.getLine();
        int colonna = choice.getColumn();
        boolean assenzaVicini;


        if (partita.getRound() == 1 && current.getContTurn()==1) {
            primoRound = firstRound(riga, colonna);
            if (!primoRound) {
                System.out.println("This slot is not on the border of your Scheme Card.\n");
                return false;
            }
        }

        tracker = occupiedSlot(choice);

        if (!tracker) {
                System.out.print("Slot occupied.\n");
                return false;
            }

        tracker = colourCheck(cSlot, cDie);

        if (!tracker) {
            System.out.print("You can't place a " + cDie + "die on a " + cSlot + " slot.\n");
            return false;
        }

        tracker = numberCheck(value, face);

        if (!tracker){
            System.out.print("You can't place this die on a slot with a " + value + " value.\n");
            return false;
        }

        if(partita.getRound() > 1 || current.getContTurn()==2) {
            tracker = neighboursCheck(current.getWindow(), colonna, riga, cDie, face);
            if (!tracker) {
                return false;}
        }
        return true;

    }



    public boolean occupiedSlot(Slot choice) {
        boolean i;
        if ((i = choice.isOccupate()) == false)
            return true;
        else
            return false;
    }



    public boolean neighboursCheck (GlassWindow current, int colonna, int riga, Colour die, int face) {
        boolean diagOccupate = true;
        boolean ortOccupate = false;

        if(!current.getSlot(riga - 1, colonna -1).isOccupate())
        {if(!current.getSlot(riga - 1, colonna + 1).isOccupate())
            if(!current.getSlot(riga + 1, colonna -1).isOccupate())
                if(!current.getSlot(riga + 1, colonna + 1).isOccupate())
                    diagOccupate = false;
        }


        if(current.getSlot(riga, colonna - 1).isOccupate()) {
            if (current.getSlot(riga, colonna - 1).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one.\n");
                return false;
            };
            if (current.getSlot(riga, colonna - 1).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one.\n");
                return false;
            };
            ortOccupate = true;
        };


        if(current.getSlot(riga - 1, colonna).isOccupate()) {
            if (current.getSlot(riga - 1, colonna).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one.\n");
                return false;
            };
            if (current.getSlot(riga - 1, colonna).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one.\n");
                return false;
            };
            ortOccupate = true;
        };


        if(current.getSlot(riga + 1, colonna).isOccupate()) {
            if (current.getSlot(riga + 1, colonna).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one.\n");
                return false;
            };
            if (current.getSlot(riga + 1, colonna).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one.\n");
                return false;
            };
            ortOccupate = true;
        };


        if(current.getSlot(riga, colonna + 1).isOccupate()) {
            if (current.getSlot(riga, colonna + 1).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one.\n");
                return false;
            };
            if (current.getSlot(riga, colonna + 1).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one.\n");
                return false;
            };
            ortOccupate = true;
        };

        if(diagOccupate == true || ortOccupate == true)
            return true;
        else{
            System.out.println("Non ci sono dadi vicino a questa casella.");
            return false;}
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


    public boolean firstRound(int riga, int colonna){
        if((riga == 1 && (colonna == 1 || colonna == 2 || colonna == 3)) || (riga == 2 && (colonna == 1 || colonna == 2 || colonna == 3)) )
            return false;
        else
            return true;
    }
}
