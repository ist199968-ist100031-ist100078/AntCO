package AntCO;

/* Name: Pheromone
description: Pheromone class for Ant Colony Optimization
Date added: 03 Jun 2023
Last modified: 04 Jun 2023
*/
public class Pheromone {
    int maxvertex;
    float rho;
    Colony antcolony;

    /*Constructor*/
    public Pheromone(int max, Colony colony, float rho) {
        this.maxvertex = max;
        this.antcolony = colony;
        this.rho = rho;
    }

    /*getter*/
    public float getFvalue(int i, int j) {
        return this.antcolony.pherovalue[i][j];
    }

    /* Name: decayFvalue
    input: i, j, rho
    output: boolean
    description: decay target pheromone value by rho
    Date added: 03 Jun 2023
    Last modified: 04 Jun 2023
    */
    public boolean decayFvalue(int i, int j) {
        boolean positive = true;
        this.antcolony.pherovalue[i][j] -= this.rho;
        this.antcolony.pherovalue[j][i] -= this.rho;
        if (this.antcolony.pherovalue[i][j] <= 0 || this.antcolony.pherovalue[j][i] <= 0) {
            this.antcolony.pherovalue[i][j] = 0;
            positive = false;
        }
        return positive;
    }

    /* Name: incrementFvalue
    input: i, j, gamma, sigma, weight
    output: none
    description: increment the pheromone value of edge (i,j)
    Date added: 03 Jun 2023
    Last modified: 04 Jun 2023
     */
    public void incrementFvalue(int i, int j, float gamma, int sigma, int weight) {
        this.antcolony.pherovalue[i][j] += gamma * weight / sigma;
        this.antcolony.pherovalue[j][i] += gamma * weight / sigma;
    }

}
