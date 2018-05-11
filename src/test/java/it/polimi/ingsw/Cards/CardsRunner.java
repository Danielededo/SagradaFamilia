package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Cards.Glasswindow_Tests.GlassWindowRunner;
import it.polimi.ingsw.Cards.PrivateObject_Test.PrivateObjectRunner;
import it.polimi.ingsw.Cards.PublicObject_Tests.PublicObjectRunner;
import it.polimi.ingsw.Cards.Slot_Tests.SlotRunner;
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

    }
}
