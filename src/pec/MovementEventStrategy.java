package pec;
import  AntCO.IColony;
import java.util.ArrayList;

public class MovementEventStrategy implements EventStrategy {
    private final Double meanMovimento;
    private final Double meanEvaporacao;
    private final IColony colony;
    private final PEC pec;

    public MovementEventStrategy(Double meanMovimento, Double meanEvaporacao, IColony colony, PEC pec) {
        this.meanMovimento = meanMovimento;
        this.meanEvaporacao = meanEvaporacao;
        this.colony = colony;
        this.pec = pec;
    }

    @Override
    public void execute(int id, double tempo, Integer[] NumberEvents) {
        NumberEvents[0]++;
        ArrayList<Integer> list = this.colony.triggerAntMovement(id);
        double time = this.pec.ExponentialTime( this.meanMovimento * colony.getCost(colony.getIdEdge(id)));
        for (Integer i : list) {
            if(i != 0){
                this.pec.Addevent(tempo + time + this.pec.ExponentialTime(this.meanEvaporacao), "Evaporação", i);
            }
        }
        this.pec.Addevent(tempo + time, "Movimento", id);
    }
}
