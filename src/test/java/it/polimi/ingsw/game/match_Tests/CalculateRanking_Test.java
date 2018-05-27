package it.polimi.ingsw.game.match_Tests;

import it.polimi.ingsw.cards.schemeCard.*;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
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
        a.setWindow(new AuroraSagradis());
        b.setWindow(new AuroraeMagnificus());
        c.setWindow(new KaleidoscopicDream());
        d.setWindow(new ChromaticSplendor());
        a.setScore(20);
        b.setScore(19);
        c.setScore(20);
        d.setScore(20);
        System.out.println(match.ranking());
    }
}
