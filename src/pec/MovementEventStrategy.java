package pec;

import java.util.Random;

public class MovementEventStrategy implements EventStrategy {
    Double mean;
    IColony colony;

    public MovementEventStrategy(Double mean, IColony colony) {
        this.mean = mean;
        this.colony = colony;
    }

    @Override
    public Double execute(Double id, Integer[] NumberEvents) {
        NumberEvents[0]++;
        //move_ant
        Random rand = new Random();
        double u = rand.nextDouble();
        return -(this.mean * colony.getcost()) * Math.log(1 - u);
        //criar um novo movimento da formiga
        //criar um evento de decay caso a lista seja != 0;
    }
}
