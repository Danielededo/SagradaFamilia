package it.polimi.ingsw.gui.components.variousSchemes;

import it.polimi.ingsw.gui.components.panels.*;
import it.polimi.ingsw.server.utils.Constants;

public class ComitasG extends Windows{

    public ComitasG(){

        super();
        this.setName("Comitas");
        this.setDifficulty(5);


        this.getList().get(0).setStyle("-fx-background-color: " + Constants.YELLOW);
        this.getList().get(1).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(2).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(3).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(4).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(5).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(6).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(7).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(8).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(9).setStyle("-fx-background-color: " + Constants.YELLOW);
        this.getList().get(10).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(11).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(12).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(13).setStyle("-fx-background-color: " + Constants.YELLOW);
        this.getList().get(14).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(15).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(16).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(17).setStyle("-fx-background-color: " + Constants.YELLOW);
        this.getList().get(18).setStyle("-fx-background-color: " + Constants.WHITE);
        this.getList().get(19).setStyle("-fx-background-color: " + Constants.WHITE);


        this.getList().get(0).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(1).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(2).getChildren().addAll(new Faccia2(Constants.SLOT,Constants.SLOT));
        this.getList().get(3).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(4).getChildren().addAll(new Faccia6(Constants.SLOT,Constants.SLOT));
        this.getList().get(5).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(6).getChildren().addAll(new Faccia4(Constants.SLOT,Constants.SLOT));
        this.getList().get(7).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(8).getChildren().addAll(new Faccia5(Constants.SLOT,Constants.SLOT));
        this.getList().get(9).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(10).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(11).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(12).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(13).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(14).getChildren().addAll(new Faccia5(Constants.SLOT,Constants.SLOT));
        this.getList().get(15).getChildren().addAll(new Faccia1(Constants.SLOT,Constants.SLOT));
        this.getList().get(16).getChildren().addAll(new Faccia2(Constants.SLOT,Constants.SLOT));
        this.getList().get(17).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getList().get(18).getChildren().addAll(new Faccia3(Constants.SLOT,Constants.SLOT));
        this.getList().get(19).getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
    }
}
