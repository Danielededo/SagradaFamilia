package it.polimi.ingsw.cards.tool_Tests;

import it.polimi.ingsw.cards.toolCard.ToolCard10;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
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
