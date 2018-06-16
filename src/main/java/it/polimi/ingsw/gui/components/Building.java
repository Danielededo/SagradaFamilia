package it.polimi.ingsw.gui.components;

import java.util.ArrayList;

public class Building {

    private ArrayList<String> plance = new ArrayList<String>();
    private ArrayList<String> pubob = new ArrayList<String>();
    private ArrayList<String> prob = new ArrayList<String>();
    private ArrayList<String> tool = new ArrayList<String>();



    public Building(){

    }

    public ArrayList<Component> buildingHolders(String path){
        ArrayList<Component> waitingshow = new ArrayList<>();
        Plancia blue = new Plancia("Vetrata Blu", path + "/planciaBlue.png");
        waitingshow.add(blue);
        Plancia red = new Plancia("Vetrata Rossa", path + "/planciaRossa.png");
        waitingshow.add(red);
        Plancia green = new Plancia("Vetrata Verde", path + "/planciaVerde.png");
        waitingshow.add(green);
        Plancia purple = new Plancia("Vetrata Viola", path + "/planciaViola.png");
        waitingshow.add(purple);

        return waitingshow;
    }

    public ArrayList<Component> buildingPubb(String path) {
        ArrayList<Component> pubbdeck = new ArrayList<>();
        pubbdeck.add(new PubbObj("Colori diversi - Riga", path + "/colori riga.png"));
        pubbdeck.add(new PubbObj("Colori diversi - Colonna", path + "/colori colonna.png"));
        pubbdeck.add(new PubbObj("Sfumature diverse - Riga", path + "/sfumature diverse riga.png"));
        pubbdeck.add(new PubbObj("Sfumature diverse - Colonna", path + "/sfumature diverse colonna.png"));
        pubbdeck.add(new PubbObj("Sfumature chiare", path + "/sfumature chiare.png"));
        pubbdeck.add(new PubbObj("Sfumature medie", path + "/sfumature medie.png"));
        pubbdeck.add(new PubbObj("Sfumature scure", path + "/sfumature scure.png"));
        pubbdeck.add(new PubbObj("Sfumature diverse", path + "/sfumature diverse.png"));
        pubbdeck.add(new PubbObj("Diagonali colorate", path + "/diagonali colorate.png"));
        pubbdeck.add(new PubbObj("Varietà di colore", path + "/varietà di colore.png"));
        //pubbdeck.add(new PubbObj("", path + "/retro pubbliche.png"));

        return pubbdeck;
    }

    public ArrayList<Component> buildingPriv(String path) {
        ArrayList<Component> privdeck = new ArrayList<>();
        privdeck.add(new PrivObje("Sfumature blu - Privata", path + "/privataBlue.png"));
        privdeck.add(new PrivObje("Sfumature gialle - Privata", path + "/privataGialls.png"));
        privdeck.add(new PrivObje("Sfumature rosse - Privata", path + "/privataRossa.png"));
        privdeck.add(new PrivObje("Sfumature verdi - Privata", path + "/privataVerde.png"));
        privdeck.add(new PrivObje("Sfumature viola - Privata", path + "/privataViola.png"));
        //privdeck.add(new PrivObje("", path + "/retroPrivate.png"));

        return privdeck;
    }

    public ArrayList<Component> buildingTools(String path){
        ArrayList<Component> tooldeck = new ArrayList<>();
        tooldeck.add(new Tools("Pinza Sgrossatrice", path + "/1 Pinza Sgrossatrice.png", 1));
        tooldeck.add(new Tools("Pennello per Eglomise", path + "/2 pennello per eglomise.png",2));
        tooldeck.add(new Tools("Alesatore per lamina di rame", path + "/3 alesatore per lamina di rame.png",3));
        tooldeck.add(new Tools("Lathekin", path + "/4 lathekin.png",4));
        tooldeck.add(new Tools("Taglierina circolare", path + "/5 taglierina circolare.png",5));
        tooldeck.add(new Tools("Pennello per Pasta Salda", path + "/6 pennello per pasta salda.png",6));
        tooldeck.add(new Tools("Martelletto", path + "/7 martelletto.png",7));
        tooldeck.add(new Tools("Tenaglia a Rotelle", path + "/8 tenaglia a rotelle.png",8));
        tooldeck.add(new Tools("Riga in Sughero", path + "/9 riga in sughero.png",9));
        tooldeck.add(new Tools("Tampone Diamantato", path + "/10 tampone diamantato.png",10));
        tooldeck.add(new Tools("Diluente per Pasta Salda", path + "/11 diluente per pasta salda.png",11));
        tooldeck.add(new Tools("Taglierina Manuale", path + "/12 taglierina manuale.png",12));
        //tooldeck.add(new Tools("Pinza Sgrossatrice", path + "/1 Pinza Sgrossatrice.png"));

        return tooldeck;
    }
}
