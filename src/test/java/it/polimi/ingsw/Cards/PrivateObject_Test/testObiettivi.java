package it.polimi.ingsw.Cards.PrivateObject_Test;

import it.polimi.ingsw.Cards.Card;
import it.polimi.ingsw.Cards.PrivateCard.*;
import org.junit.jupiter.api.Test;

public class testObiettivi {
    @Test
    void testaobiettivi() {
        Card carta1 = new RedShades();
        Card carta2 = new YellowShades();
        Card carta3 = new GreenShades();
        Card carta4 = new BlueShades();
        Card carta5 = new PurpleShades();
        System.out.println(carta1.toString());
        System.out.println(carta2.toString());
        System.out.println(carta3.toString());
        System.out.println(carta4.toString());
        System.out.println(carta5.toString());
    }
}
