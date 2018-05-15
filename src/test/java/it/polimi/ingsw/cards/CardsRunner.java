package it.polimi.ingsw.cards;

import it.polimi.ingsw.cards.glasswindow_Tests.GlassWindowRunner;
import it.polimi.ingsw.cards.privateObject_Test.PrivateObjectRunner;
import it.polimi.ingsw.cards.publicObject_Tests.PublicObjectRunner;
import it.polimi.ingsw.cards.slot_Tests.SlotRunner;
import it.polimi.ingsw.cards.tool_Tests.ToolRunner;
import org.junit.jupiter.api.Test;

public class CardsRunner {

    @Test
    public void runAllCards(){
        PublicObjectRunner pubblico = new PublicObjectRunner();
        pubblico.runPublic();

        SlotRunner slot = new SlotRunner();
        slot.runThatSlot();

        PrivateObjectRunner privato = new PrivateObjectRunner();
        privato.runPrivateObj();

        GlassWindowRunner vetrate = new GlassWindowRunner();
        vetrate.runGlassWindow();

        ToolRunner tool= new ToolRunner();
        tool.runThatTool();

    }
}
