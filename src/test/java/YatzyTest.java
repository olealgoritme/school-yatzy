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
    void shouldCalculateForOnes() {
        assertEquals(4, score("ONES", new int[] {2, 1, 1, 1, 1}));
    }

    @Test
    void shouldCalculateForFives() {
        assertEquals(5, score("FIVES", new int[] {2, 5, 1, 1, 1}));
    }

    @Test
    void shouldCalculateForPairs(){
        assertArrayEquals(new int[] {2, 5}, pairsOf(new int[]{2, 5, 5, 2, 1}));
    }

    @Test
    void shouldCalculateForThreesome(){
        assertArrayEquals(new int[] {2, 5}, pairsOf(new int[]{2, 5, 5, 2, 1}));
    }

    int[] pairsOf(int[] dice) {
        // returns array of digits that have pairs (2 equal values)
        ArrayList<Integer> pairs = new ArrayList<Integer>();

        for(int y = 0; y < dice.length; y++) {
            int current = dice[y];
            int pos = y;

            for(int i = pos+1; i < dice.length; i++) {
                if(dice[i] == current) {
                    // we have a fucking match
                    pairs.add(current);
                }
            }
        }
        int pos = 0;
        int[] p = new int[pairs.size()];
        for (int i : pairs) {
            p[pos] = i;
            pos++;
        }
        return p;
    }


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
                result+=categoryValue;
        }
        return result;
    }


}
