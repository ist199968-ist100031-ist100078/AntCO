package AntCO ;
import package graph.IWeigthedGraph;
import java.util.ArrayList;
/*
Name: Colony
description: Colony class for Ant Colony Optimization
Date added: 05 Jun 2023
Last modified: 05 Jun 2023
 */
public class Colony {
    Pheromone pheromone;
    IWeigthedGraph graph;
    float pherovalue[][]; //pheromone value
    int maxvertex;
    int start;
    float gamma;
    float alpha;
    float beta;
    int maxantpop;
    Ant population[];
    int bestpath[];
    int bestpathlength;

    /*Constructors*/
    public Colony(int max, int start, float gamma, float alpha, float beta, int maxantpop){
        this.maxvertex = max;
        this.start = start;
        this.gamma = gamma;
        this.alpha = alpha;
        this.beta = beta;
        this.maxantpop = maxantpop;
        this.pheromone = new Pheromone(this.maxvertex, this);
        this.fvalue= new float[maxvertex][maxvertex];
        Arrays.fill(this.fvalue, 0);
        this.population = new Ant[this.maxantpop];

        for (int i=0; i<this.maxantpop; i++) {
            this.population[i] = new Ant(this.maxvertex, this.start, this.gamma, i, this);
        }

        this.bestpath = new int[this.maxvertex];
    }

    /*setter*/
    public void setGraph(IWeigthedGraph graph) {
        this.graph = graph;
    }

    /* Name: getCost
    input: i, j
    output: cost of edge (i,j)
    description: Simple getter for the cost of edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
    public int getCost(int i, int j) {
        return this.graph.getCost(i+1, j+1);
    }
    public int getAdj(int i, int j){
        return this.graph.NodeAdj(i+1, j+1);
    }
    /* Name: triggerAntMovement
    input: triggerid
    output: none
    description: trigger the movement of ant with id triggerid
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
     */
    public ArrayList<Integer> triggerAntMovement(int triggerid){
        boolean hamilton=this.population[triggerid].move();
        ArrayList<Integer> path = new ArrayList<>();
        if (hamilton){
            this.updateBestPath(this.population[triggerid].path, this.population[triggerid].sigma);
            for (int i=0; i<this.maxvertext; i++){
                if(this.pherovalue[this.population[triggerid].path[i]][this.population[triggerid].path[i+1]]==0)
                    path.add(Hash(this.population[triggerid].path[i],this.population[triggerid].path[i+1]));
            }
        }
        else{
            path.add(0);
        }
        return path;
    }

    /* Name: updateBestPath
    input: path, sigma
    output: none
    description: update the best path if the path is better than the current best path
     */

    public void updateBestPath(int path[], int sigma){
        if (sigma<this.bestpathlength){
            this.bestpath = path;
        }
    }
    /* Name: triggerPheromoneDecay
    input: hashededge, rho
    output: none
    description: trigger the decay of pheromone value of edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
    public boolean triggerPheromoneDecay(int hashededge,float rho){
        int[2] coords = this.unHash(hashededge);
        return this.pheromone.decayFvalue(coords[0], coords[1], rho);
    }
    /* Name: Hash
    input: i, j
    output: hash value
    description: Hash function for edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
    public int Hash(int i, int j){
        return (i+1)*this.maxvertex + (j+1);
    }
    /* Name: unHash
    input: hash
    output: i, j
    description: unHash function for edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
    public int[2] unHash(int hash){
        int i = hash/this.maxvertex - 1;
        int j = hash%this.maxvertex - 1;
        return new int[]{i,j};
    }

}
