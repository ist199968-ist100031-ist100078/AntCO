package pec;
import  antco.IColony;

public class EvaporationEventStrategy implements EventStrategy {
    private final Double mean;
    private final IColony colony;
    private final PEC pec;

    public EvaporationEventStrategy(Double mean, IColony colony, PEC pec) {
        this.mean = mean;
        this.colony = colony;
        this.pec = pec;
    }

    @Override
    public void execute(int id, double tempo, Integer[] NumberEvents) {
        NumberEvents[1]++;
        if (this.colony.triggerPheromoneDecay(id)) {
            this.pec.Addevent(tempo + this.pec.ExponentialTime(this.mean), "Evaporação", id);
        }
    }
}
