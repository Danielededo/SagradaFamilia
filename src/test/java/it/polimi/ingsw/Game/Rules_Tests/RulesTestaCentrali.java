package it.polimi.ingsw.Game.Rules_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.AuroraeMagnificus;
import it.polimi.ingsw.Cards.SchemeCard.Battlo;
import it.polimi.ingsw.Cards.SchemeCard.FractalDrops;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Rules;
import org.junit.jupiter.api.Test;

public class RulesTestaCentrali {

    @Test
    void testaRulesCentrali() {
        //non è il primo round
        Player a = new Player("a");
        a.setWindow(new FractalDrops());
        a.setContTurn(1);

        Player b = new Player("b");
        Player c = new Player("c");
        Match partita = new Match(a,b);
        partita.setRound(2);

        Rules r = new Rules();
        Die dadoProva = new Die(6, Colour.YELLOW);

        a.getWindow().getSlot(0, 2).setDie(dadoProva);
        System.out.println(a.getWindow().toString());
        System.out.println(a.getWindow().getSlot(0,2));

        Die dado = new Die(5, Colour.BLUE);
        Slot choice = a.getWindow().getSlot(1, 1);
        System.out.println(a.getWindow().getSlot(1,1));

        a.setWindow(r.diePlacing(partita, a, choice, dado));

        System.out.println(a.getWindow().toString());
        System.out.println(a.getWindow().getSlot(1,1));


        //altri valori, questo non va a buon fine
        Die dadoProva2 = new Die(1, Colour.YELLOW);
        b.setWindow(new AuroraeMagnificus());
        b.setContTurn(1);

        b.getWindow().getSlot(2, 3).setDie(dadoProva2);

        Die dado2 = new Die(5, Colour.BLUE);
        Slot choice2 = b.getWindow().getSlot(2, 2);
        System.out.println(b.getWindow().getSlot(2, 2));

        b.setWindow(r.diePlacing(partita, b, choice2, dado2));
        System.out.println(choice2);


        //altri valori, occupato, fallisce
        Die dadoProva3 = new Die(3, Colour.YELLOW);
        c.setWindow(new Battlo());
        c.getWindow().getSlot(2, 3).setDie(dadoProva3);
        c.setContTurn(1);

        Die dado3 = new Die(4, Colour.PURPLE);
        Slot choice3 = c.getWindow().getSlot(2, 3);
        System.out.println(c.getWindow().getSlot(2,3));

        c.setWindow(r.diePlacing(partita, c, choice3, dado3));
        System.out.println(c.getWindow().toString());
        System.out.println(choice3);
    }
}
