package it.polimi.ingsw.cards.publicCard;

import it.polimi.ingsw.cards.PublicObject;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.game.Player;

import java.util.ArrayList;

public class DifferentColumnsColors extends PublicObject {
    public DifferentColumnsColors() {
        setName("Different Colors - Column");
        setValue(2);
        setPunteggio(5);
        setEffect("Columns without repeated colors");
    }

    private int numerocolonne(Player player){
        int cont=0;
        ArrayList<Colour> colori = new ArrayList<Colour>();
        for (int i=0;i<5;i++){
            colori.add(Colour.RED);
            colori.add(Colour.BLUE);
            colori.add(Colour.YELLOW);
            colori.add(Colour.GREEN);
            colori.add(Colour.PURPLE);
            for (int j=0;j<4;j++){
                if (player.getWindow().getSlot(j,i).isOccupate() &&
                        colori.contains(player.getWindow().getSlot(j,i).getDice().getDicecolor())){
                    colori.remove(player.getWindow().getSlot(j,i).getDice().getDicecolor());
                }
            }
            if (colori.size()==1) {
                cont++;
            }
            colori.clear();
        }
        return cont;
    }

    public int calcola_punteggio(Player player) {
        return numerocolonne(player)*getPunteggio();
    }
}
