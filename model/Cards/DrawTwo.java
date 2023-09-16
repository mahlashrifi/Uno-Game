package uno.model.Cards;

import uno.model.Board;
import uno.model.Cards.ActionCard;

public class DrawTwo extends ActionCard {

    /**
     * A constructor for DrawTwo class
     * @param color color of the card
     */
    public DrawTwo(int color) {
        super(color);
    }

    @Override
    public boolean doesCardMatchWithCurrentCard() {
        Card currentCard = Board.getInstance().getCurrentCard();
        if (currentCard instanceof DrawTwo)
            return true ;
        if(Board.getInstance().getCurrentColor() == this.color )
            return true ;
        return false;
    }

    @Override
    public void doCardProcess() {
        Board.getInstance().setTurn();
        Board.getInstance().setFine(Board.getInstance().getFine()+2);
        Board.getInstance().setCurrentColor(color);

    }
}
