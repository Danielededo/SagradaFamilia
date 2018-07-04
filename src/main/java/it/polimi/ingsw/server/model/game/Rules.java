package it.polimi.ingsw.server.model.game;


import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;


public class Rules {
    private String error;
    private int cont;
    private static final String[] list__of_errors={
            "Questa casella non è sul bordo dello schema",
            "Casella occupata",
            "Non puoi posizionare un dado di questo colore su questa casella",
            "Non puoi posizionare un dado con questo valore su questa casella",
            "C'è già un dado dello stesso colore vicino a questa casella",
            "C'è già un dado dello stesso valore vicino a questa casella",
            "Non ci sono dadi vicino questa casella"
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


    /**This method calls the method "rules" in order to check that everything is obeyed, and if so it modifies the
     * player scheme card.
     * @param current the current player
     * @param choice the chosen slot
     * @param selected the chosen die
     * @return GlassWindow, the scheme card will be modified only if every placemente rule is obeyed
     */
    public GlassWindow diePlacing(Player current, Slot choice, Die selected){
        boolean mayI = rules(current, choice, selected);

        if(!mayI){
            System.out.print("Scegli un altra casella.\n");
            return current.getWindow();
        }

        current.getWindow().getSlot(choice.getLine(),choice.getColumn()).setDie(selected);

        return current.getWindow();
    }


    /**
     * This mehod calls all the other ones below to make sure that every placement rule is obeyed.
     * It will set a specific error message, depending on which rule was violated.
     * @param current the current player
     * @param choice the chosen slot
     * @param selected the chosen die
     * @return boolean, true if every rule is obeyed, false otherwise
     */
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
            tracker = firstDice(choice.getLine(),choice.getColumn());
            if(!tracker) {
                System.out.println("Questa casella non è sul bordo dello schema.\n");
                error=list__of_errors[0];
                return false;}
            }

        tracker = occupiedSlot(choice);

        if (!tracker) {
                System.out.print("Casella occupata.\n");
                error=list__of_errors[1];
                return false;
            }

        tracker = colourCheck(cSlot, cDie);

        if (!tracker) {
            System.out.print("Non puoi posizionare questo " + cDie + "dado su questa " + cSlot + " casella.\n");
            error=list__of_errors[2];
            return false;
        }

        tracker = numberCheck(value, face);

        if (!tracker){
            System.out.print("Non puoi posizionare questo dado su una casella con questo " + value + " valore.\n");
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


    /**
     * This method checks if the chosen slot already has a die in it.
     * @param choice the chosen slot
     * @return boolean, true if the slot is empty.
     */
    public boolean occupiedSlot(Slot choice) {
        boolean i = choice.isOccupate();
        if (!i)
            return true;
        else
            return false;
    }


    /**
     * This method checks the eight slots around the chosen one. For the adjacent ones there can't be dice already placed
     * with the same value or colour as the chosen one. The diagonally adjacent ones are checked to see if at least one is
     * occupied. In fact we can't place a die if all the eight slots around the chosen one are empty.
     * @param current the scheme card of the current player
     * @param column the column of the chosen slot
     * @param row the row of the chosen slot
     * @param die the colour of the chosen die
     * @param face the value of the chosen die
     * @return boolean, true if the rule is obeyed.
     */
    public boolean neighboursCheck (GlassWindow current, int column, int row, Colour die, int face) {
        boolean diagOccupate = true;
        boolean ortOccupate = false;

        if(!current.getSlot(row - 1, column -1).isOccupate())
        {if(!current.getSlot(row - 1, column + 1).isOccupate())
            if(!current.getSlot(row + 1, column -1).isOccupate())
                if(!current.getSlot(row + 1, column + 1).isOccupate())
                    diagOccupate = false;
        }


        if(current.getSlot(row, column - 1).isOccupate()) {
            if (current.getSlot(row, column - 1).getDice().getDicecolor() == die) {
                System.out.print("C'è già un dado dello stesso colore vicino a questa casella.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(row, column - 1).getDice().getFace() == face) {
                System.out.print("C'è già un dado dello stesso valore vicino a questa casella.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(row - 1, column).isOccupate()) {
            if (current.getSlot(row - 1, column).getDice().getDicecolor() == die) {
                System.out.print("C'è già un dado dello stesso colore vicino a questa casella.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(row - 1, column).getDice().getFace() == face) {
                System.out.print("C'è già un dado dello stesso valore vicino a questa casella.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(row + 1, column).isOccupate()) {
            if (current.getSlot(row + 1, column).getDice().getDicecolor() == die) {
                System.out.print("C'è già un dado dello stesso colore vicino a questa casella.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(row + 1, column).getDice().getFace() == face) {
                System.out.print("C'è già un dado dello stesso valore vicino a questa casella.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(row, column + 1).isOccupate()) {
            if (current.getSlot(row, column + 1).getDice().getDicecolor() == die) {
                System.out.print("C'è già un dado dello stesso colore vicino a questa casella.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(row, column + 1).getDice().getFace() == face) {
                System.out.print("C'è già un dado dello stesso valore vicino a questa casella.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }

        if(diagOccupate == true || ortOccupate == true)
            return true;
        else{
            System.out.println("Non ci sono dadi vicino questa casella.");
            error=list__of_errors[6];
            return false;}
    }


    /**
     * The method checks if there are colour restrictions on the chosen slot, and if they are obeyed.
     * @param slot default slot colour
     * @param die colour of the chosen die
     * @return boolean, true if the rule is obeyed
     */
    public boolean colourCheck(Colour slot, Colour die) {
        if (slot == Colour.WHITE || slot == die)
            return true;
        else
            return false;
    }

    /**The method checks if there are number restrictions on the chosen slot, and if they are obeyed.
     * @param slot default slot face
     * @param die value of the die face
     * @return boolean, true if the rule is obeyed
     */
    public boolean numberCheck(int slot, int die) {
        if (slot == 0 || slot == die)
            return true;
        else
            return false;
    }


    /**This method checks if you are placing your first die on the border of your scheme card
     * @param row row of the slot
     * @param column coloumn of the slot
     * @return boolean, true if the slot is on the border
     */
    public boolean firstDice(int row, int column){
        if((row == 1 && (column == 1 || column == 2 || column == 3)) || (row == 2 && (column == 1 || column == 2 || column == 3)) )
            return false;
        else
            return true;
    }
}
