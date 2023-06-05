package AntCO;

/* Name: Ant
description: Ant class for Ant Colony Optimization
Date added: 04 Jun 2023
Last modified: 04 Jun 2023
*/
public class Ant {
    AntColony antcolony;
    int path[];
    int pathlength;
    int maxvertex;
    int start;
    int end;
    int current;
    int next;
    int visited[];
    int unvisited[];
    int unvisitedlength;
    int visitedlength;
    int sigma; /*sum of all weights*/
    int weight;
    float rho;


    /*Constructor*/
    public Ant(int max, int start, int end, float gamma, float rho, AntColony antcolony) {
        this.maxvertex = max;
        this.start = start;
        this.end = end;
        this.rho = rho;
        this.antcolony = antcolony;
        this.path = new int[maxvertex];
        this.visited = new int[maxvertex];
        this.unvisited = new int[maxvertex];
        this.pathlength = 0;
        this.visitedlength = 0;
        this.unvisitedlength = maxvertex;
        this.sigma = 0;
        this.weight = 0;
        this.current = start;
        this.next = -1;
        for (int i=0; i<maxvertex; i++) {
            this.visited[i] = -1;
            this.unvisited[i] = i;
        }
        this.visited[0] = start;
        this.unvisited[start] = -1;
        this.unvisitedlength--;
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
        this.visited[this.visitedlength] = this.next;
        this.visitedlength++;
        this.unvisited[this.next] = -1;
        this.unvisitedlength--;
        this.sigma +=this.antcolony.getCost(this.current, this.next) ;
        this.current = this.next;
    }

    /* Name: pheromonoize
    input: none
    output: none
    description: pheromonize all edges in the stored path
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
    public void pheromonize(){
        for (int i=0; i<this.pathlength-1; i++) {
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
        this.visitedlength = 0;
        this.unvisitedlength = this.maxvertex;
        this.sigma = 0;
        this.weight = 0;
        this.current = this.start;
        this.next = -1;
        for (int i=0; i<maxvertex; i++) {
            this.visited[i] = -1;
            this.unvisited[i] = i;
        }
        this.visited[0] = this.start;
        this.unvisited[this.start] = -1;
        this.unvisitedlength--;
    }
}
