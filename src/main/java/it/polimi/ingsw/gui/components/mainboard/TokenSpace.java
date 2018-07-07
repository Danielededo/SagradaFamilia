package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.panels.TokenG;
import it.polimi.ingsw.utils.Constants;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TokenSpace extends HBox {

    private ArrayList<TokenG> money = new ArrayList<>();

    public TokenSpace(){
        super();
        setSpacing(10);
    }


    public void setTok(int j){
        while(j > 0){
            TokenG provv = new TokenG(Constants.W_TOKEN, Constants.H_TOKEN);
            money.add(provv);
            this.getChildren().add(money.get(money.size() - 1));
            j--;
        }
    }

    public ArrayList<TokenG> getMoney() {
        return money;
    }
}
