package it.polimi.ingsw.server.model.dice;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.game.Match;

import java.util.ArrayList;
import java.util.Random;

public class Sack{
    private ArrayList<Die> dice=new ArrayList<Die>();
    private final int numberDice=18;

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
        for (Die d:dice){
            d.randomdado();
        }
    }

    /**
     * createpurple create and add
     * 18 purple dice in sack
     */
    private void createpurple() {
        for (int i=0;i<numberDice;i++){
            Die dadop=new Die();
            dadop.setDicecolor(Colour.PURPLE);
            dice.add(dadop);
        }
    }

    /**
     * createyellowdice create and add
     * 18 yellow dice in sack
     */
    private void createyellowdice() {
        for (int i=0;i<numberDice;i++){
            Die dadoy=new Die();
            dadoy.setDicecolor(Colour.YELLOW);
            dice.add(dadoy);
        }
    }

    /**
     * creategreendice create and add
     * 18 green dice in sack
     */
    private void creategreendice() {
        for (int i=0;i<numberDice;i++){
            Die dadog=new Die();
            dadog.setDicecolor(Colour.GREEN);
            dice.add(dadog);
        }
    }

    /**
     * createbluedice create and add
     * 18 blue dice in sack
     */
    private void createbluedice() {
        for (int i=0;i<numberDice;i++){
            Die dadob=new Die();
            dadob.setDicecolor(Colour.BLUE);
            dice.add(dadob);
        }
    }

    /**
     * createreddice create and add
     * 18 red dice in sack
     */
    private void createreddice() {
        for (int i=0;i<numberDice;i++){
            Die dador=new Die();
            dador.setDicecolor(Colour.RED);
            dice.add(dador);
        }
    }

    /**
     * @return die from sack that has been extract casually
     */
    public Die extractdie(){
        Die dado;
        Random random = new Random();
        int k=random.nextInt(dice.size());
        dado=dice.get(k);
        dado.randomdado();
        dice.remove(k);
        return dado;
    }

    /**
     * @param match is the current match that require
     *             a set of dice to put in its stock
     * @return an Arraylist of die extract from sack
     * with casually color and casually value
     */
    public ArrayList<Die> extractfromSack(Match match){
        ArrayList<Die> arrayList = new ArrayList<Die>();
        for(int i=0; i<2*match.getnumberPlayers()+1; i++){
            arrayList.add(extractdie());
        }
        return arrayList;
    }

    @Override
    public String toString() {
        return "Sacchetto{" +
                "dado=" + dice +
                '}';
    }
}
