package it.polimi.ingsw;

import Cards.*;
import Cards.SchemeCard.*;
import Cards.PrivateCard.*;
import Cards.PublicCard.*;
import Dice.Colour;
import Dice.Die;
import Dice.Sack;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    void testacalculate_score(){
        ViaLux luce = new ViaLux();
        luce.createViaLux();
        BlueShades privata=new BlueShades();
        int sum;
        for(int i=0; i<=3; i++){
            for(int j=0;j<=4;j++){
                Die a = new Die();
                a.randomdado();
                System.out.print(a.getFace()+ "     ");
                a.setDicecolor(Colour.BLUE);
                luce.getSlot(i,j).setDie(a);
            }
            System.out.println();
        }
        for(int i=0; i<=3; i++) {
            for (int j = 0; j <= 4; j++) {
                System.out.print(luce.getSlot(i, j).isOccupate()+ "   ");
            }
            System.out.println();
        }
        System.out.println(privata.toString());
        sum=privata.calculate_score(luce);
        System.out.println("somma: " + sum);
    }

    @Test
    void testa_different_rowColors(){
        GlassWindow vetrata=new GlassWindow();
        Slot s=new Slot();
        Slot t=new Slot();
        Slot u=new Slot();
        Slot v=new Slot();
        Slot z=new Slot();
        Slot x=new Slot();
        s.setSlotcolour(Colour.WHITE);
        s.setValue(0);
        t.setSlotcolour(Colour.WHITE);
        t.setValue(0);
        u.setSlotcolour(Colour.WHITE);
        u.setValue(0);
        v.setSlotcolour(Colour.WHITE);
        v.setValue(0);
        z.setSlotcolour(Colour.WHITE);
        z.setValue(0);
        x.setSlotcolour(Colour.RED);
        x.setValue(0);
        Die a=new Die();
        Die b=new Die();
        Die c=new Die();
        Die d=new Die();
        Die e=new Die();
        a.randomdado();
        a.setDicecolor(Colour.RED);
        b.randomdado();
        b.setDicecolor(Colour.BLUE);
        c.randomdado();
        c.setDicecolor(Colour.YELLOW);
        d.randomdado();
        d.setDicecolor(Colour.GREEN);
        e.randomdado();
        e.setDicecolor(Colour.PURPLE);
        PublicObject card=new DifferentRowColors();
        s.setDie(a);
        t.setDie(b);
        u.setDie(c);
        v.setDie(d);
        z.setDie(e);
        x.setDie(b);
        for (int i=1;i<3;i++){
            vetrata.setSlot(s,i,0);
            vetrata.setSlot(t,i,1);
            vetrata.setSlot(u,i,2);
            vetrata.setSlot(v,i,3);
            vetrata.setSlot(z,i,4);
        }
        vetrata.setSlot(s,3,0);
        vetrata.setSlot(t,3,1);
        vetrata.setSlot(x,3,2);
        vetrata.setSlot(v,3,3);
        vetrata.setSlot(z,3,4);

        vetrata.setSlot(s,0,0);
        vetrata.setSlot(t,0,1);
        vetrata.setSlot(s,0,2);
        vetrata.setSlot(z,0,3);
        vetrata.setSlot(t,0,4);
        for(int i=0; i<=3; i++) {
            for (int j = 0; j <= 4; j++) {
                if (vetrata.getSlot(i, j).isOccupate()) {
                    System.out.print(vetrata.getSlot(i, j).isOccupate()+
                            "-"+vetrata.getSlot(i,j).getDice().getDicecolor()+"   ");
                }else System.out.print(vetrata.getSlot(i, j).isOccupate()+" ");
            }
            System.out.println();
        }
        System.out.println("punteggio: "+card.calcola_punteggio(vetrata));
    }

    @Test
    void testa_different_columnColors(){
        GlassWindow vetrata=new GlassWindow();
        Slot s=new Slot();
        Slot t=new Slot();
        Slot u=new Slot();
        Slot v=new Slot();
        Slot z=new Slot();
        Slot x=new Slot();
        s.setSlotcolour(Colour.WHITE);
        s.setValue(0);
        t.setSlotcolour(Colour.WHITE);
        t.setValue(0);
        u.setSlotcolour(Colour.WHITE);
        u.setValue(0);
        v.setSlotcolour(Colour.WHITE);
        v.setValue(0);
        z.setSlotcolour(Colour.WHITE);
        z.setValue(0);
        x.setSlotcolour(Colour.RED);
        x.setValue(0);
        Die a=new Die();
        Die b=new Die();
        Die c=new Die();
        Die d=new Die();
        Die e=new Die();
        a.randomdado();
        a.setDicecolor(Colour.RED);
        b.randomdado();
        b.setDicecolor(Colour.BLUE);
        c.randomdado();
        c.setDicecolor(Colour.YELLOW);
        d.randomdado();
        d.setDicecolor(Colour.GREEN);
        e.randomdado();
        e.setDicecolor(Colour.PURPLE);
        PublicObject card=new DifferentColumnsColors();
        s.setDie(a);
        t.setDie(b);
        u.setDie(c);
        v.setDie(d);
        z.setDie(e);
        x.setDie(b);
        for (int i=1;i<4;i++){
            vetrata.setSlot(s,0,i);
            vetrata.setSlot(t,1,i);
            vetrata.setSlot(u,2,i);
            vetrata.setSlot(v,3,i);
        }
        vetrata.setSlot(s,0,0);
        vetrata.setSlot(t,1,0);
        vetrata.setSlot(x,2,0);
        vetrata.setSlot(v,3,0);

        vetrata.setSlot(s,0,4);
        vetrata.setSlot(t,1,4);
        vetrata.setSlot(s,2,4);
        vetrata.setSlot(z,3,4);
        for(int i=0; i<=3; i++) {
            for (int j = 0; j <= 4; j++) {
                if (vetrata.getSlot(i, j).isOccupate()) {
                    System.out.print(vetrata.getSlot(i, j).isOccupate()+
                            "-"+vetrata.getSlot(i,j).getDice().getDicecolor()+"   ");
                }else System.out.print(vetrata.getSlot(i, j).isOccupate()+" ");
            }
            System.out.println();
        }
        System.out.println("punteggio: "+card.calcola_punteggio(vetrata));
    }

    @Test
    void testadifferent_rowShades(){
        PublicObject card=new DifferentRowShades();
        GlassWindow vetrata=new GlassWindow();
        Slot s=new Slot();
        Slot t=new Slot();
        Slot u=new Slot();
        Slot v=new Slot();
        Slot z=new Slot();
        Slot x=new Slot();
        s.setSlotcolour(Colour.WHITE);
        s.setValue(0);
        t.setSlotcolour(Colour.WHITE);
        t.setValue(0);
        u.setSlotcolour(Colour.WHITE);
        u.setValue(0);
        v.setSlotcolour(Colour.WHITE);
        v.setValue(0);
        z.setSlotcolour(Colour.WHITE);
        z.setValue(0);
        x.setSlotcolour(Colour.WHITE);
        x.setValue(0);
        Die a=new Die(1,Colour.RED);
        Die b=new Die(2,Colour.PURPLE);
        Die c=new Die(3,Colour.YELLOW);
        Die d=new Die(4,Colour.GREEN);
        Die e=new Die(5,Colour.BLUE);
        Die f=new Die(6,Colour.RED);
        s.setDie(a);
        t.setDie(b);
        u.setDie(c);
        v.setDie(d);
        z.setDie(e);
        x.setDie(f);
        for (int i=1;i<3;i++){
            vetrata.setSlot(s,i,0);
            vetrata.setSlot(t,i,1);
            vetrata.setSlot(u,i,2);
            vetrata.setSlot(v,i,3);
            vetrata.setSlot(z,i,4);
        }
        vetrata.setSlot(x,0,0);
        vetrata.setSlot(v,0,1);
        vetrata.setSlot(t,0,2);
        vetrata.setSlot(z,0,3);
        vetrata.setSlot(u,0,4);

        vetrata.setSlot(x,3,0);
        vetrata.setSlot(s,3,1);
        vetrata.setSlot(x,3,2);
        vetrata.setSlot(v,3,3);
        vetrata.setSlot(t,3,4);
        for(int i=0; i<=3; i++) {
            for (int j = 0; j <= 4; j++) {
                if (vetrata.getSlot(i, j).isOccupate()) {
                    System.out.print(vetrata.getSlot(i, j).isOccupate()+
                            "-"+vetrata.getSlot(i,j).getDice().getFace()+"   ");
                }else System.out.print(vetrata.getSlot(i, j).isOccupate()+" ");
            }
            System.out.println();
        }
        System.out.println(card.calcola_punteggio(vetrata));
    }

    @Test
    void testadifferent_columnShades(){
        PublicObject card=new DifferentColumnShades();
        GlassWindow vetrata=new GlassWindow();
        Slot s=new Slot(0,Colour.WHITE);
        Slot t=new Slot(0,Colour.WHITE);
        Slot u=new Slot(0,Colour.WHITE);
        Slot v=new Slot(0,Colour.WHITE);
        Slot z=new Slot(0,Colour.WHITE);
        Slot x=new Slot(0,Colour.WHITE);
        Die a=new Die(1,Colour.RED);
        Die b=new Die(2,Colour.PURPLE);
        Die c=new Die(3,Colour.YELLOW);
        Die d=new Die(4,Colour.GREEN);
        Die e=new Die(5,Colour.BLUE);
        Die f=new Die(6,Colour.RED);
        s.setDie(a);
        t.setDie(b);
        u.setDie(c);
        v.setDie(d);
        z.setDie(e);
        x.setDie(f);
        for (int i=1;i<4;i++){
            vetrata.setSlot(s,0,i);
            vetrata.setSlot(t,1,i);
            vetrata.setSlot(u,2,i);
            vetrata.setSlot(v,3,i);
        }
        vetrata.setSlot(s,0,0);
        vetrata.setSlot(t,1,0);
        vetrata.setSlot(x,2,0);
        vetrata.setSlot(v,3,0);

        vetrata.setSlot(s,0,4);
        vetrata.setSlot(t,1,4);
        vetrata.setSlot(s,2,4);
        vetrata.setSlot(z,3,4);
        for(int i=0; i<=3; i++) {
            for (int j = 0; j <= 4; j++) {
                if (vetrata.getSlot(i, j).isOccupate()) {
                    System.out.print(vetrata.getSlot(i, j).isOccupate()+
                            "-"+vetrata.getSlot(i,j).getDice().getFace()+"   ");
                }else System.out.print(vetrata.getSlot(i, j).isOccupate()+" ");
            }
            System.out.println();
        }
        System.out.println(card.calcola_punteggio(vetrata));
    }
}
