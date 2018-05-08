package it.polimi.ingsw.Game;


import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.AuroraeMagnificus;
import it.polimi.ingsw.Cards.SchemeCard.Battlo;
import it.polimi.ingsw.Cards.SchemeCard.FractalDrops;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import org.junit.jupiter.api.Test;

public class Rules_Test {
    @Test
    void testaRulesCentrali() {
        //non è il primo round

        Rules r = new Rules();
        Die dadoProva = new Die(6, Colour.YELLOW);

        GlassWindow windowA = new FractalDrops();
        windowA.getSlot(0, 2).setDie(dadoProva);
        System.out.println(windowA.toString());

        Die dado = new Die(5, Colour.BLUE);
        Slot choice = windowA.getSlot(1, 1);
        System.out.println(choice);

        windowA = r.diePlacing(windowA, choice, dado);

        System.out.println(windowA.toString());
        System.out.println(choice);

        //altri valori, questo non va a buon fine

        Rules s = new Rules();
        Die dadoProva2 = new Die(1, Colour.YELLOW);

        GlassWindow windowB = new AuroraeMagnificus();
        windowB.getSlot(2, 3).setDie(dadoProva2);

        Die dado2 = new Die(5, Colour.BLUE);
        Slot choice2 = windowB.getSlot(2, 2);
        System.out.println(choice2);

        windowB = s.diePlacing(windowB, choice2, dado2);

        System.out.println(choice2);

        //altri valori, occupato

        Rules t = new Rules();
        Die dadoProva3 = new Die(3, Colour.YELLOW);

        GlassWindow windowC = new Battlo();
        windowC.getSlot(2, 3).setDie(dadoProva3);

        Die dado3 = new Die(4, Colour.PURPLE);
        Slot choice3 = windowC.getSlot(2, 3);
        System.out.println(choice3);

        windowC = t.diePlacing(windowC, choice3, dado3);

        System.out.println(choice3);
    }





}
