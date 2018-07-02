package it.polimi.ingsw.gui.components.variousSchemes;

import it.polimi.ingsw.gui.components.panels.*;
import it.polimi.ingsw.server.utils.Constants;

public class GravitasG extends Windows{

    public GravitasG(){

        super();
        this.setName("Gravitas");
        this.setDifficulty(5);

        this.getCasella00().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella01().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella02().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella03().setStyle("-fx-background-color: " + Constants.BLUE);
        this.getCasella04().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella10().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella11().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella12().setStyle("-fx-background-color: " + Constants.BLUE);
        this.getCasella13().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella14().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella20().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella21().setStyle("-fx-background-color: " + Constants.BLUE);
        this.getCasella22().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella23().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella24().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella30().setStyle("-fx-background-color: " + Constants.BLUE);
        this.getCasella31().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella32().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella33().setStyle("-fx-background-color: " + Constants.WHITE);
        this.getCasella34().setStyle("-fx-background-color: " + Constants.WHITE);


        this.getCasella00().getChildren().addAll(new Faccia1(Constants.SLOT,Constants.SLOT));
        this.getCasella01().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella02().getChildren().addAll(new Faccia3(Constants.SLOT,Constants.SLOT));
        this.getCasella03().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella04().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella10().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella11().getChildren().addAll(new Faccia2(Constants.SLOT,Constants.SLOT));
        this.getCasella12().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella13().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella14().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella20().getChildren().addAll(new Faccia6(Constants.SLOT,Constants.SLOT));
        this.getCasella21().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella22().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella23().getChildren().addAll(new Faccia4(Constants.SLOT,Constants.SLOT));
        this.getCasella24().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella30().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella31().getChildren().addAll(new Faccia5(Constants.SLOT,Constants.SLOT));
        this.getCasella32().getChildren().addAll(new Faccia2(Constants.SLOT,Constants.SLOT));
        this.getCasella33().getChildren().addAll(new FacciaVuota(Constants.SLOT,Constants.SLOT));
        this.getCasella34().getChildren().addAll(new Faccia1(Constants.SLOT,Constants.SLOT));
    }
}
