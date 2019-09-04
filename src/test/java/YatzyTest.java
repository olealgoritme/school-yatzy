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
    void shouldCalculateForFives() {
        assertEquals(1, score("FIVES", new int[] {2, 5, 1, 1, 1}));
    }


   /*private boolean frequency(int expected, int[]dice) {
        int result = 0;
        for (int diceValue : dice) {
            if (diceValue == dice[diceValue])
                result++;
        }
        return (expected == result);
    }*/
   private int score(String category, int[] dice) {

       if(category.equals("ONES")) {
           return frequency(1, (dice));
       } else if(category.equals("FIVES")) {
           return frequency(5, (dice));
       } else{
           return 0;
       }

   }

    private int frequency(int categoryValue, int[] dice) {
        int result = 0;
        for (int diceValue : dice) {
            if (diceValue == categoryValue)
                result++;
        }
        return result;
    }


}
