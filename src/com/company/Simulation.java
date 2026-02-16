package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Simulation {
    private static int playerDamage = 0;
    private static int dealerDamage = 0;
    private static boolean playerTurn = true;
    private static boolean repeat = false;
    private static boolean repeat2=false;
    private static ArrayList<Integer> values = new ArrayList<Integer>();
    public static ArrayList<Integer> shells = new ArrayList<Integer>();
    private static int dealerChoice = 0;
    private static int playerChoice =0;
    public static ArrayList<Integer> shuffle(){
        shells.clear();
        for (int k=0;k<8;k++){
            if (k<4){
                shells.add(0);
            } else {
                shells.add(1);
            }
        }
        Collections.shuffle(shells);
        return shells;
    }
    public static ArrayList<Integer> alwaysShootVDealer(){
        shuffle();
        values.clear();
        playerDamage = 0;
        dealerDamage = 0;
        for (int g=0;g<8;g++){
            if (!repeat&&playerTurn||g==0) {
                playerDamage += Player.alwaysShoot(shells.get(g));
                playerTurn=false;
            }
            else{
                dealerChoice = Dealer.dealerAI(shells.get(g),g);
                repeat = false;
                if (dealerChoice == 2) {
                    repeat = true;
                } else if (dealerChoice == -1) {
                    playerDamage += 1;
                } else if (dealerChoice ==0){
                    repeat=false;
                }
                else {
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
        for (int g=0;g<8;g++){
            if (playerTurn||g==0) {
                playerDamage += Player.alwaysShoot(shells.get(g));
                playerTurn=false;
            }else{
                dealerDamage +=Dealer.alwaysShoot(shells.get(g));
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
        for (int g=0;g<8;g++){
            if (!repeat&&playerTurn||g==0) {
                playerDamage += Player.alwaysShoot(shells.get(g));
                repeat = false;
                playerTurn=false;
            }else{
                dealerChoice = Dealer.probability(shells.get(g),g,shells);
                repeat=false;
                if (dealerChoice == 2) {
                    repeat = true;
                } else if (dealerChoice == -1) {
                    playerDamage += 1;
                } else if (dealerChoice==0){
                    repeat=false;
                }
                else {
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
        for (int g=0;g<8;g++){
            if (!repeat&&playerTurn||g==0||repeat2) {
                playerChoice = Player.probability(shells.get(g),g,shells);
                repeat2=false;
                if (playerChoice == 2) {
                    repeat2 = true;
                } else if (playerChoice == -1) {
                    dealerDamage+= 1;
                } else if (dealerChoice==0){
                    repeat2=false;
                }
                else {
                    playerDamage += 1;
                }
                repeat = false;
                playerTurn=false;
            }else{
                dealerChoice = Dealer.dealerAI(shells.get(g),g);
                repeat = false;
                if (dealerChoice == 2) {
                    repeat = true;
                } else if (dealerChoice == -1) {
                    playerDamage += 1;
                } else if (dealerChoice ==0){
                    repeat=false;
                }
                else {
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
        for (int g=0;g<8;g++){
            if (!repeat&&playerTurn||g==0||repeat2) {
                playerChoice = Player.probability(shells.get(g),g,shells);
                repeat2=false;
                if (playerChoice == 2) {
                    repeat2 = true;
                } else if (playerChoice == -1) {
                    dealerDamage+= 1;
                } else if (dealerChoice==0){
                    repeat2=false;
                }
                else {
                    playerDamage += 1;
                }
                repeat = false;
                playerTurn=false;
            }else{
                dealerChoice = Dealer.probability(shells.get(g),g,shells);
                repeat=false;
                if (dealerChoice == 2) {
                    repeat = true;
                } else if (dealerChoice == -1) {
                    playerDamage += 1;
                } else if (dealerChoice==0){
                    repeat=false;
                }
                else {
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
