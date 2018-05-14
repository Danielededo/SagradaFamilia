package it.polimi.ingsw.Game.Rules_Tests;

import it.polimi.ingsw.Cards.SchemeCard.Gravitas;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Rules;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RulesTestaRegoleDefinitive {
    @Test
    void TestRulesGenerale(){
        //Primo dado
        Player a = new Player("a");
        a.setWindow(new Gravitas());
        Player b = new Player("b");
        Rules r = new Rules();

        Die dado = new Die(5, Colour.BLUE);
        System.out.println(a.getWindow().getSlot(0,3));

        a.setWindow(r.diePlacing( a, a.getWindow().getSlot(0,3), dado));

        assertEquals(Colour.BLUE, a.getWindow().getSlot(0,3).getDice().getDicecolor());

        System.out.println(a.getWindow().toString());
        System.out.println(a.getWindow().getSlot(0,3));

        //secondo

        Die dado1 = new Die(3,Colour.YELLOW);
        System.out.println(a.getWindow().getSlot(0,2));

        a.setWindow(r.diePlacing(a , a.getWindow().getSlot(0,2), dado1));
        System.out.println(a.getWindow().toString());

        assertEquals(Colour.YELLOW, a.getWindow().getSlot(0,2).getDice().getDicecolor());

        //fallimento vicini
        Die dado2 = new Die(3,Colour.YELLOW);
        System.out.println(a.getWindow().toString());

        a.setWindow(r.diePlacing(a , a.getWindow().getSlot(0,1), dado2));
        System.out.println(a.getWindow().toString());

        assertEquals(false, r.rules(a, a.getWindow().getSlot(0,1), dado2));


    }
}
