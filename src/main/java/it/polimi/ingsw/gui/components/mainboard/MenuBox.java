package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.panels.DieG;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        //pass.setSelected(true);

        button.setOnMouseClicked(event -> {
            if (answer[0]==5)
                answer[0] = 1;
            window.close();
        });
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

        button.setOnMouseClicked(event -> {
            if (answer[0]==5)
                answer[0] = 1;
            window.close();
        });

        //pass.setSelected(true);

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

        //pass.setSelected(true);

        window.setOnCloseRequest(event -> answer[0] = 1);

        pass.setOnAction(event -> answer[0] = 1);
        tool.setOnAction(event -> answer[0] = 3);

        button.setOnMouseClicked(event -> {
            if (answer[0]==5)
                answer[0] = 1;
            window.close();
        });

        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.getChildren().addAll(label, pass, tool, button);


        window.setScene(new Scene(menu));
        window.showAndWait();


        return answer[0];
    }

    public int plusminus(String title, String message){
        final int[] answer = new int[1];
        answer[0] = 7;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(200);

        //plus.setSelected(true);

        window.setOnCloseRequest(event -> answer[0] = 2);

        plus.setOnAction(event -> answer[0] = 1);
        minus.setOnAction(event -> answer[0] = 0);

        Label lab = new Label(message);
        button.setOnMouseClicked(event -> {
            if (answer[0]==7)
                answer[0] = 2;
            window.close();
        });
        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.getChildren().addAll(lab, plus, minus, button);

        window.setScene(new Scene(menu));
        window.showAndWait();


        return answer[0];
    }

    public int enterValue(String title, String message, DieG die){
        final int[] answer = new int[1];
        answer[0] = 0;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(200);

        window.setOnCloseRequest(event -> answer[0] = 0);

        ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
        Label lab = new Label(message);
        choiceBox.getItems().addAll(1,2,3,4,5,6);
        choiceBox.setValue(1);

        button.setOnMouseClicked(event -> {
            answer[0] = choiceBox.getValue();
            window.close();
        });

        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.getChildren().addAll(die, lab, choiceBox, button);

        window.setScene(new Scene(menu));
        window.showAndWait();


        return answer[0];

    }

    public int howMany(String title, String message){
        final int[] answer = new int[1];
        answer[0] = 7;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(200);

        RadioButton one = new RadioButton("1");
        RadioButton two = new RadioButton("2");
        ToggleGroup another = new ToggleGroup();
        one.setToggleGroup(another);
        two.setToggleGroup(another);

        window.setOnCloseRequest(event -> answer[0] = 3);

        one.setOnAction(event -> answer[0] = 1);
        two.setOnAction(event -> answer[0] = 2);

        Label lab = new Label(message);
        button.setOnMouseClicked(event -> {
            if (answer[0]==7)
                answer[0] = 3;
            window.close();
        });
        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.getChildren().addAll(lab, one, two, button);

        window.setScene(new Scene(menu));
        window.showAndWait();


        return answer[0];
    }

}
