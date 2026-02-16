package com.company;

import java.util.ArrayList;

public class Player {
    public static int alwaysShoot(int shell){
        return shell;
    }
    private static int liveCount =0;
    private static int blankCount = 0;
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
