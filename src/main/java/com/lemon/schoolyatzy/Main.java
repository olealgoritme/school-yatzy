package com.lemon.schoolyatzy;

import java.util.HashMap;
import java.util.Map;

/**
 *  HOT TIP!
 *  Compile: "mvn clean package"
 *  Run: java -jar target/schoolyatzy-0.1.jar
 *
 */
public class Main {

    public static void main(String[] args) {

        Map<int[], Yatzy.YATZY_TYPE> plays = new HashMap<>() {{
            put(new int[]{6, 6, 6, 6, 6}, Yatzy.YATZY_TYPE.YATZY);
            put(new int[]{4, 4, 5, 5, 4}, Yatzy.YATZY_TYPE.HOUSE);
            put(new int[]{2, 6, 6, 6, 6}, Yatzy.YATZY_TYPE.QUADRUPLE);
            put(new int[]{2, 5, 5, 5, 1}, Yatzy.YATZY_TYPE.TRIPLET);
            put(new int[]{2, 5, 5, 2, 1}, Yatzy.YATZY_TYPE.PAIR);
            put(new int[]{1, 2, 4, 5, 6}, Yatzy.YATZY_TYPE.SINGLES);
        }};

        Yatzy yatzy = new Yatzy();

        for (Map.Entry<int[], Yatzy.YATZY_TYPE> play : plays.entrySet()) {
            yatzy.setDice(play.getKey());
            yatzy.setPlayType(play.getValue());

            DiceRolling rolling = new DiceRolling(yatzy.getPlayType());
            for (int i = 0; i < 30; i++) {
                rolling.animate();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            yatzy.play();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n------ TOTAL SCORE [" + yatzy.getScore() + "] WELL DONE-----------");
    }
}
