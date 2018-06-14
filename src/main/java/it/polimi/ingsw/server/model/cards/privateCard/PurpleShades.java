package it.polimi.ingsw.server.model.cards.privateCard;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.cards.PrivateObject;

public class PurpleShades extends PrivateObject{
    public PurpleShades() {
        super.setValue(5);
        super.setName("Sfumature Viola");
        super.setColor(Colour.PURPLE);
        super.setEffect("Somma dei valori su tutti i dadi viola");
    }
}
