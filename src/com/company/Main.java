package com.company;


import static com.company.Simulation.simulate;

public class Main {
    public static void run(String name, Strategies.Strategy p1, Strategies.Strategy p2, int trials) {
        double playerTotal = 0;
        double dealerTotal = 0;
        double wins = 0;

        double loopsTot=0;

        for (int i = 0; i < trials; i++) {
            int[] results = simulate(p1,p2);
            wins +=results[0];
            double loops = results[3];
            double playerDamage = results[1]/loops;
            double dealerDamage = results[2]/loops;
            loopsTot += loops;
            playerTotal += playerDamage;
            dealerTotal += dealerDamage;
        }

        System.out.println("=== " + name + " ===");
        System.out.println("Finished in "+loopsTot/trials+ " loops on average");
        System.out.println("Player Winrate: "+String.format("%.2f",wins/trials*100)+"% ("+wins/trials+")");
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

        run("Aggressive vs Probability", Strategies::aggressive, Strategies::probability, trials);

        run("Aggressive vs Conservative", Strategies::aggressive,
                Strategies::conservative, trials);

        run("Probability vs Inverse Probability", Strategies::probability,
                Strategies::inverseProbability, trials);

        run("Probability vs Alternating", Strategies::probability,
                Strategies::alternating, trials);

        run("Always vs Cheater", Strategies::alwaysShoot,
                Strategies::cheater, trials);
    }
}
