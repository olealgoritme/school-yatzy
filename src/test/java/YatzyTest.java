import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        assertEquals(6, calculateScoreFromDiceResult(new int[] {1, 2, 4, 5, 6}, YATZY_TYPE.SINGLES));
    }

    @Test
    void shouldCalculateForMultiple() {
        assertEquals(10, calculateScoreFromDiceResult(new int[] {2, 5, 5, 2, 1}, YATZY_TYPE.PAIR));
        assertEquals(15, calculateScoreFromDiceResult(new int[] {2, 5, 5, 5, 1}, YATZY_TYPE.TRIPLET));
        assertEquals(24, calculateScoreFromDiceResult(new int[] {2, 6, 6, 6, 6}, YATZY_TYPE.QUADRUPLE));
    }

    private enum YATZY_TYPE {
        SINGLES(1),
        PAIR(2),
        TRIPLET(3),
        QUADRUPLE(4);

        YATZY_TYPE(int frequency) {
        }
    }

    private void increaseScore(int score) {
            this.YATZY_SCORE += score;
    }

    private int calculateSingles(int[] diceThrow) {
        List<Integer> intList = new ArrayList<>();
        for (int i : diceThrow) intList.add(i);
        return Collections.max(intList);
    }

    private int calculateMultiple(int[] diceThrow) {
        List<Integer> intList = new ArrayList<>();
        for (int i : diceThrow) intList.add(i);
        int max = Collections.max(intList);
        int occurences = Collections.frequency(intList, max);
        return (max * occurences);
    }


    private int calculateScoreFromDiceResult(int[] diceThrow, YATZY_TYPE yatzyType) {

        int res = 0;
        switch(yatzyType) {
            case SINGLES:
                res = calculateSingles(diceThrow);
                break;
            case PAIR:
            case TRIPLET:
            case QUADRUPLE:
                res = calculateMultiple(diceThrow);
                break;
        }
        increaseScore(res);
        return res;
    }

}
