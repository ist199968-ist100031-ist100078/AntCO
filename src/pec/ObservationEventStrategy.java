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
        System.out.println("\nPresent istant:\t" + tempo);
        System.out.println("Number of move events:\t" + NumberEvents[0]);
        System.out.println("Number of evaporation events:\t" + NumberEvents[1]);
        System.out.println("Top candidate cycles:\tcycles");
        System.out.println("Best Hamiltonian cycle:\tbest\n");
        if(tempo >= tau.intValue()){
            this.pec.getPriorQueue().clear();
        }
    }
}
