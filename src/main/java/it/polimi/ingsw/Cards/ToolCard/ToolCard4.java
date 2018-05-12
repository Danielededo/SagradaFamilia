package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Die;

public class ToolCard4 extends Tool {
    public ToolCard4() {
        super.setEffect("Muovi esattamente due dadi," +
                " rispettando tutte le restrizioni di" +
                " piazzamento");
        super.setName("Lathekin");
    }

    public GlassWindow effect(Slot slot1,Slot slot2,Slot slot3,Slot slot4){
        Die a,b;
        int i=0;
        while(i!=2){
            if(i==0) {
                if (getPlayer().getWindow().getSlot(slot2).isOccupate()) {
                    System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
                    return null;
                }
                if (!getPlayer().getWindow().getSlot(slot1).isOccupate()) {
                    System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
                    return null;
                }
                a = getPlayer().getWindow().getSlot(slot1).getDice();
                getPlayer().getWindow().getSlot(slot2).setDie(a);
                i++;
            }
            else {
                if (getPlayer().getWindow().getSlot(slot4).isOccupate()) {
                    System.out.println("Lo slot selezionato per posizionare il dado possiede già un dado");
                    return null;
                }
                if (!getPlayer().getWindow().getSlot(slot3).isOccupate()) {
                    System.out.println("Lo slot selezionato per prendere il dado non possiede un dado");
                    return null;
                }
                a = getPlayer().getWindow().getSlot(slot3).getDice();
                getPlayer().getWindow().getSlot(slot4).setDie(a);
                i++;
            }
        }
        return getPlayer().getWindow();
    }
}
