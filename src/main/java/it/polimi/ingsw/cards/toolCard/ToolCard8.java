package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Rules;

public class ToolCard8 extends Tool {
    public ToolCard8() {
        super.setEffect("Dopo il tuo primo turno scegli" +
                "immediatamente un altro dado\n\n" +
                "Salta il tuo secondo turno in questo round");
        super.setName("Tenaglia a Rotelle");
        super.setValue(8);
    }

    public boolean effect(Die die, Slot slot){
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return false;
                }
            }
        }
        Rules rules=new Rules();
        if (getPlayer().getContTurn()==1 && !slot.isOccupate()){
            getPlayer().setWindow(rules.diePlacing(getPlayer(),slot,die));
            if (slot.isOccupate()){
                getPlayer().setMissednext_turn(true);
                return true;
            }else {
                System.out.println("Non è stato possibile piazzare il dado in questa casella");
                return false;
            }
        }else {
            System.out.println("Non puoi utilizzare questa carta nel tuo secondo turno");
            return false;
        }
    }

}
