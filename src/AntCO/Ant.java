package AntCO;
import java.util.ArrayList;

/* Name: Ant
description: Ant class for Ant Colony Optimization
Date added: 04 Jun 2023
Last modified: 04 Jun 2023
*/
public class Ant {
    Colony antcolony;
    int id;
    int path[];
    int pathlength;
    int maxvertex;
    int start;
    int current;
    int next;
    int sigma; /*sum of all weights*/
    int weight;


    /*Constructor*/
    public Ant(int max, int start, int end, float gamma, int id, AntColony antcolony) {
        this.id = id;
        this.maxvertex = max;
        this.start = start;
        this.antcolony = antcolony;
        this.path = new int[this.maxvertex];
        this.sigma = 0;
        this.weight = 0;
        this.current = start;
        this.next = -1;
        this.pathlength = 0;
        this.path[0] = start;
    }

    /* Name: move
    input: none
    output: none
    description: move the ant to the next vertex and update stored path
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
    public void move() {
        this.next = this.selectNext(this.current);
        this.path[this.pathlength] = this.next;
        this.pathlength++;
        this.sigma +=this.antcolony.getCost(this.current, this.next) ;
        this.current = this.next;
    }

    public int selectNext(int current){
        int next = -1;
        float max = 0;
        float alpha = this.antcolony.alpha;
        float beta = this.antcolony.beta;
        int Arraylist<Integer> possible = this.antcolony.graph.nodeAdj(current);

        if (possible.size > 0) {
            /*Have to fix this. Had an EMERGENCY and had to left the work a meio */
            //Get probability array for all adjacent nodes
            for (int i = 0; i < possible.size; i++) {
                next = possible.get(i);
                abs_prob = (float) alpha / (beta + this.antcolony.getCost(current, next));
                prob_arr.add(abs_prob);
                sum+=prob_arr.get(i);
            }
            for (int i = 0; i < possible.size; i++) {
                prob_arr.set(i, prob_arr.get(i) / sum);
                //System.out.println("Probabilities: " + prob_arr.get(i));
            }

            sum = 0;
            aux = 0;
            for (int i = 0; i < possible.size; i++) {
                aux = prob_arr.get(i);
                prob_arr.set(i, aux + sum);
                sum+=aux;
            }

            aux = rand.nextFloat();
            for (int i = 0; i < sz; i++) {
                if (Float.compare(aux, prob_arr.get(i)) < 0) {
                    next = adj.get(i);
                    this.path.add(next);
                    this.p_cost += G.getCost(path.get(len), next);
                    len++;
                    break;
                }
            }
        }

    }

    /* Name: pheromonize
    input: none
    output: none
    description: pheromonize all edges in the stored path
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
    public void pheromonize(){
        for (int i=0; i<this.pathlength; i++)
            this.antcolony.pheromone.incrementFvalue(this.path[i], this.path[i+1], this.antcolony.gamma, this.sigma , this.antcolony.getCost(i,j));
    }

    /* Name: reset
    input: none
    output: none
    description: reset the ant to its initial state
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
    public void reset() {
        this.pathlength = 0;
        this.sigma = 0;
        this.weight = 0;
        this.current = this.start;
        this.next = -1;
        this.path[0] = this.start;

    }
}
