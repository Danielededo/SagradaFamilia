package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;

import java.util.List;

public class ToolCard9 extends Tool {
    public ToolCard9() {
        super.setEffect("Dopo aver scelto un dado, " +
                "piazzalo in una casella che " +
                "non sia adiacente a un altro dado. " +
                "Devi rispettare tutte le " +
                "restrizioni di piazzamento ");
        super.setName("Riga in Sughero");
        super.setValue(9);
    }

    /**
     * This method is the effect of the 9th tool card. When used this card lets you place a die on your scheme card so
     * that it is not adjacent to any of the dice already placed. You'll have to respect all the other placement rules.
     * @param dice the die you want to place on your scheme card
     * @param slots the slot you want to move it into
     * @return boolean, true if the card was effective, false if you don't have enough tokens or if you've made mistakes
     *          while using this card
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value){
        int i=tokenpayment();
        if (i==0) return false;
        Rules r = new Rules();
        if(!r.occupiedSlot(slots.get(0))){
            System.out.println("Questo slot è occupato, scegli un altro slot.");
            error=list__of_errors[2];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
        if(!r.colourCheck(slots.get(0).getSlotcolour(), dice.get(0).getDicecolor())){
            System.out.println("Non puoi mettere un dado di questo colore in questo slot, scegli un altro slot.");
            error=list__of_errors[4];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
        if(!r.numberCheck(slots.get(0).getValue(), dice.get(0).getFace())){
            System.out.println("Non puoi mettere un dado con questa faccia in questo slot, scegli un altro slot.");
            error=list__of_errors[4];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }

        if(!super.getPlayer().getWindow().getSlot(slots.get(0).getLine() - 1, slots.get(0).getColumn()).isOccupate()) {
            if (!super.getPlayer().getWindow().getSlot(slots.get(0).getLine(), slots.get(0).getColumn() + 1).isOccupate()) {
                if (!super.getPlayer().getWindow().getSlot(slots.get(0).getLine() + 1, slots.get(0).getColumn()).isOccupate()) {
                    if (!super.getPlayer().getWindow().getSlot(slots.get(0).getLine(), slots.get(0).getColumn() - 1).isOccupate()) {
                        super.getPlayer().getWindow().getSlot(slots.get(0).getLine(), slots.get(0).getColumn()).setDie(dice.get(0));
                        return true;
                    }
                }
            }
        }
        System.out.println("C'è un dado in uno slot adiacente a quello scelto, scegli un altro slot.");
        error=list__of_errors[4];
        getPlayer().setMarker(getPlayer().getMarker()+i);
        if (i==1)setAccessed(false);
        return false;
    }

}



