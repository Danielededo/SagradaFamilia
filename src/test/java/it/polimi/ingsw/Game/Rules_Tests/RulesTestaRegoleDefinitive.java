package it.polimi.ingsw.Game.Rules_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.Gravitas;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Rules;
import org.junit.jupiter.api.Test;

public class RulesTestaRegoleDefinitive {
    @Test
    void TestRulesGenerale(){
        Player a = new Player("a");
        a.setWindow(new Gravitas());
        a.setContTurn(1);

        Player b = new Player("b");
        Match partita = new Match(a,b);
        partita.setRound(1);
        Rules r = new Rules();

        Die dado = new Die(5, Colour.BLUE);
        System.out.println(a.getWindow().getSlot(0,3));

        a.setWindow(r.diePlacing(partita, a, a.getWindow().getSlot(0,3), dado));

        System.out.println(a.getWindow().toString());
        System.out.println(a.getWindow().getSlot(0,3));

        //secondo turno

        partita.setRound(1);
        a.setContTurn(2);
        Die dado1 = new Die(3,Colour.YELLOW);
        System.out.println(a.getWindow().getSlot(0,2));

        a.setWindow(r.diePlacing(partita, a , a.getWindow().getSlot(0,2), dado1));
        System.out.println(a.getWindow().toString());

        //fallimento vicini
        partita.setRound(6);
        a.setContTurn(1);
        Die dado2 = new Die(3,Colour.YELLOW);
        System.out.println(a.getWindow().toString());

        a.setWindow(r.diePlacing(partita, a , a.getWindow().getSlot(0,1), dado2));
        System.out.println(a.getWindow().toString());
    }
}
