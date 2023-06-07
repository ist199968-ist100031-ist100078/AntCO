package AntCO;

/* Name: Pheromone
description: Pheromone class for Ant Colony Optimization
Date added: 03 Jun 2023
Last modified: 04 Jun 2023
*/
public class Pheromone {
    int maxvertex;
    Colony antcolony;
    /*Constructor*/
    public Pheromone(int max, Colony colony) {
        this.maxvertex = max;
        this.antcolony = colony;
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
    public boolean decayFvalue(int i, int j, float rho) {
        boolean negative = false;
        this.antcolony.pherovalue[i][j] = this.antcolony.pherovalue[i][j]-rho;
        this.antcolony.pherovalue[j][i] = this.antcolony.pherovalue[j][i]-rho;
        if (this.antcolony.pherovalue[i][j] < 0 || this.antcolony.pherovalue[j][i] < 0) {
            this.antcolony.pherovalue[i][j] = 0;
            negative = true;
        }
        return negative;
    }

    /* Name: incrementFvalue
    input: i, j, gamma, sigma, weight
    output: none
    description: increment the pheromone value of edge (i,j)
    Date added: 03 Jun 2023
    Last modified: 04 Jun 2023
     */
    public void incrementFvalue(int i, int j, float gamma, int sigma, int weight) {
        this.getFvalue(i,j) += gamma*weight/sigma;
        this.getFvalue(j,i) += gamma*weight/sigma;
    }

}
