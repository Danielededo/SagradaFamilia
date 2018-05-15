package it.polimi.ingsw.Cards.Tool_Tests;

import it.polimi.ingsw.Cards.ToolCard.ToolCard10;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testToolCard10 {

    @Test
    void testingTool10(){
        Die dado = new Die(4, Colour.BLUE);
        ToolCard10 carta10 = new ToolCard10();
        carta10.setUsed(true);

        carta10.effetto(dado);

        assertEquals(3,dado.getFace());
    }
}
