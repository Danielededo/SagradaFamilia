package Dice;

import java.util.ArrayList;
import java.util.Random;

public class Sack{
    private ArrayList<Die> dice=new ArrayList<Die>();

    public int getsize() {
        return dice.size();
    }

    public void adddie(Die d){
        dice.add(d);
    }

    public void createdice(){
        createreddice();
        createbluedice();
        creategreendice();
        createyellowdice();
        createpurple();
    }

    private void createpurple() {
        Die dadop=new Die();
        dadop.setDicecolor(Colour.PURPLE);
        for (int i=0;i<18;i++){
            dice.add(dadop);
        }
    }

    private void createyellowdice() {
        Die dadoy=new Die();
        dadoy.setDicecolor(Colour.YELLOW);
        for (int i=0;i<18;i++){
            dice.add(dadoy);
        }
    }

    private void creategreendice() {
        Die dadog=new Die();
        dadog.setDicecolor(Colour.GREEN);
        for (int i=0;i<18;i++){
            dice.add(dadog);
        }
    }

    private void createbluedice() {
        Die dadob=new Die();
        dadob.setDicecolor(Colour.BLUE);
        for (int i=0;i<18;i++){
            dice.add(dadob);
        }
    }

    private void createreddice() {
        Die dador=new Die();
        dador.setDicecolor(Colour.RED);
        for (int i=0;i<18;i++){
            dice.add(dador);
        }
    }

    public Die extractdie(){
        Die dado;
        Random random = new Random();
        int k=random.nextInt(dice.size());
        dado=dice.get(k);
        dado.randomdado();
        dice.remove(k);
        return dado;
    }
}
