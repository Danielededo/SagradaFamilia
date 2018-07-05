package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;
import it.polimi.ingsw.utils.Colour;

import java.util.List;

public class ToolCard3 extends Tool {
    public ToolCard3() {
        super.setEffect("Muovi un qualsiasi dado nella tua" +
                " vetrata ignorando le restrizioni" +
                " di valore. " +
                "Devi rispettare tutte le altre" +
                " restrizioni di piazzamento ");
        super.setName("Alesatore per lamina di rame");
        super.setValue(3);
    }

    /**
     * This method represents the effect of the 3rd tool card. It lets you move a die already placed on your scheme card
     * to a different slot, ignoring the shade restrictions. You have to obey all the others placement rules.
     * @param slots slots where take and place die
     * @return boolean, true if the card was effective, false if you don't have enough tokens or if you've made mistakes
     *          while using this card.
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value) {
        Rules rules = new Rules();
        int i=tokenpayment();
        if (i==0)return false;
        Die a;
        if (getPlayer().getWindow().getSlot(slots.get(1)).isOccupate()) {
            System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
            error=list__of_errors[2];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
        if (!getPlayer().getWindow().getSlot(slots.get(0)).isOccupate()) {
            System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
            error=list__of_errors[3];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
        a = getPlayer().getWindow().getSlot(slots.get(0)).getDice();
        getPlayer().getWindow().getSlot(slots.get(0)).setOccupate(false);
        getPlayer().getWindow().getSlot(slots.get(0)).setDie(null);
        if (rules.neighboursCheck(getPlayer().getWindow(), slots.get(1).getColumn(), slots.get(1).getLine(), a.getDicecolor(), a.getFace())) {
            if (slots.get(1).getSlotcolour() == Colour.WHITE) {
                getPlayer().getWindow().getSlot(slots.get(1)).setDie(a);
                return true;
            } else {
                if (slots.get(1).getSlotcolour() == a.getDicecolor()) {
                    getPlayer().getWindow().getSlot(slots.get(1)).setDie(a);
                    return true;
                } else {
                    System.out.println("Non puoi posizionare il dado in questa casella");
                    error=list__of_errors[4];
                    getPlayer().getWindow().getSlot(slots.get(0)).setDie(a);
                    getPlayer().setMarker(getPlayer().getMarker()+i);
                    if (i==1)setAccessed(false);
                    return false;
                }
            }
        }
        else{
            System.out.println("Il dado non può essere posizionato in questo slot perchè non soddisfa le regole di posizionamento");
            error=list__of_errors[5];
            getPlayer().getWindow().getSlot(slots.get(0)).setDie(a);
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
    }

}
