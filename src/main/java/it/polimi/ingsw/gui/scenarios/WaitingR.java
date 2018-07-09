package it.polimi.ingsw.gui.scenarios;

import it.polimi.ingsw.gui.components.Plancia;
import it.polimi.ingsw.utils.Constants;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class WaitingR extends GridPane {


    private String planciapath = new String(Constants.IMG_PRINC + Constants.IMG_SCHE + "/planciaBlue.png");

    private Label current = new Label("");
    private VBox players = new VBox();
    private Label other = new Label();
    private Label soli = new Label("");



    public WaitingR(){
        super();

        VBox gioc1 = new VBox();
        gioc1.setAlignment(Pos.CENTER);
        ImageView vetcurr = new Plancia("Vetrata", planciapath);

        current.setTextFill(Paint.valueOf("#ffffff"));
        gioc1.getChildren().addAll(vetcurr, current);

        add(gioc1, 0,2);

        Label label = new Label("Lista d'attesa: ");
        label.setTextFill(Paint.valueOf("#ffffff"));
        label.setVisible(true);
        label.setFont(Font.font("Times New Roman"));
        label.setStyle("-fx-font-size: 20");
        players.getChildren().add(label);
        soli.setTextFill(Paint.valueOf("#ffffff"));
        other.setTextFill(Paint.valueOf("#ffffff"));
        other.setStyle("-fx-font-size: 50");

        add(other, 0, 0);
        add(players, 1,2, 3,1);
        add(soli, 0,4);

        setStyle("-fx-background-color: black;");
        setAlignment(Pos.CENTER);
    }


    public void wrlistens(String oldie, String newie) {
        if (oldie.equals("Timer")) {
            Platform.runLater(() -> other.setText(newie));
        }
    }

    public Label getSoli() {
        return soli;
    }

    public void setSoli(Label soli) {
        this.soli = soli;
    }




    public Label getCurrent() {
        return current;
    }

    public void setCurrent(Label current) {
        this.current = current;
    }

    public VBox getPlayers() {
        return players;
    }

    public void setPlayers(VBox players) {
        this.players = players;
    }

    public Label getOther() {
        return other;
    }

    public void setOther(Label other) {
        this.other = other;
    }


}
