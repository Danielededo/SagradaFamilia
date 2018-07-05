package it.polimi.ingsw.server.model.cards.privateCard;

import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.cards.PrivateObject;

public class YellowShades extends PrivateObject {
    public YellowShades() {
        super.setValue(2);
        super.setName("Sfumature Gialle");
        super.setColor(Colour.YELLOW);
        super.setEffect("Somma dei valori su tutti i dadi gialle");
    }
}
