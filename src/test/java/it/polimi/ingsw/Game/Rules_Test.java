package it.polimi.ingsw.Game;


import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.AuroraeMagnificus;
import it.polimi.ingsw.Cards.SchemeCard.Battlo;
import it.polimi.ingsw.Cards.SchemeCard.FractalDrops;
import it.polimi.ingsw.Cards.SchemeCard.Gravitas;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import org.junit.jupiter.api.Test;

public class Rules_Test {
    @Test
    void testaRulesCentrali() {
        //non è il primo round
        Player a = new Player("a");
        Player b = new Player("b");
        Match partita = new Match(a,b);
        partita.setRound(2);

        Rules r = new Rules();
        Die dadoProva = new Die(6, Colour.YELLOW);

        GlassWindow windowA = new FractalDrops();
        windowA.getSlot(0, 2).setDie(dadoProva);
        System.out.println(windowA.toString());
        System.out.println(windowA.getSlot(0,2));

        Die dado = new Die(5, Colour.BLUE);
        Slot choice = windowA.getSlot(1, 1);
        System.out.println(choice);

        windowA = r.diePlacing(partita, windowA, choice, dado);

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

        windowB = s.diePlacing(partita, windowB, choice2, dado2);

        System.out.println(choice2);

        //altri valori, occupato

        Rules t = new Rules();
        Die dadoProva3 = new Die(3, Colour.YELLOW);

        GlassWindow windowC = new Battlo();
        windowC.getSlot(2, 3).setDie(dadoProva3);

        Die dado3 = new Die(4, Colour.PURPLE);
        Slot choice3 = windowC.getSlot(2, 3);
        System.out.println(choice3);

        windowC = t.diePlacing(partita, windowC, choice3, dado3);

        System.out.println(choice3);
    }

    @Test
    void TestRulesGenerale(){
        Player a = new Player("a");
        Player b = new Player("b");
        Match partita = new Match(a,b);
        partita.setRound(1);

        Rules r = new Rules();
        GlassWindow windowA = new Gravitas();

        Die dado = new Die(5, Colour.BLUE);
        Slot choice = windowA.getSlot(0, 3);
        System.out.println(choice);

        windowA = r.diePlacing(partita, windowA, choice, dado);

        System.out.println(windowA.toString());
        System.out.println(choice);

        //round diverso dal primo

        partita.setRound(5);
        Slot choice1 = windowA.getSlot(0,2);
        Die dado1 = new Die(3,Colour.YELLOW);
        System.out.println(choice1);

        windowA = r.diePlacing(partita, windowA, choice1, dado1);
        System.out.println(choice1);

        //fallimento vicini
        partita.setRound(6);
        Slot choice2 = windowA.getSlot(0,1);
        Die dado2 = new Die(3,Colour.YELLOW);
        System.out.println(choice2);

        windowA = r.diePlacing(partita, windowA, choice2, dado2);
        System.out.println(choice2);
    }



    @Test
    void Cose(){
        GlassWindow hey = new Battlo();
        System.out.println(hey.toString());
    }



}
