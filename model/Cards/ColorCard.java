package uno.model.Cards;

import java.util.ArrayList;
import java.util.Objects;

public abstract class ColorCard extends Card{
    protected int color ;
    /**
     * A constructor foe ColorCard class
     * @param color color of the card
     */
    public ColorCard(int color) {
        this.color = color;
    }
    public int getColor(){
        return color ;
    }

}
