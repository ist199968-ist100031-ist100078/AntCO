import antco.Colony;
import graph.FileStrategy;
import graph.Graph;
import graph.Generator;

import graph.RandomStrategy;
import pec.PEC;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
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
            numNodes = Integer.parseInt(args[1]);
            maxWeight = Integer.parseInt(args[2]);
            nest = Integer.parseInt(args[3]);
            for (int i = 0; i < 8; i++) {
                inParams[i] = Float.parseFloat(args[i + 4]);
            }
            graph = new Graph(numNodes);

            gen.setGenerationStrat(new RandomStrategy());
            gen.generate(graph, maxWeight);

        } else {
            try {
                File f = new File(args[1]);
                Scanner reader = new Scanner(f);

                //Read Int parameters
                numNodes = reader.nextInt();
                nest = reader.nextInt();
                //System.out.println(numNodes + " " + nest);

                //Read float parameters
                try {
                    for (int i = 0; i < 8; i++) {
                        inParams[i] = reader.nextFloat();
                        System.out.println(i + ":" + inParams[i]);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Float has wrong format: " + e + "\n Format should be 'x.y'");
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
        /*graph.displayGraph();
        graph.displayAdj();
        System.out.println("Graph: ");*/
        /*graph.displayMat();*/
        PEC a = new PEC(inParams[3], inParams[2], new Colony(numNodes,nest,inParams[5], inParams[0], inParams[1], (int)inParams[6], graph, inParams[4]), (int)inParams[6], (double) inParams[7], numNodes);
        while (!a.isEmpty()) {
            a.getFirstElement();
        }
    }
}
