package it.polimi.ingsw.cards;

import it.polimi.ingsw.cards.toolCard.*;

public class ToolCards {

    public Tool creatingTool(int value) {

        Tool tool = null;
        switch (value) {
            case 1: {
                tool = new ToolCard1();
                break;
            }
            case 2: {
                tool = new ToolCard2();
                break;
            }
            case 3: {
                tool = new ToolCard3();
                break;
            }
            case 4: {
                tool = new ToolCard4();
                break;
            }
            case 5: {
                tool = new ToolCard5();
                break;
            }
            case 6: {
                tool = new ToolCard6();
                break;
            }
            case 7: {
                tool = new ToolCard7();
                break;
            }
            case 8: {
                tool = new ToolCard8();
                break;
            }
            case 9: {
                tool = new ToolCard9();
                break;
            }
            case 10: {
                tool = new ToolCard10();
                break;
            }
            case 11: {
                tool = new ToolCard11();
                break;
            }
            case 12: {
                tool = new ToolCard12();
                break;
            }
        }
        return tool;
    }

}
