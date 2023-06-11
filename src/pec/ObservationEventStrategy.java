package pec;

public class ObservationEventStrategy implements EventStrategy {
    private final Double tau;
    private final PEC pec;

    public ObservationEventStrategy(Double tau, PEC pec) {
        this.tau = tau;
        this.pec = pec;
    }

    @Override
    public void execute(int id, double tempo, Integer[] NumberEvents) {
        System.out.println("\nPresent instant\t" + tempo);
        System.out.println("Number of move events:\t" + NumberEvents[0]);
        System.out.println("Number of evaporation events:\t" + NumberEvents[1]);
        System.out.print("Top candidate cycles:\n");
        int size = this.pec.getTopCycles().size();
        int aux = 0, custo = 0;
        if (size != 0) {
            for (int i = 0; i < 5; i++, aux = 0) {
                System.out.print("{");
                for (Integer cycle : this.pec.getTopCycles()) {
                    aux++;
                    if (size == aux) {
                        System.out.print(cycle + "}:" + custo + "\n");
                    } else {
                        System.out.print(cycle + ",");
                    }
                }
            }
        } else {
            System.out.print("}\n");
        }
        aux = 0;
        System.out.print("Best Hamiltonian cycle:\n{");
        if (size != 0) {
                for (Integer cycle : this.pec.getTopCycles()) {
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
        if (tempo >= tau.intValue()) {
            this.pec.getPriorQueue().clear();
        }
    }
}
