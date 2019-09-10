
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.assertEquals;

class YatzyTest {


    private int YATZY_SCORE = 0;

    @Test
    void yatzyTest() {
        assertEquals(4, 2+2);
    }

    @Test
    void shouldCalculateForSingles() {
        assertEquals(5, calculateScoreFromDiceResult(new int[] {1, 2, 3, 4, 5}, YATZY_TYPE.SINGLES));
        assertEquals(8, calculateScoreFromDiceResult(new int[] {1, 4, 4, 5, 6}, YATZY_TYPE.SINGLES));
        assertEquals(12, calculateScoreFromDiceResult(new int[] {1, 2, 5, 6, 6}, YATZY_TYPE.SINGLES));
    }

    @Test
    void shouldCalculateForMultiple() {
        assertEquals(10, calculateScoreFromDiceResult(new int[] {2, 5, 5, 2, 1}, YATZY_TYPE.PAIR));
        assertEquals(15, calculateScoreFromDiceResult(new int[]{2, 5, 5, 5, 1}, YATZY_TYPE.TRIPLET));
        assertEquals(24, calculateScoreFromDiceResult(new int[] {2, 6, 6, 6, 6}, YATZY_TYPE.QUADRUPLE));
    }

    @Test
    void shouldCalculateForSmallStraight(){
        assertEquals(15, calculateScoreFromDiceResult(new int[] {1, 2, 3, 4, 5}, YATZY_TYPE.SMALL_STRAIGHT));
    }

    private enum YATZY_TYPE {
        SINGLES("singles",1),
        PAIR("pairs",2),
        TRIPLET("three of a kind",3),
        QUADRUPLE("four",4),
        SMALL_STRAIGHT("small straight");


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

    private int calculateScoreFromDiceResult(int[] diceThrow, YATZY_TYPE yatzyType) {
        int result = 0;

        if(yatzyType == YATZY_TYPE.SINGLES){
            result = calculateSingles(diceThrow);
        }else {
            result = calculateLikes(diceThrow, yatzyType);
        }
        increaseScore(result);
        return result;
    }


    private int calculateSingles(int [] diceThrow){
        List<Integer> intList = new ArrayList<>();
        for (int i : diceThrow) intList.add(i);
        int occurences;
        int highestOccurencySum = 0;

        for(int die : intList){
            occurences = Collections.frequency(intList, die);
            int sumOfCurrentDie = die*occurences;
            if(highestOccurencySum<sumOfCurrentDie){
                highestOccurencySum = sumOfCurrentDie;
            }
        }
        return highestOccurencySum;
    }

    private int calculateLikes(int[] diceThrow, YATZY_TYPE yatzyType) {

        List<Integer> intList = new ArrayList<>();
        for (int i : diceThrow) intList.add(i);
        int occurences;
        int highestDice = 0;

            for (int die : intList) {
                occurences = Collections.frequency(intList, die);

                //If the current die in the array occurs the required amount of times this is sat as the die value to calculate the score
                if (occurences >= yatzyType.frequency) {

                    //Since it's possible to have multiple pairs in a throw we need to find the highest one
                    if(yatzyType == YATZY_TYPE.PAIR){
                        if(highestDice<die){
                            highestDice = die;
                        }
                    } else {
                        highestDice = die;
                    }
                }
            }

            //Returns the highest found dice of the frequency timed with the frequency to get the score
            //throws an exception if dicethrow does not meet requirements.
            if(highestDice != 0){
                return (highestDice* yatzyType.frequency);
            } else {
                throw new IllegalArgumentException("The dices thrown do not meet the requirements for " + yatzyType.name);
            }

    }

    private void increaseScore(int score) {
        this.YATZY_SCORE += score;
    }


}
