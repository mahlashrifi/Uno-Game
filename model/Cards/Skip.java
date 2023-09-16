package uno.model.Cards;

import uno.model.Board;
import uno.model.Cards.ActionCard;

public class Skip extends ActionCard {

    /**
     * A constructor for Skip class
     * @param color color of the card
     */
    public Skip(int color) {
        super(color);
    }

    @Override
    public boolean doesCardMatchWithCurrentCard() {
        Card currentCard = Board.getInstance().getCurrentCard();
        if (currentCard instanceof DrawTwo)
            return true ;
        if(Board.getInstance().getCurrentColor() == this.color)
            return true ;
        return false;
    }


    @Override
    public void doCardProcess() {
        Board.getInstance().setTurn();
        Board.getInstance().setTurn();
        Board.getInstance().setCurrentColor(color);

    }
}
