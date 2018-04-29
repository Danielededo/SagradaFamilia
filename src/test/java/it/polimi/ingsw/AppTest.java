package it.polimi.ingsw;

import Cards.*;
import Dice.Colour;
import Dice.Die;
import Dice.Sack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest{
    @Test
    void sacksize(){
        Sack s=new Sack();
        s.createdice();
        System.out.println(s.getsize());
        assertEquals(90,s.getsize());
    }

    @Test
    void sackremove(){
        Sack s = new Sack();
        Sack t = new Sack();
        Die d;
        s.createdice();
        for (int i=0;i<90;i++){
            d=s.extractdie();
            t.adddie(d);
        }
        assertEquals(0,s.getsize());
        assertEquals(90,t.getsize());
    }

    @Test
    void testaobiettivi(){
        Card carta1=new RedShades();
        Card carta2=new YellowShades();
        Card carta3=new GreenShades();
        Card carta4=new BlueShades();
        Card carta5=new PurpleShades();
        System.out.println(carta1.toString());
        System.out.println(carta2.toString());
        System.out.println(carta3.toString());
        System.out.println(carta4.toString());
        System.out.println(carta5.toString());
    }
}
