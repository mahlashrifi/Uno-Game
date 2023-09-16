package uno.model;

import uno.model.Cards.*;
import uno.view.Show;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player {
    private String name ;
    private ArrayList<Card> cards ;
    private int score ;
    private Show show ;

    /**
     * A constructor for Player class
     * @param name name of player
     */
    public Player(String name) {
        this.name = name ;
        show = new Show();
        cards = new ArrayList<>();
        addStartCards();
    }

    /**
     * A getter for name field
     * @return name field
     */
    public String getName() {
        return name;
    }
    /**
     * A getter for array list of cards which player has
     * @return array list of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * A method which adds seven random card to player tank card
     */
    public void addStartCards(){
        for (int k =0 ; k< 7 ; k++)
            takeACardFromRepository();
    }

    /**
     * A method to find number of  player cards which match with current card of the board
     * @return the number of match cards that player has
     */
    public int findNumberOfMatchedCards(){
        int i = 0 ;
        for (Card card : cards)
            if (card.doesCardMatchWithCurrentCard())
                i++ ;
        return i ;
    }

    /**
     * A method that finds indexes of cards which are match to current card of the board
     * @return array list of indexes of cards which are match to current card of the board
     */
    public int[] findMatchedCards(){
        int[] matchedCards = new int[findNumberOfMatchedCards()];
        int i =0 ;
        for (int k =0 ; k<cards.size() ; k++)
            if (cards.get(k).doesCardMatchWithCurrentCard()) {
                matchedCards[i] = k;
                i++;
            }
        return matchedCards ;
    }

    /**
     * A method that finds number of wild draw cards of player
     * @return the number of player cards that are wild draw
     */
    public int findNumberOfWildDrawCards(){
        int i = 0 ;
        for (Card card : cards)
            if (card instanceof WildDraw)
                i++ ;
        return i ;
    }

    /**
     * A method to find indexes of wild draw cards
     * @return array list of indexes of cards which are wild draw
     */
    public int[] findWildDrawCards(){
        int[] WildDrawCards = new int[findNumberOfWildDrawCards()];
        int i =0 ;
        for (int k =0 ; k<cards.size() ; k++)
            if (cards.get(k) instanceof WildDraw) {
                WildDrawCards[i] = k;
                i++;
            }
        return WildDrawCards ;
    }

    /**
     * A method to finds number of draw cards of player
     * @return the number of draw cards of player
     */
    public int findNumberOfDrawCards(){
        int i = 0 ;
        for (Card card : cards)
            if (card instanceof DrawTwo)
                i++ ;
        return i ;
    }

    /**
     * A method to find indexes of draw cards
     * @return array list of indexes card which are draw
     */
    public int[] findDrawCards(){
        int[] WildDrawCards = new int[findNumberOfDrawCards()];
        int i =0 ;
        for (int k =0 ; k<cards.size() ; k++)
            if (cards.get(k) instanceof DrawTwo) {
                WildDrawCards[i] = k;
                i++;
            }
        return WildDrawCards ;
    }

    /**
     * A method that checks if there is a special element in array or not
     * @param arrayToCheck array
     * @param intToCheck element
     * @return true if element exist in array and false if not
     */
    public boolean doesArrayContainElement(int[] arrayToCheck , int intToCheck){
        for (int i =0 ; i< arrayToCheck.length ;i++)
            if (arrayToCheck[i] == intToCheck)
                return true ;
        return false ;
    }

    /**
     * A method to put a card of player on the board
     * @param indexOfCard index of selected card
     */
    public void putACard(int indexOfCard){
        Board.getInstance().updateCurrentCard(cards.get(indexOfCard));
        cards.remove(indexOfCard);
    }

    /**
     * method of receiving a card from the repository and adding it to the player's cards
     * @return a card which has received from the repository
     */
    public Card takeACardFromRepository(){
        Card card = Board.getInstance().getTheLatestTankCard();
        cards.add(card);
        return card ;
    }

    /**
     * A method which do process of playing in turn when player does not have any match card to put
     */
    public void playTheTurnByGettingACardFromRepository(){
        Card card = takeACardFromRepository();
        if(this instanceof Cpu){
            System.out.println(this.name+" ... got a cart from repository");
            if(card.doesCardMatchWithCurrentCard()) {
                card.doCardProcess();
                putACard(cards.size() - 1);
                if (Board.getInstance().getCurrentColor() == 0)
                    Board.getInstance().setCurrentColor(chooseAColor());
                    show.showChosenCardOfACpuPlayer(Board.getInstance().getCurrentCard());
            }
            else {
                System.out.println("New card of "+this.getName()+" has been checked and is not valid to put .");
                Board.getInstance().setTurn();
            }
        }
        if (this instanceof Human) {
            System.out.println("\nYou do not have any valid card to choice, So you will receive a card from the repository\n");
            if (card.doesCardMatchWithCurrentCard()) {
                System.out.println("Your new cards :");
                show.showPlayerCards(this);
                System.out.println("Now you have a valid card !Please enter the number of card");
                Scanner scanner = new Scanner(System.in);
                while (scanner.nextLine().equals(cards.size()));
                card.doCardProcess();
                putACard(cards.size()-1);
                if(Board.getInstance().getCurrentColor() == 0)
                    Board.getInstance().setCurrentColor(chooseAColor());
            }
            else {
                System.out.println("Your new card has been checked and There is still no valid card to put on the board !");
                Board.getInstance().setTurn();
            }
        }
    }

    /**
     * A method which does process of playing in turn which player can put a card
     */
    public void playTheTurnByPuttingACard() {
        if (findNumberOfMatchedCards() != 0) {
           doBothChooseACardAndColor(1);
           if(this instanceof Cpu)
               show.showChosenCardOfACpuPlayer(Board.getInstance().getCurrentCard());
            return;
        }
        if (findNumberOfWildDrawCards() != 0) {
            doBothChooseACardAndColor(3);
            if(this instanceof Cpu)
                show.showChosenCardOfACpuPlayer(Board.getInstance().getCurrentCard());
            return;
        }
    }

    /**
     * A method which do process of scape from paying fine for player
     * @param typeOfValidCards type of cards which fine was created because of that kind
     * "2" : draw
     * "3" : wild draw
     */
    public void avoidPayingFine(int typeOfValidCards){
        doBothChooseACardAndColor(typeOfValidCards);
        if(this instanceof Cpu)
            show.showChosenCardOfACpuPlayer(Board.getInstance().getCurrentCard());
    }

    /**
     * A method to pay fine
     * @param typeOfValidCards type of cards which fine was created because of that kind
     * "2" : draw
     * "3" : wild draw
     */
    public void payTheFine(int typeOfValidCards){
        for (int i = 0 ; i< Board.getInstance().getFine() ; i++)
            takeACardFromRepository();
        Board.getInstance().setFine(0);
        Board.getInstance().setTurn();
        System.out.println(this.name +" received the fine because it could not neutralize card fine on the board");
    }
    public void doBothChooseACardAndColor(int typeOfValidCards){
        Card card = cards.get(chooseACard(typeOfValidCards));
        card.doCardProcess();
        Board.getInstance().updateCurrentCard(card);
        cards.remove(card);
        if (Board.getInstance().getCurrentColor() == 0)
            Board.getInstance().setCurrentColor(chooseAColor());
    }

    /**
     * A method that finds score of player
     * @return score of player
     */
    public int findScore() {
        int totalScore = 0;
        for (Card card : cards) {
            if (card instanceof Numeric)
                totalScore += ((Numeric) card).getNumber();
            else if (card instanceof ActionCard)
                totalScore += 20;
            else if (card instanceof Wild)
                totalScore += 50;
        }
        return totalScore;
    }

    /**
     * An abstract method that do process of choosing a card .
     * @param typeOfValidCards index of type that player should choose among that kinds of card :
     *  "1" : cards that are match with current card of the board
     *  "2" : draw cards
     *  "3" : wild draw cards
     * @return index of selected card
     */
    public abstract int chooseACard(int typeOfValidCards);

    /**
     * An abstract method that do process of choosing a color .
     * @return index of color :
     * "0" : black
     * "1" : red
     * "2" : yellow
     * "3" : cyan
     * "4" : blue
     */
    public abstract int chooseAColor();
}
