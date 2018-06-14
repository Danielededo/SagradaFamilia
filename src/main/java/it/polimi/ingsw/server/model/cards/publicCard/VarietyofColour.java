package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.game.Player;

public class VarietyofColour extends PublicObject {
    public VarietyofColour() {
        setName("Varietà di Colore");
        setValue(10);
        setPunteggio(4);
        setEffect("Set di dadi di ogni colore ovunque");
    }

    private int numberofset(Player player){
        int[] cont={0,0,0,0,0};
        int min=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()== Colour.RED) cont[0]++;
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()== Colour.PURPLE) cont[1]++;
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()== Colour.GREEN) cont[2]++;
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()== Colour.YELLOW) cont[3]++;
                    if (player.getWindow().getSlot(i,j).getDice().getDicecolor()==Colour.BLUE) cont[4]++;
                }
            }
        }
        min=cont[0];
        for (int i=1;i<cont.length;i++){
            if (cont[i]<min) min=cont[i];
        }
        return min;
    }

    public int calcola_punteggio(Player player) {
        return numberofset(player)*getPunteggio();
    }
}
