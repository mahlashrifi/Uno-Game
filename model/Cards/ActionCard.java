package uno.model.Cards;

import uno.model.Board;

public abstract class ActionCard extends ColorCard {
    /**
     * A constructor for ActionCard class
     * @param color color of the card
     */
    public ActionCard(int color) {
        super(color);
    }

    public boolean DoesCardMatchWithCurrentCard(){
        return false ;
    }
}
