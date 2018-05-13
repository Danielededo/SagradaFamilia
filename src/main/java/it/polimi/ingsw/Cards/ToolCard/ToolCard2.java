package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Rules;

public class ToolCard2 extends Tool {
    public ToolCard2() {
        super.setEffect("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni" +
                " di colore\n" +
                "Devi rispettare tutte le altre" +
                " restrizioni di piazzamento");
        super.setName("Pennello per Eglomise");
    }

    public GlassWindow effect(Slot slot1, Slot slot2) {
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return getPlayer().getWindow();
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return getPlayer().getWindow();
                }
            }
        }
        Die a;
        if (getPlayer().getWindow().getSlot(slot2).isOccupate()) {
            System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
            return getPlayer().getWindow();
        }
        if (!getPlayer().getWindow().getSlot(slot1).isOccupate()) {
            System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
            return getPlayer().getWindow();
        }
        a = getPlayer().getWindow().getSlot(slot1).getDice();
        Rules rules = new Rules();
        if (rules.neighboursCheck(getPlayer().getWindow(), slot2.getColumn(), slot2.getLine(), a.getDicecolor(), a.getFace())) {
            if (slot2.getValue() == 0) {
                getPlayer().getWindow().getSlot(slot2).setDie(a);
                slot1.setOccupate(false);
                slot1.setDie(null);
                setUsed(false);
                return getPlayer().getWindow();
            } else {
                if (slot2.getValue() == a.getFace()) {
                    getPlayer().getWindow().getSlot(slot2).setDie(a);
                    slot1.setOccupate(false);
                    slot1.setDie(null);
                    setUsed(false);
                    return getPlayer().getWindow();
                } else {
                    System.out.println("Non puoi posizionare il dado in questa casella");
                    return getPlayer().getWindow();
                }
            }
        }
        else{
            System.out.println("Il dado non può essere posizionato in questo slot perchè non soddisfa le regole di posizionamento");
            return getPlayer().getWindow();
        }
    }
}