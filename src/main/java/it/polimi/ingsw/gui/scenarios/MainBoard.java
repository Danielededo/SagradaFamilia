package it.polimi.ingsw.gui.scenarios;

import it.polimi.ingsw.gui.components.Building;
import it.polimi.ingsw.gui.components.PrivObje;
import it.polimi.ingsw.gui.components.PubbObj;
import it.polimi.ingsw.gui.components.Tools;
import it.polimi.ingsw.gui.components.mainboard.*;
import it.polimi.ingsw.gui.components.panels.DieG;
import it.polimi.ingsw.gui.components.panels.Tassel;
import it.polimi.ingsw.gui.components.variousSchemes.Windows;
import it.polimi.ingsw.utils.Constants;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainBoard extends GridPane{

    private Label mex = new Label();
    private Label timer = new Label();

    private Building necessary = new Building();


    private VBox two = new VBox();
    private BorderPane lower = new BorderPane();
    private VBox half = new VBox();
    private VBox personal = new VBox();

    private TokenSpace tspace = new TokenSpace();
    private Windows scheme;
    private ChooseBox popup = new ChooseBox();
    private MenuBox menuBox;


    private HBox cards = new HBox();
    private HBox cardsp = new PubbSel();


    private ToolSel cardst = new ToolSel();


    private PrivObje yours;
    private ArrayList<PubbObj> pubb = new ArrayList<>();
    private ArrayList<Tools> toolz = new ArrayList<>();


    private ArrayList<Adversary> adv = new ArrayList<>();
    private ArrayList<String> players = new ArrayList<>();
    private HBox adversus = new HBox();

    private RoundtrackG roundtrack = new RoundtrackG();
    private DraftG draftp = new DraftG();

    private BorderPane mainone = new BorderPane();


    public MainBoard(){
        super();


        Label segnfav = new Label("SEGNALINI FAVORE");
        Label vetr = new Label("CARTA VETRATA SCELTA");
        half.getChildren().add(draftp);

        half.getChildren().add(adversus);
        personal.getChildren().addAll(segnfav, tspace, vetr);
        lower.setCenter(half);
        lower.setRight(personal);
        cards.getChildren().addAll(cardsp, cardst);
        cards.setSpacing(50);
        two.setSpacing(10);
        two.getChildren().add(cards);
        two.getChildren().add(lower);

        mainone.setCenter(two);
        mainone.setBottom(mex);
        mainone.setTop(roundtrack);
        mainone.setPrefSize(1200,900);

        getChildren().add(mainone);
        setAlignment(Pos.CENTER);

    }




    public void setting(String oldie, String newie, IntegerProperty hey){
        if(oldie.equals("Public")){
            Platform.runLater(() -> {
                JSONArray publi = new JSONArray(newie);
                int i = 0;
                while (i < publi.length()) {
                    for (PubbObj p : necessary.buildingPubb(necessary.getPubpath())) {
                        if (p.getName().equals(publi.get(i))) {
                            pubb.add(p);
                            cardsp.getChildren().add(p);
                        }
                    }
                    i++;
            }
        });}else if(oldie.equals("Tool")){
            Platform.runLater(() -> {
                JSONArray too = new JSONArray(newie);
                int i = 0;
                while(i < too.length()) {
                    for (Tools p : necessary.buildingTools(necessary.getToolpath())) {
                        if (p.getName().equals(too.get(i))) {
                            p.setValue(i);
                            toolz.add(p);
                            cardst.add(p,i,0);
                        }
                    }
                    i++;
                }
            });
        }else if(oldie.equals("Privato")){
            Platform.runLater(() -> {
                for(PrivObje p: necessary.buildingPriv(necessary.getPrivpath())){
                    if(p.getName().equals(newie)){
                        yours = p;
                        cards.getChildren().add(yours);
                    }
                }
            });
        }else if(oldie.equals("Scheme")) {
            Platform.runLater(() -> {
                popup.getAmong().add(updatingScheme(new JSONObject(newie)));
                });
        }else if(newie.equals("Scheme done")){
            Platform.runLater(() -> hey.setValue(popup.gimmeInt("Vetrate estratte", "Scegli una carta vetrata tra quelle estratte")));
        }else if(newie.equals("Timer scelta stop")){
            Platform.runLater(() -> {
                scheme = popup.getAmong().get(hey.getValue()-1);
                personal.getChildren().add(new Label(scheme.getName()));
                personal.getChildren().add(scheme);
                //tspace = new TokenSpace();
                tspace.setTok(scheme.getDifficulty());
            });
        }else if(oldie.equals("Adv")){
            Platform.runLater(() -> {
                Adversary provv = updatingAdversary(new JSONObject(newie));
                adv.add(provv);
                adversus.getChildren().add(adv.get(adv.size() - 1));
            });
        }
    }




    public void duringTurn(String oldie, String newie, IntegerProperty hey) {
        if (oldie.equals("DRAFT")) {
            Platform.runLater(() -> {
                    JSONArray dr = new JSONArray(newie);
                    int i;
                    for(i = 0; i < dr.length(); i++){
                        JSONObject obj = dr.getJSONObject(i);
                        DieG provv = new DieG(necessary.faceComparing(String.valueOf(obj.get("Face"))));
                        provv.setColour(necessary.colorComparing(obj.getString("Color")));
                        provv.setPos(draftp.getDraftie().size() + 1);
                        draftp.getDraftie().add(provv);
                    }
                    for (DieG d: draftp.getDraftie())
                        draftp.add(d, draftp.getDraftie().indexOf(d), 1);
                    hey.setValue(-1);
        });
        }else if (oldie.equals("DRAFT END")) {
            Platform.runLater(() -> mex.setText(newie));
        } else if (newie.equals(Constants.MENU_W)) {
            Platform.runLater(()->{
                menuBox=new MenuBox();
                hey.setValue(menuBox.menuC("Menù") + Constants.MENU);
            });
        } else if (newie.equals(Constants.MENU_D)) {
            Platform.runLater(() ->{
                menuBox=new MenuBox();
                hey.setValue(menuBox.menuD("Menù") + Constants.MENU);
            });
        } else if (newie.equals(Constants.MENU_T)) {
            Platform.runLater(() -> {
                menuBox=new MenuBox();
                hey.setValue(menuBox.menuT("Menù") + Constants.MENU);
            });
        } else if (oldie.equals(Constants.SHIFT)) {
            Platform.runLater(() -> {
                mex.setText(newie);
                hey.setValue(-1);
            });
        } else if(newie.equals(Constants.PASS)){
            Platform.runLater(() -> hey.setValue(1 + Constants.MENU));

        } else if (newie.equals(Constants.CLEAN_DRAFT)) {
            Platform.runLater(() -> {
                draftp.getChildren().clear();
                draftp.getDraftie().removeAll(draftp.getDraftie());
                hey.setValue(-1);
            });
        } else if (oldie.equals(Constants.ADV_RELOAD)) {
            Platform.runLater(() -> {
                JSONObject avv = new JSONObject(newie);
                for(Adversary a: adv){
                    if(a.getName().equals(avv.getString("player"))){
                        a.getChildren().remove(a.getGlasswindow());
                        a.setGlasswindow(updatingAdversary(avv).getGlasswindow());
                        a.getChildren().add(a.getGlasswindow());
                    }
                }
                hey.setValue(-1);
            });
        }else if(oldie.equals("ERROR")){
            Platform.runLater(() -> {
                mex.setText(newie);
                hey.setValue(-1);
            });
        }else if (oldie.equals("ROUNDTRACK")){
            Platform.runLater(() -> {
                JSONObject obj = new JSONObject(newie);
                int i = obj.getInt("round");
                GridPane provv = diceUnpackR(obj.getJSONArray("roundtrack"));
                roundtrack.add(provv, i, 1);
                hey.setValue(-1);
            });
        }

    }

    public void placeThatDie(String oldie, String newie, IntegerProperty hey){
        if (newie.equals(Constants.CHOOSE_DIE)) {
            Platform.runLater(() -> {
                for (DieG d : draftp.getDraftie()) {
                    d.getButton().setOnMouseClicked(e -> {
                        hey.setValue(d.getPos() + Constants.F_DIE);
                    });
                    d.getChildren().add(d.getButton());
                }
                mex.setText(newie);
            });
        } else if (newie.equals(Constants.ON_DIE_CLICKED)) {
            Platform.runLater(() -> {
                for (DieG d : draftp.getDraftie()) {
                    d.getChildren().remove(d.getButton());
                }
                hey.setValue(-1);
            });
        } else if (newie.equals(Constants.CHOOSE_TAS)) {
            Platform.runLater(() -> {
                for (Tassel t : scheme.getList()) {
                    t.getButton().setOnMouseClicked(e -> hey.setValue(t.getValue() + Constants.F_SLOT));
                    t.getChildren().add(t.getButton());
                }
                mex.setText(newie);
            });

        } else if (oldie.equals(Constants.SCHEME_RELOAD)) {
            Platform.runLater(() -> {
                personal.getChildren().remove(scheme);
                scheme = updatingScheme(new JSONObject(newie));
                personal.getChildren().add(scheme);
                hey.setValue(-1);
            });
        } else if (oldie.equals("ERROR D")) {
            Platform.runLater(() -> {
                mex.setText(newie);
                hey.setValue(-1);
            });
        }
    }


    public void tooling(String oldie, String newie, IntegerProperty key){
        if(newie.equals(Constants.CHOOSE_TOOL)){
            Platform.runLater(() -> {
                int i = 0;
                while(i < 3){
                    Button provv = new Button("Scegli");
                    int cho = i + Constants.CLICK_TOOL;
                    provv.setOnMouseClicked(event -> key.setValue(cho));
                    cardst.getChoosing().add(provv);
                    cardst.add(cardst.getChoosing().get(i), i, 1);
                    i++;
                }
                mex.setText(newie);
            });
        }else if(newie.equals(Constants.ON_TOOL_CLICKED)){
            Platform.runLater(() -> {
                int i = cardst.getChoosing().size() - 1;
                while(i >= 0){
                    cardst.getChildren().remove(cardst.getChoosing().get(i));
                    cardst.getChoosing().remove(i);
                    i--;
                }
            });
        }else if(newie.equals(Constants.PLUS_MINUS)){
            Platform.runLater(() -> key.setValue(menuBox.plusminus("Pinza Sgrossatrice", Constants.PLUS_MINUS)));
        }else if(oldie.equals(Constants.ENTER_VALUE)){
            Platform.runLater(() -> {
                    JSONObject object = new JSONObject(newie);
                    DieG d = new DieG(necessary.faceComparing(object.getString("face")));
                    d.setColour(necessary.colorComparing(object.getString("color")));
                    key.setValue(menuBox.enterValue("Diluente per Pasta Salda", Constants.ENTER_VALUE, d));
            });
        }else if(oldie.equals("ERROR")){
            Platform.runLater(() -> {
                mex.setText(newie);
                key.setValue(-1);
            });
        }else if(newie.equals(Constants.HOW_MANY)){
            Platform.runLater(() -> key.setValue(menuBox.howMany("Taglierina Manuale", Constants.HOW_MANY)));
        }else if(newie.equals(Constants.CLICK_ON_TRACK)){
            Platform.runLater(() -> {

            });
        }else if(oldie.equals(Constants.TOOL_RIGHT_USE)){
            Platform.runLater(()->{
                int i=Integer.parseInt(newie);
                cardst.getSignal().get(i).setText("Segnalini necessari: 2");
            });
        }





        else if(newie.equals(Constants.CHOOSE_FROM_SCHEME)){
            Platform.runLater(() -> {
                for (Tassel t : scheme.getList()) {
                    t.getButton().setOnMouseClicked(e -> key.setValue(t.getValue() + Constants.F_SLOT));
                    t.getChildren().add(t.getButton());
                }
                mex.setText(newie);
            });
        }else if(newie.equals(Constants.WHERE_ON_SCHEME)){
            Platform.runLater(() -> {
                for (Tassel t : scheme.getList()) {
                    t.getButton().setOnMouseClicked(e -> key.setValue(t.getValue() + Constants.S_SLOT));
                    t.getChildren().add(t.getButton());
                }
                mex.setText(newie);
            });
        }else if(newie.equals(Constants.CHOOSE_FROM_SCHEME_2)){
            Platform.runLater(() -> {
                for (Tassel t : scheme.getList()) {
                    t.getButton().setOnMouseClicked(e -> key.setValue(t.getValue() + Constants.T_SLOT));
                    t.getChildren().add(t.getButton());
                }
                mex.setText(newie);
            });
        }else if(newie.equals(Constants.WHERE_ON_SCHEME_2)) {
            Platform.runLater(() -> {
                for (Tassel t : scheme.getList()) {
                    t.getButton().setOnMouseClicked(e -> key.setValue(t.getValue() + Constants.FO_SLOT));
                    t.getChildren().add(t.getButton());
                }
                mex.setText(newie);
            });
        } else if(newie.equals(Constants.CLICK_ON_TRACK)){
            Platform.runLater(() -> {
                for(DieG d: roundtrack.getList()){
                    d.getButton().setOnMouseClicked(e -> key.setValue(roundtrack.getList().indexOf(d) + Constants.ROUNDTRACK));
                    d.getChildren().add(d.getButton());
                }
                mex.setText(newie);
            });
        }else if(newie.equals(Constants.ON_TRACK_CLICKED)){
            Platform.runLater(() -> {
                for(DieG d: roundtrack.getList()){
                    d.getChildren().remove(d.getButton());
                }
            });
        } else if(oldie.equals(Constants.PAY_UP)){
            Platform.runLater(() -> {
                if((tspace.getMoney().size() - Integer.parseInt(newie)) == 1){
                    tspace.getChildren().remove(0);
                    tspace.getMoney().remove(0);
                }else if((tspace.getMoney().size() - Integer.parseInt(newie)) == 2){
                    tspace.getChildren().remove(0);
                    tspace.getChildren().remove(0);
                    tspace.getMoney().remove(0);
                    tspace.getMoney().remove(0);
                }else{
                    while(tspace.getMoney().size()!=0){
                        tspace.getChildren().remove(0);
                        tspace.getMoney().remove(0);
                    }
                }
            });
        }
    }












    public Windows updatingScheme(JSONObject obj){
        Windows gone = new Windows();
        gone.setName(obj.getString("name"));
        gone.setDifficulty(obj.getInt("diff"));
        JSONArray tass = obj.getJSONArray("glass");

        for(int i = 0; i < Constants.F_SLOT; i++){
            JSONObject provv = tass.getJSONObject(i);
            gone.getList().get(i).getChildren().add(necessary.faceComparing(provv.getString("face")));
            gone.getList().get(i).setStyle("-fx-background-color: " + necessary.colorComparing(provv.getString("color")));
        }
        return gone;
    }

    public Adversary updatingAdversary(JSONObject obj){

        Adversary def = new Adversary(obj.getString("player"));
        JSONObject window = obj.getJSONObject("glasswindow");
        def.setGlasswindow(updatingScheme(window));
        def.getChildren().add(def.getGlasswindow());
        return def;
    }

    public GridPane diceUnpackR(JSONArray obj){
        GridPane neuv = new GridPane();
        int i = 0;
        int c = 0;
        int r = 0;
        while(i < obj.length()){
            JSONObject die = obj.getJSONObject(i);
            DieG provv = new DieG(necessary.faceComparing(die.getString("Face")));
            provv.setColour(necessary.colorComparing(die.getString("Color")));
            provv.setPos(roundtrack.getList().size());
            roundtrack.getList().add(provv);
            neuv.add(roundtrack.getList().get(roundtrack.getList().size()-1),c,r);
            if (r==2){
                r=0;
                c++;
            }else r++;
            i++;
        }

        return neuv;
    }

    public VBox getTwo() {
        return two;
    }

    public void setTwo(VBox two) {
        this.two = two;
    }

    public Windows getScheme() {
        return scheme;
    }

    public void setScheme(Windows scheme) {
        this.scheme = scheme;
    }

    public HBox getCards() {
        return cards;
    }

    public void setCards(HBox cards) {
        this.cards = cards;
    }

}
