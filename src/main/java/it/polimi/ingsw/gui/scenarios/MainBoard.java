package it.polimi.ingsw.gui.scenarios;

import it.polimi.ingsw.gui.components.Building;
import it.polimi.ingsw.gui.components.PrivObje;
import it.polimi.ingsw.gui.components.PubbObj;
import it.polimi.ingsw.gui.components.Tools;
import it.polimi.ingsw.gui.components.mainboard.*;
import it.polimi.ingsw.gui.components.panels.DieG;
import it.polimi.ingsw.gui.components.panels.Tassel;
import it.polimi.ingsw.gui.components.variousSchemes.Windows;
import it.polimi.ingsw.server.utils.Constants;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainBoard extends BorderPane{

    private Adversary temp;

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
    private MenuBox menuBox = new MenuBox();


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

        setCenter(two);
        setBottom(mex);
        setTop(roundtrack);
        autosize();
        setPrefSize(1200,900);
    }




    public void setting(String oldie, String newie, IntegerProperty hey){
        if(oldie.equals("Public")){
            Platform.runLater(() -> {
                JSONArray publi = new JSONArray(newie);
                int i = 0;
                while (i < 3) {

                    for (PubbObj p : necessary.buildingPubb(necessary.getPubpath())) {
                        if (p.getName().equals(publi.get(i))) {
                            pubb.add(p);
                            cardsp.getChildren().add(p);
                        }
                    }
                    i++;
                }
            });
        }else if(oldie.equals("Tool")){
            Platform.runLater(() -> {
                JSONArray too = new JSONArray(newie);
                int i = 0;
                while(i < 3) {
                    for (Tools p : necessary.buildingTools(necessary.getToolpath())) {
                        if (p.getName().equals(too.get(i))) {
                            p.setValue(i);
                            toolz.add(p);
                            cardst.getChildren().add(p);
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
            Platform.runLater(() -> popup.getAmong().add(updatingScheme(new JSONObject(newie))));

        }else if(newie.equals("Scheme done")){
            Platform.runLater(() -> hey.setValue(popup.gimmeInt("Vetrate estratte", "Scegli una carta vetrata tra quelle estratte")));
        }else if(newie.equals("Timer scelta stop")){
            Platform.runLater(() -> {
                scheme = popup.getAmong().get(hey.getValue()-1);
                personal.getChildren().add(new Label(scheme.getName()));
                personal.getChildren().add(scheme);
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
            JSONArray dr = new JSONArray(newie);
            int i;
            for(i = 0; i < dr.length(); i++){
                JSONObject obj = dr.getJSONObject(i);
                DieG provv = new DieG(necessary.faceComparing(String.valueOf(obj.get("Face"))));
                provv.setColour(necessary.colorComparing(obj.getString("Color")));
                provv.setPos(draftp.getDraftie().size() + 1);
                draftp.getDraftie().add(provv);
            }
            Platform.runLater(() -> {
                for (DieG d: draftp.getDraftie())
                    draftp.add(d, draftp.getDraftie().indexOf(d), 1);
        });
        }else if (oldie.equals("Draft end")) {
            Platform.runLater(() -> mex.setText(newie));
        } else if (newie.equals("MENU whole")) {
            Platform.runLater(() -> hey.setValue(menuBox.menuC("Menù") + Constants.MENU));
        } else if (newie.equals("MENU die")) {
            Platform.runLater(() -> hey.setValue(menuBox.menuD("Menù") + Constants.MENU));
        } else if (newie.equals("MENU tool")) {
            Platform.runLater(() -> hey.setValue(menuBox.menuT("Menù") + Constants.MENU));
        } else if (oldie.equals("Shift")) {
            Platform.runLater(() -> {
                mex.setText(newie);
                hey.setValue(-1);
            });
        } else if (oldie.equals("Remove die")) {
            Platform.runLater(() -> {
                draftp.getChildren().clear();
                draftp.getDraftie().removeAll(draftp.getDraftie());
            });
        } else if (oldie.equals("Adv place")) {
            Platform.runLater(() -> {
                JSONObject avv = new JSONObject(newie);
                for(Adversary a: adv){
                    if(a.getName().equals(avv.getString("player"))){
                        a.getChildren().remove(a.getGlasswindow());
                        a.setGlasswindow(updatingAdversary(avv).getGlasswindow());
                        a.getChildren().add(a.getGlasswindow());
                    }
                }
            });
        } else if (oldie.equals("ROUNDTRACK")){

        }

    }

    public void placeThatDie(String oldie, String newie, IntegerProperty hey){
        if (newie.equals("Scegli un dado.")) {
            Platform.runLater(() -> {
                for (DieG d : draftp.getDraftie()) {
                    d.getButton().setOnMouseClicked(e -> {
                        hey.setValue(d.getPos() + Constants.F_DIE);
                    });
                    d.getChildren().add(d.getButton());
                }
                mex.setText(newie);
            });
        } else if (newie.equals("DIE OK")) {
            Platform.runLater(() -> {
                for (DieG d : draftp.getDraftie()) {
                    d.getChildren().remove(d.getButton());
                }
            });
        } else if (newie.equals("Ora scegli una casella della tua vetrata.")) {
            Platform.runLater(() -> {
                for (Tassel t : scheme.getList()) {
                    t.getButton().setOnMouseClicked(e -> {
                        hey.setValue(t.getValue() + Constants.F_SLOT);
                    });
                    t.getChildren().add(t.getButton());
                }
                mex.setText(newie);
            });

        } else if (oldie.equals("Dado piazzato correttamente")) {
            Platform.runLater(() -> {
                personal.getChildren().remove(scheme);
                scheme = updatingScheme(new JSONObject(newie));
                personal.getChildren().add(scheme);
            });
        } else if (oldie.equals("ERROR D")) {
            Platform.runLater(() -> {
                mex.setText(newie);
                hey.setValue(-1);
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


    public VBox getTwo() {
        return two;
    }

    public void setTwo(VBox two) {
        this.two = two;
    }

    public BorderPane getLower() {
        return lower;
    }

    public void setLower(BorderPane lower) {
        this.lower = lower;
    }

    public VBox getHalf() {
        return half;
    }

    public void setHalf(VBox half) {
        this.half = half;
    }

    public TokenSpace getTspace() {
        return tspace;
    }

    public void setTspace(TokenSpace tspace) {
        this.tspace = tspace;
    }

    public ChooseBox getPopup() {
        return popup;
    }

    public void setPopup(ChooseBox popup) {
        this.popup = popup;
    }

    public MenuBox getMenuBox() {
        return menuBox;
    }

    public void setMenuBox(MenuBox menuBox) {
        this.menuBox = menuBox;
    }

    public HBox getCardsp() {
        return cardsp;
    }

    public void setCardsp(HBox cardsp) {
        this.cardsp = cardsp;
    }

    public ToolSel getCardst() {
        return cardst;
    }

    public void setCardst(ToolSel cardst) {
        this.cardst = cardst;
    }

    public PrivObje getYours() {
        return yours;
    }

    public void setYours(PrivObje yours) {
        this.yours = yours;
    }

    public ArrayList<PubbObj> getPubb() {
        return pubb;
    }

    public void setPubb(ArrayList<PubbObj> pubb) {
        this.pubb = pubb;
    }

    public ArrayList<Tools> getToolz() {
        return toolz;
    }

    public void setToolz(ArrayList<Tools> toolz) {
        this.toolz = toolz;
    }

    public ArrayList<Adversary> getAdv() {
        return adv;
    }

    public void setAdv(ArrayList<Adversary> adv) {
        this.adv = adv;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public DraftG getDraftp() {
        return draftp;
    }

    public void setDraftp(DraftG draftp) {
        this.draftp = draftp;
    }

    public Label getMex() {
        return mex;
    }

    public void setMex(Label mex) {
        this.mex = mex;
    }

    public Label getTimer() {
        return timer;
    }

    public void setTimer(Label timer) {
        this.timer = timer;
    }


    public VBox getPersonal() {
        return personal;
    }

    public void setPersonal(VBox personal) {
        this.personal = personal;
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

    public HBox getAdversus() {
        return adversus;
    }

    public void setAdversus(HBox adversus) {
        this.adversus = adversus;
    }

    public GridPane getRoundtrack() {
        return roundtrack;
    }

    public void setRoundtrack(RoundtrackG roundtrack) {
        this.roundtrack = roundtrack;
    }
}
