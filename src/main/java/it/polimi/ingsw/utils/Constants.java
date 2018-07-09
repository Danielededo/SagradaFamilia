package it.polimi.ingsw.utils;

import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    //Configuration default parameter
    public static final ArrayList<String> config=new ArrayList<String>(Arrays.asList("8080","60","30","120"));

    public static final double resize=0.6;

    //GlassWindow Bounds
    public static final int W_ROWS = 4;
    public static final int W_COLOMN = 5;


    //GUI main path
    public static final String IMG_PRINC = "/images";
    public static final String IMG_PRIV = "/cartePrivate";
    public static final String IMG_PUBB = "/obiettivoPubblico";
    public static final String IMG_SCHE = "/schemeImages";
    public static final String IMG_TOOL = "/toolsImages";


    //GUI html codes colours
    public static final String RED = "#cc0000";
    public static final String BLUE = "#4d94ff";
    public static final String GREEN = "#00994d";
    public static final String PURPLE = "#a64dff";
    public static final String YELLOW = "#ffc34d";
    public static final String WHITE = "#ffffff";
    public static final String BLACK = "#000000";


    //GUI panels
    public static final double SLOT = 75  *resize;
    public static final double H_SLOT = 75    *resize;
    public static final double R_DOT = 16.2   *resize;
    public static final double W_TOKEN = 60   *resize;
    public static final double H_TOKEN = 60   *resize;

    public static final double C_BOX_X = 29.25*resize;
    public static final double C_BOX_Y = 29.25*resize;

    public static final double NE2_BOX_X = 13.5*resize;
    public static final double NE2_BOX_Y = 13.5*resize;
    public static final double SO2_BOX_X = 45 *resize;
    public static final double SO2_BOX_Y = 45 *resize;

    public static final double NE3_BOX_X = 8.25*resize;
    public static final double NE3_BOX_Y = 8.25*resize;
    public static final double SO3_BOX_X = 49.5*resize;
    public static final double SO3_BOX_Y = 49.5*resize;

    public static final double NO5_BOX_X = 49.5*resize;
    public static final double NO5_BOX_Y = 8.25*resize;
    public static final double SE5_BOX_X = 8.25*resize;
    public static final double SE5_BOX_Y = 49.5*resize;

    public static final double NO_BOX_X = 45 *resize;
    public static final double NO_BOX_Y = 13.5*resize;
    public static final double SE_BOX_X = 13.5*resize;
    public static final double SE_BOX_Y = 45 *resize;

    public static final double BOX6_X1 = 12.75*resize;
    public static final double BOX6_X2 = 44.25*resize;

    public static final double BOX6_Y1 = 6*resize;
    public static final double BOX6_Y2 = 29.25*resize;
    public static final double BOX6_Y3 = 52.5 *resize;

    //GUI Window Case
    public static final double WI_CASE = 418  *resize;
    public static final double HE_CASE = 390  *resize;
    public static final double PADD_CASE = 10 *resize;
    public static final Insets INS_WIND = new Insets(100,28,45,30);
    public static final Insets INS_LAB = new Insets(35,30,350,30);


    //GUI Label Titles
    public static final String TITLE_SIZE = "20";
    public static final String REG_SIZE_WR = "25";

    //GUI messages
    public static final int F_DIE = 10;
    public static final int F_SLOT = 20;
    public static final int S_SLOT = 40;
    public static final int T_SLOT = 60;
    public static final int FO_SLOT = 80;
    public static final int MENU = 100;
    public static final int CLICK_TOOL = 200;
    public static final int ROUNDTRACK = 300;



    //String messages
    public static final String CHOOSE_DIE = "Scegli un dado dalla riserva.";
    public static final String CHOOSE_TAS = "Adesso scegli una casella dalla tua vetrata.";
    public static final String CLEAN_DRAFT = "Remove dice";
    public static final String SHIFT = "Shift";
    public static final String DISCONNECTED = " salta il turno perchè è disconnesso";
    public static final String WINNER ="Hai vinto dato che non ci sono altri giocatori connessi";
    public static final String PASS = "Pass";
    public static final String MENU_W = "Menù complete";
    public static final String MENU_T = "Menù tool";
    public static final String MENU_D = "Menù die";
    public static final String ADV_RELOAD = "Upgrade other players";
    public static final String SCHEME_RELOAD = "Upgrade personal scheme";
    public static final String CHOOSE_TOOL = "Scegli una carta utensile";
    public static final String TOOL_RIGHT_USE = "Operazione completata";
    public static final String CHOOSE_FROM_SCHEME = "Scegli la casella dalla quale prendere il dado.";
    public static final String CHOOSE_FROM_SCHEME_2 = "Scegli la casella dalla quale prendere il secondo dado.";
    public static final String ON_SLOT_CLICKED = "SLOT OKAY";
    public static final String ON_DIE_CLICKED = "DIE OKAY";
    public static final String ON_TOOL_CLICKED = "TOOL OKAY";
    public static final String WHERE_ON_SCHEME = "Scegli la casella dove posizionare il dado";
    public static final String WHERE_ON_SCHEME_2 = "Scegli la casella dove posizionare il dado";
    public static final String PLUS_MINUS = "Scegli se decrementare o diminuire il valore";
    public static final String ENTER_VALUE = " Scegli quale valore dare al dado";
    public static final String HOW_MANY = "Vuoi scegliere 1 o 2 dadi?";
    public static final String CLICK_ON_TRACK = "Ora scegli un dado che si trova sul tracciato del round";
    public static final String ON_TRACK_CLICKED = "Rimuovi i dadi dal tracciato del round";
    public static final String END_GAME = "LA PARTITA E' TERMINATA";
    public static final String PAY_UP = "Segnalini favore necessari";
    public static final String RECONNECTED = "Sei stato riconnesso";
    public static final String TOKEN = "Ricarica token";
    public static final String SCHEME_NAME="Nome carta schema";
    public static final String PLAY_AGAIN="E' stato riconnesso";

}
