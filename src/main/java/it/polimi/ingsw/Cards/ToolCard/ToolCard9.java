package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Rules;

public class ToolCard9 extends Tool {
    public ToolCard9() {
        super.setEffect("Dopo aver scelto un dado," +
                "piazzalo in una casella che" +
                "non sia adiacente a un altro dado\n\n" +
                "Devi rispettare tutte le " +
                "restrizioni di piazzamento");
        super.setName("Riga in Sughero");
    }

    public GlassWindow effetto9(Player giocatore, Die dado, Slot choice){
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return giocatore.getWindow();
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return giocatore.getWindow();
                }
            }
        }
        Rules r = new Rules();
        if(!r.occupiedSlot(choice)){
            System.out.println("Questo slot è occupato, scegli un altro slot.");
            return giocatore.getWindow();
        }
        if(!r.colourCheck(choice.getSlotcolour(), dado.getDicecolor())){
            System.out.println("Non puoi mettere un dado di questo colore in questo slot, scegli un altro slot.");
            return giocatore.getWindow();
        }
        if(!r.numberCheck(choice.getValue(), dado.getFace())){
            System.out.println("Non puoi mettere un dado con questa faccia in questo slot, scegli un altro slot.");
            return giocatore.getWindow();
        }

        if(!giocatore.getWindow().getSlot(choice.getLine() - 1, choice.getColumn()).isOccupate()) {
            if (!giocatore.getWindow().getSlot(choice.getLine(), choice.getColumn() + 1).isOccupate()) {
                if (!giocatore.getWindow().getSlot(choice.getLine() + 1, choice.getColumn()).isOccupate()) {
                    if (!giocatore.getWindow().getSlot(choice.getLine(), choice.getColumn() - 1).isOccupate()) {
                        giocatore.getWindow().getSlot(choice.getLine(), choice.getColumn()).setDie(dado);
                        return giocatore.getWindow();
                    }
                }
            }
        }
        System.out.println("C'è un dado in uno slot adiacente a quello scelto, scegli un altro slot.");
        return giocatore.getWindow();
    }




}



