package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Rules;
import it.polimi.ingsw.server.model.game.Stock;

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
     * @param dado1 die to be put in slot1
     * @param stock collection of die in from where remove dado1
     * @param slot1 slot of window where place the die
     * @return true if effect is done false in other case
     */
    @Override
    public boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value){
        if(!isUsed()) {
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
        Rules rules=new Rules();
        if (getPlayer().getContTurn()==1 && !slot1.isOccupate()){
            getPlayer().setWindow(rules.diePlacing(getPlayer(),slot1,dado1));
            if (slot1.isOccupate()){
                getPlayer().setMissednext_turn(true);
                stock.getDicestock().remove(dado1);
                this.setUsed(false);
                return true;
            }else {
                System.out.println("Non è stato possibile piazzare il dado in questa casella");
                error=list__of_errors[4];
                return false;
            }
        }else {
            System.out.println("Non puoi utilizzare questa carta nel tuo secondo turno");
            error=list__of_errors[8];
            return false;
        }
    }

}
