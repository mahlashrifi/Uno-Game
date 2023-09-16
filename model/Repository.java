package uno.model;
import uno.model.Cards.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Repository {
    private LinkedList<Card> cards;

    /**
     * A constructor for Repository class
     */
    public Repository() {

        cards = new LinkedList<>();
        makeSortedCardList();
        shuffle();
    }

    /**
     * A getter for cards filed
     * @return cards that are contain in repository
     */
    public LinkedList<Card> getCards() {
        return cards;
    }

    /**
     * A method that make a sorted list of cards that still ate not shuffled
     */
    public void makeSortedCardList() {
        for (int color = 1; color < 5; color++) {
            cards.add(new Numeric(color , 0));
            for (int k = 0; k < 2; k++) {
                for (int number = 1; number < 10; number++)
                    cards.add(new Numeric(color , number));
                cards.add(new DrawTwo(color));
                cards.add(new Reverse(color));
                cards.add(new Skip(color));
            }
        }
        for (int k = 0; k < 4; k++) {
            cards.add(new Wild());
            cards.add(new WildDraw());
        }
    }

    /**
     * A method that makes array of indexes of shuffled cards
     * @return array of indexes of cards
     */
    public int[] makeArrayToShuffle() {
        Random random = new Random();
        int[] indexes = new int[108];
        int randomInt;
        for (int i = 0; i < 108; i++) {
            while (contain(indexes , randomInt = random.nextInt(108) + 1));
            indexes[i] = randomInt;
        }
        return indexes ;
    }

    /**
     * A method that checks weather an array contain an special element or not
     * @return true if there is the specific element in the array,otherwise false
     */
    public boolean contain(int[] array , int element){
        for (int i : array) {
            if(i==element)
                return true ;
        }
        return false ;
    }

    /**
     * A method that shuffle cards of repository
     */
    public void shuffle(){
        LinkedList<Card> cardsCopy = new LinkedList<>();
        int[] indexes = makeArrayToShuffle();
        for (int i = 0 ; i<108 ; i++)
            cardsCopy.add(cards.get(indexes[i]-1));
        cards = cardsCopy ;
    }
}
