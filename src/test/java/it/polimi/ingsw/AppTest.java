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

    @Test
    void testcreateWindow(){
        SunCatcher c = new SunCatcher();
        c.createSunCatcher();
        assertEquals(3,c.getSlot(3,1).getValue());
        assertEquals(Colour.WHITE, c.getSlot(3,1).getSlotcolour());
    }

    @Test
    void testWindow(){
        Virtus virtus = new Virtus();
        Die dado =new Die();
        virtus.createVirtus();
        dado.randomdado();
        dado.setDicecolor(Colour.GREEN);
        System.out.println(dado.getFace());
        System.out.println(virtus.getSlot(3,1).isOccupate());
        virtus.getSlot(3,1).setDie(dado);
        System.out.println(virtus.getSlot(3,1).isOccupate());
    }

    @Test
    void testsetDice(){
        Die a = new Die();
        Die b = new Die();
        Comitas comitas = new Comitas();
        comitas.createComitas();
        a.randomdado();
        System.out.println(a.getFace());
        b.randomdado();
        System.out.println(b.getFace());
        a.setDicecolor(Colour.YELLOW);
        b.setDicecolor(Colour.YELLOW);
        comitas.getSlot(2,3).setDie(a);
        comitas.getSlot(2,3).setDie(b);
        System.out.println(comitas.getSlot(2,3).getDice());
        assertEquals(a,comitas.getSlot(2,3).getDice());
    }

    @Test
    void testRiempimento(){
        WaterofLife window = new WaterofLife();
        window.createWaterofLife();
        for(int i=0; i<=3; i++){
            for(int j=0;j<=4;j++){
                Die a = new Die();
                a.randomdado();
                System.out.print(a.getFace()+ "     ");
                a.setDicecolor(Colour.BLUE);
                window.getSlot(i,j).setDie(a);
            }
            System.out.println();
        }
        for(int i=0; i<=3; i++) {
            for (int j = 0; j <= 4; j++) {
                System.out.print(window.getSlot(i, j).isOccupate()+ "   ");
            }
            System.out.println();
        }
        }
}
