package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Rules;
import it.polimi.ingsw.game.Stock;

public class ToolCard4 extends Tool {
    public ToolCard4() {
        super.setEffect("Muovi esattamente due dadi," +
                " rispettando tutte le restrizioni di" +
                " piazzamento. ");
        super.setName("Lathekin");
        super.setValue(4);
    }

    @Override
    public boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value) {
        boolean e = false, f = false;
        if (!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
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
        Die a= new Die();
        Die b= new Die();
        Rules rules = new Rules();
        int i = 0;
        while (i != 2) {
            if (i == 0) {
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
                getPlayer().setWindow(rules.diePlacing(getPlayer(), slot2, a));
                if (getPlayer().getWindow().getSlot(slot2).isOccupate()) {
                    getPlayer().getWindow().getSlot(slot1).setOccupate(false);
                    getPlayer().getWindow().getSlot(slot1).setDie(null);
                    e = true;
                    i++;
                } else {
                    System.out.println("Il dado selezionato non può essere spostato in questa casella");
                    error=list__of_errors[6];
                    return false;
                }
            } else {
                if (getPlayer().getWindow().getSlot(slot4).isOccupate()) {
                    System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
                    error=list__of_errors[2];
                    i++;
                }else if (!getPlayer().getWindow().getSlot(slot3).isOccupate()) {
                    System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
                    error=list__of_errors[3];
                    i++;
                }else {
                    b = getPlayer().getWindow().getSlot(slot3).getDice();
                    getPlayer().setWindow(rules.diePlacing(getPlayer(), slot4, b));
                    if (getPlayer().getWindow().getSlot(slot4).isOccupate()) {
                        getPlayer().getWindow().getSlot(slot3).setOccupate(false);
                        getPlayer().getWindow().getSlot(slot3).setDie(null);
                        f = true;
                        i++;
                    } else {
                        System.out.println("Il dado selezionato non può essere spostato in questa casella");
                        error=list__of_errors[6];
                        i++;
                    }
                }
            }
        }
        if (e && f)
            return true;
        else{
            getPlayer().setWindow(rules.diePlacing(getPlayer(),slot1,a));
            getPlayer().getWindow().getSlot(slot2).setOccupate(false);
            getPlayer().getWindow().getSlot(slot2).setDie(null);
            return false;
        }
    }

}
