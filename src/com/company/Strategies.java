package com.company;

import java.util.ArrayList;

public class Strategies {

    @FunctionalInterface
    public interface Strategy{
        int choose(int index, ArrayList<Integer> shells);
    }

    // taking a shell value (0 = blank, 1 = live) and an index (0 = first shell, 1 = second, ... 7 = last), returns
    // a value according to the dealer's natural AI. That is, 50% chance to shoot the player, 50% chance to shoot self.
    // 1 if the opposing player is shot and takes damage, 0 if the player doesn't, -1 if the dealer takes damage,
    // 2 if the dealer shoots themselves and takes no damage
    public static int dealerAI(int index, ArrayList<Integer> shells){
        int shell = shells.get(index);
        if (index == 7){ // if last shell
            return shell;
        }
        double rand = Math.random();
        if (rand>0.5){ // 50% chance to shoot player
            return shell;
        }
        // 50% chance to shoot self
        if (shell == 1){ //shot self, live round
            return -1;
        } return 2; //shot self, blank round
    }

    // taking the shell value, returns the shell value (this strategy simply always shoots)
    public static int alwaysShoot(int index, ArrayList<Integer> shells) {return shells.get(index);}

    // taking the current shell value, an index, and an ArrayList of the shells, returns a value according to a perfect
    // probabilistic strategy. That is, based on the available information, this strategy shoots self if there are
    // less live rounds than blanks, and shoots the opposing player if the amount of live rounds is greater than or
    // equal to the amount of blanks.
    public static int probability(int index, ArrayList<Integer> shells){
        int shell = shells.get(index);
        int liveCount = 0;
        // count the amount of live rounds already used
        for (int t=0;t<index;t++){
            liveCount +=shells.get(t);
        }
        // extrapolate the amount of blank rounds already used
        int blankCount = index- liveCount;
        //if more lives have been used far than blanks, that means there are more blank rounds than lives left.
        // Therefore, shoot self
        if (blankCount < liveCount) {
            if (shell==0){
                return 2;
            } return -1;
        }
        //otherwise return shell (shoot dealer)
        return shell;
    }

    // taking the current shell value, an index, and an ArrayList of the shells, returns a value according to the
    // opposite of a perfect probabilistic strategy. That is, based on the available information, this strategy shoots
    // self if there are more live rounds than blanks, and shoots the opposing player if the amount of live rounds is
    // less than or equal to the amount of blanks
    public static int inverseProbability(int index, ArrayList<Integer> shells){
        int shell = shells.get(index);
        int liveCount = 0;
        // count the amount of live rounds already used
        for (int t=0;t<index;t++){
            liveCount +=shells.get(t);
        }
        // extrapolate the amount of blank rounds already used
        int blankCount = index- liveCount;
        //if more blanks have been used far than lives, that means there are more live rounds than blanks left.
        // Therefore, shoot self
        if (blankCount > liveCount) {
            if (shell==0){
                return 2;
            } return -1;
        }
        //otherwise return shell (shoot dealer)
        return shell;
    }

    // taking the current shell value, an index, and an ArrayList of the shells, returns a value according to an
    // aggressive strategy. That is, if the number of blank rounds remaining is greater than the amount of live rounds
    // remaining by 2 or more, shoots self, otherwise shoots opposing player.
    public static int aggressive(int index, ArrayList<Integer> shells){
        int shell = shells.get(index);
        int liveCount = 0;
        // count the amount of live rounds already used
        for (int t=0;t<index;t++){
            liveCount +=shells.get(t);
        }
        // extrapolate the amount of blank rounds already used
        int blankCount = index- liveCount;
        // extrapolate remaining
        int livesLeft = 4- liveCount;
        int blanksLeft = 4- blankCount;

        // if the difference is greater than or equal to 2 (in blanks' favor), shoot self
        if (blanksLeft >= livesLeft+2) {
            if (shell==0){
                return 2;
            } return -1;
        }
        //otherwise return shell (shoot dealer)
        return shell;
    }

    // taking the current shell value, an index, and an ArrayList of the shells, returns a value according to a
    // conservative strategy. That is, if the number of live rounds remaining is greater than the amount of blank rounds
    // remaining by 2 or more, shoots opposing player, otherwise self.
    public static int conservative(int index, ArrayList<Integer> shells){
        int shell = shells.get(index);
        int liveCount = 0;
        // count the amount of live rounds already used
        for (int t=0;t<index;t++){
            liveCount +=shells.get(t);
        }
        // extrapolate the amount of blank rounds already used
        int blankCount = index- liveCount;
        // extrapolate remaining
        int livesLeft = 4- liveCount;
        int blanksLeft = 4- blankCount;

        // if the difference is greater than or equal to 2 (in blanks' favor), shoot dealer
        if (livesLeft >= blanksLeft+2) {
            return shell;
        }

        //otherwise shoot self
        if (shell==0){
            return 2;
        } return -1;


    }

    // taking the current shell value, an index, and an ArrayList of the shells, returns a value according to an
    // alternating strategy. That is, for even indexes the strategy shoots the opposing player, and for odd indexes
    // shoots self
    public static int alternating(int index, ArrayList<Integer> shells){
        //even index
        if (index%2==0) {
            return shells.get(index);
        }
        //otherwise (odd index) shoot self
        if (shells.get(index)==0){
            return 2;
        } return -1;
    }

    // taking the current shell value, an index, and an ArrayList of the shells, returns a value according to a cheater
    // with knowledge of all shells and positions making perfect decisions
    public static int cheater(int index, ArrayList<Integer> shells){
        int shell = shells.get(index);
        //if live
        if (shell==1) {
            return shell;
        }
        //otherwise shoot self, we know it's not live
        return 2;
    }
}
