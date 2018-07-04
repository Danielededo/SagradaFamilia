package it.polimi.ingsw.gui.components;

public class Tools extends ComponentG {

    private int value;

    public Tools(String nome, String perc, int pos){
        super(nome,perc);
        setFitWidth(220);
        setFitHeight(220);
        setPreserveRatio(true);
        value = pos;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}