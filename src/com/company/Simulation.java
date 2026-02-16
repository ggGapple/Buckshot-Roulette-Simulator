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

    // takes two Strategy methods as input, simulates a game according to those strategies, and returns the amount of
    // damage the player dealt to the dealer throughout that game. Knowing that there are always four live rounds, we
    // can therefore extrapolate the amount of damage the dealer dealt to the player with 4 - the return value.
    // By only keeping track of playerDamage, we skip a lot of case checks (i.e. dealer shoots player or player
    // shoots self) and simply move on to the next shell via the loop, which implicitly increments dealerDamage in
    // the form of 4 - return value
    public static int simulate(Strategies.Strategy player, Strategies.Strategy dealer){
        shuffle();
        int playerDamage = 0;
        int playerChoice;
        int dealerChoice;
        boolean playerTurn = true;

        for (int g=0;g<8;g++){
            //player turn
            if (playerTurn) {
                //make choice based on given method
                playerChoice = player.choose(g,shells);
                //if player shot self and it was blank
                if (playerChoice == 2) {
                    //repeat turn
                    continue;
                }
                //if player shot dealer and it was live
                else if (playerChoice==1){
                    playerDamage += 1;
                }
                //player does not get repeat turn
                playerTurn=false;
            }
            //dealer turn
            else{
                //make choice based on given method
                dealerChoice = dealer.choose(g,shells);
                //if dealer shot self and it was blank
                if (dealerChoice == 2) {
                    continue;
                }
                //if dealer shot self and it was live
                else if (dealerChoice == -1) {
                    playerDamage += 1;
                }
                //dealer does not repeat turn
                playerTurn=true;
            }
        }
        return playerDamage;
    }
}
