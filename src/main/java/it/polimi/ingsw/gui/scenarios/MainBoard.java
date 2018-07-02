package it.polimi.ingsw.gui.scenarios;

import it.polimi.ingsw.gui.components.Building;
import it.polimi.ingsw.gui.components.PrivObje;
import it.polimi.ingsw.gui.components.PubbObj;
import it.polimi.ingsw.gui.components.Tools;
import it.polimi.ingsw.gui.components.mainboard.*;
import it.polimi.ingsw.gui.components.panels.DieG;
import it.polimi.ingsw.gui.components.panels.Tassel;
import it.polimi.ingsw.gui.components.variousSchemes.WindCase;
import it.polimi.ingsw.gui.components.variousSchemes.Windows;
import it.polimi.ingsw.server.utils.Constants;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private WindCase scheme = new WindCase(Constants.WI_CASE, Constants.HE_CASE);
    private ChooseBox popup;
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
    }




    public void setting(String oldie, String newie, IntegerProperty hey){
        if(oldie.equals("Public")){
            Platform.runLater(() -> {
                for (PubbObj p : necessary.buildingPubb(necessary.getPubpath())) {
                    if (p.getName().equals(newie)) {
                        pubb.add(p);
                        cardsp.getChildren().add(p);
                    }
                }

            });
        }else if(oldie.equals("Tool")){
            Platform.runLater(() -> {
                for(Tools p: necessary.buildingTools(necessary.getToolpath())){
                    if(p.getName().equals(newie)){
                        toolz.add(p);
                        cardst.getChildren().add(p);
                    }
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
        }else if(oldie.equals("Scheme")){
            popup = new ChooseBox();
        }else if(oldie.equals("Glasswindow")){
            for(Windows p: necessary.buildingGlasswindow()){
                if(p.getName().equals(newie)){
                    popup.getAmong().add(p);
                }
            }
        }else if(newie.equals("Scheme done")){
            Platform.runLater(() -> hey.setValue(popup.gimmeInt("Vetrate estratte", "Scegli una carta vetrata tra quelle estratte")));
        }else if(newie.equals("Timer scelta stop")){
            Platform.runLater(() -> {
                personal.getChildren().add(scheme.addChosen(popup.getAmong().get(hey.getValue()-1)));
                tspace.setTok(scheme.getScelta().getDifficulty());
            });
        }else if(oldie.equals("Avversario nome")){
            players.add(newie);
        }else if(oldie.equals("Avversario vetrata")){
            Adversary provv = new Adversary(players.get(players.size() - 1));

            for(Windows w: necessary.buildingGlasswindow()){
               if(w.getName().equals(newie)){
                   provv.setGlasswindow(w);
               }
            }
            adv.add(provv);
            Platform.runLater(() -> adversus.getChildren().add(adv.get(adv.size() - 1).getWindowcase().addChosen(adv.get(adv.size() - 1).getGlasswindow())));

        }
    }




    public void duringTurn(String oldie, String newie, IntegerProperty hey) {
        if (oldie.equals("Draft face")) {
            DieG provv = new DieG(necessary.faceComparing(newie));
            provv.setPos(draftp.getDraftie().size() + 1);
            draftp.getDraftie().add(provv);

        } else if (oldie.equals("Draft color")) {
            draftp.getDraftie().get(draftp.getDraftie().size() - 1).setColour(necessary.colorComparing(newie));
            draftp.getDraftie().get(draftp.getDraftie().size() - 1).setPos(draftp.getDraftie().size());

        } else if (oldie.equals("Draft end")) {
            Platform.runLater(() -> {
                mex.setText(newie);
                for (DieG d: draftp.getDraftie()){
                    draftp.add(d, draftp.getDraftie().indexOf(d), 1);
                }
            });
        } else if (newie.equals("MENU whole")) {
            Platform.runLater(() -> hey.setValue(menuBox.menuC("Menù", "Scegli cosa fare in questo turno")));
        } else if (newie.equals("MENU die")) {
            Platform.runLater(() -> hey.setValue(menuBox.menuD("Menù")));
        } else if (newie.equals("MENU tool")) {
            Platform.runLater(() -> hey.setValue(menuBox.menuT("Menù")));
        } else if (oldie.equals("Shift")) {
            Platform.runLater(() -> mex.setText(newie));
        } else if (newie.equals("Scegli un dado.")) {
            Platform.runLater(() -> {
                for (DieG d : draftp.getDraftie()) {
                    //d.getButton.setStyle("-fx-background-color: transparent;");
                    d.getButton().setOnMouseClicked(e -> {
                        draftp.setSelected(d);
                        hey.setValue(draftp.getSelected().getPos() + Constants.F_DIE);
                    });
                }
                mex.setText(newie);
            });
        } else if (newie.equals("DIE OK")) {
            Platform.runLater(() -> {
                for (DieG d : draftp.getDraftie()) {
                    d.setButton(null);
                }
            });
        } else if (newie.equals("Ora scegli una casella della tua vetrata.")) {
            Platform.runLater(() -> {
                for (Tassel t : scheme.getScelta().getList()) {
                    Button button = new Button();
                    //button.setStyle("-fx-background-color: transparent;");
                    button.setOnMouseClicked(e -> {
                        scheme.getScelta().setSelected(t.getValue());
                        hey.setValue(scheme.getScelta().getSelected() + Constants.F_SLOT);
                    });
                    t.setButton(button);
                    t.getChildren().add(t.getButton());
                }
                mex.setText(newie);
            });

        } else if (newie.equals("Dado piazzato correttamente")) {
            Platform.runLater(() -> {
                for (Tassel t : scheme.getScelta().getList()) {
                    t.setButton(null);
                }
                scheme.getScelta().getList().get(scheme.getScelta().getSelected() - 1).setStyle("-fx-background-color: " + draftp.getSelected().getColour());
                scheme.getScelta().getList().get(scheme.getScelta().getSelected() - 1).getChildren().add(draftp.getSelected().getFace());
                draftp.setSelected(null);
            });
        } else if (oldie.equals("ERROR")) {
            Platform.runLater(() -> mex.setText(newie));
        } else if (oldie.equals("Remove die")) {
            Platform.runLater(() -> draftp.getChildren().clear());

        } else if (oldie.equals("Adv place")) {
            Platform.runLater(() -> {
                for(Adversary a: adv){
                    if(a.getName().equals(newie)){
                        temp = a;
                        adversus.getChildren().remove(a);
                    }
                }
                adv.remove(temp);
            });
        }else if(oldie.equals("Placed face")){
            temp.getGlasswindow().setUpdate(new DieG(necessary.faceComparing(newie)));
        }else if(oldie.equals("Placed color")){
            temp.getGlasswindow().getUpdate().setColour(necessary.colorComparing(newie));

        }else if(oldie.equals("Tassel place")){
            Platform.runLater(() -> {
                int i = Integer.valueOf(newie);
                temp.getGlasswindow().getList().get(i - 1).setStyle("fx-background-color: " + temp.getGlasswindow().getUpdate().getColour());
                temp.getGlasswindow().getList().get(i - 1).getChildren().add(temp.getGlasswindow().getUpdate().getFace());
                adversus.getChildren().add(temp);
                adv.add(temp);
            });
        }

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

    public WindCase getScheme() {
        return scheme;
    }

    public void setScheme(WindCase scheme) {
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

    public HBox getRoundtrack() {
        return roundtrack;
    }

    public void setRoundtrack(RoundtrackG roundtrack) {
        this.roundtrack = roundtrack;
    }
}
