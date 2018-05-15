package it.polimi.ingsw.dice;

import it.polimi.ingsw.game.Match;

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

    public Sack(){
        createreddice();
        createbluedice();
        creategreendice();
        createyellowdice();
        createpurple();
    }

    private void createpurple() {
        for (int i=0;i<18;i++){
            Die dadop=new Die();
            dadop.setDicecolor(Colour.PURPLE);
            dice.add(dadop);
        }
    }

    private void createyellowdice() {
        for (int i=0;i<18;i++){
            Die dadoy=new Die();
            dadoy.setDicecolor(Colour.YELLOW);
            dice.add(dadoy);
        }
    }

    private void creategreendice() {
        for (int i=0;i<18;i++){
            Die dadog=new Die();
            dadog.setDicecolor(Colour.GREEN);
            dice.add(dadog);
        }
    }

    private void createbluedice() {
        for (int i=0;i<18;i++){
            Die dadob=new Die();
            dadob.setDicecolor(Colour.BLUE);
            dice.add(dadob);
        }
    }

    private void createreddice() {
        for (int i=0;i<18;i++){
            Die dador=new Die();
            dador.setDicecolor(Colour.RED);
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

    public ArrayList<Die> extractfromSack(Match match){
        ArrayList<Die> arrayList = new ArrayList<Die>();
        for(int i=0; i<2*match.getnumberPlayers()+1; i++){
            arrayList.add(extractdie());
        }
        return arrayList;
    }

    @Override
    public String toString() {
        return "Sack{" +
                "dice=" + dice +
                '}';
    }
}
