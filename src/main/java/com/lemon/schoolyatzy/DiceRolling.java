package com.lemon.schoolyatzy;
/**
*
 * Copyright logicbig.com
 * https://www.logicbig.com/how-to/code-snippets/jcode-java-command-line-animation
 *
 * Just throw in for fun....
* */
class DiceRolling {

    DiceRolling(Yatzy.YATZY_TYPE yatzyType) {
        System.out.print("\nRolling dice, baby!");
        System.out.print("\nPLAYING YATZY [" + yatzyType.toString() + "]\n");
    }
    private String lastLine = "";

    private void print(String line) {
        //clear the last line if longer
        if (lastLine.length() > line.length()) {
            String temp = " ".repeat(lastLine.length());
            if (temp.length() > 1)
                System.out.print("\r" + temp);
        }
        System.out.print("\r" + line);
        lastLine = line;
    }

    private byte anim;

    void animate() {
        switch (anim) {
            case 1:

            case 6:
                print("[ | ]");
                break;
            case 2:
                print("[ / ]");
                break;
            case 3:
            case 5:
                print("[ - ]");
                break;
            case 4:
                print("[ \\ ]");
                break;
            default:
                anim = 0;
                print("[ - ]");
        }
        anim++;
    }
}
