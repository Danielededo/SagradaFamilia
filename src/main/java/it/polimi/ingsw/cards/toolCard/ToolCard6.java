package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;

public class ToolCard6 extends Tool {
    public ToolCard6() {
        super.setEffect("Dopo aver scelto un dado," +
                "tira nuovamente quel dado\n\n" +
                "Se non puoi piazzarlo riponilo nella riserva");
        super.setName("Pennello per Pasta Salda");
    }

    public boolean effect(Die die){
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
        die.randomdado();
        return true;
    }
}
