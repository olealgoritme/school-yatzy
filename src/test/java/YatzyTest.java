import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YatzyTest {

    @Test
    void yatzyTest() {
        assertEquals(4, 2+2);
    }

    @Test
    void shouldCalculateForOnes() {
        assertEquals(4, score("ONES", new int[] {2, 1, 1, 1, 1}));
    }

    @Test
    void shouldCalculateForTwos() {
        assertEquals(1, score("FIVES", new int[] {2, 1, 1, 1, 1}));
    }


    private boolean frequency(int expected, int[]dice) {
        int result = 0;
        for (int diceValue : dice) {
            if (diceValue == dice[diceValue])
                result++;
        }
        return (expected == result);
    }

    private int frequency(int[]dice) {
        int result = 0;
        for (int diceValue : dice) {
            if (diceValue == dice[diceValue])
                result++;
        }
        return result;
    }

    private int score(String category, int[] dice) {

        if(category.equals("ONES")) {
            return frequency((dice));
        }

        return 0;
    }

}
