package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.toolCard.ToolCard10;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testToolCard10_Test {

    @Test
    void testingTool10(){
        Die dado = new Die(4, Colour.BLUE);
        ToolCard10 carta10 = new ToolCard10();
        Player a=new Player("mario");
        a.setMarker(3);
        carta10.setPlayer(a);
        ArrayList<Die> dice = new ArrayList<>();
        dice.add(dado);
        carta10.effect(dice,null,null,0);

        assertEquals(3,dado.getFace());
    }
}
