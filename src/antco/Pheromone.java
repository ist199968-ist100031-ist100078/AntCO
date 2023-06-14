package antco;

import java.util.ArrayList;

/** Pheromone class for Ant Colony Optimization
 * @since 03-Jun-2023
 * @see antco.Colony
*/
public class Pheromone {

    private final float rho;
    private Colony antcolony;
    private final float gamma;
    private final int maxWeight; /*can we change this?*/
    private final float[][] pherovalue;
    /** Public constructor for Pheromone Class
     * @param colony Colony to Which the pheromone refers to
     * @param rho rho paramenter for pheromone decay
     * @param gamma parameter for pheromone increment
     * @since 03-Jun-2023
     */
    public Pheromone(Colony colony, float rho, float gamma){
        this.antcolony = colony;
        this.rho = rho;
        maxWeight = colony.getGraph().getTotWeight();
	this.pherovalue = new float[colony.getMaxVertex()][colony.getMaxVertex()];
        this.gamma = gamma;
    } 

    /**
     * Public getter for pheromone trail in edge of nodes i and j
     * @param i a node
     * @param j other node connected to j
     * @return pheromone trail in (i,j)
     */
    public float getFvalue(int i, int j) {
        return this.pherovalue[i][j];
    }
    
    /**
     * Public setter for pheromone trail in edge of nodes i and j
     * @param i a node
     * @param j other node connected to j
     * @param value new pheromone value in edge
     * @since 04-Jun-2023
     */

    public void setFvalue(int i, int j, float value) {
        this.pherovalue[i][j]=value;
	    this.pherovalue[j][i]=value;
    }

    /** Decay pheromone value of edge that connects node i an j
     * @param i ID of node
     * @param j ID of node connected to i
     * @return is positive/negative pheromone information
     * @since 03-Jun-2023
    */
    public boolean decayFvalue(int i, int j) {
        boolean positive = true;
        this.setFvalue(i,j,this.getFvalue(i,j)-this.rho);
        if (this.getFvalue(i,j) <= 0 || this.getFvalue(j,i) <= 0) {
            this.setFvalue(i,j, 0.0F);
            positive = false;
        }
        return positive;
    }

    /** Increment pheromone value of edge that connects node i an j after completing a hamilton cycle
     * @param i ID of node
     * @param j ID of node connected to i
     * @param sigma total cost of hamilton cycle
     * @since 03-Jun-2023
    */

    public void incrementFvalue(int i, int j, int sigma) {
        this.setFvalue(i,j,this.getFvalue(i,j) + (this.gamma * this.maxWeight) / sigma);
    }
}
