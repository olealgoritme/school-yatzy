package com.lemon.schoolyatzy;

import java.util.HashMap;
import java.util.Map;

/**
 *  TIP!
 *  Compile: "mvn clean package"
 *  Run: java -jar target/schoolyatzy-0.1.jar
 *
 */
public class Main {

    public static void main(String[] args) {

        Map<int[], Yatzy.YATZY_TYPE> plays = new HashMap<>() {{
            put(new int[]{1, 2, 3, 4, 5}, Yatzy.YATZY_TYPE.SINGLES);
            put(new int[]{1, 2, 4, 5, 6}, Yatzy.YATZY_TYPE.SINGLES);
            put(new int[]{2, 5, 5, 2, 1}, Yatzy.YATZY_TYPE.PAIR);
            put(new int[]{2, 5, 5, 5, 1}, Yatzy.YATZY_TYPE.TRIPLET);
            put(new int[]{2, 6, 6, 6, 6}, Yatzy.YATZY_TYPE.QUADRUPLE);
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
