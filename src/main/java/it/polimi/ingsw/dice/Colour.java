package it.polimi.ingsw.dice;

public enum  Colour {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[93m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[95m"),
    WHITE("\u001B[37m");

    public static final String RESET = "\u001B[0m";

    private String escape;

    Colour(String escape){
        this.escape=escape;
    }

    public String escape(){
        return escape;
    }
}
