package AntCO;

/* Name: Ant
description: Ant class for Ant Colony Optimization
Date added: 04 Jun 2023
Last modified: 04 Jun 2023
*/
public class Ant {
    Colony antcolony;
    int path[];
    int pathlength;
    int maxvertex;
    int start;
    int current;
    int next;
    int sigma; /*sum of all weights*/
    int weight;


    /*Constructor*/
    public Ant(int max, int start, int end, float gamma, float rho, AntColony antcolony) {
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
        this.next = this.selectNext();
        this.path[this.pathlength] = this.next;
        this.pathlength++;
        this.sigma +=this.antcolony.getCost(this.current, this.next) ;
        this.current = this.next;
    }

    /* Name: pheromonize
    input: none
    output: none
    description: pheromonize all edges in the stored path
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
    public void pheromonize(){
        for (int i=0; i<this.pathlength; i++) {
            this.antcolony.pheromone.incrementFvalue(this.path[i], this.path[i+1], this.antcolony.gamma, this.sigma , this.antcolony.getCost(i,j));
        }
    }

    /* Name: move
    input: none
    output: none
    description: move the ant to the next vertex and update stored path
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
