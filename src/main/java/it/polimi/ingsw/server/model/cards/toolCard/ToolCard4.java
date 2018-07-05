package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;

import java.util.List;

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
     * @param slots slots where take and place die
     * @return boolean, true if the card was effective, false if you don't have enough tokens or if you've made mistakes
     *          while using this card.
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value) {
        Rules rules = new Rules();
        boolean e = false, f = false;
        int j=tokenpayment();
        if (j==0)return false;
        Die a= new Die();
        Die b= new Die();
        int i = 0;
        while (i != 2) {
            if (i == 0) {
                if (getPlayer().getWindow().getSlot(slots.get(1)).isOccupate()) {
                    error=list__of_errors[2];
                    getPlayer().setMarker(getPlayer().getMarker()+j);
                    if (j==1)setAccessed(false);
                    return false;
                }
                if (!getPlayer().getWindow().getSlot(slots.get(0)).isOccupate()) {
                    error=list__of_errors[3];
                    getPlayer().setMarker(getPlayer().getMarker()+j);
                    if (j==1)setAccessed(false);
                    return false;
                }
                a = getPlayer().getWindow().getSlot(slots.get(0)).getDice();
                getPlayer().getWindow().getSlot(slots.get(0)).setOccupate(false);
                getPlayer().getWindow().getSlot(slots.get(0)).setDie(null);
                rules.diePlacing(getPlayer(), slots.get(1), a);
                if (!getPlayer().getWindow().getSlot(slots.get(1)).isOccupate()) {
                    getPlayer().getWindow().getSlot(slots.get(0)).setDie(a);
                    error=list__of_errors[6];
                    getPlayer().setMarker(getPlayer().getMarker()+j);
                    if (j==1)setAccessed(false);
                    return false;
                } else {
                    e = true;
                    i++;
                }
            } else {
                if (getPlayer().getWindow().getSlot(slots.get(3)).isOccupate()) {
                    error=list__of_errors[2];
                    i++;
                }else if (!getPlayer().getWindow().getSlot(slots.get(2)).isOccupate()) {
                    error=list__of_errors[3];
                    i++;
                }else {
                    b = getPlayer().getWindow().getSlot(slots.get(2)).getDice();
                    getPlayer().getWindow().getSlot(slots.get(2)).setOccupate(false);
                    getPlayer().getWindow().getSlot(slots.get(2)).setDie(null);
                    rules.diePlacing(getPlayer(), slots.get(3), b);
                    if (!getPlayer().getWindow().getSlot(slots.get(3)).isOccupate()) {
                        getPlayer().getWindow().getSlot(slots.get(2)).setDie(b);
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
            return true;
        }
        else{
            rules.diePlacing(getPlayer(),slots.get(0),a);
            getPlayer().getWindow().getSlot(slots.get(1)).setOccupate(false);
            getPlayer().getWindow().getSlot(slots.get(1)).setDie(null);
            getPlayer().setMarker(getPlayer().getMarker()+j);
            if (j==1)setAccessed(false);
            return false;
        }
    }

}
