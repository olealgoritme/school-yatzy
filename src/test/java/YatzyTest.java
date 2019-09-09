import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class YatzyTest {

    @Test
    void yatzyTest() {
        assertEquals(4, 2+2);
    }

    @Test
    void shouldCalculateForSingles() {
        assertEquals(5, calculateScoreFromDiceResult(YATZY_TYPE.SINGLES, new int[] {1, 2, 3, 4, 5}));
        assertEquals(6, calculateScoreFromDiceResult(YATZY_TYPE.SINGLES, new int[] {1, 2, 4, 5, 6}));
    }

    @Test
    void shouldCalculateForMultiple() {
        assertEquals(14, calculateScoreFromDiceResult(YATZY_TYPE.PAIR, new int[] {2, 5, 5, 2, 1}));
        assertEquals(15, calculateScoreFromDiceResult(YATZY_TYPE.TRIPLET, new int[] {2, 5, 5, 5, 1}));
        assertEquals(24, calculateScoreFromDiceResult(YATZY_TYPE.QUADRUPLE, new int[] {2, 6, 6, 6, 6}));
    }

    private enum YATZY_TYPE {
        SINGLES(1),
        PAIR(2),
        TRIPLET(3),
        QUADRUPLE(4);

        private int frequency;
        YATZY_TYPE(int frequency) {
            this.frequency = frequency;
        }
    }

    private int calculateScoreFromDiceResult(YATZY_TYPE dice_type, int[] diceThrow) {
        int score = 0;

        if(dice_type == YATZY_TYPE.SINGLES){
           for(int i : singlesCounter(diceThrow)){
               score += i;
           }
        } else if(dice_type == YATZY_TYPE.PAIR || dice_type == YATZY_TYPE.TRIPLET || dice_type == YATZY_TYPE.QUADRUPLE){
            for(int i : digitCounter(diceThrow, dice_type)){
                score +=i;
            }
        }
        return score;
    }

    int[] singlesCounter(int[] diceArray){
        ArrayList<Integer> frequencyList = new ArrayList<>();
        int highestNum = 0;

        for(int ints : diceArray){
            if(highestNum<ints){
                highestNum = ints;
            }
        }
        for(int ints : diceArray){
            if(ints == highestNum){
                frequencyList.add(highestNum);
            }
        }

        return getBestInts(frequencyList, YATZY_TYPE.SINGLES);
    }

    int[] digitCounter(int[] diceArray, YATZY_TYPE dice_type) {

        ArrayList<Integer> frequencyList = new ArrayList<>();

        int count = 0;

            for (int i = 0; i < diceArray.length; i++) {
                int currentDice = diceArray[i];
                    for (int y = i + 1; y < diceArray.length; y++) {
                        if (diceArray[y] == currentDice) {

                        //Adds like values to arrayList
                            if (count != dice_type.frequency) {
                                frequencyList.add(currentDice);
                                count++;
                            }
                        }
                    }
                }

        return getBestInts(frequencyList, dice_type);

    }


    private int[] getBestInts(ArrayList<Integer> resultList, YATZY_TYPE dice_type) {
        int pos = 0;
        int[] p = new int[resultList.size()];
        for (int i : resultList) {
            p[pos] = i;
            pos++;
        }
        return p;
    }

}
