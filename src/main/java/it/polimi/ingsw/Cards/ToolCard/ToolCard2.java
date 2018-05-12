package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Die;

public class ToolCard2 extends Tool {
    public ToolCard2() {
        super.setEffect("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni" +
                " di colore\n" +
                "Devi rispettare tutte le altre" +
                " restrizioni di piazzamento");
        super.setName("Pennello per Eglomise");
    }

    public GlassWindow effect(Slot slot1, Slot slot2){
        Die a;
        if(getPlayer().getWindow().getSlot(slot2).isOccupate()){
            System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
            return null;
        }
        if(!getPlayer().getWindow().getSlot(slot1).isOccupate()){
            System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
            return null;
        }
        a=getPlayer().getWindow().getSlot(slot1).getDice();
        getPlayer().getWindow().getSlot(slot2).setDie(a);
        return getPlayer().getWindow();
    }
}
