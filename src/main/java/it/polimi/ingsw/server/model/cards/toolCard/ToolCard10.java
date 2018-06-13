package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Stock;

public class ToolCard10 extends Tool {
    public ToolCard10() {
        super.setEffect("Dopo aver scelto un dado, " +
                "giralo sulla faccia opposta. " +
                "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc. ");
        super.setName("Tampone Diamantato");
        super.setValue(10);
    }

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
        int faccia = dado1.getFace();
        switch (faccia){
            case 1: {dado1.setFace(6);
                     break;}
            case 2: {dado1.setFace(5);
                     break;}
            case 3: {dado1.setFace(4);
                     break;}
            case 4: {dado1.setFace(3);
                     break;}
            case 5: {dado1.setFace(2);
                     break;}
            case 6: {dado1.setFace(1);
                     break;}
        }
        this.setUsed(false);
        return true;
    }

}
