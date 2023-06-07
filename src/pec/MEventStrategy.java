package pec;

public class MEventStrategy implements EventStrategy {
    private final Double mean;

    public MEventStrategy(Double mean) {
        this.mean = mean;
    }
    @Override
    public Double execute(int id){
        //move_ant

        //criar um novo movimento da formiga
        //criar um evento de decay caso a lista seja != 0;
        return this.mean;
    }
}
