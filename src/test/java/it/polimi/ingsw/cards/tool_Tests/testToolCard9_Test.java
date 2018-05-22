package it.polimi.ingsw.cards.tool_Tests;

import it.polimi.ingsw.cards.schemeCard.LuzCelestial;
import it.polimi.ingsw.cards.toolCard.ToolCard9;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Rules;
import org.junit.jupiter.api.Test;

public class testToolCard9_Test {

    @Test
    void testingTool9(){
        Die dado = new Die(4, Colour.BLUE);
        ToolCard9 carta9 = new ToolCard9();
        carta9.setUsed(true);

        Player b = new Player("b");
        Player a = new Player("a");
        Match partita = new Match(a,b);
        partita.setRound(2);
        Rules r = new Rules();


        a.setWindow(new LuzCelestial());
        Die prova1 = new Die(3,Colour.BLUE);
        a.getWindow().getSlot(0,0).setDie(prova1);

        Die prova2 = new Die(2,Colour.PURPLE);
        a.setWindow(r.diePlacing(a, a.getWindow().getSlot(1,0), prova2));

        Die prova3 = new Die(3,Colour.BLUE);
        a.setWindow(r.diePlacing(a, a.getWindow().getSlot(2,1), prova3));

        ToolCard9 tool9 = new ToolCard9();
        tool9.setPlayer(a);

        if (tool9.effect(dado, null,false,null,null, a.getWindow().getSlot(2,2),null,null,null,0))System.out.println("operazione riuscita");
        else System.out.println("operazione fallita");
        System.out.println(a.getWindow().toString());

        if (tool9.effect(dado,null,false,null,null, a.getWindow().getSlot(3,4),null,null,null,0))System.out.println("operazione riuscita");
        else System.out.println("operazione fallita");
        System.out.println(a.getWindow().toString());




    }
}
