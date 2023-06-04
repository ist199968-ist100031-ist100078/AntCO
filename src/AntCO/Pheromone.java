package AntCO;

public class Pheromone {
    float value[][]; //pheromone value
    int maxvertex;

    public Pheromone(int max) {
        this.maxvertex = max;
        this.fvalue= new float[vertex][vertex];
        Arrays.fill(this.fvalue, 0);
    }

    public float getFvalue(int i, int j) {
        return this.fvalue[i][j];
    }

    public void decayFvalue(float rho) {
        for (int i=0; i<maxvertex; i++)
            for (int j=0; j<maxvertex; j++)
                    this.fvalue = fvalue-rho;
    }

    public void incrementFvalue(int i, int j, float gamma, int sigma-mu, int weight) {
        this.fvalue[i][j] += gamma*weight/sigma-mu;
    }

}
