package uno.model;

import uno.conroller.Manager;
import uno.model.Cards.*;

import java.util.Random;

public class Board {
    private static Board board = null;
    private Repository repository ;
    private Card currentCard ;;
    private int direction ;
    private int numberOfPlayers ;
    private int currentColor ;
    private int turn ;
    private int fine ;

    /**
     * Private constructor for Board class which is singleton class
     */
    private Board() {
        repository = new Repository();
        direction = 1 ;
        fine = 0 ;
    }

    /**
     * A method that sets information of primary table
     */
    public void makePrimaryBoard(){
      currentCard = findTheFirstColoredCardOfRepository();
      if(currentCard instanceof DrawTwo) {
          Manager.getInstance().getPlayers().get(turn).takeACardFromRepository();
          Manager.getInstance().getPlayers().get(turn).takeACardFromRepository();
          setTurn();
      }
      else if(currentCard instanceof Skip)
          setTurn();
      else if (currentCard instanceof Reverse)
          setDirection(-1*direction);
    }

    /**
     * A Method to access the only available sample of Board class
     * @return the only instance of Board class
     */
    public static Board getInstance(){
        if (board == null)
            board = new Board();
        return board ;
    }

    /**
     * A setter for current card of the board
     */
    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    /**
     * A setter for current direction of the board :
     * "+1" for clockwise direction and "-1" for anticlockwise direction
     * @param direction direction which wants to be set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * A setter for number of players
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * A setter for current color of the board
     * @param currentColor number of color which wants to be set
     * "0" : black
     * "1" : red
     * "2" : yellow
     * "3" : cyan
     * "4" : blue
     */
    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * A setter for index of player who is its turn in the current game
     */
    public void setTurn() {
        turn = (turn+direction)%numberOfPlayers ;
        turn = turn < 0 ? turn+=numberOfPlayers :turn ;
    }

    /**
     * Set the turn in the primary board
     */
    public void setFirstTurn(){
        turn = (new Random()).nextInt(numberOfPlayers);
    }

    /**
     * A setter for the fine which should be pay by cards of players
     * @param fine fine that wants to be set
     */
    public void setFine(int fine) {
        this.fine = fine;
    }

    /**
     * A getter for current card of the board
     * @return current card
     */
    public Card getCurrentCard() {
        return currentCard;
    }

    /**
     * A getter for current direction of the board
     * @return current direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * A getter for number of players
     * @return number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * A getter for current color of the board
     * "0" : black
     * "1" : red
     * "2" : yellow
     * "3" : cyan
     * "4" : blue
     * @return current color
     */
    public int getCurrentColor() {
        return currentColor;
    }

    /**
     * A getter for index of player who is its turn in the current game
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Unpaid fine in the current game
     * @return fine
     */
    public int getFine() {
        return fine;
    }

    /**
     * A method to update the current card
     * @param card card which wants to be replaced by the last card on the board
     */
    public void updateCurrentCard(Card card){
        repository.getCards().addFirst(currentCard);
        currentCard = card ;
    }

    /**
     * A method to get the last card of repository
     * @return last card of repository
     */
    public Card getTheLatestTankCard(){
        if (repository.getCards().size()==0)
            repository = new Repository();
        Card card = repository.getCards().getLast();
        repository.getCards().removeLast();
        return card ;
    }

    /**
     * A method which finds the first card of repository which is not action or wild card ( wild draw or wild)
     * @return the first Colored card of repository
     */
    public Card findTheFirstColoredCardOfRepository(){
        Card card;
        while (!((card = repository.getCards().getLast()) instanceof ColorCard)) {
            repository.getCards().removeLast();
            repository.getCards().addFirst(card);
        }
        repository.getCards().removeLast();
        return card ;
    }
}
