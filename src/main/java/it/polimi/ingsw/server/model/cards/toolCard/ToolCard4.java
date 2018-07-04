package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;
import it.polimi.ingsw.server.model.game.Stock;

public class ToolCard4 extends Tool {
    public ToolCard4() {
        super.setEffect("Muovi esattamente due dadi," +
                " rispettando tutte le restrizioni di" +
                " piazzamento ");
        super.setName("Lathekin");
        super.setValue(4);
    }

    /**
     * This method represents the effect of the 4th tool card. It lets you move exactly two dice already placed on your
     * scheme card to different slots, obeying every placement rule.
     * @param slot1 the slot from which you want to move the first die
     * @param slot2 the slot you want to move the first die into
     * @param slot3 the slot from which you want to move the second die
     * @param slot4 the slot you want to move the second die into
     * @return boolean, true if the card was effective, false if you don't have enough tokens or if you've made mistakes
     *          while using this card.
     */
    @Override
    public boolean effect(Die dado1, Die dado2, boolean plusminus, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value) {
        Rules rules = new Rules();
        if (rules.getCont(getPlayer())<2){ error=list__of_errors[14];return false;}
        int marker=getPlayer().getMarker();
        boolean e = false, f = false;
        if (!isUsed()) {
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
        Die a= new Die();
        Die b= new Die();
        int i = 0;
        while (i != 2) {
            if (i == 0) {
                if (getPlayer().getWindow().getSlot(slot2).isOccupate()) {
                    System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
                    error=list__of_errors[2];
                    setUsed(false);
                    getPlayer().setMarker(marker);
                    return false;
                }
                if (!getPlayer().getWindow().getSlot(slot1).isOccupate()) {
                    System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
                    error=list__of_errors[3];
                    setUsed(false);
                    getPlayer().setMarker(marker);
                    return false;
                }
                a = getPlayer().getWindow().getSlot(slot1).getDice();
                getPlayer().getWindow().getSlot(slot1).setOccupate(false);
                getPlayer().getWindow().getSlot(slot1).setDie(null);
                rules.diePlacing(getPlayer(), slot2, a);
                if (!getPlayer().getWindow().getSlot(slot2).isOccupate()) {
                    getPlayer().getWindow().getSlot(slot1).setDie(a);
                    System.out.println("Il dado selezionato non può essere spostato in questa casella");
                    error=list__of_errors[6];
                    setUsed(false);
                    getPlayer().setMarker(marker);
                    return false;
                } else {
                    e = true;
                    i++;
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
                    getPlayer().getWindow().getSlot(slot3).setOccupate(false);
                    getPlayer().getWindow().getSlot(slot3).setDie(null);
                    rules.diePlacing(getPlayer(), slot4, b);
                    if (!getPlayer().getWindow().getSlot(slot4).isOccupate()) {
                        getPlayer().getWindow().getSlot(slot3).setDie(b);
                        System.out.println("Il dado selezionato non può essere spostato in questa casella");
                        error=list__of_errors[6];
                        i++;
                    } else {
                        f = true;
                        i++;
                    }
                }
            }
        }
        if (e && f){
            this.setUsed(false);
            return true;
        }
        else{
            getPlayer().setWindow(rules.diePlacing(getPlayer(),slot1,a));
            getPlayer().getWindow().getSlot(slot2).setOccupate(false);
            getPlayer().getWindow().getSlot(slot2).setDie(null);
            setUsed(false);
            getPlayer().setMarker(marker);
            return false;
        }
    }

}
