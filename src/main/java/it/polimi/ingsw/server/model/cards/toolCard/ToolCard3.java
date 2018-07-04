package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;
import it.polimi.ingsw.server.model.game.Stock;

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
     * @param slot1 the slot from which you want to move the die
     * @param slot2 the slot you want to place the die into
     * @return boolean, true if the card was effective, false if you don't have enough tokens or if you've made mistakes
     *          while using this card.
     */
    @Override
    public boolean effect(Die dado1, Die dado2, boolean plusminus, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value) {
        Rules rules = new Rules();
        if (rules.getCont(getPlayer())==1){ error=list__of_errors[14];return false;}
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setAccessed(true);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    error=list__of_errors[0];
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    error=list__of_errors[0];
                    return false;
                }
            }
        }
        Die a;
        if (getPlayer().getWindow().getSlot(slot2).isOccupate()) {
            System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
            error=list__of_errors[2];
            return false;
        }
        if (!getPlayer().getWindow().getSlot(slot1).isOccupate()) {
            System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
            error=list__of_errors[3];
            return false;
        }
        a = getPlayer().getWindow().getSlot(slot1).getDice();
        getPlayer().getWindow().getSlot(slot1).setOccupate(false);
        getPlayer().getWindow().getSlot(slot1).setDie(null);
        if (rules.neighboursCheck(getPlayer().getWindow(), slot2.getColumn(), slot2.getLine(), a.getDicecolor(), a.getFace())) {
            if (slot2.getSlotcolour() == Colour.WHITE) {
                getPlayer().getWindow().getSlot(slot2).setDie(a);
                setUsed(false);
                return true;
            } else {
                if (slot2.getSlotcolour() == a.getDicecolor()) {
                    getPlayer().getWindow().getSlot(slot2).setDie(a);
                    setUsed(false);
                    return true;
                } else {
                    System.out.println("Non puoi posizionare il dado in questa casella");
                    error=list__of_errors[4];
                    getPlayer().getWindow().getSlot(slot1).setDie(a);
                    return false;
                }
            }
        }
        else{
            System.out.println("Il dado non può essere posizionato in questo slot perchè non soddisfa le regole di posizionamento");
            error=list__of_errors[5];
            getPlayer().getWindow().getSlot(slot1).setDie(a);
            return false;
        }
    }

}
