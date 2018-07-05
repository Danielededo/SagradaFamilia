package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;

import java.util.List;

public class ToolCard2 extends Tool {
    public ToolCard2() {
        super.setEffect("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni" +
                " di colore. " +
                "Devi rispettare tutte le altre" +
                " restrizioni di piazzamento. ");
        super.setName("Pennello per Eglomise");
        super.setValue(2);
    }

    /**
     * This method is the effect of tool card 2, move reference of die in slot1's attribute to slot2's die attribute only if
     * are respected the rules; slot1's die is not null while slot2 hasn't got a die, if these things are not respected effect cannot be used
     * and method return false.
     * @param match current Match
     * @param slots slots where take and place die
     * @return boolean, true if slot1's die has been moved correctly, false if the effect cannot be used either because of not enough favor tokens
     * or slot1's die is null or slot2's die is not null.
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value) {
        Rules rules = new Rules();
        int i=tokenpayment();
        if (i==0)return false;
        Die a;
        if (getPlayer().getWindow().getSlot(slots.get(1)).isOccupate()) {
            error=list__of_errors[2];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
        if (!getPlayer().getWindow().getSlot(slots.get(0)).isOccupate()) {
            error=list__of_errors[3];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
        a = getPlayer().getWindow().getSlot(slots.get(0)).getDice();
        getPlayer().getWindow().getSlot(slots.get(0)).setOccupate(false);
        getPlayer().getWindow().getSlot(slots.get(0)).setDie(null);
        if (rules.neighboursCheck(getPlayer().getWindow(), slots.get(1).getColumn(), slots.get(1).getLine(), a.getDicecolor(), a.getFace())) {
            if (slots.get(1).getValue() == 0) {
                getPlayer().getWindow().getSlot(slots.get(1)).setDie(a);
                return true;
            } else {
                if (slots.get(1).getValue() == a.getFace()) {
                    getPlayer().getWindow().getSlot(slots.get(1)).setDie(a);
                    return true;
                } else {
                    error=list__of_errors[4];
                    getPlayer().getWindow().getSlot(slots.get(0)).setDie(a);
                    getPlayer().setMarker(getPlayer().getMarker()+i);
                    if (i==1)setAccessed(false);
                    return false;
                }
            }
        }
        else{
            error=list__of_errors[5];
            getPlayer().getWindow().getSlot(slots.get(0)).setDie(a);
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
    }
}