package AntCO;

/* Name: Pheromone
description: Pheromone class for Ant Colony Optimization
Date added: 03 Jun 2023
Last modified: 04 Jun 2023
*/
public class Pheromone {
    float fvalue[][]; //pheromone value
    int maxvertex;

    /*Constructor*/
    public Pheromone(int max) {
        this.maxvertex = max;
        this.fvalue= new float[maxvertex][maxvertex];
        Arrays.fill(this.fvalue, 0);
    }
    /*getter*/
    public float getFvalue(int i, int j) {
        return this.fvalue[i][j];
    }

    /* Name: decayFvalue
    input: rho
    output: none
    description: decay the all pheromone values by rho
    Date added: 03 Jun 2023
    Last modified: 04 Jun 2023
    */
    public void decayFvalue(float rho) {
        for (int i=0; i<maxvertex; i++) {
            for (int j = 0; j < maxvertex; j++) {
                this.fvalue[i][j] = fvalue[i][j] - rho;
                this.fvalue[j][i] = fvalue[j][i] - rho;
            }
        }
    }

    /* Name: incrementFvalue
    input: i, j, gamma, sigma, weight
    output: none
    description: increment the pheromone value of edge (i,j)
    Date added: 03 Jun 2023
    Last modified: 04 Jun 2023
     */
    public void incrementFvalue(int i, int j, float gamma, int sigma, int weight) {
        this.fvalue[i][j] += gamma*weight/sigma;
        this.fvalue[j][i] += gamma*weight/sigma;
    }

}
