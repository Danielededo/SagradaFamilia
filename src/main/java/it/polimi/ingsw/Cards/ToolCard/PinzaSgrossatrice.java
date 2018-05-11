package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Die;

public class PinzaSgrossatrice extends Tool{
    public PinzaSgrossatrice() {
        super.setEffect("Dopo aver scelto un dado," +
                "aumenta o dominuisci il valore" +
                "del dado scelto di 1\n" +
                "Non puoi cambiare" +
                "un 6 in 1 o un 1 in 6");
        super.setName("Pinza Sgrossatrice");
    }

    public Die effect(Die die, boolean piumeno) {
        if((die.getFace()==1 && !piumeno)|| (die.getFace()==6 && piumeno)) {
            System.out.println("Non puoi cambiare un 6 in 1 o un 1 in 6, scegli un nuovo dado");
            return null;
        }
        else
        if(piumeno){
            die.setFace(die.getFace()+1);
            return die;
        }
        else{
            die.setFace(die.getFace()-1);
            return die;
        }

    }
}
