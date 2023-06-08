package pec;

import java.util.Random;

public class EvaporationEventStrategy implements EventStrategy {
    Double mean;
    IColony colony;

    public EvaporationEventStrategy(Double mean, IColony colony) {
        this.mean = mean;
        this.colony = colony;
    }

    @Override
    public Double execute(Double id, Integer[] NumberEvents) {
        NumberEvents[1]++;
        //Querem que faça aqui o decaimento
        //decayFvalue true(fazer algo) or false
        Random rand = new Random();
        if (decayFvalue(rand.nextInt(0,2))) {
            double u = rand.nextDouble();
            return -(this.mean) * Math.log(1 - u);
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
    public boolean decayFvalue(int a)
    {
        return a == 1;
    }
}
