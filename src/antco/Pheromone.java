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
        this.gamma = gamma;
    } 

    /** Decay pheromone value of edge that connects node i an j
     * @param i ID of node
     * @param j ID of node connected to i
     * @return is positive/negative pheromone information
     * @since 03-Jun-2023
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

    /** Increment pheromone value of edge that connects node i an j after completing a hamilton cycle
     * @param i ID of node
     * @param j ID of node connected to i
     * @param sigma total cost of hamilton cycle
     * @since 03-Jun-2023
    */

    public void incrementFvalue(int i, int j, int sigma) {
        this.antcolony.setFvalue(i,j,this.antcolony.getFvalue(i,j) + (this.gamma * this.maxWeight) / sigma);
    }
}
