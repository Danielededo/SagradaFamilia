package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;

import java.util.List;

public class ToolCard11 extends Tool {
    public ToolCard11() {
        super.setEffect("Dopo aver scelto un dado, " +
                "riponilo nel Sacchetto, " +
                "poi pescane uno dal Sacchetto. " +
                "Scegli il valore del nuovo dado e piazzalo, " +
                "rispettando tutte le restrizioni di piazzamento ");
        super.setName("Diluente per Pasta Salda");
        super.setValue(11);
    }


    /**
     * This method represents the effect of the 11th tool card. It removes a die from the stock and puts it back in the bag.
     * @param dice die from the stock
     * @param match current match
     * @return boolean, true if the card was effective, false if you don't have enough tokens or if you've made mistakes
     *          while using this card.
     */
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value) {
        int i=tokenpayment();
        if (i==0)return false;
        match.getSack().adddie(dice.get(0));
        match.getStock().getDicestock().remove(dice.get(0));
        return true;
    }

}
