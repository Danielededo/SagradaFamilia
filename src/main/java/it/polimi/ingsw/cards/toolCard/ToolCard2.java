package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Rules;
import it.polimi.ingsw.game.Stock;

public class ToolCard2 extends Tool {
    public ToolCard2() {
        super.setEffect("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni" +
                " di colore. " +
                "Devi rispettare tutte le altre" +
                " restrizioni di piazzamento. ");
        super.setName("Pennello per Eglomise");
        super.setValue(2);
    }

    @Override
    public boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value) {
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
        if (rules.neighboursCheck(getPlayer().getWindow(), slot2.getColumn(), slot2.getLine(), a.getDicecolor(), a.getFace())) {
            if (slot2.getValue() == 0) {
                getPlayer().getWindow().getSlot(slot2).setDie(a);
                slot1.setOccupate(false);
                slot1.setDie(null);
                setUsed(false);
                return true;
            } else {
                if (slot2.getValue() == a.getFace()) {
                    getPlayer().getWindow().getSlot(slot2).setDie(a);
                    slot1.setOccupate(false);
                    slot1.setDie(null);
                    setUsed(false);
                    return true;
                } else {
                    System.out.println("Non puoi posizionare il dado in questa casella");
                    error=list__of_errors[4];
                    return false;
                }
            }
        }
        else{
            System.out.println("Il dado non può essere posizionato in questo slot perchè non soddisfa le regole di posizionamento");
            error=list__of_errors[5];
            return false;
        }
    }
}