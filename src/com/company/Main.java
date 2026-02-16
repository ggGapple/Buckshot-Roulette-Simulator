package com.company;

import java.util.ArrayList;

import static com.company.Simulation.simulate;

public class Main {
    public static void run(String name, Strategies.Strategy p1, Strategies.Strategy p2, int trials) {
        int playerTotal = 0;
        int dealerTotal = 0;

        for (int i = 0; i < trials; i++) {
            int result = simulate(p1, p2);
            playerTotal += result;
            dealerTotal += (4-result);
        }

        System.out.println("=== " + name + " ===");
        System.out.println("Player Average Damage: " + playerTotal / (double) trials);
        System.out.println("Dealer Average Damage: " + dealerTotal / (double) trials);
        System.out.println();
    }


    public static void main(String[] args) {
        int trials = 1000000; // big sample for smooth averages

        run("Always vs Always", Strategies::alwaysShoot, Strategies::alwaysShoot, trials);

        run("Always vs Dealer (Random 50/50)", Strategies::alwaysShoot, Strategies::dealerAI, trials);

        run("Always vs Probability", Strategies::alwaysShoot, Strategies::probability, trials);

        run("Probability vs Dealer", Strategies::probability, Strategies::dealerAI, trials);

        run("Probability vs Probability", Strategies::probability, Strategies::probability, trials);
    }
}
