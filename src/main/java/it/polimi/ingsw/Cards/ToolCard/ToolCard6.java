package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Die;

public class ToolCard6 extends Tool {
    public ToolCard6() {
        super.setEffect("Dopo aver scelto un dado," +
                "tira nuovamente quel dado\n\n" +
                "Se non puoi piazzarlo riponilo nella riserva");
        super.setName("Pennello per Pasta Salda");
    }

    public Die effect(Die die){
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return die;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return die;
                }
            }
        }
        die.randomdado();
        return die;
    }
}
