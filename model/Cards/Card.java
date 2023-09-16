package uno.model.Cards;

public abstract class Card {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[1m" + "\u001B[30m";
    public static final String ANSI_RED = "\u001B[1m" + "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[1m" + "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[1m" + "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[1m" +"\u001B[34m";
    /**
     * A filled that shows color of cards
     * colors :
     * 0 : black
     * 1 : red
     * 2 : yellow
     * 3 :cyan
     * 4: blue
     */
    protected int color ;

    /**
     * @return String of card color
     */
    public String findColor(){
        switch (this.getColor()){
            case 0 :
                return ANSI_BLACK ;
            case 1 :
                return ANSI_RED ;
            case 2 :
                return ANSI_YELLOW ;
            case 3 :
                return ANSI_CYAN ;
            case 4 :
                return ANSI_BLUE ;
        }
       return ANSI_RESET ;
    }

    /**
     * A method that prints first line of the card
     */
    public void printFirstLineOfCard(){
        System.out.print(findColor()+"|$$$$$$$$$$$$$$$$$$|   "+ANSI_RESET);
    }

    /**
     * A method that prints second line of the card
     */
    public void printSecondLineOfCard(){
            System.out.print(findColor()+"|                  |   "+ANSI_RESET);
    }

    /**
     * A method that prints third line of the card
     */
    public void printThirdLineOfCard(){
        if(this instanceof Numeric)
            System.out.print(findColor()+"|        "+((Numeric)this).getNumber()+"         |   "+ANSI_RESET);
        else if(this instanceof DrawTwo)
            System.out.print(findColor()+"|       Draw2      |   "+ANSI_RESET);
        else if(this instanceof Reverse)
            System.out.print(findColor()+"|      Reverse     |   "+ANSI_RESET);
        else if (this instanceof Skip)
            System.out.print(findColor()+"|       Skip       |   "+ANSI_RESET);
        else if(this instanceof Wild)
            System.out.print(findColor()+"|       Wild       |   "+ANSI_RESET);
        else if(this instanceof WildDraw)
            System.out.print(findColor()+"|     Wild Draw    |   "+ANSI_RESET);
    }

    /**
     * An abstract method to get color of the Card
     * @return number of card color :
     * 0 : black
     * 1 : red
     * 2 : yellow
     * 3 :cyan
     * 4: blue
     */
    public abstract int getColor();

    /**
     * Each card must perform operations when it comes to the board of the game . this is the abstract method that do those operations
     */
    public  void doCardProcess(){};

    /**
     * A method that checks weather card is match with current card of the board or not
     * @return true if card is match with current card of the board and false if not
     */
    public boolean doesCardMatchWithCurrentCard(){return false;}
}
