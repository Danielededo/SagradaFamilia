package it.polimi.ingsw.server.model.cards.privateCard;

import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.cards.PrivateObject;

public class BlueShades extends PrivateObject{
    public BlueShades() {
        super.setValue(4);
        super.setName("Sfumature Blu");
        super.setColor(Colour.BLUE);
        super.setEffect("Somma dei valori su tutti i dadi blu");
    }
}
