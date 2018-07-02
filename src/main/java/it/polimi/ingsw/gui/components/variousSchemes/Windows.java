package it.polimi.ingsw.gui.components.variousSchemes;

import it.polimi.ingsw.gui.components.panels.DieG;
import it.polimi.ingsw.gui.components.panels.Tassel;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public abstract class Windows extends GridPane{
    private int difficulty;
    private String name;
    private ArrayList<Tassel> list = new ArrayList<>();

    private int selected = -1;

    private DieG update;

    private Tassel casella00 = new Tassel(1);
    private Tassel casella01 = new Tassel(2);
    private Tassel casella02 = new Tassel(3);
    private Tassel casella03 = new Tassel(4);
    private Tassel casella04 = new Tassel(5);
    private Tassel casella10 = new Tassel(6);
    private Tassel casella11 = new Tassel(7);
    private Tassel casella12 = new Tassel(8);
    private Tassel casella13 = new Tassel(9);
    private Tassel casella14 = new Tassel(10);
    private Tassel casella20 = new Tassel(11);
    private Tassel casella21 = new Tassel(12);
    private Tassel casella22 = new Tassel(13);
    private Tassel casella23 = new Tassel(14);
    private Tassel casella24 = new Tassel(15);
    private Tassel casella30 = new Tassel(16);
    private Tassel casella31 = new Tassel(17);
    private Tassel casella32 = new Tassel(18);
    private Tassel casella33 = new Tassel(19);
    private Tassel casella34 = new Tassel(20);


    public Windows(){

        list.add(casella00);
        list.add(casella01);
        list.add(casella02);
        list.add(casella03);
        list.add(casella04);
        list.add(casella10);
        list.add(casella11);
        list.add(casella12);
        list.add(casella13);
        list.add(casella14);
        list.add(casella20);
        list.add(casella21);
        list.add(casella22);
        list.add(casella23);
        list.add(casella24);
        list.add(casella30);
        list.add(casella31);
        list.add(casella32);
        list.add(casella33);
        list.add(casella34);



    //prima riga
        this.add(list.get(0),0,0);

        this.add(list.get(1),1,0);

        this.add(list.get(2),2,0);

        this.add(list.get(3),3,0);

        this.add(list.get(4),4,0);


    //seconda riga

        this.add(list.get(5),0,1);

        this.add(list.get(6),1,1);

        this.add(list.get(7),2,1);

        this.add(list.get(8),3,1);

        this.add(list.get(9),4,1);


    //terza riga

        this.add(list.get(10),0,2);

        this.add(list.get(11),1,2);

        this.add(list.get(12),2,2);

        this.add(list.get(13),3,2);

        this.add(list.get(14),4,2);


    //quarta riga

        this.add(list.get(15),0,3);

        this.add(list.get(16),1,3);

        this.add(list.get(17),2,3);

        this.add(list.get(18),3,3);

        this.add(list.get(19),4,3);

        this.setGridLinesVisible(true);
        this.setPadding(new Insets(10,10,10,10));
    }


    public DieG getUpdate() {
        return update;
    }

    public void setUpdate(DieG update) {
        this.update = update;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public ArrayList<Tassel> getList() {
        return list;
    }

    public void soltOffIt(Windows w){
        selected = -1;
    }

    public int getSelected() {
        return selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Tassel getCasella00() {
        return casella00;
    }

    public void setCasella00(Tassel casella00) {
        this.casella00 = casella00;
    }

    public Tassel getCasella01() {
        return casella01;
    }

    public void setCasella01(Tassel casella01) {
        this.casella01 = casella01;
    }

    public Tassel getCasella02() {
        return casella02;
    }

    public void setCasella02(Tassel casella02) {
        this.casella02 = casella02;
    }

    public Tassel getCasella03() {
        return casella03;
    }

    public void setCasella03(Tassel casella03) {
        this.casella03 = casella03;
    }

    public Tassel getCasella04() {
        return casella04;
    }

    public void setCasella04(Tassel casella04) {
        this.casella04 = casella04;
    }

    public Tassel getCasella10() {
        return casella10;
    }

    public void setCasella10(Tassel casella10) {
        this.casella10 = casella10;
    }

    public Tassel getCasella11() {
        return casella11;
    }

    public void setCasella11(Tassel casella11) {
        this.casella11 = casella11;
    }

    public Tassel getCasella12() {
        return casella12;
    }

    public void setCasella12(Tassel casella12) {
        this.casella12 = casella12;
    }

    public Tassel getCasella13() {
        return casella13;
    }

    public void setCasella13(Tassel casella13) {
        this.casella13 = casella13;
    }

    public Tassel getCasella14() {
        return casella14;
    }

    public void setCasella14(Tassel casella14) {
        this.casella14 = casella14;
    }

    public Tassel getCasella20() {
        return casella20;
    }

    public void setCasella20(Tassel casella20) {
        this.casella20 = casella20;
    }

    public Tassel getCasella21() {
        return casella21;
    }

    public void setCasella21(Tassel casella21) {
        this.casella21 = casella21;
    }

    public Tassel getCasella22() {
        return casella22;
    }

    public void setCasella22(Tassel casella22) {
        this.casella22 = casella22;
    }

    public Tassel getCasella23() {
        return casella23;
    }

    public void setCasella23(Tassel casella23) {
        this.casella23 = casella23;
    }

    public Tassel getCasella24() {
        return casella24;
    }

    public void setCasella24(Tassel casella24) {
        this.casella24 = casella24;
    }

    public Tassel getCasella30() {
        return casella30;
    }

    public void setCasella30(Tassel casella30) {
        this.casella30 = casella30;
    }

    public Tassel getCasella31() {
        return casella31;
    }

    public void setCasella31(Tassel casella31) {
        this.casella31 = casella31;
    }

    public Tassel getCasella32() {
        return casella32;
    }

    public void setCasella32(Tassel casella32) {
        this.casella32 = casella32;
    }

    public Tassel getCasella33() {
        return casella33;
    }

    public void setCasella33(Tassel casella33) {
        this.casella33 = casella33;
    }

    public Tassel getCasella34() {
        return casella34;
    }

    public void setCasella34(Tassel casella34) {
        this.casella34 = casella34;
    }
}