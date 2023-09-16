package uno.model;

import java.util.Scanner;

public class Human extends Player {
    /**
     * A constructor for Human class
     * @param name
     */
    public Human(String name) {
        super(name);
    }

    /**
     * A method that checks if input is number of a valid card that player can choose
     * @param typeOfValidCards type of cards that are valid :
     *  "1" : cards that are match with current card of the board
     *  "2" : draw cards
     *  "3" : wild draw cards
     * @param input string og input
     * @return true if input is valid and false if not
     */
    public boolean isInputAValidCard(int typeOfValidCards,String input){
        if(input.toCharArray().length == 0 )
            return false ;
        for (char c : input.toCharArray()) {
            if (c > '9' || c < '0')
                return false;
        }
        switch (typeOfValidCards){
            case 1:
                if(doesArrayContainElement(findMatchedCards(),Integer.parseInt(input)-1))
                    return true ;
                break;
            case 2:
                if(doesArrayContainElement(findDrawCards(),Integer.parseInt(input)-1))
                    return true ;
                break;
            case 3:
                if(doesArrayContainElement(findWildDrawCards(),Integer.parseInt(input)-1))
                    return true ;
                break;
        }
        return false ;
    }
    /**
     * A method that checks weather an input is information of a color :
     * "0" : black
     * "1" : red
     * "2" : yellow
     * "3" : cyan
     * "4" : blue
     */
    public boolean isInputAValidColor(String input){
        if(input.toCharArray().length !=1)
            return false ;
        if(input.toCharArray()[0] > '4' || input.toCharArray()[0]<'1')
            return false ;
        return true ;

    }
    @Override
    public int chooseACard(int typeOfValidCards) {
        String cardNumber ;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of chosen card");
        while (!isInputAValidCard(typeOfValidCards,cardNumber = scanner.nextLine()))
            System.out.println("Your input is not valid . Enter another one");
        return Integer.parseInt(cardNumber)-1;
    }
    @Override
    public int chooseAColor(){
        String colorNumber ;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose board color\n1.Red\n2.Yellow\n3.Cyan\n4.Blue");
        while (!isInputAValidColor(colorNumber = scanner.nextLine()))
            System.out.println("There is no color which can be match to input . Enter another one");
        return Integer.parseInt(colorNumber);

    }

}
