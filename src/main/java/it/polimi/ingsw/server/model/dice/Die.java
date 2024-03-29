package it.polimi.ingsw.server.model.dice;
import it.polimi.ingsw.utils.Colour;

import java.util.Random;

public class Die {
    private int face;
    private String value;
    private Colour dicecolor;
    public static final String[] faces={
            "\u2680",
            "\u2681",
            "\u2682",
            "\u2683",
            "\u2684",
            "\u2685"
    };

    public Die() {
    }

    public Die(int face, Colour dicecolor) {
        this.face = face;
        value=faces[face-1];
        this.dicecolor = dicecolor;
    }

    /**
     * This method is used to randomly assign
     * a value to the die face
     */
    public void randomdado(){
        int count = faces.length;
        Random rand = new Random();
        int index = rand.nextInt(count);
        this.face=index+1;
        this.value = faces[index];
    }

    public void setDicecolor(Colour dicecolor) {
        this.dicecolor = dicecolor;
    }

    @Override
    public String toString() {
        String escape = this.dicecolor.escape();
        return escape+value + Colour.RESET;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
        value=faces[face-1];
    }

    public Colour getDicecolor() {
        return dicecolor;
    }
}