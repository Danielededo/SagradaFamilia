package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Rules;

public class ToolCard8 extends Tool {
    public ToolCard8() {
        super.setEffect("Dopo il tuo primo turno scegli" +
                "immediatamente un altro dado\n\n" +
                "Salta il tuo secondo turno in questo round");
        super.setName("Tenaglia a Rotelle");
    }

    public GlassWindow effect(Die die, Slot slot){
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return getPlayer().getWindow();
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return getPlayer().getWindow();
                }
            }
        }
        Rules rules=new Rules();
        if (getPlayer().getContTurn()==1 && !slot.isOccupate()){
            getPlayer().setWindow(rules.diePlacing(getPlayer(),slot,die));
            if (slot.isOccupate()){
                getPlayer().setMissednext_turn(true);
                return getPlayer().getWindow();
            }else {
                System.out.println("Non è stato possibile piazzare il dado in questa casella");
                return getPlayer().getWindow();
            }
        }else {
            System.out.println("Non puoi utilizzare questa carta nel tuo secondo turno");
            return getPlayer().getWindow();
        }
    }
}
