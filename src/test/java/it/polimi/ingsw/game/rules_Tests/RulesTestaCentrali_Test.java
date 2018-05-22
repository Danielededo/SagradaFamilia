package it.polimi.ingsw.game.rules_Tests;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.schemeCard.AuroraeMagnificus;
import it.polimi.ingsw.cards.schemeCard.Battlo;
import it.polimi.ingsw.cards.schemeCard.FractalDrops;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Rules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RulesTestaCentrali_Test {

    @Test
    void testaRulesCentrali() {
        //non è il primo dado
        Player a = new Player("a");
        a.setWindow(new FractalDrops());

        Player b = new Player("b");
        Player c = new Player("c");

        Rules r = new Rules();
        Die dadoProva = new Die(6, Colour.YELLOW);

        a.getWindow().getSlot(0, 2).setDie(dadoProva);
        System.out.println(a.getWindow().toString());
        System.out.println(a.getWindow().getSlot(0,2));

        Die dado = new Die(5, Colour.BLUE);
        Slot choice = a.getWindow().getSlot(1, 1);
        System.out.println(a.getWindow().getSlot(1,1));

        a.setWindow(r.diePlacing(a, choice, dado));

        System.out.println(a.getWindow().toString());
        System.out.println(a.getWindow().getSlot(1,1));

        assertEquals(Colour.BLUE,a.getWindow().getSlot(choice).getDice().getDicecolor());
        assertEquals(5, a.getWindow().getSlot(choice).getDice().getFace());


        //altri valori, questo non va a buon fine
        Die dadoProva2 = new Die(1, Colour.YELLOW);
        b.setWindow(new AuroraeMagnificus());
        b.setContTurn(1);

        b.getWindow().getSlot(2, 3).setDie(dadoProva2);

        Slot choice2 = b.getWindow().getSlot(2, 2);
        System.out.println(b.getWindow().getSlot(2, 2));

        b.setWindow(r.diePlacing(b, choice2, dado));
        System.out.println(b.getWindow().getSlot(choice2));

        assertEquals(false, b.getWindow().getSlot(choice2.getLine(), choice2.getColumn()).isOccupate());


        //altri valori, occupato, fallisce
        Die dadoProva3 = new Die(3, Colour.YELLOW);
        c.setWindow(new Battlo());
        c.getWindow().getSlot(2, 3).setDie(dadoProva3);

        Die dado3 = new Die(4, Colour.PURPLE);
        Slot choice3 = c.getWindow().getSlot(2, 3);
        System.out.println(c.getWindow().getSlot(2,3));

        c.setWindow(r.diePlacing(c, choice3, dado3));

        assertEquals(false, r.rules(c, choice3, dado3));


        System.out.println(c.getWindow().toString());
        System.out.println(choice3);
    }
}
