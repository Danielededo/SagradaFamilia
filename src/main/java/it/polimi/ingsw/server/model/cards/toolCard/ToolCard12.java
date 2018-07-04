package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.utils.Colour;

import java.util.List;

public class ToolCard12 extends Tool {

    public ToolCard12() {
        super.setEffect("Muovi fino a due dadi " +
                "dello stesso colore di un solo dado " +
                "sul Tracciato del Round. " +
                "Devi rispettare tutte le restrizioni di piazzamento ");
        super.setName("Taglierina Manuale");
        super.setValue(12);
    }


    /**
     * This method represents the effect of the 12th tool card. By using this card you can choose to move up to two dice
     * already placed on your scheme card. They'll need to have the same colour of the same die on the roundtrack and
     * you'll have to obey all the placement rules.
     * @param dice
     * @param match the current match
     * @param slots slots where take and place die
     * @return boolean, true if the card was effective, false if you don't have enough tokens or if you've made mistakes
     *          while using this card
     */
    public boolean effect(List<Die> dice, Match match, List<Slot> slots, int value){
        int i=tokenpayment();
        if (i==0)
            return false;
        Colour scelto = slots.get(0).getDice().getDicecolor();

        if(this.getPlayer().getWindow().getSlot(slots.get(0)).getDice() == null){
            System.out.println("This slot is empty.\n");
            error=list__of_errors[3];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }

        if(!this.scorroTrack(match, scelto)){
            System.out.println("You can't choose this die.\n");
            error=list__of_errors[11];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }


        if(!match.getRules().rules(ToolCard12.super.getPlayer(), slots.get(1), slots.get(0).getDice())){
            System.out.println("Choose another slot.\n");
            error=list__of_errors[12];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }


        this.getPlayer().getWindow().getSlot(slots.get(1)).setDie(slots.get(0).getDice());
        this.getPlayer().getWindow().getSlot(slots.get(0)).setOccupate(false);
        this.getPlayer().getWindow().getSlot(slots.get(0)).setDie(null);


        if(slots.size()==2){
            return true;
        }

        //secondo dado
        //esiste
        if(this.getPlayer().getWindow().getSlot(slots.get(2)).getDice() == null){
            System.out.println("This slot is empty");
            this.getPlayer().getWindow().getSlot(slots.get(0)).setDie(slots.get(1).getDice());
            this.getPlayer().getWindow().getSlot(slots.get(1)).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slots.get(1)).setDie(null);
            error=list__of_errors[3];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }


        //sono dello stesso colore?
        if(slots.get(2).getDice().getDicecolor() != slots.get(1).getDice().getDicecolor()){
            System.out.println("You can't choose dice with different colours. Start again.\n");
            this.getPlayer().getWindow().getSlot(slots.get(0)).setDie(slots.get(1).getDice());
            this.getPlayer().getWindow().getSlot(slots.get(1)).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slots.get(1)).setDie(null);
            error=list__of_errors[13];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }



        //regole di piazzamento
        if(!match.getRules().rules(this.getPlayer(),slots.get(3), slots.get(2).getDice())){
            System.out.println("You can't place the selected die on an occupied slot.\n");
            this.getPlayer().getWindow().getSlot(slots.get(0)).setDie(slots.get(1).getDice());
            this.getPlayer().getWindow().getSlot(slots.get(1)).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slots.get(1)).setDie(null);
            error=list__of_errors[2];
            getPlayer().setMarker(getPlayer().getMarker()+i);
            if (i==1)setAccessed(false);
            return false;
        }


        this.getPlayer().getWindow().getSlot(slots.get(3)).setDie(slots.get(2).getDice());
        this.getPlayer().getWindow().getSlot(slots.get(2)).setOccupate(false);
        this.getPlayer().getWindow().getSlot(slots.get(2)).setDie(null);
        return true;

    }




    public boolean scorroTrack(Match match,Colour colore){
        for(int i = 0; i <match.getRound()-1; i++) {
            for (int j=0;j<match.getRoundTrackList(i).size();j++){
                if(match.getRoundTrackList(i).get(j).getDicecolor()== colore){
                    return true;
                }
            }
        }
        return false;
    }

}
