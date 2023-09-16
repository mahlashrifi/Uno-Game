package uno.model.Cards;

import uno.model.Board;
import uno.model.Cards.ActionCard;

public class Reverse extends ActionCard {

    /**
     * A constructor for Reverse class
     * @param color color of the card
     */
    public Reverse(int color) {
        super(color);
    }

    @Override
    public boolean doesCardMatchWithCurrentCard() {
        Card currentCard = Board.getInstance().getCurrentCard();
        if (currentCard instanceof Reverse)
            return true ;
        if(Board.getInstance().getCurrentColor() == this.color)
            return true ;
        return false ;
    }

    @Override
    public void doCardProcess() {
        Board.getInstance().setDirection(-1*Board.getInstance().getDirection());
        Board.getInstance().setTurn();
        Board.getInstance().setCurrentColor(color);

    }
}
