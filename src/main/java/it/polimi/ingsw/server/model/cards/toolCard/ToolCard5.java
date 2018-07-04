package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;

import java.util.List;

public class ToolCard5 extends Tool {
    public ToolCard5() {
        super.setEffect("Dopo aver scelto un dado, " +
                "scambia quel dado con un dado " +
                "sul Tracciato dei Round ");
        super.setName("Taglierina circolare");
        super.setValue(5);
    }

    /**
     * This method is the effect of this card called by match
     * @param dice die to be put in the round track
     * @param value index of the position of the die to be taken from the round track
     * @return true if effect is done false in other case
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value){
        int j=tokenpayment();
        if (j==0)return false;
        int i= match.getRoundTrackList(value).indexOf(dice.get(1));
        Die d;
        d = match.getRoundTrackList(value).get(i);
        match.getRoundTrackList(value).set(i,dice.get(0));
        match.getStock().getDicestock().remove(dice.get(0));
        match.getStock().getDicestock().add(d);
        return true;
    }

}
