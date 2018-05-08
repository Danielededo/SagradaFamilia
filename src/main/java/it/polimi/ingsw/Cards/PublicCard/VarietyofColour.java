package it.polimi.ingsw.Cards.PublicCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.PublicObject;
import it.polimi.ingsw.Dice.Colour;

public class VarietyofColour extends PublicObject {
    public VarietyofColour() {
        setName("Variety of Color");
        setValue(10);
        setPunteggio(4);
        setEffect("Set of dice of every color everywhere");
    }

    private int numberofset(GlassWindow scheme){
        int[] cont={0,0,0,0,0};
        int min=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                if (scheme.getSlot(i,j).isOccupate()){
                    if (scheme.getSlot(i,j).getDice().getDicecolor()== Colour.RED) cont[0]++;
                    if (scheme.getSlot(i,j).getDice().getDicecolor()== Colour.PURPLE) cont[1]++;
                    if (scheme.getSlot(i,j).getDice().getDicecolor()== Colour.GREEN) cont[2]++;
                    if (scheme.getSlot(i,j).getDice().getDicecolor()== Colour.YELLOW) cont[3]++;
                    if (scheme.getSlot(i,j).getDice().getDicecolor()==Colour.BLUE) cont[4]++;
                }
            }
        }
        min=cont[0];
        for (int i=1;i<5;i++){
            if (cont[i]<min) min=cont[i];
        }
        return min;
    }

    public int calcola_punteggio(GlassWindow scheme) {
        return numberofset(scheme)*getPunteggio();
    }
}
