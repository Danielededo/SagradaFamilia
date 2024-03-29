package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.schemeCard.LuzCelestial;
import it.polimi.ingsw.server.model.cards.toolCard.ToolCard9;
import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Rules;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testToolCard9_Test {

    @Test
    void testingTool9(){
        Die dado = new Die(4, Colour.BLUE);
        ToolCard9 carta9 = new ToolCard9();

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

        ArrayList<Die> dice=new ArrayList<>();
        ArrayList<Slot> slots=new ArrayList<>();

        dice.add(dado);
        slots.add(a.getWindow().getSlot(2,2));

        if (tool9.effect(dice,null,slots,0))System.out.println("operazione riuscita");
        else System.out.println("operazione fallita");
        System.out.println(a.getWindow().toString());

        slots.clear();
        slots.add(a.getWindow().getSlot(3,4));
        if (tool9.effect(dice,null,slots,0))System.out.println("operazione riuscita");
        else System.out.println("operazione fallita");
        System.out.println(a.getWindow().toString());




    }
}
