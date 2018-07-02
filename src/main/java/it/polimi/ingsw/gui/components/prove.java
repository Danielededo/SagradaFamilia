package it.polimi.ingsw.gui.components;

import it.polimi.ingsw.gui.components.variousSchemes.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class prove extends Application {


    @Override
    public void start(Stage primaryStage){
        Group dr = new Group();

        Windows svddd = new SunGloryG();





        dr.getChildren().addAll(svddd);

        primaryStage.setScene(new Scene(dr));
        primaryStage.show();

    }
}
