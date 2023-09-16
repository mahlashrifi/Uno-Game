package uno.model.Cards;

import uno.model.Board;
import uno.model.Cards.WildCard;

public class WildDraw extends WildCard {

    public void doCardProcess() {
        Board.getInstance().setCurrentColor(0);
        Board.getInstance().setTurn();
        Board.getInstance().setFine(Board.getInstance().getFine()+4);
    }
    public  boolean doesCardMatchWithCurrentCard(){return false;}

}
