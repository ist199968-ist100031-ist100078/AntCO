package pec;

public class EEventStrategy implements EventStrategy {
    private Double mean;
    @Override
    public Double execute() {
        //Querem que faça aqui o decaimento // não, chama o decaimento da feromona aka dar trigger no AColony
        mean = 2.0; //?????
        Double lambda = 1/mean;
        return lambda*(Math.exp(-lambda)); //Multiplicar por t?
    }
}
