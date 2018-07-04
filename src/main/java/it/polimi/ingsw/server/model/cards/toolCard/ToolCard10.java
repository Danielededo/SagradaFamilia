package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;

import java.util.List;

public class ToolCard10 extends Tool {
    public ToolCard10() {
        super.setEffect("Dopo aver scelto un dado, " +
                "giralo sulla faccia opposta. " +
                "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc. ");
        super.setName("Tampone Diamantato");
        super.setValue(10);
    }

    /**
     * This method represents the effect of the 10th tool card. Once the player has chosen a die from the stock, he can
     * change the value with the one on the opposite face.
     * @param dice the die you want to change
     * @return boolean, true if the card was effective, false if you don't have enough tokens or if you've made mistakes
     *          while using this card.
     */
    @Override
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value){
        int i=tokenpayment();
        if (i==0)return false;
        int faccia = dice.get(0).getFace();
        switch (faccia){
            case 1: {dice.get(0).setFace(6);
                     break;}
            case 2: {dice.get(0).setFace(5);
                     break;}
            case 3: {dice.get(0).setFace(4);
                     break;}
            case 4: {dice.get(0).setFace(3);
                     break;}
            case 5: {dice.get(0).setFace(2);
                     break;}
            case 6: {dice.get(0).setFace(1);
                     break;}
        }
        return true;
    }
}
