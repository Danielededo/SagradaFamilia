package it.polimi.ingsw.gui.components;

import it.polimi.ingsw.gui.components.variousSchemes.AuroraeMagnificusG;
import it.polimi.ingsw.gui.components.variousSchemes.WindCase;
import it.polimi.ingsw.gui.components.variousSchemes.Windows;
import it.polimi.ingsw.server.utils.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class prove extends Application {


    @Override
    public void start(Stage primaryStage){
        WindCase choice = new WindCase(Constants.W_CASE,Constants.H_CASE);
        Windows hell = new AuroraeMagnificusG();
        StackPane bu = choice.addChosen(hell);

        primaryStage.setScene(new Scene(bu));
        primaryStage.show();
    }
}
