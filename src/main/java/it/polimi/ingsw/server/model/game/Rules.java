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

    public GlassWindow diePlacing(Player current, Slot choice, Die selected){
        boolean mayI = rules(current, choice, selected);

        if(!mayI){
            System.out.print("Scegli un altra casella.\n");
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
                System.out.print("C'è già un dado dello stesso colore vicino a questa casella.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(riga, colonna - 1).getDice().getFace() == face) {
                System.out.print("C'è già un dado dello stesso valore vicino a questa casella.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(riga - 1, colonna).isOccupate()) {
            if (current.getSlot(riga - 1, colonna).getDice().getDicecolor() == die) {
                System.out.print("C'è già un dado dello stesso colore vicino a questa casella.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(riga - 1, colonna).getDice().getFace() == face) {
                System.out.print("C'è già un dado dello stesso valore vicino a questa casella.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(riga + 1, colonna).isOccupate()) {
            if (current.getSlot(riga + 1, colonna).getDice().getDicecolor() == die) {
                System.out.print("C'è già un dado dello stesso colore vicino a questa casella.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(riga + 1, colonna).getDice().getFace() == face) {
                System.out.print("C'è già un dado dello stesso valore vicino a questa casella.\n");
                error=list__of_errors[5];
                return false;
            }
            ortOccupate = true;
        }


        if(current.getSlot(riga, colonna + 1).isOccupate()) {
            if (current.getSlot(riga, colonna + 1).getDice().getDicecolor() == die) {
                System.out.print("C'è già un dado dello stesso colore vicino a questa casella.\n");
                error=list__of_errors[4];
                return false;
            }
            if (current.getSlot(riga, colonna + 1).getDice().getFace() == face) {
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
