package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;

import java.util.List;

public class ToolCard8 extends Tool {
    public ToolCard8() {
        super.setEffect("Dopo il tuo primo turno scegli " +
                "immediatamente un altro dado. " +
                "Salta il tuo secondo turno in questo round ");
        super.setName("Tenaglia a Rotelle");
        super.setValue(8);
    }

    /**
     * This method is the effect of this card called by match
     * @param dice die to be put in slot1
     * @param match match has the collection of die in from where remove dado1
     * @param slots slot of window where place the die
     * @return true if effect is done false in other case
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value){
        int i=tokenpayment();
        if (i==0)return false;
        Rules rules=new Rules();
        if (getPlayer().getContTurn()==1 && !slots.get(0).isOccupate()){
            rules.diePlacing(getPlayer(),slots.get(0),dice.get(0));
            if (slots.get(0).isOccupate()){
                getPlayer().setMissednextturn(true);
                match.getStock().getDicestock().remove(dice.get(0));
                return true;
            }else {
                error=list__of_errors[4];
                getPlayer().setMarker(getPlayer().getMarker()+i);
                if (i==1)setAccessed(false);
                return false;
            }
        }else {
            error=list__of_errors[8];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
    }

}
