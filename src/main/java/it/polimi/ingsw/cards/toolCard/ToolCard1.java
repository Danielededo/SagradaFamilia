package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;

public class ToolCard1 extends Tool{
    public ToolCard1() {
        super.setEffect("Dopo aver scelto un dado," +
                "aumenta o dominuisci il valore" +
                "del dado scelto di 1\n" +
                "Non puoi cambiare" +
                "un 6 in 1 o un 1 in 6");
        super.setName("Pinza Sgrossatrice");
        super.setValue(1);
    }



    public boolean effect(Die die, boolean piumeno) {
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    return false;
                }
            }
        }
        if((die.getFace()==1 && !piumeno)|| (die.getFace()==6 && piumeno)) {
            System.out.println("Non puoi cambiare un 6 in 1 o un 1 in 6, scegli un nuovo dado");
            return false;
        }
        else
        if(piumeno){
            setUsed(false);
            die.setFace(die.getFace()+1);
            return true;
        }
        else{
            setUsed(false);
            die.setFace(die.getFace()-1);
            return true;
        }
    }

}
