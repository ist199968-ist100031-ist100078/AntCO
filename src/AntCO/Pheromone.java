package AntCO;

/* Name: Pheromone
description: Pheromone class for Ant Colony Optimization
Date added: 03 Jun 2023
Last modified: 04 Jun 2023
*/
public class Pheromone {
    private int maxvertex;
    private float rho;
    private Colony antcolony;

    /*Constructor*/
    public Pheromone(int max, Colony colony, float rho) {
        this.maxvertex = max;
        this.antcolony = colony;
        this.rho = rho;
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
        this.antcolony.setFvalue(i,j,this.antcolony.getFvalue(i,j)-this.rho);
        if (this.antcolony.getFvalue(i,j) <= 0 || this.antcolony.getFvalue(j,i) <= 0) {
            this.antcolony.setFvalue(i,j) = 0;
	    this.antcolony.setFvalue(i,j) = 0;
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
        this.antcolony.setFvalue(i,j,this.antcolony.getFvalue(i,j) + gamma * weight / sigma);
	return;
    }

}
