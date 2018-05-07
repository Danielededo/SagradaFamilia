package Cards;

public class GlassWindow  {
    private String name;
    private  static final int width = 4;
    private static final int height = 5;
    private int difficulty;
    private int link;
    private Slot[][] scheme = new Slot[width][height];

    public void setLink(int link) {
        this.link = link;
    }

    public int getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setSlot(Slot scheme, int i, int j) {
        this.scheme[i][j] = scheme;
        scheme.setLine(i);
        scheme.setColumn(j);
    }

    @Override
    public String toString() {
        String string = "\n";
        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                string += scheme[i][j].toString()+ "\t";
            }
            string+="\n";
        }
        return "GlassWindow{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", scheme=" + string +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Slot getSlot(int i, int j) {
        return scheme[i][j];
    }

}
