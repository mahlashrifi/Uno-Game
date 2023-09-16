package uno.model.Cards;

import uno.model.Board;
import uno.model.Cards.ColorCard;

public  class Numeric extends ColorCard {
    private int number ;

    /**
     * A constructor for Numeric class
     * @param color color of the card
     * @param number number of the card
     */
    public Numeric(int color , int number) {
        super(color);
        this.number = number;
    }

    /**
     * A getter for number filled of Numeric class
     * @return number of the card
     */
    public int getNumber() {
        return number;
    }
    @Override
    public int getColor() {
        return super.getColor();
    }

    @Override
    public boolean doesCardMatchWithCurrentCard() {
       Card currentCard = Board.getInstance().getCurrentCard();
        if(Board.getInstance().getCurrentColor() == this.color)
            return true ;
        if (currentCard instanceof Numeric && ((Numeric) currentCard).getNumber() == number)
            return true ;
        return false ;
    }

    @Override
    public void doCardProcess() {
        Board.getInstance().setTurn();
        Board.getInstance().setCurrentColor(color);
    }
}
