package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Rules;

public class ToolCard11 extends Tool {
    public ToolCard11() {
        super.setEffect("Dopo aver scelto un dado," +
                "riponilo nel Sacchetto," +
                "poi pescane uno dal Sacchetto\n\n" +
                "Scegli il valore del nuovo dado e piazzalo," +
                "rispettando tutte le restrizioni di piazzamento");
        super.setName("Diluente per Pasta Salda");
        super.setValue(11);
    }

    public boolean effect(Die die, Match match, Slot slot,int value) {
        if ((value <= 6 && value > 0)&& !slot.isOccupate()) {
            if (!isUsed()) {
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
            match.getSack().adddie(die);
            Die d = match.getSack().extractdie();
            d.setFace(value);
            Rules rules = new Rules();
            rules.diePlacing(getPlayer(), slot, d);
            if (slot.isOccupate()) {
                System.out.println("Operazione conclusa con successo");
                return true;
            } else {
                System.out.println("Piazzamento non andata a buon fine");
                return false;
            }
        }else System.out.println("Intero errato o slot già occupato");
        return false;
    }

}
