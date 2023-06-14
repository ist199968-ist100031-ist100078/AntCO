package antco;

import java.util.ArrayList;
import java.util.Random;

/** Ant Class for Ant Colony Optimization
* @since 04-Jun-2023
*/
public class Ant {
    /**Colony to which the Ant belongs*/	
    private final Colony antcolony;
    /**Path taken by Ant*/
    private final ArrayList<Integer> path;
    /**Nest Node aka From which to start exploring*/
    private final int start;
    /**Node in which the Ant is currently in*/
    private int current;
    /**Node to which the Ant has chosen to travel to*/
    private int next;
    /**Sum of the weight of the edges travelled*/
    private int sigma; 


    /**
     * Public Constructor for class Ant
     * @param start nest vertex
     * @param antcolony Colony to which the Ant belongs to
     */
    public Ant(int start, Colony antcolony) {
        this.start = start;
        this.antcolony = antcolony;
        this.path = new ArrayList<>();
        this.sigma = 0;
        this.current = start;
        this.next = -1;
        this.path.add(start);
    }
	/** Public getter for the path attribute
	 * @return Current Path of the Ant
	 *
	 */
    public ArrayList<Integer> getPath() {
        return path;

    }
	/**Public getter for the sigma attribute
	 * @return Current sum of cost of edges travelled by Ant
	 */
    public int getSigma() {
        return sigma;
    }

    /** Move Ant to adjacent node and register such move
    *@return If Ant has completed a Hamilton cycle (back to the nest) 
    *@since 04-Jun-2023
    */
    public boolean move() {
        boolean hamilton = false;
        this.next = this.selectNext(this.current);
        while (this.path.get(this.path.size() - 1) == this.next) { //Caso em que o next é um nó repetido
            current = this.path.get(this.path.size() - 1);
            this.next = this.selectNext(this.next);
        }
        this.path.add(this.next);
        this.sigma += this.antcolony.getGraph().getCost(this.current, this.next);
        this.current = this.next;
        if (this.current == this.start && this.path.size() == this.antcolony.getMaxVertex() + 1) {
            hamilton = true;
        }
        return hamilton;
    }

    /** Select the next node to move into using a arithmetic expression, with loop detection
     * @param current Node
     * @return Node to move into next
     * @since 10-Jun-2023
     */
    public int selectNext(int current) {
        int next = -1;
        double aux;
        float alpha = this.antcolony.getAlpha();
        float beta = this.antcolony.getBeta();
        double abs_prob;
        ArrayList<Integer> possible = this.antcolony.getAdj(current);


        //Remove all already visited nodes from adjacency list
        for (Integer n : this.path) {
            for (Integer p : possible) {
                if (p.equals(n - 1)) {
                    possible.remove(possible.indexOf(p));
                    break;
                }
            }
        }

        ArrayList<Double> prob_arr = new ArrayList<>();
        double sum = 0;
        Random rand = new Random();
        aux = rand.nextDouble(); //random number for probabilities

        if (possible.size() > 0) {
            for (int p : possible) {
                //Cijk
                abs_prob = (alpha + this.antcolony.getPheromone().getFvalue(current - 1, p));
                abs_prob /= (beta + this.antcolony.getGraph().getCost(current, p + 1));
                prob_arr.add(abs_prob + sum);

                sum += abs_prob; //Ci
            }

            for (double p : prob_arr) {
                next++;
                if (Double.compare(aux, p / sum) < 0) {
                    next = possible.get(next) + 1;
                    break;
                }
            }
        } else { //If there are no not yet visited nodes in adjacency list
            possible = this.antcolony.getAdj(current);
            sum = possible.size();
            for (int i = 1; i <= (int) sum; i++) {
                if (aux < i / sum) {
                    next = possible.get(i - 1) + 1;
                    break;
                }
            }
            if (this.path.size() == this.antcolony.getMaxVertex() && next == this.start) {
                return next;
            }

            int idx;
            idx = this.path.indexOf(next);
            int p_anterior = this.path.get(idx);
            for (Integer p : path.subList(idx + 1, path.size())) {
                this.sigma -= this.antcolony.getGraph().getCost(p_anterior, p);
                p_anterior = p;
            }
            this.path.subList(idx + 1, this.path.size()).clear(); //remove all elements after idx
        }
        return next;
    }

    /** Reset Ant (for after completing a Hamilton Cycle) 
     * @since 04-Jun-2023
    */
    public void reset() {
        this.sigma = 0;
        this.current = this.start;
        this.next = -1;
        this.path.clear();
        this.path.add(start);
    }

    /** Pheromonize all edges travelled (for after completing a Hamilton Cycle)
     * @since 04-Jun-2023
    */
    public void pheromonize() {
        int i;
        for (int p : path.subList(0, path.size() - 1)) {
            i = path.indexOf(p) + 1;
            this.antcolony.getPheromone().incrementFvalue(p - 1, path.get(i) - 1, sigma);
        }
    }
}
