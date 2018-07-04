package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;

import java.util.List;

public class ToolCard1 extends Tool{
    public ToolCard1() {
        super.setEffect("Dopo aver scelto un dado," +
                " aumenta o dominuisci il valore" +
                " del dado scelto di 1. " +
                "Non puoi cambiare " +
                "un 6 in 1 o un 1 in 6 ");
        super.setName("Pinza Sgrossatrice");
        super.setValue(1);
    }


    /**
     * This method is the effect of tool card 1, change the value of dado1 passed as reference, added one if boolean 'plusminus'
     * is true instead remove one if is false. Return true if dado1's value is not 1 or 6 and boolean 'plusminus' is respectively
     * false and true else return false because 1 cannot changes in 6 and 6 in 1.
     * @param dice Die whose value will be change
     * @param value involves adding or removing of a unit from dice value
     * @param match reference to Match
     * @return boolean, true if the die's value is changed, false if the effect cannot be used either because of not enough favor tokens or
     * int 'value' not possible to associated to die's value
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value) {
        int i=tokenpayment();
        if (i==0) return false;
        if((dice.get(0).getFace()==1 && value==0)|| (dice.get(0).getFace()==6 && value==1)) {
            System.out.println("Non puoi cambiare un 6 in 1 o un 1 in 6, scegli un nuovo dado");
            error=list__of_errors[1];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }
        else
        if(value==1){
            dice.get(0).setFace(dice.get(0).getFace()+1);
            return true;
        }
        else{
            dice.get(0).setFace(dice.get(0).getFace()-1);
            return true;
        }
    }
}
