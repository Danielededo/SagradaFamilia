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
        int faccia = dado.getFace();
        switch (faccia){
            case 1: dado.setFace(6);
            case 2: dado.setFace(5);
            case 3: dado.setFace(4);
            case 4: dado.setFace(3);
            case 5: dado.setFace(2);
            case 6: dado.setFace(1);
        }
        return dado;
    }

}
