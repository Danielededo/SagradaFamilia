package it.polimi.ingsw.dice;
import java.util.Random;

public class Die {
    private int face;
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
        face=k;
        return face;
    }

    public void setDicecolor(Colour dicecolor) {
        this.dicecolor = dicecolor;
    }

    @Override
    public String toString() {
        if (dicecolor==Colour.RED) {
            return "Die{" +
                    "face=" + face +
                    ", dicecolor=" + dicecolor+"   "+
                    '}';
        }else if (dicecolor==Colour.BLUE) {
            return "Die{" +
                    "face=" + face +
                    ", dicecolor=" + dicecolor+"  "+
                    '}';
        }else if (dicecolor==Colour.GREEN) {
            return "Die{" +
                    "face=" + face +
                    ", dicecolor=" + dicecolor+" "+
                    '}';
        }else  {
            return "Die{" +
                    "face=" + face +
                    ", dicecolor=" + dicecolor+
                    '}';
        }
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public Colour getDicecolor() {
        return dicecolor;
    }
}