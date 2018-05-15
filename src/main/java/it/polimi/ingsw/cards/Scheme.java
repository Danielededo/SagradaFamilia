package it.polimi.ingsw.cards;

import it.polimi.ingsw.cards.schemeCard.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Scheme extends Card {
    private final int dimension = 24;
    private GlassWindow[] glasswindow= new GlassWindow[dimension];

    public Scheme(){
        GlassWindow a = new Virtus();
        glasswindow[0]=a;
        GlassWindow b= new SymphonyofLight();
        glasswindow[1]=b;
        GlassWindow c = new ViaLux();
        glasswindow[2]=c;
        GlassWindow d = new Industria();
        glasswindow[3]=d;
        GlassWindow e= new ShadowThief();
        glasswindow[4]=e;
        GlassWindow f = new SunCatcher();
        glasswindow[5]=f;
        GlassWindow g = new Battlo();
        glasswindow[6]=g;
        GlassWindow h= new Bellesguard();
        glasswindow[7]=h;
        GlassWindow i = new KaleidoscopicDream();
        glasswindow[8]=i;
        GlassWindow j = new Firmitas();
        glasswindow[9]=j;
        GlassWindow k= new AuroraeMagnificus();
        glasswindow[10]=k;
        GlassWindow l = new AuroraSagradis();
        glasswindow[11]=l;
        GlassWindow m = new Gravitas();
        glasswindow[12]=m;
        GlassWindow n= new WaterofLife();
        glasswindow[13]=n;
        GlassWindow o = new LuxMundi();
        glasswindow[14]=o;
        GlassWindow p = new LuxAstram();
        glasswindow[15]=p;
        GlassWindow q= new SunGlory();
        glasswindow[16]=q;
        GlassWindow r = new Firelight();
        glasswindow[17]=r;
        GlassWindow s = new FulgordelCielo();
        glasswindow[18]=s;
        GlassWindow t= new LuzCelestial();
        glasswindow[19]=t;
        GlassWindow u = new ChromaticSplendor();
        glasswindow[20]=u;
        GlassWindow v = new Comitas();
        glasswindow[21]=v;
        GlassWindow w= new FractalDrops();
        glasswindow[22]=w;
        GlassWindow x = new RipplesofLight();
        glasswindow[23]=x;
    }


    public ArrayList<GlassWindow> extractGlass(){
        GlassWindow temp1,temp2;
        ArrayList<GlassWindow> glasses = new ArrayList<GlassWindow>();
        int i=0;
        while(i<2) {
            Random random = new Random();
            int k = random.nextInt(glasswindow.length);
            if(glasswindow[k]!=null) {
                temp1 = glasswindow[k];
                glasswindow[k]=null;
                glasses.add(temp1);
                if (k % 2 == 0) {
                    temp2 = glasswindow[k + 1];
                    glasswindow[k+1]=null;
                    glasses.add(temp2);
                } else {
                    temp2 = glasswindow[k - 1];
                    glasswindow[k-1]=null;
                    glasses.add(temp2);
                }
                i++;
            }
        }
        return glasses;
    }

    @Override
    public String toString() {
        return "Scheme{" +
                "dimension=" + dimension +
                ", glasswindow=" + Arrays.toString(glasswindow) +
                '}';
    }
}
