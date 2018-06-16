package it.polimi.ingsw.gui.components.variousSchemes;

import it.polimi.ingsw.server.model.cards.Slot;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public abstract class Windows extends GridPane{
    private int link;
    private String name;

    private Slot selected = new Slot();
    private IntegerProperty riga = new SimpleIntegerProperty(-1);
    private IntegerProperty colonna = new SimpleIntegerProperty(-1);


    private StackPane casella00 = new StackPane();
    private StackPane casella01 = new StackPane();
    private StackPane casella02 = new StackPane();
    private StackPane casella03 = new StackPane();
    private StackPane casella04 = new StackPane();
    private StackPane casella10 = new StackPane();
    private StackPane casella11 = new StackPane();
    private StackPane casella12 = new StackPane();
    private StackPane casella13 = new StackPane();
    private StackPane casella14 = new StackPane();
    private StackPane casella20 = new StackPane();
    private StackPane casella21 = new StackPane();
    private StackPane casella22 = new StackPane();
    private StackPane casella23 = new StackPane();
    private StackPane casella24 = new StackPane();
    private StackPane casella30 = new StackPane();
    private StackPane casella31 = new StackPane();
    private StackPane casella32 = new StackPane();
    private StackPane casella33 = new StackPane();
    private StackPane casella34 = new StackPane();


    public Windows(){

    //prima riga
        casella00.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(0);
            slot.setLine(0);
            //fare metodo che butta fuori lo slot al server
            System.out.println(slot.toString());
        });
        this.add(casella00,0,0);


        casella01.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(1);
            slot.setLine(0);
        });
        this.add(casella01,1,0);


        casella02.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(2);
            slot.setLine(0);
        });
        this.add(casella02,2,0);


        casella03.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(3);
            slot.setLine(0);
        });
        this.add(casella03,3,0);


        casella04.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(4);
            slot.setLine(0);
        });
        this.add(casella04,4,0);


    //seconda riga
        casella10.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(0);
            slot.setLine(1);
        });
        this.add(casella10,0,1);


        casella11.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(1);
            slot.setLine(1);
        });
        this.add(casella11,1,1);


        casella12.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(2);
            slot.setLine(1);
        });
        this.add(casella12,2,1);


        casella13.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(3);
            slot.setLine(1);
        });
        this.add(casella13,3,1);


        casella14.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(4);
            slot.setLine(1);
        });
        this.add(casella14,4,1);


    //terza riga
        casella20.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(0);
            slot.setLine(2);
        });
        this.add(casella20,0,2);


        casella21.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(1);
            slot.setLine(2);
        });
        this.add(casella21,1,2);


        casella22.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(2);
            slot.setLine(2);
        });
        this.add(casella22,2,2);


        casella23.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(3);
            slot.setLine(2);
        });
        this.add(casella23,3,2);


        casella24.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(4);
            slot.setLine(2);
        });
        this.add(casella24,4,2);


    //quarta riga
        casella30.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(0);
            slot.setLine(3);
        });
        this.add(casella30,0,3);


        casella31.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(1);
            slot.setLine(3);
        });
        this.add(casella31,1,3);


        casella32.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(2);
            slot.setLine(3);
        });
        this.add(casella32,2,3);


        casella33.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(3);
            slot.setLine(3);
        });
        this.add(casella33,3,3);


        casella34.setOnMouseClicked(event -> {
            Slot slot = new Slot();
            slot.setColumn(4);
            slot.setLine(3);
        });
        this.add(casella34,4,3);



        this.setGridLinesVisible(true);
        this.setPadding(new Insets(10,10,10,10));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public StackPane getCasella00() {
        return casella00;
    }

    public void setCasella00(StackPane casella00) {
        this.casella00 = casella00;
    }

    public StackPane getCasella01() {
        return casella01;
    }

    public void setCasella01(StackPane casella01) {
        this.casella01 = casella01;
    }

    public StackPane getCasella02() {
        return casella02;
    }

    public void setCasella02(StackPane casella02) {
        this.casella02 = casella02;
    }

    public StackPane getCasella03() {
        return casella03;
    }

    public void setCasella03(StackPane casella03) {
        this.casella03 = casella03;
    }

    public StackPane getCasella04() {
        return casella04;
    }

    public void setCasella04(StackPane casella04) {
        this.casella04 = casella04;
    }

    public StackPane getCasella10() {
        return casella10;
    }

    public void setCasella10(StackPane casella10) {
        this.casella10 = casella10;
    }

    public StackPane getCasella11() {
        return casella11;
    }

    public void setCasella11(StackPane casella11) {
        this.casella11 = casella11;
    }

    public StackPane getCasella12() {
        return casella12;
    }

    public void setCasella12(StackPane casella12) {
        this.casella12 = casella12;
    }

    public StackPane getCasella13() {
        return casella13;
    }

    public void setCasella13(StackPane casella13) {
        this.casella13 = casella13;
    }

    public StackPane getCasella14() {
        return casella14;
    }

    public void setCasella14(StackPane casella14) {
        this.casella14 = casella14;
    }

    public StackPane getCasella20() {
        return casella20;
    }

    public void setCasella20(StackPane casella20) {
        this.casella20 = casella20;
    }

    public StackPane getCasella21() {
        return casella21;
    }

    public void setCasella21(StackPane casella21) {
        this.casella21 = casella21;
    }

    public StackPane getCasella22() {
        return casella22;
    }

    public void setCasella22(StackPane casella22) {
        this.casella22 = casella22;
    }

    public StackPane getCasella23() {
        return casella23;
    }

    public void setCasella23(StackPane casella23) {
        this.casella23 = casella23;
    }

    public StackPane getCasella24() {
        return casella24;
    }

    public void setCasella24(StackPane casella24) {
        this.casella24 = casella24;
    }

    public StackPane getCasella30() {
        return casella30;
    }

    public void setCasella30(StackPane casella30) {
        this.casella30 = casella30;
    }

    public StackPane getCasella31() {
        return casella31;
    }

    public void setCasella31(StackPane casella31) {
        this.casella31 = casella31;
    }

    public StackPane getCasella32() {
        return casella32;
    }

    public void setCasella32(StackPane casella32) {
        this.casella32 = casella32;
    }

    public StackPane getCasella33() {
        return casella33;
    }

    public void setCasella33(StackPane casella33) {
        this.casella33 = casella33;
    }

    public StackPane getCasella34() {
        return casella34;
    }

    public void setCasella34(StackPane casella34) {
        this.casella34 = casella34;
    }
}
