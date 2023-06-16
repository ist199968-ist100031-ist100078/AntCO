package main;

import antco.Colony;
import graph.FileStrategy;
import graph.Graph;
import graph.Generator;

import graph.RandomStrategy;
import pec.PEC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**Main class*/
public class Main {
    /**
     * Main method
     *
     * @param args input command line arguments
     */
    public static void main(String[] args) {
        String[] params = new String[]{"-r", "-f"};
        if (args.length < 2 || (!args[0].equals(params[0]) && !args[0].equals(params[1])) || (args[0].equals(params[0]) && args.length != 12)) {
            System.out.println("ERROR - Incorrect Arguments");
            return;
        }
        int numNodes, maxWeight, nest;
        float[] inParams = new float[8];//alpha, beta, delta, eta, ro, gamma, nu, tau

        Graph graph;
        Generator gen = new Generator();

        if (args[0].equals(params[0])) {
            try {
            numNodes = Integer.parseInt(args[1]);
            maxWeight = Integer.parseInt(args[2]);
            nest = Integer.parseInt(args[3]);
            if(numNodes < 2 || maxWeight < 1 || nest < 1 || nest > numNodes){
                System.out.println("ERROR - Incorrect Value For Arguments");
                return;
            }
                for (int i = 0; i < 8; i++) {
                    inParams[i] = Float.parseFloat(args[i + 4]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Input arguments have the wrong format: " + e);
                return;
            }
            graph = new Graph(numNodes);

            gen.setGenerationStrat(new RandomStrategy());
            gen.generate(graph, maxWeight);

        }else {
            try {
                File f = new File(args[1]);
                Scanner reader = new Scanner(f);

                //Read Int parameters
                try {
                numNodes = reader.nextInt();
                nest = reader.nextInt();
                if(numNodes < 2 || nest < 1 || nest > numNodes){
                    System.out.println("ERROR - Incorrect Value For Arguments");
                    return;
                }
                //Read float parameters
                    for (int i = 0; i < 8; i++) {
                        inParams[i] = reader.nextFloat();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Values have wrong formats: " + e);
                    reader.close();
                    return;
                } catch (NoSuchElementException e) {
                    System.out.println("Not enough parameters in the file: " + e);
                    reader.close();
                    return;
                }
                graph = new Graph(numNodes);

                gen.setGenerationStrat(new FileStrategy());
                gen.generate(graph, reader);

            } catch (FileNotFoundException e) {
                System.out.println("Exception: " + e);
                return;
            }

        }
        if(inParams[0] <= 0 || inParams[1] < 0 || inParams[2] <= 0 || inParams[3] < 0 || inParams[4] < 0 || inParams[5] < 0 || inParams[6] <= 0 || inParams[7] <= 0){
            System.out.println("ERROR - Incorrect Value For Arguments");
            return;
        }
        System.out.println("Input parameters:\n\t" + numNodes + ": number of nodes in the graph\n\t" + nest + ": the nest node\n\t" + inParams[0] + ": alpha, ant move event");
        System.out.println("\t" + inParams[1] + ": beta, ant move event\n\t" + inParams[2] + ": delta, ant move event\n\t" + inParams[3] + ": eta, pheromone evaporation event");
        System.out.println("\t" + inParams[4] + ": rho, pheromone evaporation event\n\t" + inParams[5] + ": gamma, pheromone level\n\t" + (int) inParams[6] + ": nu, ant colony size\n\t" + (int) inParams[7] + ": tau, final instant");
        graph.displayMat();
        PEC a = new PEC(inParams[3], inParams[2], new Colony(nest, inParams[5], inParams[0], inParams[1], (int) inParams[6], graph, inParams[4]), (int) inParams[6], inParams[7], numNodes);
        while (!a.isEmpty()) {
            a.getFirstElement();
        }
    }
}
