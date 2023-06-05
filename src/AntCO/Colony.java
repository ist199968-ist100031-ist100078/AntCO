package AntCO ;
import package graph.IWeigthedGraph;
/*
Name: Colony
description: Colony class for Ant Colony Optimization
Date added: 05 Jun 2023
Last modified: 05 Jun 2023
 */
public class Colony {
    Pheromone pheromone;
    IWeigthedGraph graph;
    int maxvertex;
    int start;
    float gamma;
    int maxantpop;
    Ant population[];
    int bestpath[];

    /*Constructors*/
    public Colony(int max, int start, float gamma, int maxantpop){
        this.maxvertex = max;
        this.start = start;
        this.gamma = gamma;
        this.maxantpop = maxantpop;
        this.pheromone = new Pheromone(this.maxvertex);
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
        return this.graph.getCost(i, j);
    }

    public void triggerAntMovement(int triggerid){
        this.population[triggerid].move();
    }

}
