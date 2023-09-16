package uno.conroller;
import uno.model.* ;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A class which has access to player information and have methods to search in them
 */
public class Manager {
    private static Manager manager = null;
    private ArrayList<Player> players;
    private Scanner scanner;

    /**
     * Manager class is singleton and this is private constructor of that
     */
    private Manager() {
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    /**
     * @return the only instance of Manager class which is a singleton class
     */
    public static Manager getInstance() {
        if (manager == null)
            manager = new Manager();
        return manager;
    }

    /**
     * A getter for array of players
     * @return ArrayList of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * A method that checks whether the information entered is correct for a player
     * @param splitInput array of input words which has split by space
     * @return true if entered information is valid and false if not
     */
    public boolean isEnteredInformationValid(String[] splitInput) {
        if (splitInput.length != 2)
            return false;
        if (splitInput[0].toCharArray().length == 0)
            return false;
        if (splitInput[1].equalsIgnoreCase("human") || splitInput[1].equalsIgnoreCase("cpu"))
            return true;
        return false;
    }

    /**
     * A method to do process of adding new player
     */
    public void addNewPlayer() {
        System.out.println("Enter the name and kind of player " + (players.size() + 1) + " :");
        String[] splitInput;
        while (!isEnteredInformationValid(splitInput = scanner.nextLine().split("\\s"))) {
            System.out.println("Your input is not valid . second word should be \"cpu\" or \"human\"");
            System.out.println("Try another one");
        }
        if (splitInput[1].equalsIgnoreCase("cpu")) {
            Cpu cpu = new Cpu(splitInput[0]);
            players.add(cpu);
        } else if (splitInput[1].equalsIgnoreCase("human")) {
            Human human = new Human(splitInput[0]);
            players.add(human);
        }
    }

    /**
     * A method that checks whether we have reached the end of the game or not
     * @return true if the game is ended and false if not
     */
    public boolean checkEndOfGame() {
        for (Player player : players)
            if (player.getCards().size() == 0)
                return true;
        return false;
    }

    /**
     * A method which find lowest score among players
     * @return lowest score
     */
    public int findTheLowestScore(){
        int lowest = 5000000 ;
        for (int i =0 ; i<players.size() ; i++){
            if (lowest > players.get(i).findScore())
                lowest = players.get(i).findScore();
        }
        return lowest ;
    }

    /**
     * A method which find index of player who has the lowest score among other players
     * @return The number of the player with the lowest score
     */
    public int findIndexOfPlayerWithLowestScore(int lowestScore) {
        for (int i =0 ; i< players.size() ;i++)
            if(players.get(i).findScore()==lowestScore)
                return i ;
        return 0 ;

    }

    /**
     * A method which finds index of player who has win
     * @return index of winner
     */
    public int findIndexOfWinner(){
        for (int i = 0 ; i< players.size() ;i++)
            if(players.get(i).getCards().size()==0)
                return i ;
            return -1 ;
    }

    /**
     * A method to make an orderly list of players based on their score
     * @return
     */
    public LinkedList<Player> makeSortedListOfPlayers() {
        LinkedList<Player> sortedPlayers = new LinkedList<>();
        Player player = players.get(findIndexOfWinner());
        sortedPlayers.addLast(player);
        players.remove(player);
        int size = players.size();
        for (int i =0 ; i<size ; i++){
            player = players.get(findIndexOfPlayerWithLowestScore(findTheLowestScore()));
            sortedPlayers.addLast(player);
            players.remove(player);
        }
        return sortedPlayers ;
    }
}



