package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Stock;

public class ToolCard12 extends Tool {

    public ToolCard12() {
        super.setEffect("Muovi fino a due dadi " +
                "dello stesso colore di un solo dado " +
                "sul Tracciato del Round. " +
                "Devi rispettare tutte le restrizioni di piazzamento ");
        super.setName("Taglierina Manuale");
        super.setValue(12);
    }


    public boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value){
        if (!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setAccessed(true);
                    setUsed(true);
                } else {
                    System.out.println("You can't use this toolCard, you don't have enough Favor Tokens.\n");
                    error=list__of_errors[0];
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("You can't use this toolCard, you don't have enough Favor Tokens.\n");
                    error=list__of_errors[0];
                    return false;
                }
            }
        }

        Colour scelto = slot1.getDice().getDicecolor();

        if(this.getPlayer().getWindow().getSlot(slot1).getDice() == null){
            System.out.println("This slot is empty.\n");
            error=list__of_errors[3];
            return false;
        }

        if(!this.scorroTrack(partita, scelto)){
            System.out.println("You can't choose this die.\n");
            error=list__of_errors[11];
            return false;
        }


        if(!partita.getRules().rules(ToolCard12.super.getPlayer(), slot3, slot1.getDice())){
            System.out.println("Choose another slot.\n");
            error=list__of_errors[12];
            return false;
        }


        this.getPlayer().getWindow().getSlot(slot3).setDie(slot1.getDice());
        this.getPlayer().getWindow().getSlot(slot1).setOccupate(false);
        this.getPlayer().getWindow().getSlot(slot1).setDie(null);


        if(slot2 == null){
            this.setUsed(false);
            return true;
        }

        //secondo dado
        //esiste
        if(this.getPlayer().getWindow().getSlot(slot2).getDice() == null){
            System.out.println("This slot is empty");
            this.getPlayer().getWindow().getSlot(slot1).setDie(slot3.getDice());
            this.getPlayer().getWindow().getSlot(slot3).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slot3).setDie(null);
            error=list__of_errors[3];
            return false;
        }


        //sono dello stesso colore?
        if(slot2.getDice().getDicecolor() != slot3.getDice().getDicecolor()){
            System.out.println("You can't choose dice with different colours. Start again.\n");
            this.getPlayer().getWindow().getSlot(slot1).setDie(slot3.getDice());
            this.getPlayer().getWindow().getSlot(slot3).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slot3).setDie(null);
            error=list__of_errors[13];
            return false;
        }



        //regole di piazzamento
        if(!partita.getRules().rules(this.getPlayer(),slot4, slot2.getDice())){
            System.out.println("You can't place the selected die on an occupied slot.\n");
            this.getPlayer().getWindow().getSlot(slot1).setDie(slot3.getDice());
            this.getPlayer().getWindow().getSlot(slot3).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slot3).setDie(null);
            error=list__of_errors[2];
            return false;
        }


        this.getPlayer().getWindow().getSlot(slot4).setDie(slot2.getDice());
        this.getPlayer().getWindow().getSlot(slot2).setOccupate(false);
        this.getPlayer().getWindow().getSlot(slot2).setDie(null);
        this.setUsed(false);
        return true;

    }




    public boolean scorroTrack(Match partita,Colour colore){
        for(int i = 0; i <partita.getRound()-1; i++) {
            for (int j=0;j<partita.getRoundTrackList(i).size();j++){
                if(partita.getRoundTrackList(i).get(j).getDicecolor()== colore){
                    return true;
                }
            }
        }
        return false;
    }

}
