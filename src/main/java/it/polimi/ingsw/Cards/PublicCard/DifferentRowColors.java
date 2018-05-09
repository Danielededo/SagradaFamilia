package it.polimi.ingsw.Cards.PublicCard;

import it.polimi.ingsw.Cards.PublicObject;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Game.Player;

import java.util.ArrayList;

public class DifferentRowColors extends PublicObject {
    public DifferentRowColors() {
        setName("Different Colors - Row");
        setValue(1);
        setPunteggio(6);
        setEffect("Rows without repeated colors");
    }

    private int numerorighe(Player player) {
        int cont=0;
        ArrayList<Colour> colori = new ArrayList<Colour>();
        for (int i=0;i<4;i++){
            colori.add(Colour.RED);
            colori.add(Colour.BLUE);
            colori.add(Colour.YELLOW);
            colori.add(Colour.GREEN);
            colori.add(Colour.PURPLE);
            for (int j=0;j<5;j++){
                if (player.getWindow().getSlot(i,j).isOccupate() &&
                        colori.contains(player.getWindow().getSlot(i,j).getDice().getDicecolor())){
                    colori.remove(player.getWindow().getSlot(i,j).getDice().getDicecolor());
                }
            }
            if (colori.size()==0) {
                cont++;
            }
            colori.clear();
        }
        return cont;
    }

    public int calcola_punteggio(Player player) {
        return numerorighe(player)*getPunteggio();
    }
}
