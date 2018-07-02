package it.polimi.ingsw.server.model.cards.toolCard;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.Tool;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Stock;

public class ToolCard5 extends Tool {
    public ToolCard5() {
        super.setEffect("Dopo aver scelto un dado, " +
                "scambia quel dado con un dado " +
                "sul Tracciato dei Round ");
        super.setName("Taglierina circolare");
        super.setValue(5);
    }

    /**
     * This method is the effect of this card called by match
     * @param dado1 die to be put in the round track
     * @param value index of the position of the die to be taken from the round track
     * @return true if effect is done false in other case
     */
    @Override
    public boolean effect(Die dado1, Die dado2, boolean plusminus, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value){
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
        int i=partita.getRoundTrackList(value).indexOf(dado2);
        Die d;
        d = partita.getRoundTrackList(value).get(i);
        partita.getRoundTrackList(value).set(i,dado1);
        partita.getStock().getDicestock().remove(dado1);
        partita.getStock().getDicestock().add(d);
        this.setUsed(false);
        return true;
    }

}
