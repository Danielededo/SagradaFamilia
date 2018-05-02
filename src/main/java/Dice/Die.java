package Dice;
import java.util.Random;

public class Die {
    private int face;
    private int[] number = {1, 2, 3, 4, 5, 6};
    private Colour dicecolor;

    public Die() {
    }

    public Die(int face, Colour dicecolor) {
        this.face = face;
        this.dicecolor = dicecolor;
    }

    public int randomdado(){
        Random random = new Random();
        int k;
        k = random.nextInt(6)+1;
        face=number[k-1];
        return face;
    }

    public void setDicecolor(Colour dicecolor) {
        this.dicecolor = dicecolor;
    }

    @Override
    public String toString() {
        return "Die{" +
                "face=" + face +
                ", dicecolor=" + dicecolor +
                '}';
    }

    public int getFace() {
        return face;
    }


    public Colour getDicecolor() {
        return dicecolor;
    }
}