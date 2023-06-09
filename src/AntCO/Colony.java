package AntCO ;
import  graph.IWeightedGraph;
import java.util.ArrayList;
/*
Name: Colony
description: Colony class for Ant Colony Optimization
Date added: 05 Jun 2023
Last modified: 08 Jun 2023
 */
public class Colony  implements IColony {
    Pheromone pheromone;
    IWeightedGraph graph;
    float[][] pherovalue; //pheromone value
    int maxvertex;
    int start;
    float gamma;
    float alpha;
    float beta;
    float rho;
    int maxantpop;
    Ant[] population;
    BestPath[] bestpath = new BestPath[5];

    /*Constructors*/
    public Colony(int max, int start, float gamma, float alpha, float beta, int maxantpop) {
        this.maxvertex = max;
        this.start = start;
        this.gamma = gamma;
        this.alpha = alpha;
        this.beta = beta;
        this.maxantpop = maxantpop;
        this.pheromone = new Pheromone(this.maxvertex, this);
        this.pherovalue = new float[maxvertex][maxvertex];
        this.population = new Ant[this.maxantpop];

        for (int i = 0; i < this.maxantpop; i++) {
            this.population[i] = new Ant(this.maxvertex, this.start, i, this);
        }
        for (BestPath bp : this.bestpath) {
            bp = new BestPath(-1);
        }
}


        /*setter*/
        public void setGraph (IWeightedGraph graph){
            this.graph = graph;
        }

    /* Name: getCost
    input: edge
    output: cost of edge (i,j)
    description: Simple getter for the cost of edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
        public int getCost ( int hashedge){
            int[] hash =unHash(hashedge);
            return this.graph.getCost(hash[0] + 1, hash[1] + 1);
        }
        public ArrayList<Integer> getAdj ( int target){
            return this.graph.nodeAdj(target + 1);
        }
    /* Name: triggerAntMovement
    input: triggerid
    output: none
    description: trigger the movement of ant with id triggerid
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
     */
        public ArrayList<Integer> triggerAntMovement ( int triggerid){
            boolean hamilton = this.population[triggerid].move();
            ArrayList<Integer> path = new ArrayList<>();
            if (hamilton) {
                this.population[triggerid].pheromonize();
                this.updateBestPath(this.population[triggerid].path, this.population[triggerid].sigma);
                for (int i = 0; i < this.population[triggerid].pathlength; i++) {
                    if (this.pherovalue[this.population[triggerid].path[i]][this.population[triggerid].path[i + 1]] == 0)
                        path.add(Hash(this.population[triggerid].path[i], this.population[triggerid].path[i + 1]));
                }
            } else {
                path.add(0);
            }
            return path;
        }

    /* Name: updateBestPath
    input: path, sigma
    output: none
    description: update the best path if the path is better than the current best path
     */

        public void updateBestPath (int[] path, int sigma){
            for (BestPath bp : this.bestpath) {
                if (sigma < bp.cost) {
                    bp.path = path;
                    bp.cost = sigma;
                    break;
                }
            }
        }
    /* Name: triggerPheromoneDecay
    input: hashededge, rho
    output: none
    description: trigger the decay of pheromone value of edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
        public boolean triggerPheromoneDecay ( int hashededge){
            int[]coords = this.unHash(hashededge);
            return this.pheromone.decayFvalue(coords[0], coords[1], this.rho);
        }
    /* Name: Hash
    input: i, j
    output: hash value
    description: Hash function for edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
        public int Hash ( int i, int j){
            return (i + 1) * this.maxvertex + (j + 1);
        }
    /* Name: unHash
    input: hash
    output: i, j
    description: unHash function for edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
        public int[] unHash( int hash){
            int i = hash / this.maxvertex - 1;
            int j = hash % this.maxvertex - 1;
            return new int[]{i, j};
        }
    }


/*NESTED
Name: BestPath
description: BestPath class for Ant Colony Optimization
Date added: 07 Jun 2023
*/

class BestPath{
        int[] path;
        int cost;
        public BestPath(int cost){
            this.cost = cost;
        }
}
