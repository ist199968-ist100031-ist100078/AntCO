package pec;

import java.util.Random;

public class MEventStrategy implements EventStrategy {
    Double mean;

    public MEventStrategy(Double mean) {
        this.mean = mean;
    }

    @Override
    public Double execute(int id){
        //move_ant
        Double lambda = 1/(mean);
        Random rand = new Random();
        double u = rand.nextDouble();
        return -(1/lambda) * Math.log(1 - u);
        //criar um novo movimento da formiga
        //criar um evento de decay caso a lista seja != 0;
        //return mean;
    }
}
