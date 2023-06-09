package pec;

import java.util.Random;

public class EvaporationEventStrategy implements EventStrategy {
    Double mean;
    IColony colony;
    PEC pec;

    public EvaporationEventStrategy(Double mean, IColony colony, PEC pec) {
        this.mean = mean;
        this.colony = colony;
        this.pec = pec;
    }

    @Override
    public Double execute(Double id, Integer[] NumberEvents) {
        NumberEvents[1]++;
        //decayFvalue true(fazer algo) or false
        Random rand = new Random(); //apagar depois
        if (this.colony.triggerPheromoneDecay(rand.nextInt(0,2))) {
            return this.pec.ExponentialTime(this.mean);
            /*int aux = 0;
            for (int x= 0; u > (1 - Math.exp(-this.lambda*x)); x++) {
                aux = x;
            }
            aux++;
            System.out.println(u + " " + (1 - Math.exp(-this.lambda*aux)));
            return (double) aux;*/
        }
        return -1.0; // Caso em que não há decaimento, porque já chegou a 0
    }

}
