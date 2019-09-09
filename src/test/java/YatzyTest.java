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
        assertArrayEquals(new int[] {5}, calculateScoreFromDiceResult(YATZY_TYPE.SINGLES, new int[] {1, 2, 3, 4, 5}));
        assertArrayEquals(new int[] {6}, calculateScoreFromDiceResult(YATZY_TYPE.SINGLES, new int[] {1, 2, 4, 5, 6}));
    }

    @Test
    void shouldCalculateForMultiple() {
        assertArrayEquals(new int[] {2, 5}, calculateScoreFromDiceResult(YATZY_TYPE.PAIR, new int[] {2, 5, 5, 2, 1}));
        assertArrayEquals(new int[] {5, 5, 5}, calculateScoreFromDiceResult(YATZY_TYPE.TRIPLET, new int[] {2, 5, 5, 5, 1}));
        assertArrayEquals(new int[] {6, 6, 6, 6}, calculateScoreFromDiceResult(YATZY_TYPE.QUADRUPLE, new int[] {2, 6, 6, 6, 6}));
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

    private int[] calculateScoreFromDiceResult(YATZY_TYPE dice_type, int[] dice) {
        return digitCounter(dice, dice_type);
    }

    int[] digitCounter(int[] diceArray, YATZY_TYPE dice_type) {

        ArrayList<Integer> frequencyList = new ArrayList<Integer>();

        int count = 0;

        for (int y = 0; y < diceArray.length; y++) {
            int currentDice = diceArray[y];

            for (int i = y + 1; i < diceArray.length; i++) {

                if (diceArray[i] == currentDice) {
                    if (dice_type.frequency == 2 && count != 2) {
                        // We have a pair!
                        frequencyList.add(currentDice);
                        count++;
                    }
                    if(dice_type.frequency == 3 && count != 3){
                        // We have a triplet!
                        frequencyList.add(currentDice);
                        count++;
                    }
                    if(dice_type.frequency == 4 && count != 4){
                        // We have a quadruplet
                        frequencyList.add(currentDice);
                        count++;
                    }
                }

            }
        }
        return getBestInts(frequencyList, dice_type);
    }

    private int[] getBestInts(ArrayList<Integer> pairs, YATZY_TYPE dice_type) {
        int pos = 0;
        int[] p = new int[pairs.size()];
        for (int i : pairs) {
            p[pos] = i;
            pos++;
        }
        return p;
    }

}
