package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;
import it.polimi.ingsw.server.model.game.Stock;

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
     * @param stock attribute of Match
     * @param slot1 Slot of player's Glasswindow that contains the die
     * @param slot2 Slot of player's Glasswindow that will be assigned slot1's die
     * @return boolean, true if slot1's die has been moved correctly, false if the effect cannot be used either because of not enough favor tokens
     * or slot1's die is null or slot2's die is not null.
     */
    @Override
    public boolean effect(Die dado1, Die dado2, boolean plusminus, Match match, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value) {
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
            if (slot2.getValue() == 0) {
                getPlayer().getWindow().getSlot(slot2).setDie(a);
                setUsed(false);
                return true;
            } else {
                if (slot2.getValue() == a.getFace()) {
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