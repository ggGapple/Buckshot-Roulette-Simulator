package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        int[] values = new int[2];
        ArrayList<Integer> values2 = new ArrayList<Integer>();
        int[] values3 = new int[2];
        ArrayList<Integer> values4 = new ArrayList<Integer>();
        int[] values5 = new int[2];
        ArrayList<Integer> values6 = new ArrayList<Integer>();
        int[] values7 = new int[2];
        ArrayList<Integer> values8 = new ArrayList<Integer>();
        int[] values9 = new int[2];
        ArrayList<Integer> values10 = new ArrayList<Integer>();
        for (int w =0;w<10000;w++) {
            values2 = Simulation.alwaysShootVDealer();
            values[0] +=values2.get(0);
            values[1]+=values2.get(1);
            values4 = Simulation.alwaysShootMirror();
            values3[0] +=values4.get(0);
            values3[1]+=values4.get(1);
            values6 = Simulation.alwaysShootVProbability();
            values5[0] +=values6.get(0);
            values5[1]+=values6.get(1);
            values8 = Simulation.alwaysShootVProbability();
            values7[0] +=values8.get(0);
            values7[1]+=values8.get(1);
            values10=Simulation.probabilityVDealer();
            values9[0] +=values10.get(0);
            values9[1]+=values10.get(1);
        }
        System.out.println("Always shoot v. regular dealer");
        System.out.println("Player Damage Dealt: "+values[0]+"\nDealer Damage Dealt: "+values[1]);
        System.out.println("Average Player Damage Dealt: "+values[0]/10000.0+"\nAverage Dealer Damage Dealt: "+values[1]/10000.0+"\n");
        System.out.println("Always shoot v. mirror dealer");
        System.out.println("Player Damage Dealt: "+values3[0]+"\nDealer Damage Dealt: "+values3[1]);
        System.out.println("Average Player Damage Dealt: "+values3[0]/10000.0+"\nAverage Dealer Damage Dealt: "+values3[1]/10000.0+"\n");
        System.out.println("Always shoot v. probability dealer");
        System.out.println("Player Damage Dealt: "+values5[0]+"\nDealer Damage Dealt: "+values5[1]);
        System.out.println("Average Player Damage Dealt: "+values5[0]/10000.0+"\nAverage Dealer Damage Dealt: "+values5[1]/10000.0+"\n");
        System.out.println("Probability v. mirror dealer");
        System.out.println("Player Damage Dealt: "+values7[0]+"\nDealer Damage Dealt: "+values7[1]);
        System.out.println("Average Player Damage Dealt: "+ values7[0] /10000.0+"\nAverage Dealer Damage Dealt: "+values7[1]/10000.0+"\n");
        System.out.println("Probability v. regular dealer");
        System.out.println("Player Damage Dealt: "+values9[0]+"\nDealer Damage Dealt: "+values9[1]);
        System.out.println("Average Player Damage Dealt: "+ values9[0] /10000.0+"\nAverage Dealer Damage Dealt: "+values9[1]/10000.0+"\n");
    }
}
