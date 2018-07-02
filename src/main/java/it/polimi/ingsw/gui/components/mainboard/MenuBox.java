package it.polimi.ingsw.gui.components.mainboard;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuBox {

    private VBox menu = new VBox();
    private Scene scene = new Scene(menu);
    private RadioButton die = new RadioButton("Scegli un dado");
    private RadioButton tool = new RadioButton("Usa una carta strumento");
    private RadioButton pass = new RadioButton("Passa o salta il turno");
    Button button = new Button("Scegli");


    public MenuBox(){
        ToggleGroup group = new ToggleGroup();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);

        pass.setToggleGroup(group);
        die.setToggleGroup(group);
        tool.setToggleGroup(group);

    }

    public int menuC(String title, String tag){

        final int[] answer = new int[1];
        answer[0] = 5;

        pass.setOnAction(event -> answer[0] = 5);
        die.setOnAction(event -> answer[0] = 6);
        tool.setOnAction(event -> answer[0] = 7);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(200);
        Label label = new Label();
        label.setText(tag);


        button.setOnMouseClicked(event -> window.close());

        window.setOnCloseRequest(event -> answer[0] = 5);

        menu.getChildren().addAll(label, pass, die, tool, button);

        window.setScene(scene);
        window.showAndWait();


        return answer[0];
    }


    public int menuD(String title){

        final int[] answer = new int[1];
        answer[0] = 5;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(200);

        window.setOnCloseRequest(event -> answer[0] = 5);

        pass.setOnAction(event -> answer[0] = 5);
        die.setOnAction(event -> answer[0] = 6);

        button.setOnMouseClicked(event -> window.close());

        menu.getChildren().remove(tool);

        scene = new Scene(menu);
        window.setScene(scene);
        window.showAndWait();


        return answer[0];
    }

    public int menuT(String title){

        final int[] answer = new int[1];
        answer[0] = 5;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(200);


        window.setOnCloseRequest(event -> answer[0] = 5);

        pass.setOnAction(event -> answer[0] = 5);
        tool.setOnAction(event -> answer[0] = 7);

        button.setOnMouseClicked(event -> window.close());

        menu.getChildren().remove(die);


        window.setScene(scene);
        window.showAndWait();


        return answer[0];
    }

}
