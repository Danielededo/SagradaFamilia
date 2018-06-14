package it.polimi.ingsw.server.model.game.match_Tests;

import it.polimi.ingsw.server.model.cards.schemeCard.*;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

public class CalculateRanking_Test {
    @Test
    void TwoPlayerRanking(){
        Player a=new Player("mario");
        Player b=new Player("daniele");
        Match match=new Match(a,b);
        a.setWindow(new AuroraSagradis());
        b.setWindow(new AuroraeMagnificus());
        a.setScore(20);
        b.setScore(20);
        System.out.println(match.ranking());
    }

    @Test
    void ThreePlayerRanking(){
        Player a=new Player("mario");
        Player b=new Player("daniele");
        Player c=new Player("sara");
        Match match=new Match(a,b,c);
        a.setWindow(new AuroraSagradis());
        b.setWindow(new AuroraeMagnificus());
        c.setWindow(new KaleidoscopicDream());
        a.setScore(20);
        b.setScore(20);
        c.setScore(20);
        System.out.println(match.ranking());
    }

    @Test
    void FourPlayerRanking(){
        Player a=new Player("mario");
        Player b=new Player("daniele");
        Player c=new Player("sara");
        Player d=new Player("antonio");
        Match match=new Match(a,b,c,d);
        a.setWindow(new ViaLux());
        b.setWindow(new AuroraSagradis());
        c.setWindow(new KaleidoscopicDream());
        d.setWindow(new ChromaticSplendor());
        a.setScore(20);
        b.setScore(20);
        c.setScore(20);
        d.setScore(20);
        System.out.println(match.ranking());
    }
}
