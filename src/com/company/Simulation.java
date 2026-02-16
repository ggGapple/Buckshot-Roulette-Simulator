package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Simulation {
    private static int playerDamage = 0;
    private static int dealerDamage = 0;
    private static boolean playerTurn = true;
    private static ArrayList<Integer> values = new ArrayList<Integer>();
    public static ArrayList<Integer> shells = new ArrayList<Integer>();
    private static int dealerChoice = 0;
    private static int playerChoice =0;

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

    public static ArrayList<Integer> alwaysShootVDealer(){
        shuffle();
        values.clear();
        playerDamage = 0;
        dealerDamage = 0;
        playerTurn = true;

        for (int g=0;g<8;g++){
            if (playerTurn) {
                playerDamage += Strategies.alwaysShoot(g,shells);
                playerTurn=false;
            }
            else{
                dealerChoice = Strategies.dealerAI(g,shells);
                if (dealerChoice == 2) {
                    continue;
                } else if (dealerChoice == -1) {
                    playerDamage += 1;
                }
                else if (dealerChoice == 1){
                    dealerDamage += 1;
                }
                playerTurn=true;
            }
        }
        values.add(playerDamage);
        values.add(dealerDamage);
        return values;
    }

    public static ArrayList<Integer> alwaysShootMirror(){
        shuffle();
        values.clear();
        playerDamage = 0;
        dealerDamage = 0;
        playerTurn = true;

        for (int g=0;g<8;g++){
            if (playerTurn) {
                playerDamage += Strategies.alwaysShoot(g,shells);
                playerTurn=false;
            }else{
                dealerDamage +=Strategies.alwaysShoot(g,shells);
                playerTurn=true;
            }
        }
        values.add(playerDamage);
        values.add(dealerDamage);
        return values;
    }

    public static ArrayList<Integer> alwaysShootVProbability(){
        shuffle();
        values.clear();
        playerDamage = 0;
        dealerDamage = 0;
        playerTurn = true;

        for (int g=0;g<8;g++){
            if (playerTurn) {
                playerDamage += Strategies.alwaysShoot(g,shells);
                playerTurn=false;
            }else{
                dealerChoice = Strategies.probability(g,shells);
                if (dealerChoice == 2) {
                    continue;
                } else if (dealerChoice == -1) {
                    playerDamage += 1;
                } else if (dealerChoice==1) {
                    dealerDamage += 1;
                }
                playerTurn=true;
            }
        }
        values.add(playerDamage);
        values.add(dealerDamage);
        return values;
    }

    public static ArrayList<Integer> probabilityVDealer(){
        shuffle();
        values.clear();
        playerDamage = 0;
        dealerDamage = 0;
        playerTurn = true;

        for (int g=0;g<8;g++){
            if (playerTurn) {
                playerChoice = Strategies.probability(g,shells);
                if (playerChoice == 2) {
                    continue;
                } else if (playerChoice == -1) {
                    dealerDamage+= 1;
                } else if (playerChoice==1) {
                    playerDamage += 1;
                }
                playerTurn=false;
            }else{
                dealerChoice = Strategies.dealerAI(g,shells);
                if (dealerChoice == 2) {
                    continue;
                } else if (dealerChoice == -1) {
                    playerDamage += 1;
                } else if (dealerChoice == 1) {
                    dealerDamage += 1;
                }
                playerTurn=true;
            }
        }
        values.add(playerDamage);
        values.add(dealerDamage);
        return values;
    }
    public static ArrayList<Integer> mirrorProbability(){
        shuffle();
        values.clear();
        playerDamage = 0;
        dealerDamage = 0;
        playerTurn = true;

        for (int g=0;g<8;g++){
            if (playerTurn) {
                playerChoice = Strategies.probability(g,shells);
                if (playerChoice == 2) {
                    continue;
                } else if (playerChoice == -1) {
                    dealerDamage+= 1;
                }
                else if (playerChoice==1){
                    playerDamage += 1;
                }
                playerTurn=false;
            }else{
                dealerChoice = Strategies.probability(g,shells);
                if (dealerChoice == 2) {
                    continue;
                } else if (dealerChoice == -1) {
                    playerDamage += 1;
                }
                else if (dealerChoice==1) {
                    dealerDamage += 1;
                }
                playerTurn=true;
            }
        }
        values.add(playerDamage);
        values.add(dealerDamage);
        return values;
    }
}
