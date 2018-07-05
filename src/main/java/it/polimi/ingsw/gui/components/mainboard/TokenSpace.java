package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.panels.TokenG;
import it.polimi.ingsw.utils.Constants;
import javafx.scene.layout.HBox;

public class TokenSpace extends HBox {

    private TokenG one = new TokenG(Constants.W_TOKEN,Constants.H_TOKEN);
    private TokenG two = new TokenG(Constants.W_TOKEN,Constants.H_TOKEN);
    private TokenG three = new TokenG(Constants.W_TOKEN,Constants.H_TOKEN);
    private TokenG four = new TokenG(Constants.W_TOKEN,Constants.H_TOKEN);
    private TokenG five = new TokenG(Constants.W_TOKEN,Constants.H_TOKEN);
    private TokenG six = new TokenG(Constants.W_TOKEN,Constants.H_TOKEN);


    public TokenSpace(){
        super();
        setSpacing(10);
    }


    public void setTok(int j){
        switch (j){
            case (3): {this.getChildren().addAll(one, two, three); break;}
            case (4): {this.getChildren().addAll(one, two, three, four); break;}
            case (5): {this.getChildren().addAll(one, two, three, four, five); break;}
            case (6): {this.getChildren().addAll(one, two, three, four, five, six); break;}
        }
    }
}
