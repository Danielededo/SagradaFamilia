package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;

import java.util.List;

public class ToolCard6 extends Tool {
    public ToolCard6() {
        super.setEffect("Dopo aver scelto un dado, " +
                "tira nuovamente quel dado. " +
                "Se non puoi piazzarlo riponilo nella riserva ");
        super.setName("Pennello per Pasta Salda");
        super.setValue(6);
    }

    /**
     * This method is the effect of this card called by match
     * @param dice die that need to change face value
     * @return true if effect is done false in other case
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value){
        int i=tokenpayment();
        if (i==0)return false;
        dice.get(0).randomdado();
        return true;
    }

}
