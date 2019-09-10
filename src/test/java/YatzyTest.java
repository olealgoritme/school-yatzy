import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class YatzyTest {

    @Test
    void shouldCalculateForSingles() {
        Assertions.assertEquals(5, calculateScoreFromDiceResult(new int[] {1, 2, 3, 4, 5}, YATZY_TYPE.SINGLES));
        Assertions.assertEquals(8, calculateScoreFromDiceResult(new int[] {1, 4, 4, 5, 6}, YATZY_TYPE.SINGLES));
        Assertions.assertEquals(12, calculateScoreFromDiceResult(new int[] {1, 2, 5, 6, 6}, YATZY_TYPE.SINGLES));
    }

    @Test
    void shouldCalculateForMultiple() {
        Assertions.assertEquals(10, calculateScoreFromDiceResult(new int[] {2, 5, 5, 2, 1}, YATZY_TYPE.PAIR));
        Assertions.assertEquals(15, calculateScoreFromDiceResult(new int[]{2, 5, 5, 5, 1}, YATZY_TYPE.TRIPLET));
        Assertions.assertEquals(24, calculateScoreFromDiceResult(new int[] {2, 6, 6, 6, 6}, YATZY_TYPE.QUADRUPLE));
    }

    @Test
    void shouldCalculateForSmallStraight(){
        Assertions.assertEquals(15, calculateScoreFromDiceResult(new int[] {1, 2, 3, 4, 5}, YATZY_TYPE.SMALL_STRAIGHT));
    }

    @Test
    void shouldCalculateHouse() {
        Assertions.assertEquals(28, calculateScoreFromDiceResult(new int[] {5, 5, 6, 6, 6}, YATZY_TYPE.HOUSE));
        Assertions.assertEquals(24, calculateScoreFromDiceResult(new int[] {4, 4, 4, 6, 6}, YATZY_TYPE.HOUSE));
        Assertions.assertEquals(19, calculateScoreFromDiceResult(new int[] {3, 3, 3, 5, 5}, YATZY_TYPE.HOUSE));
    }

    @Test
    void shouldCalculateYatzy() {
        Assertions.assertEquals(30, calculateScoreFromDiceResult(new int[] {6, 6, 6, 6, 6}, YATZY_TYPE.YATZY));
        Assertions.assertEquals(25, calculateScoreFromDiceResult(new int[] {5, 5, 5, 5, 5}, YATZY_TYPE.YATZY));
    }

    private enum YATZY_TYPE {
        SINGLES("singles",1),
        PAIR("pairs",2),
        TRIPLET("three of a kind",3),
        QUADRUPLE("four",4),
        SMALL_STRAIGHT("small straight"),
        HOUSE("house"),
        YATZY("yatzy", 5);

        private String name;
        private int frequency;

        //Constructors
        YATZY_TYPE(String name){
            this.name = name;
        }
        YATZY_TYPE(String name, int frequency) {
            this.name = name;
            this.frequency = frequency;
        }

    }


    private int calculateHouse(int[] diceThrow) {
        // House = 3 + 2
        // Mission: identify 3, then identify 2
        // if we find them, calculate result/score and return it
        // else we just return 0

       // storing boolean if we found matches
       boolean foundTriple = false;
       boolean foundPair = false;

       // store result/score
       int res = 0;

       // Converting the integer array to list, to use Collections()
       List<Integer> intList = new ArrayList<>();
       for (int dice : diceThrow) intList.add(dice); // we forgot to add the dice to the integer list, ops.

       // iterate over the integer list
       for ( int i : intList ) {
           if(3 == Collections.frequency(intList, i) && !foundTriple) {
                res += 3 * i; // calculate the result of the 3 * dice digits
                foundTriple = true; // found the triple, setting boolean
            } else if( 2 == Collections.frequency(intList, i) && !foundPair) {
                res += 2 * i; // calculate the result of the 2 * dice digits
                foundPair = true; // found the pair, setting boolean
           }
       }

        return res;
    }


    private int calculateScoreFromDiceResult(int[] diceThrow, YATZY_TYPE yatzyType) {
        int result;

        if(yatzyType == YATZY_TYPE.SINGLES) {
            result = calculateSingles(diceThrow);
        } else if(yatzyType == YATZY_TYPE.SMALL_STRAIGHT){
            result = calculateSmallStraight(diceThrow);
        } else if (yatzyType == YATZY_TYPE.HOUSE) {
            result = calculateHouse(diceThrow);
        } else {
            result = calculateLikes(diceThrow, yatzyType);
        }
        return result;
    }


    private int calculateSmallStraight(int[] diceThrow) {
        Arrays.sort(diceThrow);

        int res = 0;
        for(int i = 0; i < diceThrow.length; i++){
            try {
                //Checks if dice are sequential
                if((diceThrow[i]+1) == diceThrow[i+1]){
                    res += diceThrow[i];
                }
            } catch (ArrayIndexOutOfBoundsException e){
                res += diceThrow[i];
            }
        }
        //As it runs now, this will work for Large Straights as well.
        return res;
    }


    private int calculateSingles(int [] diceThrow){
        List<Integer> intList = new ArrayList<>();
        for (int i : diceThrow) intList.add(i);
        int occurrences;
        int highestOccurrenceSum = 0;

        //Finds the frequency of each dice in the roll and calculates the sum of it
        //The one that provides the highest score will be returned as the score
        for(int dice : intList){
            occurrences = Collections.frequency(intList, dice);
            int sumOfCurrentDice = dice*occurrences;

            if(highestOccurrenceSum<sumOfCurrentDice){
                highestOccurrenceSum = sumOfCurrentDice;
            }
        }
        return highestOccurrenceSum;
    }

    private int calculateLikes(int[] diceThrow, YATZY_TYPE yatzyType) {

        List<Integer> intList = new ArrayList<>();
        for (int i : diceThrow) intList.add(i);
        int occurrences;
        int highestDice = 0;

            for (int dice : intList) {
                occurrences = Collections.frequency(intList, dice);

                //If the current dice in the array occurs the required amount of times this is sat as the dice value to calculate the score
                if (occurrences >= yatzyType.frequency) {

                    //Since it's possible to have multiple pairs in a row we need to find the highest one
                    if(yatzyType == YATZY_TYPE.PAIR){
                        if(highestDice<dice){
                            highestDice = dice;
                        }
                    } else {
                        highestDice = dice;
                    }
                }
            }

            //Returns the highest found dice of the frequency timed with the frequency to get the score
            //throws an exception if diceThrow does not meet requirements.
            if(highestDice != 0){
                return (highestDice* yatzyType.frequency);
            } else {
                throw new IllegalArgumentException("The dices thrown do not meet the requirements for " + yatzyType.name);
            }

    }

}
