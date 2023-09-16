package uno.view;

import uno.conroller.Manager;
import uno.model.Board;
import uno.model.Cards.Card;
import uno.model.Player;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The only UI class which is a consular UI
 */
public class Show {
    /**
     * A method to show cards of a player
     * @param player player
     */
    public void showPlayerCards(Player player) {
        int j = 0;
        int i =0 ;
        while (j*7 <player.getCards().size()) {
            for ( i = 0; i < 7; i++) {
                if (j * 7 + i >= player.getCards().size())
                    break;
                player.getCards().get(j * 7 + i).printFirstLineOfCard();
            }
            System.out.println();
            for ( i = 0; i < 7; i++) {
                if (j * 7+ i >= player.getCards().size())
                    break;
                player.getCards().get(j *7 + i).printSecondLineOfCard();
            }
            System.out.println();
            for (i = 0; i < 7; i++) {
                if (j * 7 + i >= player.getCards().size())
                    break;
                player.getCards().get(j * 7+ i).printThirdLineOfCard();
            }
            System.out.println();
            for ( i = 0; i < 7; i++) {
                if (j * 7 + i >= player.getCards().size())
                    break;
                player.getCards().get(j * 7+i).printSecondLineOfCard();
            }
            System.out.println();
            for (i = 0; i < 7; i++) {
                if (j * 7+ i >= player.getCards().size())
                    break;
                player.getCards().get(j * 7 + i).printFirstLineOfCard();
            }
            System.out.println();
            j++ ;
        }
        System.out.println();
    }

    /**
     * A method that shows direction of current game
     * @param direction direction of current game that is :
     *  "+1" for clockwise
     *  "-1" for anticlockwise
     */
    public void showGameDirection(int direction){
        String directionName ;
        directionName = (direction==1)?"Clockwise":"Anticlockwise";
        System.out.println("Direction : "+directionName);
    }

    /**
     * A method that shows number of cards which each player has
     */
    public void ShowTheNumberOfPlayersCards(){
        ArrayList <Player> players = Manager.getInstance().getPlayers() ;
        int i =1 ;
        for (Player player : players) {
            System.out.println(i+". "+player.getName()+" : "+player.getCards().size());
            i++ ;
        }
    }

    /**
     * A method to show scoreboard at the end of the game
     */
    public void showScoreTable(){
        System.out.println("\n\n"+"\u001B[1m"+"Scoreboard");
        System.out.println("------------------------------------");
        LinkedList<Player> sortedPlayersByTheirScores  = Manager.getInstance().makeSortedListOfPlayers();
        Player player =sortedPlayersByTheirScores.get(0);
        System.out.println("\u001B[1m"+"Winner : "+player.getName());
        for (int i =1 ; i<sortedPlayersByTheirScores.size() ;i++) {
             player = sortedPlayersByTheirScores.get(i);
            System.out.println(player.getName() + " : " + player.findScore());
        }

        System.exit(1);
    }

    /**
     * A method to show current card which is on the board
     * @param card current card
     */
    public void showCurrentCard(Card card){
        System.out.print("                    ");
        card.printFirstLineOfCard();
        System.out.println();
        System.out.print("                    ");
        card.printSecondLineOfCard();
        System.out.println();
        System.out.print("Card of the board : ");
        card.printThirdLineOfCard();
        System.out.println();
        System.out.print("                    ");
        card.printSecondLineOfCard();
        System.out.println();
        System.out.print("                    ");
        card.printFirstLineOfCard();
        System.out.println();

    }

    /**
     * A method which shows the current color of the game
     * @param color
     */
    public void showCurrentColor(int color){
        String colorName ;
        switch (color){
            case 1 :
                colorName = "Red" ;
                break;
            case 2 :
                colorName = "Yellow" ;
                break;
            case 3 :
                colorName = "Cyan" ;
                break;
            case 4 :
                colorName = "Blue" ;
                break;
            default:
               colorName ="No color" ;
        }
        System.out.println("Current Color = "+colorName);
    }

    /**
     * A method which shows the card which a cpu has chosen
     * @param card selected card by cpu
     */
    public void showChosenCardOfACpuPlayer(Card card){
        System.out.print("                    ");
        card.printFirstLineOfCard();
        System.out.println();
        System.out.print("                    ");
        card.printSecondLineOfCard();
        System.out.println();
        System.out.print("Cpu selected card : ");
        card.printThirdLineOfCard();
        System.out.println();
        System.out.print("                    ");
        card.printSecondLineOfCard();
        System.out.println();
        System.out.print("                    ");
        card.printFirstLineOfCard();
        System.out.println();
    }
}
