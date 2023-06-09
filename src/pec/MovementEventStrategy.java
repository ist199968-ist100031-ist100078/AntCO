package pec;

import java.util.ArrayList;
import java.util.Random;

public class MovementEventStrategy implements EventStrategy {
    Double meanMovimento;
    Double meanEvaporacao;
    IColony colony;

    PEC pec;

    public MovementEventStrategy(Double meanMovimento, Double meanEvaporacao, IColony colony, PEC pec) {
        this.meanMovimento = meanMovimento;
        this.meanEvaporacao = meanEvaporacao;
        this.colony = colony;
        this.pec = pec;
    }

    @Override
    public Double execute(Double id, Integer[] NumberEvents) {
        NumberEvents[0]++;
        ArrayList<Integer> list = this.colony.triggerAntMovement(id.intValue());
        for (Integer i : list) {
            if(i != 0){
                this.pec.Addevent(this.pec.ExponentialTime(this.meanEvaporacao * this.colony.getCost(i)), "Evaporação", i);
            }
        }
        return this.pec.ExponentialTime(this.meanMovimento * colony.getCost(1));
        //criar um evento de decay caso a lista seja != 0;
    }
}
