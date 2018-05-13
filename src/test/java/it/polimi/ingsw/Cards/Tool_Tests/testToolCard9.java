package it.polimi.ingsw.Cards.Tool_Tests;

import it.polimi.ingsw.Cards.SchemeCard.LuzCelestial;
import it.polimi.ingsw.Cards.ToolCard.ToolCard9;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Rules;
import org.junit.jupiter.api.Test;

public class testToolCard9 {

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
        a.setWindow(r.diePlacing(partita, a, a.getWindow().getSlot(1,0), prova2));

        Die prova3 = new Die(3,Colour.BLUE);
        a.setWindow(r.diePlacing(partita, a, a.getWindow().getSlot(2,1), prova3));

        ToolCard9 tool9 = new ToolCard9();
        tool9.setPlayer(a);

        a.setWindow(tool9.effetto9(a, dado, a.getWindow().getSlot(2,2)));
        System.out.println(a.getWindow().toString());

        a.setWindow(tool9.effetto9(a, dado, a.getWindow().getSlot(3,4)));
        System.out.println(a.getWindow().toString());




    }
}
