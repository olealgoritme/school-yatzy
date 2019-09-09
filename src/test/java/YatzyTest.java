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
        assertArrayEquals(new int[] {2, 5}, pairsOf(new int[]{2, 5, 5, 2, 1}, 2));
    }

    @Test
    void shouldCalculateForLikes(){
        assertArrayEquals(new int[]{5, 5, 5}, pairsOf(new int[]{2, 5, 5, 5, 1}, 3));
        assertArrayEquals(new int[]{6, 6, 6, 6}, pairsOf(new int[]{2, 6, 6, 6, 6}, 4));
    }

    int[] pairsOf(int[] dice, int checkFor) {

        ArrayList<Integer> pairs = new ArrayList<Integer>();

        int count = 0;

        for (int y = 0; y < dice.length; y++) {
            int current = dice[y];

            for (int i = y + 1; i < dice.length; i++) {

                if (dice[i] == current) {
                    if (checkFor == 2 && count != 2) {
                        pairs.add(current);
                        count++;
                    }
                    if(checkFor == 3 && count != 3){
                        pairs.add(current);
                        count++;
                    }
                    if(checkFor == 4 && count != 4){
                        pairs.add(current);
                        count++;
                    }
                }

            }
        }
        return getInts(pairs);
        }

    private int[] getInts(ArrayList<Integer> pairs) {
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
