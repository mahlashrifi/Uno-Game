package uno.model.Cards;

import uno.model.Board;

public abstract class WildCard extends Card {
    /**
     * A constructor for WildCard class
     */
    public WildCard() {
        color = 0 ;
    }
    @Override
    public int getColor() {
        return 0;
    }

}
