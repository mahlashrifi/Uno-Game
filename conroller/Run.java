package uno.conroller;

import uno.model.Cards.DrawTwo;
import uno.model.Cards.Wild;
import uno.model.* ;
import uno.model.Cards.WildDraw;
import uno.view.Show;
import javax.swing.*;
import java.util.Scanner;

/**
 * A class to handle programme in running process
 */
public class Run {
    private Manager manager;
    private Board board;
    private Show show;
    private Scanner scanner;

    /**
     * Constructor of Run class
     */
    public Run() {
        manager = Manager.getInstance();
        board = Board.getInstance();
        show = new Show();
        scanner = new Scanner(System.in);
    }

    /**
     * A method that checks whether a string of characters is a number or not
     * @param str input string
     * @return true if input string is number and false if not
     */
    public static boolean isNumeric(String str) {
        if(str.equals(" ") || str.equals(" "))
            return false ;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * A method that takes the number of players and their information from the user
     */
    public void getInformationOfPlayers() {
        System.out.println("Enter the number of players ");
        String input;
        while (!isNumeric(input = scanner.nextLine())) {
            System.out.println("Input should be a number ! Enter another one");
        }
        board.setNumberOfPlayers(Integer.parseInt(input));
        for (int i = 0; i < Integer.parseInt(input); i++)
            manager.addNewPlayer();
    }

    /**
     * A method to run game
     */
    public void runGame() {
        getInformationOfPlayers();
        board.setFirstTurn();
        board.makePrimaryBoard();
        board.setCurrentColor(board.getCurrentCard().getColor());

        while (!manager.checkEndOfGame()) {

            Player player = manager.getPlayers().get(board.getTurn());
            System.out.println("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
            System.out.println("Turn : "+player.getName());
             showInformationOfTheGame();
            if (player instanceof Human) {
                show.showPlayerCards(player);
                doProcessOfPlayingATurn(player);
            }
            else if (player instanceof Cpu) {
                doProcessOfPlayingATurn(player);
            }
        }
        show.showScoreTable();
    }

    /**
     * A Method which shows following information of current game to players :
     * 1 : color
     * 2 : direction
     * 3 : number of cards which each player has
     * 4 : card of the board
     */
    public void showInformationOfTheGame() {
        show.showCurrentColor(board.getCurrentColor());
        show.showGameDirection(board.getDirection());
        show.ShowTheNumberOfPlayersCards();
        show.showCurrentCard(board.getCurrentCard());
        System.out.println();
    }

    /**
     * A method which do the process of a player movement in its turn
     * @param player The player whose turn it is
     */
    public void doProcessOfPlayingATurn(Player player) {
        if (board.getFine() == 0) {
            if (player.findNumberOfMatchedCards() != 0 || player.findNumberOfWildDrawCards() != 0)
                player.playTheTurnByPuttingACard();
            else
                player.playTheTurnByGettingACardFromRepository();
        } else
            playWhenFineIsNotZero(player);
    }

    /**
     * A method in which the player movement will be done in turn while there is an unpaid fine
     * @param player
     */
    public void playWhenFineIsNotZero(Player player) {
        if (board.getCurrentCard() instanceof DrawTwo) {
            if (player.findNumberOfDrawCards() != 0)
                player.avoidPayingFine(2);
            else
                player.payTheFine(2);
            return;
        }
        if (board.getCurrentCard() instanceof WildDraw) {
            if (player.findNumberOfWildDrawCards() != 0)
                player.avoidPayingFine(3);
            else
                player.payTheFine(2);
            return;
        }
    }
}

