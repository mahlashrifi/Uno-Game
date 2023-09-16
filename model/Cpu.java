package uno.model;

import java.util.Random;

public class Cpu extends Player {
    /**
     * A constructor for Cpu class
     * @param name name of computer player
     */
    public Cpu(String name) {
        super(name);
    }

    /**
     * A method that produces a random number between zero numbers and the number of members of an array
     * @return a random number
     */
    public int findARandomNumberInArray(int[] array){
        Random random = new Random();
        return random.nextInt(array.length);
    }
    @Override
    public int chooseACard(int typeOfValidCards) {
      switch (typeOfValidCards){
          case 1 :
               return findMatchedCards()[findARandomNumberInArray(findMatchedCards())];
          case 2 :
              return findDrawCards()[findARandomNumberInArray(findDrawCards())];
          case 3 :
              return findWildDrawCards()[findARandomNumberInArray(findWildDrawCards())];
      }
      return 0 ;
    }

    @Override
    public int chooseAColor() {
      Random random = new Random();
      return (random.nextInt(4)+1);
    }
}
