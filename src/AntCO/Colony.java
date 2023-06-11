package AntCO;

import graph.IWeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;

/*
Name: Colony
description: Colony class for Ant Colony Optimization
Date added: 05 Jun 2023
Last modified: 08 Jun 2023
 */
public class Colony implements IColony {
    Pheromone pheromone;
    IWeightedGraph graph;
    float[][] pherovalue; //pheromone value
    int maxvertex;
    int start;
    float gamma;
    float alpha;
    float beta;
    int maxantpop;
    Ant[] population;
    ArrayList<BestPath> bestpath;

    /*Constructors*/
    public Colony(int max, int start, float gamma, float alpha, float beta, int maxantpop, IWeightedGraph graph, float rho) {
        this.maxvertex = max;
        this.start = start;
        this.gamma = gamma;
        this.alpha = alpha;
        this.beta = beta;
        this.maxantpop = maxantpop;
        this.pheromone = new Pheromone(this.maxvertex, this, rho);
        this.pherovalue = new float[maxvertex][maxvertex];
        this.population = new Ant[this.maxantpop];
        for (int i = 0; i < this.maxantpop; i++) {
            this.population[i] = new Ant(this.maxvertex, this.start, i, this);
        }
        //Initializing bestpath
        bestpath = new ArrayList<>();
        //Add one empty path to the bestpath ArrayList
        setGraph(graph);
    }


    /*setter*/
    public void setGraph(IWeightedGraph graph) {
        this.graph = graph;
    }

    /* Name: getCost
    input: edge
    output: cost of edge (i,j)
    description: Simple getter for the cost of edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
    public int getCost(int hashedge) {
        int[] hash = unHash(hashedge);
        return this.graph.getCost(hash[0] + 1, hash[1] + 1);
    }

    public int getIdEdge(int id) {
        int size = this.population[id].path.size();
        return Hash(this.population[id].path.get(size - 2) - 1, this.population[id].path.get(size - 1) - 1);
    }

    public ArrayList<Integer> getAdj(int target) {
        ArrayList<Integer> adj = this.graph.nodeAdj(target);
        int j = 0;
        for (Integer i : adj) {
            i--;
            adj.set(j, i);
            j++;
        }
        return adj;
    }

    /* Name: triggerAntMovement
    input: triggerid
    output: none
    description: trigger the movement of ant with id triggerid
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
     */
    public ArrayList<Integer> triggerAntMovement(int triggerid) {
        if (this.population[triggerid].path.size() == this.maxvertex + 1) {
            this.population[triggerid].reset();
        }
        boolean hamilton = this.population[triggerid].move();
        ArrayList<Integer> path = new ArrayList<>();
        if (hamilton) {
            for (int p : this.population[triggerid].path.subList(0, this.population[triggerid].path.size() - 1)) {
                int i = population[triggerid].path.get(this.population[triggerid].path.indexOf(p) + 1) - 1;
                if (pherovalue[p - 1][i] == 0.0) {
                    path.add(Hash(p - 1, i));
                }
            }
            this.population[triggerid].pheromonize();
            this.updateBestPath(this.population[triggerid].path, this.population[triggerid].sigma);
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

    public void updateBestPath(ArrayList<Integer> path, int sigma) {
        ArrayList<Integer> bestPath = new ArrayList<>(path);
        bestPath.remove(path.size() - 1);
        BestPath aux = new BestPath(sigma, bestPath);
        //Add the first path discovered
        if (this.bestpath.isEmpty()) {
            this.bestpath.add(aux);
            return;
        }
        int idx = 0;
        for (BestPath bp : this.bestpath) {
            if (sigma < bp.cost || this.bestpath.get(idx).path.isEmpty()) { //insert new candidate in the correct position
                this.bestpath.add(idx, aux);
                if (this.bestpath.size() > 6 || this.bestpath.get(this.bestpath.size() - 1).path.isEmpty()) {
                    this.bestpath.remove(this.bestpath.size() - 1);
                }
                return;
            }
            idx++;
        }
        if (idx < 6) {
            this.bestpath.add(aux);
        }
    }


    /* Name: triggerPheromoneDecay
    input: hashededge, rho
    output: none
    description: trigger the decay of pheromone value of edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
    public boolean triggerPheromoneDecay(int hashededge) {
        int[] coords = this.unHash(hashededge);
        return this.pheromone.decayFvalue(coords[0], coords[1]);
    }

    /* Name: Hash
    input: i, j
    output: hash value
    description: Hash function for edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
    public int Hash(int i, int j) {
        return i * this.maxvertex + j;
    }

    /* Name: unHash
    input: hash
    output: i, j
    description: unHash function for edge (i,j)
    Date added: 05 Jun 2023
    Last modified: 05 Jun 2023
    */
    public int[] unHash(int hash) {
        int i = hash / this.maxvertex;
        int j = hash % this.maxvertex;
        return new int[]{i, j};
    }

    public ArrayList<Integer> getBestPath(int i) {
        if (i >= this.bestpath.size()) {
            return new ArrayList<>();
        }
        return this.bestpath.get(i).getPath();
    }

    public int getBestCost(int i) {
        return this.bestpath.get(i).getCost();
    }
}


