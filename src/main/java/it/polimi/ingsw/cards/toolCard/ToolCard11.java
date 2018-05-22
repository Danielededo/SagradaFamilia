package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Rules;
import it.polimi.ingsw.game.Stock;

public class ToolCard11 extends Tool {
    public ToolCard11() {
        super.setEffect("Dopo aver scelto un dado, " +
                "riponilo nel Sacchetto, " +
                "poi pescane uno dal Sacchetto. " +
                "Scegli il valore del nuovo dado e piazzalo, " +
                "rispettando tutte le restrizioni di piazzamento. ");
        super.setName("Diluente per Pasta Salda");
        super.setValue(11);
    }

    
    public boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value) {
        if ((value <= 6 && value > 0)&& !slot1.isOccupate()) {
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
            partita.getSack().adddie(dado1);
            Die d = partita.getSack().extractdie();
            d.setFace(value);
            Rules rules = new Rules();
            rules.diePlacing(getPlayer(), slot1, d);
            if (slot1.isOccupate()) {
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