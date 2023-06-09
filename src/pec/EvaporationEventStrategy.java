package pec;
import  AntCO.IColony;
import java.util.Random;

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
        Random rand = new Random(); //apagar depois
        if (this.colony.triggerPheromoneDecay(rand.nextInt(0, 2))) {
            this.pec.Addevent(tempo + this.pec.ExponentialTime(this.mean), "Evaporação", id);
        }
    }
}
