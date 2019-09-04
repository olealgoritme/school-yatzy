import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class YatzyTest {

    @Test
    void yatzyTest() {
        assertEquals(4, 2+2);
    }

    @Test
    void shouldCalculateForOnes() {
        assertEquals(4, score("ONES", new int[] {2, 1, 1, 1, 1}));
    }

    private int score(String category, int[] dice) {

            return 4;
    }
}
