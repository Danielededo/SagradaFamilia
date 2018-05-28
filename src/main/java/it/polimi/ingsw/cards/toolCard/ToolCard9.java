package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Rules;
import it.polimi.ingsw.game.Stock;

public class ToolCard9 extends Tool {
    public ToolCard9() {
        super.setEffect("Dopo aver scelto un dado, " +
                "piazzalo in una casella che " +
                "non sia adiacente a un altro dado. " +
                "Devi rispettare tutte le " +
                "restrizioni di piazzamento. ");
        super.setName("Riga in Sughero");
        super.setValue(9);
    }

    @Override
    public boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value){
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
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
        Rules r = new Rules();
        if(!r.occupiedSlot(slot1)){
            System.out.println("Questo slot è occupato, scegli un altro slot.");
            error=list__of_errors[2];
            return false;
        }
        if(!r.colourCheck(slot1.getSlotcolour(), dado1.getDicecolor())){
            System.out.println("Non puoi mettere un dado di questo colore in questo slot, scegli un altro slot.");
            error=list__of_errors[4];
            return false;
        }
        if(!r.numberCheck(slot1.getValue(), dado1.getFace())){
            System.out.println("Non puoi mettere un dado con questa faccia in questo slot, scegli un altro slot.");
            error=list__of_errors[4];
            return false;
        }

        if(!super.getPlayer().getWindow().getSlot(slot1.getLine() - 1, slot1.getColumn()).isOccupate()) {
            if (!super.getPlayer().getWindow().getSlot(slot1.getLine(), slot1.getColumn() + 1).isOccupate()) {
                if (!super.getPlayer().getWindow().getSlot(slot1.getLine() + 1, slot1.getColumn()).isOccupate()) {
                    if (!super.getPlayer().getWindow().getSlot(slot1.getLine(), slot1.getColumn() - 1).isOccupate()) {
                        super.getPlayer().getWindow().getSlot(slot1.getLine(), slot1.getColumn()).setDie(dado1);
                        this.setUsed(false);
                        return true;
                    }
                }
            }
        }
        System.out.println("C'è un dado in uno slot adiacente a quello scelto, scegli un altro slot.");
        error=list__of_errors[4];
        return false;
    }

}



