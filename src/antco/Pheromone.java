package antco;

import java.util.ArrayList;

/* Name: Pheromone
description: Pheromone class for Ant Colony Optimization
Date added: 03 Jun 2023
Last modified: 04 Jun 2023
*/
public class Pheromone {

    private final float rho;
    private Colony antcolony;
    private final float gamma;
    private final int maxWeight;

    /*Constructor*/
    public Pheromone(Colony colony, float rho, float gamma){
        this.antcolony = colony;
        this.rho = rho;
        maxWeight = colony.getGraph().getTotWeight();
        this.gamma = gamma;
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
            this.antcolony.setFvalue(i,j, 0.0F);
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
    public void incrementFvalue(int i, int j, int sigma) {
        this.antcolony.setFvalue(i,j,this.antcolony.getFvalue(i,j) + (this.gamma * this.maxWeight) / sigma);
    }
}
