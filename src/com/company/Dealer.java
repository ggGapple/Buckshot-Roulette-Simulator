package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Dealer {
    private static int liveCount =0;
    private static int blankCount = 0;

    public static int dealerAI(int shell, int index){
        if (index == 7){ // if last shell
            return shell;
        }
            double rand = Math.random()*100;
            if (rand>50){ //shoot
                return shell;
            } if (shell == 1){ //shot self
                return -1;
            } return 2; //blank self
    }

    public static int alwaysShoot(int shell)
    {
        return shell;
    }

    public static int probability(int shell, int index, ArrayList<Integer> shells){
        liveCount = 0;
        blankCount = 0;
        for (int t=0;t<index;t++){
            liveCount+=shells.get(t);
        } blankCount = index-liveCount;
        if (blankCount<liveCount) {
            if (shell==0){
                return 2;
            } return -1;
        }return shell;
    }
}
