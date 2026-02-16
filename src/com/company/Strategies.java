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
        // count the amount of live and blank rounds already used
        for (int t=0;t<index;t++){
            liveCount +=shells.get(t);
        }
        int blankCount = index- liveCount;
        if (blankCount< liveCount) {
            if (shell==0){
                return 2;
            } return -1;
        }return shell;
    }
}
