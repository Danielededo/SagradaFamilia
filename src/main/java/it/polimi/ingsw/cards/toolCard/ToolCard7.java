package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Stock;

public class ToolCard7 extends Tool {
    public ToolCard7() {
        super.setEffect("Tira nuovamente tutti i dadi della Riserva. " +
                "Questa carta può essere usata solo durante " +
                "il tuo secondo turno, prima di scegliere un dado. ");
        super.setName("Martelletto");
        super.setValue(7);
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
        if (getPlayer().getContTurn()==2){
            for (Die die:stock.getDicestock()){
                die.randomdado();
            }
            return true;
        }else System.out.println("Non puoi utilizzare l'effetto della carta perchè non è il tuo secondo turno");
            return false;
    }

}