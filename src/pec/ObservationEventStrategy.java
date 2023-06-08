package pec;

public class ObservationEventStrategy implements EventStrategy {
    private final Double tau;

    public ObservationEventStrategy(Double tau) {
        this.tau = tau;
    }

    @Override
    public Double execute(Double tempo, Integer[] NumberEvents) {
        System.out.println("\nPresent istant:\t" + tempo);
        System.out.println("Number of move events:\t" + NumberEvents[0]);
        System.out.println("Number of evaporation events:\t" + NumberEvents[1]);
        System.out.println("Top candidate cycles:\tcycles");
        System.out.println("Best Hamiltonian cycle:\tbest\n");
        if(tempo >= tau.intValue()){
            return -2.0;
        }
        return -1.0;
    }
}
