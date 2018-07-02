package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.variousSchemes.Windows;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChooseBox {

    private GridPane extracted = new GridPane();
    private ArrayList<Windows> among = new ArrayList<>();

    private Button one = new Button("1");
    private Button two = new Button("2");
    private Button three = new Button("3");
    private Button four = new Button("4");

    public ChooseBox(){
        super();


        extracted.add(one, 0,2);
        extracted.add(two, 1, 2);
        extracted.add(three, 0, 4);
        extracted.add(four, 1, 4);

        extracted.setAlignment(Pos.CENTER);
        extracted.setVgap(20);
    }


    public void lastAdding(ArrayList<Windows> glass){
        extracted.add(new StackPane(new Label(glass.get(0).getName())),0,0);
        extracted.add(glass.get(0),0,1);

        extracted.add(new StackPane(new Label(glass.get(1).getName())),1,0);
        extracted.add(glass.get(1),1,1);

        extracted.add(new StackPane(new Label(glass.get(2).getName())),0,2);
        extracted.add(glass.get(2),0,3);

        extracted.add(new StackPane(new Label(glass.get(3).getName())),1,2);
        extracted.add(glass.get(3),1,3);
    }

    public int gimmeInt(String title, String message){
        final int[] answer = new int[1];

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(1000);
        Label label = new Label();
        label.setText(message);

        getOne().setOnMouseClicked(event -> {
            answer[0] = 1;
            window.close();
        });

        getTwo().setOnMouseClicked(event -> {
            answer[0] = 2;
            window.close();
        });

        getThree().setOnMouseClicked(event -> {
            answer[0] = 3;
            window.close();
        });

        getFour().setOnMouseClicked(event -> {
            answer[0] = 4;
            window.close();
        });

        lastAdding(among);
        VBox layout = new VBox();
        layout.getChildren().addAll(extracted,label);

        window.setScene(new Scene(layout));
        window.showAndWait();

        //Make sure to return answer
        return answer[0];
    }

    public Button getOne() {
        return one;
    }

    public Button getTwo() {
        return two;
    }

    public Button getThree() {
        return three;
    }

    public Button getFour() {
        return four;
    }

    public GridPane getExtracted() {
        return extracted;
    }

    public void setExtracted(GridPane extracted) {
        this.extracted = extracted;
    }

    public ArrayList<Windows> getAmong() {
        return among;
    }

    public void setAmong(ArrayList<Windows> among) {
        this.among = among;
    }
}
