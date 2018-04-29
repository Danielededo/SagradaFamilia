package Cards;

public abstract class Card {
    private int value;
    private String name;

    @Override
    public abstract String toString();
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
