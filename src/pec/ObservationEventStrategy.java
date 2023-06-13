package pec;

import antco.IColony;

import java.util.ArrayList;

public class ObservationEventStrategy implements EventStrategy {
    private final Double tau;
    private final PEC pec;
    private final IColony colony;

    public ObservationEventStrategy(Double tau, PEC pec, IColony colony) {
        this.tau = tau;
        this.pec = pec;
        this.colony = colony;
    }

    @Override
    public void execute(int id, double tempo, Integer[] NumberEvents) {
        System.out.println("\nObservation " + id+ ":");
        System.out.println("\tPresent instant: " + tempo);
        System.out.println("\tNumber of move events: " + NumberEvents[0]);
        System.out.println("\tNumber of evaporation events: " + NumberEvents[1]);
        System.out.println("\tTop candidate cycles:");
        ArrayList<Integer> path;
        int aux = 0, custo, size;
        for (int i = 1; i <= 5; i++, aux = 0) {
            path = this.colony.getBestPath(i);
            size = path.size();
            if (size != 0) {
                System.out.print("\t\t{");
                custo = this.colony.getBestCost(i);
                for (Integer cycle : path) {
                    aux++;
                    if (size == aux) {
                        System.out.print(cycle + "}:" + custo + "\n");
                    } else {
                        System.out.print(cycle + ",");
                    }
                }
            }
        }
        System.out.print("\tBest Hamiltonian cycle:\n\t\t{");
        path = this.colony.getBestPath(0);
        size = path.size();
        if (size != 0) {
            custo = this.colony.getBestCost(0);
            for (Integer cycle : path) {
                aux++;
                if (size == aux) {
                    System.out.print(cycle + "}:" + custo + "\n");
                } else {
                    System.out.print(cycle + ",");
                }
            }
        } else {
            System.out.print("}\n");
        }
    }
}
