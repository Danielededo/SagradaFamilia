package it.polimi.ingsw.game.rules_Tests;


import org.junit.jupiter.api.Test;


public class Rules_Tests {

    @Test
    public void runRules(){
    RulesTestaCentrali cen = new RulesTestaCentrali();
    cen.testaRulesCentrali();

    RulesTestaRegoleDefinitive def = new RulesTestaRegoleDefinitive();
    def.TestRulesGenerale();}

}





