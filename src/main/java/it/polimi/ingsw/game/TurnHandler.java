package it.polimi.ingsw.game;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Die;

import java.util.ArrayList;


public class TurnHandler {

    public int placeDie(Match partita, Die dado, Slot scelta, Player a){

        boolean tracker = scorroStock(partita.getStock(), dado);
        if(!tracker)
            return 1;
        Rules r = new Rules();
        tracker = r.rules(a, a.getWindow().getSlot(scelta), dado);
        if(!tracker){
            return 2;
        }
        a.getWindow().getSlot(scelta).setDie(dado);
        return 0;
    }

    public int usaTool(int num, Match partita){
        ArrayList<Tool> tool = partita.getTool();
        boolean tracker = false;
        if(tracker){
            return 0;
        }
        return 1;
    }








    public boolean scorroStock(Stock riserva, Die dado){
        int cont;
        for(cont = 0; cont < riserva.getDicestock().size(); cont++){
            if(riserva.getDicestock().get(cont).getDicecolor() == dado.getDicecolor() && riserva.getDicestock().get(cont).getFace() == dado.getFace()){
                riserva.extract_die(cont);}
            return true; }
        return false;
        }

}
