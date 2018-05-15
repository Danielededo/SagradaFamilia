package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;

public class ToolCard5 extends Tool {
    public ToolCard5() {
        super.setEffect("Dopo aver scelto un dado," +
                "scambia quel dado con un dado" +
                "sul Tracciato dei Round");
        super.setName("Taglierina circolare");
    }
    public boolean effect(Die fromStock, Die fromRoundTrack,Match match){
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
        int i=match.getRoundTrack().indexOf(fromRoundTrack);
        Die d;
        d = match.getRoundTrack().get(i);
        match.getRoundTrack().set(i,fromStock);
        match.getStock().getDicestock().remove(fromStock);
        match.getStock().getDicestock().add(d);
        return true;
    }
}
