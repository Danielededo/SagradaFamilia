package it.polimi.ingsw.Cards.PrivateObject_Test;

import org.junit.jupiter.api.Test;

public class PrivateObjectRunner {

    @Test
    public void runPrivateObj(){
        testCalcoloScore calcolo = new testCalcoloScore();
        calcolo.testacalculate_score();

        testObiettivi fungono = new testObiettivi();
        fungono.testaobiettivi();

    }
}
