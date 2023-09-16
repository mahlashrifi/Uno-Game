package uno.model.Cards;

import uno.model.Board;

public class Wild extends WildCard {

    @Override
    public void doCardProcess() {
        Board.getInstance().setTurn();
        Board.getInstance().setCurrentColor(0);
    }
    public boolean doesCardMatchWithCurrentCard(){
       return true;
    }

}
