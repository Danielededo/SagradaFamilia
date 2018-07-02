package it.polimi.ingsw.gui.components;

import it.polimi.ingsw.gui.components.panels.*;
import it.polimi.ingsw.gui.components.variousSchemes.*;
import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class Building {

    private String pubpath = Constants.IMG_PRINC + Constants.IMG_PUBB;
    private String privpath = Constants.IMG_PRINC + Constants.IMG_PRIV;
    private String toolpath = Constants.IMG_PRINC + Constants.IMG_TOOL;

    public Building(){

    }

    public ArrayList<PubbObj> buildingPubb(String path) {
        ArrayList<PubbObj> pubbdeck = new ArrayList<>();
        pubbdeck.add(new PubbObj("Colori diversi - Riga", path + "/colori riga.png"));
        pubbdeck.add(new PubbObj("Colori diversi - Colonna", path + "/colori colonna.png"));
        pubbdeck.add(new PubbObj("Sfumature diverse - Riga", path + "/sfumature diverse riga.png"));
        pubbdeck.add(new PubbObj("Sfumature diverse - Colonna", path + "/sfumature diverse colonna.png"));
        pubbdeck.add(new PubbObj("Sfumature Chiare", path + "/sfumature chiare.png"));
        pubbdeck.add(new PubbObj("Sfumature Medie", path + "/sfumature medie.png"));
        pubbdeck.add(new PubbObj("Sfumature Scure", path + "/sfumature scure.png"));
        pubbdeck.add(new PubbObj("Sfumature Diverse", path + "/sfumature diverse.png"));
        pubbdeck.add(new PubbObj("Diagonali Colorate", path + "/diagonali colorate.png"));
        pubbdeck.add(new PubbObj("Varietà di Colore", path + "/varietà di colore.png"));
        pubbdeck.add(new PubbObj("Retro", path + "/retro pubbliche.png"));

        return pubbdeck;
    }

    public ArrayList<PrivObje> buildingPriv(String path) {
        ArrayList<PrivObje> privdeck = new ArrayList<>();
        privdeck.add(new PrivObje("Sfumature Blu", path + "/privataBlue.png"));
        privdeck.add(new PrivObje("Sfumature Gialle", path + "/privataGialla.png"));
        privdeck.add(new PrivObje("Sfumature Rosse", path + "/privataRossa.png"));
        privdeck.add(new PrivObje("Sfumature Verdi", path + "/privataVerde.png"));
        privdeck.add(new PrivObje("Sfumature Viola", path + "/privataViola.png"));
        privdeck.add(new PrivObje("Retro", path + "/retroPrivate.png"));

        return privdeck;
    }

    public ArrayList<Tools> buildingTools(String path){
        ArrayList<Tools> tooldeck = new ArrayList<>();
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
        tooldeck.add(new Tools("Retro", path + "/retro tool.png", 13));

        return tooldeck;
    }

    public ArrayList<Windows> buildingGlasswindow(){
        ArrayList<Windows> windowdeck = new ArrayList<>();
        windowdeck.add(new AuroraeMagnificusG());
        windowdeck.add(new AuroraSagradisG());
        windowdeck.add(new BattloG());
        windowdeck.add(new BellesguardG());
        windowdeck.add(new ChromaticSplendorG());
        windowdeck.add(new ComitasG());
        windowdeck.add(new FirelightG());
        windowdeck.add(new FirmitasG());
        windowdeck.add(new FractalDropsG());
        windowdeck.add(new FulgordelCieloG());
        windowdeck.add(new GravitasG());
        windowdeck.add(new IndustriaG());
        windowdeck.add(new KaleidoscopicDreamG());
        windowdeck.add(new LuxAstramG());
        windowdeck.add(new LuxMundiG());
        windowdeck.add(new LuzCelestialG());
        windowdeck.add(new RipplesofLightG());
        windowdeck.add(new ShadowThiefG());
        windowdeck.add(new SunCatcherG());
        windowdeck.add(new SunGloryG());
        windowdeck.add(new SymphonyofLightG());
        windowdeck.add(new ViaLuxG());
        windowdeck.add(new VirtusG());
        windowdeck.add(new WaterofLifeG());


        return windowdeck;
    }

    public String colorComparing(String color) {
        String prov = color;
        switch (prov) {
            case ("RED"): return Constants.RED;
            case ("GREEN"): return Constants.GREEN;
            case ("YELLOW"): return Constants.YELLOW;
            case ("BLACK"): return Constants.BLACK;
            case ("PURPLE"): return Constants.PURPLE;
            case ("WHITE"): return Constants.WHITE;
        }
        return prov;
    }

    public Canvas faceComparing(String numb){
        Canvas prov = new FacciaVuota(Constants.SLOT, Constants.SLOT);
        switch (numb) {
            case ("1"): {prov = new Faccia1(Constants.SLOT, Constants.SLOT); break;}
            case ("2"): {prov = new Faccia2(Constants.SLOT, Constants.SLOT); break;}
            case ("3"): {prov = new Faccia3(Constants.SLOT, Constants.SLOT); break;}
            case ("4"): {prov = new Faccia4(Constants.SLOT, Constants.SLOT); break;}
            case ("5"): {prov = new Faccia5(Constants.SLOT, Constants.SLOT); break;}
            case ("6"): {prov = new Faccia6(Constants.SLOT, Constants.SLOT); break;}
        }
        return prov;
    }

    public String getPubpath() {
        return pubpath;
    }

    public String getPrivpath() {
        return privpath;
    }

    public String getToolpath() {
        return toolpath;
    }
}
