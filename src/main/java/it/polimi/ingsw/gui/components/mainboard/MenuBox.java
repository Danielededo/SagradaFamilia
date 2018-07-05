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

    private RadioButton die = new RadioButton("Scegli un dado");
    private RadioButton tool = new RadioButton("Usa una carta strumento");
    private RadioButton pass = new RadioButton("Passa o salta il turno");
    private Button button = new Button("Scegli");
    private Label label = new Label("Scegli cosa fare in questo turno");

    private RadioButton plus = new RadioButton("+");
    private RadioButton minus = new RadioButton("-");


    public MenuBox(){
        ToggleGroup group = new ToggleGroup();

        pass.setToggleGroup(group);
        die.setToggleGroup(group);
        tool.setToggleGroup(group);

        ToggleGroup other = new ToggleGroup();
        plus.setToggleGroup(other);
        minus.setToggleGroup(other);

    }

    public int menuC(String title){

        final int[] answer = new int[1];
        answer[0] = 5;

        pass.setOnAction(event -> answer[0] = 1);
        die.setOnAction(event -> answer[0] = 2);
        tool.setOnAction(event -> answer[0] = 3);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(200);


        button.setOnMouseClicked(event -> window.close());

        window.setOnCloseRequest(event -> answer[0] = 1);

        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);

        menu.getChildren().addAll(label, pass, die, tool, button);

        window.setScene(new Scene(menu));
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

        window.setOnCloseRequest(event -> answer[0] = 1);

        pass.setOnAction(event -> answer[0] = 1);
        die.setOnAction(event -> answer[0] = 2);

        button.setOnMouseClicked(event -> window.close());

        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.getChildren().addAll(label, pass, die, button);



        window.setScene(new Scene(menu));
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


        window.setOnCloseRequest(event -> answer[0] = 1);

        pass.setOnAction(event -> answer[0] = 1);
        tool.setOnAction(event -> answer[0] = 3);

        button.setOnMouseClicked(event -> window.close());

        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.getChildren().addAll(label, pass, tool, button);


        window.setScene(new Scene(menu));
        window.showAndWait();


        return answer[0];
    }

    public int piumeno(String title){
        final int[] answer = new int[1];
        answer[0] = 2;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(200);

        window.setOnCloseRequest(event -> answer[0] = 2);

        plus.setOnAction(event -> answer[0] = 1);
        plus.setOnAction(event -> answer[0] = 0);

        button.setOnMouseClicked(event -> window.close());
        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.getChildren().addAll(plus, minus);

        window.setScene(new Scene(menu));
        window.showAndWait();


        return answer[0];
    }

}
