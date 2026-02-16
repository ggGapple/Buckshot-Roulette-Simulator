package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Simulation {
    public static ArrayList<Integer> shells = new ArrayList<Integer>();

    // initializes arrayList shells with 4 live rounds and 4 blank rounds, then shuffles
    private static void shuffle(){
        shells.clear();
        for (int k=0;k<8;k++){
            if (k<4){
                shells.add(0);
            } else {
                shells.add(1);
            }
        }
        Collections.shuffle(shells);
    }

    // takes two Strategy methods as input, simulates a game according to those strategies, and returns four values:
    // the winner (1 for player, 0 for dealer), the total damage dealt by the player, the total damage dealt by the
    // dealer, and the amount of loops it took to win (loops are fully played out, even if someone goes to negative
    // health)
    public static int[] simulate(Strategies.Strategy player, Strategies.Strategy dealer){
        int playerDamage = 0;
        int dealerDamage = 0;
        int playerChoice;
        int dealerChoice;
        int winner = -1;
        int loops = 0;
        //loop until someone wins
        while (winner==-1) {
            shuffle();
            loops++;
            boolean playerTurn = true;
            for (int g = 0; g < 8; g++) {
                //player turn
                if (playerTurn) {
                    //make choice based on given method
                    playerChoice = player.choose(g, shells);
                    //if player shot self and it was blank
                    if (playerChoice == 2) {
                        //repeat turn
                        continue;
                    }
                    // if player shot self and it was live
                    else if (playerChoice==-1){
                        dealerDamage++;
                    }
                    //if player shot dealer and it was live
                    else if (playerChoice == 1) {
                        playerDamage += 1;
                    }
                    //player does not get repeat turn
                    playerTurn = false;
                }
                //dealer turn
                else {
                    //make choice based on given method
                    dealerChoice = dealer.choose(g, shells);
                    //if dealer shot self and it was blank
                    if (dealerChoice == 2) {
                        continue;
                    }
                    //if dealer shot self and it was live
                    else if (dealerChoice == -1) {
                        playerDamage += 1;
                    }
                    //if dealer shot player and it was live
                    else if (dealerChoice==1){
                        dealerDamage++;
                    }
                    //dealer does not repeat turn
                    playerTurn = true;
                }

                //health check
                if (playerDamage >= 4) {
                    winner = 1;
                } else if (dealerDamage >= 4) {
                    winner = 0;
                }

            }
        }
        return new int[]{winner,playerDamage,dealerDamage,loops};
    }


}
