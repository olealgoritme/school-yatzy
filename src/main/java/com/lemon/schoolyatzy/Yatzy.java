package com.lemon.schoolyatzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Very simple Yatzy
 * Project within Kristinia University College, Oslo, Norway
 *
 * @author Guro Olsen Ørbech & Ole Algoritme
 */

class Yatzy {


    private int YATZY_SCORE = 0;
    private YATZY_TYPE yatzyType;
    private int[] diceRolled;


    public enum YATZY_TYPE {
        SINGLES("singles",1),
        PAIR("pairs",2),
        TRIPLET("three of a kind",3),
        QUADRUPLE("four",4),
        STRAIGHT("small straight"),
        HOUSE("house"),
        YATZY("yatzy", 5);

        protected String name;
        protected int frequency;

        YATZY_TYPE(String name){
            this.name = name;
        }
        YATZY_TYPE(String name, int frequency) {
            this.name = name;
            this.frequency = frequency;
        }

    }


    private void increaseScore(int score) {
        this.YATZY_SCORE += score;
    }


    private int getMaxInt() {
        List<Integer> intList = new ArrayList<>();
        for (int i : this.diceRolled) intList.add(i);
        return Collections.max(intList);
    }


    private int calculateSingles(){
        List<Integer> intList = new ArrayList<>();
        for (int i : this.diceRolled) intList.add(i);
        int occurrences;
        int highestOccurrenceSum = 0;

        //Finds the frequency of each dice in the roll and calculates the sum of it
        //The one that provides the highest score will be returned as the score
        for(int dice : intList){
            occurrences = Collections.frequency(intList, dice);
            int sumOfCurrentDice = dice*occurrences;

            if(highestOccurrenceSum<sumOfCurrentDice){
                highestOccurrenceSum = sumOfCurrentDice;
            }
        }
        return highestOccurrenceSum;
    }


    private int calculateStraight() {
        Arrays.sort(this.diceRolled);
        int res = 0;
        for(int i = 0; i < this.diceRolled.length; i++){
            try {
                if((this.diceRolled[i]+1) == this.diceRolled[i+1]){
                    res += this.diceRolled[i];
                }
            } catch (ArrayIndexOutOfBoundsException e){
                res += this.diceRolled[i];
            }
        }
        return res;
    }


    private int calculateMultiple() {
        List<Integer> intList = new ArrayList<>();
        for (int i : this.diceRolled) intList.add(i);
        int max = Collections.max(intList);
        int occurrences = Collections.frequency(intList, max);
        return (max * occurrences);
    }


    private int calculateHouse() {
        boolean foundTriple = false;
        boolean foundPair = false;
        int res = 0;

        List<Integer> intList = new ArrayList<>();
        for (int dice : this.diceRolled) intList.add(dice);

        for ( int i : intList ) {
            if(3 == Collections.frequency(intList, i) && !foundTriple) {
                res += 3 * i;
                foundTriple = true;
            } else if( 2 == Collections.frequency(intList, i) && !foundPair) {
                res += 2 * i;
                foundPair = true;
            }
        }

        return res;
    }


    /**
     *  Calculates the integers and updates score
     *  @return int (calculated integer based on result)
     */
    private int calculateScoreFromDiceResult() {
        int res = 0;
        switch(yatzyType) {
            case SINGLES:
                res = calculateSingles();
                break;
            case PAIR:
            case TRIPLET:
            case QUADRUPLE:
            case YATZY:
                res = calculateMultiple();
                break;
            case HOUSE:
                res = calculateHouse();
                break;
            case STRAIGHT:
                res = calculateStraight();
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
        int res = calculateScoreFromDiceResult();
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
