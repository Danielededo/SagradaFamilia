package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Stock;

public class ToolCard1 extends Tool{
    public ToolCard1() {
        super.setEffect("Dopo aver scelto un dado," +
                " aumenta o dominuisci il valore" +
                " del dado scelto di 1. " +
                "Non puoi cambiare " +
                "un 6 in 1 o un 1 in 6 ");
        super.setName("Pinza Sgrossatrice");
        super.setValue(1);
    }


    @Override
    public boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value) {
        if(!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setAccessed(true);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    error=list__of_errors[0];
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("Non puoi utilizzare questa carta Tool perchè non possiedi abbastanza segnalini favore");
                    error=list__of_errors[0];
                    return false;
                }
            }
        }
        if((dado1.getFace()==1 && !piumeno)|| (dado1.getFace()==6 && piumeno)) {
            System.out.println("Non puoi cambiare un 6 in 1 o un 1 in 6, scegli un nuovo dado");
            error=list__of_errors[1];
            return false;
        }
        else
        if(piumeno){
            setUsed(false);
            dado1.setFace(dado1.getFace()+1);
            return true;
        }
        else{
            setUsed(false);
            dado1.setFace(dado1.getFace()-1);
            return true;
        }
    }
}