package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;

import java.util.List;

public class ToolCard7 extends Tool {
    public ToolCard7() {
        super.setEffect("Tira nuovamente tutti i dadi della Riserva. " +
                "Questa carta può essere usata solo durante " +
                "il tuo secondo turno, prima di scegliere un dado ");
        super.setName("Martelletto");
        super.setValue(7);
    }

    /**
     * This method is the effect of this card called by match
     * @param match current match where take stock in which all die need to change face value
     * @return true if effect is done false in other case
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value){
        int i=tokenpayment();
        if (i==0)return false;
        for (Die die: match.getStock().getDicestock()){
            die.randomdado();
        }
        return true;
    }

}
