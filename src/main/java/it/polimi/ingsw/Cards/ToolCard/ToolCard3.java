package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Rules;

public class ToolCard3 extends Tool {
    public ToolCard3() {
        super.setEffect("Muovi un qualsiasi dado nella tua" +
                " vetrata ignorando le restrizioni" +
                " di valore\n" +
                "Devi rispettare tutte le altre" +
                " restrizioni di piazzamento");
        super.setName("Alesatore per lamina di rame");
    }
    public boolean effect(Slot slot1, Slot slot2) {
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return false;
                }
            }
        }
        Die a= new Die();
        if (getPlayer().getWindow().getSlot(slot2).isOccupate()) {
            System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
            return false;
        }
        if (!getPlayer().getWindow().getSlot(slot1).isOccupate()) {
            System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
            return false;
        }
        a.setFace(getPlayer().getWindow().getSlot(slot1).getDice().getFace());
        a.setDicecolor(getPlayer().getWindow().getSlot(slot1).getDice().getDicecolor());
        Rules rules = new Rules();
        if (rules.neighboursCheck(getPlayer().getWindow(), slot2.getColumn(), slot2.getLine(), a.getDicecolor(), a.getFace())) {
            if (slot2.getSlotcolour() == Colour.WHITE) {
                getPlayer().getWindow().getSlot(slot2).setDie(a);
                slot1.setOccupate(false);
                slot1.setDie(null);
                setUsed(false);
                return true;
            } else {
                if (slot2.getSlotcolour() == a.getDicecolor()) {
                    getPlayer().getWindow().getSlot(slot2).setDie(a);
                    slot1.setOccupate(false);
                    slot1.setDie(null);
                    setUsed(false);
                    return true;
                } else {
                    System.out.println("Non puoi posizionare il dado in questa casella");
                    return false;
                }
            }
        }
        else{
            System.out.println("Il dado non può essere posizionato in questo slot perchè non soddisfa le regole di posizionamento");
            return false;
        }
    }
}
