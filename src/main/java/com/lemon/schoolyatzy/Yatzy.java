package com.lemon.schoolyatzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Very simple Yatzy
 * Project within Kristinia University Collect, Oslo, Norway
 *
 * @authors Guro Olsen Ørbech & Ole Algoritme
 */

class Yatzy {

    // total yatzy score
    private int YATZY_SCORE = 0;

    // which yatzy type/game is being played
    private YATZY_TYPE yatzyType;

    // 5 x dice is being rolled
    private int[] diceRolled;

    // enumerated yatzy play types
    public enum YATZY_TYPE {
        SINGLES(1),
        PAIR(2),
        TRIPLET(3),
        QUADRUPLE(4);

        YATZY_TYPE(int frequency) {
        }
    }

    // internal score calculation
    private void increaseScore(int score) {
        this.YATZY_SCORE += score;
    }

    // getting the largest integer
    private int calculateSingles(int[] diceThrow) {
        List<Integer> intList = new ArrayList<>();
        for (int i : diceThrow) intList.add(i);
        return Collections.max(intList);
    }

    // getting the result of frequency by integer
    private int calculateMultiple(int[] diceThrow) {
        List<Integer> intList = new ArrayList<>();
        for (int i : diceThrow) intList.add(i);
        int max = Collections.max(intList);
        int occurences = Collections.frequency(intList, max);
        return (max * occurences);
    }

    /**
     *  Calculates the integers and updates score
     *
    * @return int (calculated integer based on result)
     */
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
        return res;
    }

    void setPlayType(YATZY_TYPE yatzyType) {
        this.yatzyType = yatzyType;
    }

    void setDice(int[] diceRolled) {
        this.diceRolled = diceRolled;
    }

    void play() {
        if (this.yatzyType == null)
            throw new IllegalArgumentException("You need to set yatzy play type - Yatzy.setPlayType()");
        if(this.diceRolled == null || this.diceRolled.length > 5)
            throw new IllegalArgumentException("You need to set 5 dice digits - Yatzy.setDice()");
        int res = calculateScoreFromDiceResult(this.diceRolled, this.yatzyType);
        increaseScore(res);
        System.out.print("\nROLLED DICE [" + Arrays.toString(this.diceRolled) + "] Points: " + res);
        System.out.print("\nCurrent score: " + this.YATZY_SCORE + "\n");
    }

    int getScore() {
        return this.YATZY_SCORE;
    }

    YATZY_TYPE getPlayType() {
        return this.yatzyType;
    }

}
