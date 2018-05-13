package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;

public class ToolCard10 extends Tool {
    public ToolCard10() {
        super.setEffect("Dopo aver scelto un dado," +
                "giralo sulla faccia opposta\n\n" +
                "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.");
        super.setName("Tampone Diamantato");
    }

    public Die effetto(Die dado){
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return dado;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return dado;
                }
            }
        }
        int faccia = dado.getFace();
        switch (faccia){
            case 1: {dado.setFace(6);
                     break;}
            case 2: {dado.setFace(5);
                     break;}
            case 3: {dado.setFace(4);
                     break;}
            case 4: {dado.setFace(3);
                     break;}
            case 5: {dado.setFace(2);
                     break;}
            case 6: {dado.setFace(1);
                     break;}
        }
        return dado;
    }

}
