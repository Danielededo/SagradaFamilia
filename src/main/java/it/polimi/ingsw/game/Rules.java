package it.polimi.ingsw.game;


import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;


public class Rules {
    private String error;
    private int cont;
    private String[] list__of_errors={
            "This slot is not on the border of your Scheme Card",
            "Slot occupied",
            "You can't place a die of this color on this slot",
            "You can't place a die with this face on this slot",
            "There's already a die of the same colour next to this one",
            "There's already a die with the same value next to this one",
            "There are no dice next to this slot"
    };

    public int getCont(Player current) {
        cont=0;
        for(int i=0; i < 4; i++){
            for(int j=0; j < 5; j++) {
                if(current.getWindow().getSlot(i,j).getDice() != null) {
                    cont++;
                }
            }
        }
        return cont;
    }

    public String getError() {
        return error;
    }

    public GlassWindow diePlacing(Player current, Slot choice, Die selected){
        boolean mayI = rules(current, choice, selected);

        if(!mayI){
            System.out.print("Choose another slot.\n");
            return current.getWindow();
        }

        current.getWindow().getSlot(choice.getLine(),choice.getColumn()).setDie(selected);

        return current.getWindow();
    }


    public boolean rules(Player current, Slot choice, Die selected){
        boolean tracker;
        boolean vuota = false;
        Colour cSlot = choice.getSlotcolour();
        Colour cDie = selected.getDicecolor();
        int value = choice.getValue();
        int face = selected.getFace();
        int riga = choice.getLine();
        int colonna = choice.getColumn();
        int i,j;

        for(i=0; i < 4; i++){
            for(j=0; j < 5; j++) {
                if(current.getWindow().getSlot(i,j).getDice() != null)
                    {vuota = true;}
            }
        }

        if(!vuota) {
            tracker = firstDado(choice.getLine(),choice.getColumn());
            if(!tracker) {
                System.out.println("This slot is not on the border of your Scheme Card.\n");
                error=list__of_errors[0];
                return false;}
            }

        tracker = occupiedSlot(choice);

        if (!tracker) {
                System.out.print("Slot occupied.\n");
                error=list__of_errors[1];
                return false;
            }

        tracker = colourCheck(cSlot, cDie);

        if (!tracker) {
            System.out.print("You can't place a " + cDie + "die on a " + cSlot + " slot.\n");
            error=list__of_errors[2];
            return false;
        }

        tracker = numberCheck(value, face);

        if (!tracker){
            System.out.print("You can't place this die on a slot with a " + value + " value.\n");
            error=list__of_errors[3];
            return false;
        }

        if(vuota) {
            tracker = neighboursCheck(current.getWindow(), colonna, riga, cDie, face);
            if (!tracker) {
                return false;}
        }
        return true;

    }



    public boolean occupiedSlot(Slot choice) {
        boolean i = choice.isOccupate();
        if (!i)
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
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(riga, colonna - 1).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(riga - 1, colonna).isOccupate()) {
            if (current.getSlot(riga - 1, colonna).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(riga - 1, colonna).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(riga + 1, colonna).isOccupate()) {
            if (current.getSlot(riga + 1, colonna).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(riga + 1, colonna).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(riga, colonna + 1).isOccupate()) {
            if (current.getSlot(riga, colonna + 1).getDice().getDicecolor() == die) {
                System.out.print("There's already a die of the same colour next to this one.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(riga, colonna + 1).getDice().getFace() == face) {
                System.out.print("There's already a die with the same value next to this one.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }

        if(diagOccupate == true || ortOccupate == true)
            return true;
        else{
            System.out.println("There are no dice next to this slot.");
            error=list__of_errors[6];
            return false;}
    }



    public boolean colourCheck(Colour slot, Colour die) {
        if (slot == Colour.WHITE || slot == die)
            return true;
        else
            return false;
    }

    public boolean numberCheck(int slot, int die) {
        if (slot == 0 || slot == die)
            return true;
        else
            return false;
    }


    public boolean firstDado(int riga, int colonna){
        if((riga == 1 && (colonna == 1 || colonna == 2 || colonna == 3)) || (riga == 2 && (colonna == 1 || colonna == 2 || colonna == 3)) )
            return false;
        else
            return true;
    }
}
