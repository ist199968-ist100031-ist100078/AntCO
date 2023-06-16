package antco;

import graph.IWeightedGraph;
import java.util.ArrayList;


/** Colony class for Ant Colony Optimization
 * @since 05-Jun-2023
 */
public class Colony implements IColony {
    /**Pheromone which the colony will use for path calculations*/
    private final Pheromone pheromone;
    /**Graph in which the Colony will operate its ants over*/
    private IWeightedGraph graph;
    /**Arithmethic parameter for ant travel calculations*/
    private final float alpha;
    /**Arithmethic parameter for ant travel calculations*/
    private final float beta;
    /**Ant population in colony*/
    private final Ant[] population;
    /**List of best paths found in colony*/
    private final ArrayList<BestPath> bestpath;
    /**
     * Public constructor for a Colony class
     * @param start nest node identifier
     * @param gamma gamma parameter for pheromone decay
     * @see antco.Pheromone#decayFvalue Pheromone decay function
     * @param alpha alpha parameter for ant movement
     * @param beta beta parameter for ant movement
     * @see antco.Ant#move Ant move function 
     * @see antco.Ant#selectNext Ant selectNext function
     * @param maxantpop maximum population of Ants in a Colony
     * @param graph Interface to acess graph to operate over
     * @param rho rho parameter for pheromone increment
     * @see antco.Pheromone#incrementFvalue Pheromone increment function
     *
     */
    public Colony(int start, float gamma, float alpha, float beta, int maxantpop, IWeightedGraph graph, float rho) {
        this.alpha = alpha;
        this.beta = beta;
        setGraph(graph);
        this.pheromone = new Pheromone(this, rho, gamma);
        this.population = new Ant[maxantpop];
        for (int i = 0; i < maxantpop; i++) {
            this.population[i] = new Ant(start, this);
        }
        //Initializing bestpath
        bestpath = new ArrayList<>();
        //Add one empty path to the bestpath ArrayList

    }


    /**
     * Private setter for graph parameter
     * @param graph graph to operate over
     */
    private void setGraph(IWeightedGraph graph) {
        this.graph = graph;
    }
    /**
     * Public getter for graph
     * @return graph interface
     */
    public IWeightedGraph getGraph(){
	    return this.graph;
    }
    /**
     * Public getter for pheromone
     * @return pheromone object
     */
    public Pheromone getPheromone(){
	    return this.pheromone;
    }
    /**
     * Public getter for alpha
     * @return alpha
     */
    public float getAlpha(){
	    return alpha;
    }
	/**
	 * Public getter for Beta
	 * @return beta
	 */
	public float getBeta(){
	    return beta;
    }
    /**
     * Public getter for maxvertex in Colony Graph
     * @return maximum vertex in Colony Graph
     */
    public int getMaxVertex(){
	    return this.graph.getNumNodes();
    }


    /*[TRANSLATORS AND HASHES]*/
    /**
     * Public translator getter for graph getter
     * @param hashedge hashed edge of nodes i and j
     * @see antco.Colony#unHash
     * @see antco.Colony#Hash
     * @return cost of hashed edge
     * @since 05-Jun-2023
    */
    public int getCost(int hashedge) {
        int[] hash = unHash(hashedge);
        return this.graph.getCost(hash[0] + 1, hash[1] + 1);
    }
    /** Public translator getter for current Ant position in edge format
     * @see antco.Colony#Hash
     * @param id ID of Ant to get current position from
     * @return current position in edge format
     */
    public int getIdEdge(int id) {
        int size = this.population[id].getPath().size();
        return Hash(this.population[id].getPath().get(size - 2) - 1, this.population[id].getPath().get(size - 1) - 1);
    }

    /**Public translator for graph Adjacencies getter 
     * @param target node to find adjacencies for
    * @return list of adjacencies to target node in Colony usable format
    */
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

 /**
     * Hasher to convert two connected node IDs into a edge ID
     * @param i Node ID
     * @param j Node ID
     * @return edge ID
     * @since 05-Jun-2023
    */
    public int Hash(int i, int j) {
        return i * this.getMaxVertex() + j;
    }

    /**
     * unHasher to convert a edge ID into two connected node IDs
     * @param hash hashed edge
     * @return Node IDs into array format
    */
    public int[] unHash(int hash) {
        int i = hash / this.getMaxVertex();
        int j = hash % this.getMaxVertex();
        return new int[]{i, j};
    }




    /*[BEST PATH]*/

    /** Updater for Candidates to Best Cycle
     * @param path Hamilton Cycle taken
     * @param sigma Total cost of Hamilton cycle
     * @see antco.BestPath
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
            if(bp.equals(aux)){
                return;
            }
            else if (sigma < bp.getCost() || this.bestpath.get(idx).getPath().isEmpty()) { //insert new candidate in the correct position
                this.bestpath.add(idx, aux);
                if (this.bestpath.size() > 6 || this.bestpath.get(this.bestpath.size() - 1).getPath().isEmpty()) {
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
     /** getter for path in BestPath object list in index i
     * @param i index of desired Bestpath to get path
     * @return path of Bestpath in index i
     * @see antco.BestPath
     */
    public ArrayList<Integer> getBestPath(int i) {
        if (i >= this.bestpath.size()) {
            return new ArrayList<>();
        }
        return this.bestpath.get(i).getPath();
    }

     /** getter for cost in BestPath object list in index i
     * @param i index of desired Bestpath to get cost
     * @return cost of Bestpath in index i
     * @see antco.BestPath
     */
    public int getBestCost(int i) {
        return this.bestpath.get(i).getCost();
    }
   	/*[TRIGGERS]*/
    /**
     * Public trigger for Pheromone decay
     * @param hashededge edge in which pheromone must decay
     * @return If value of pheromone in edge is positive
     * @since 05-Jun-2023
    */
    public boolean triggerPheromoneDecay(int hashededge) {
        int[] coords = this.unHash(hashededge);
        return this.pheromone.decayFvalue(coords[0], coords[1]);
    }

     /** Public Trigger for Ant movement
     * @param triggerid Id of Ant to trigger movement sequence
     * @return path of a hamilton cycle or 0 if not yet found a hamilton cycle
     * @since 05-Jun-2023
     */
    public ArrayList<Integer> triggerAntMovement(int triggerid) {
        if (this.population[triggerid].getPath().size() == this.getMaxVertex() + 1) {
            this.population[triggerid].reset();
        }
        boolean hamilton = this.population[triggerid].move();
        ArrayList<Integer> path = new ArrayList<>();
        if (hamilton) {
            for (int p : this.population[triggerid].getPath().subList(0, this.population[triggerid].getPath().size() - 1)) {
                int i = population[triggerid].getPath().get(this.population[triggerid].getPath().indexOf(p) + 1) - 1;
                if (this.pheromone.getFvalue(p - 1,i) == 0.0) {
                    path.add(Hash(p - 1, i));
                }
            }
            this.population[triggerid].pheromonize();
            this.updateBestPath(this.population[triggerid].getPath(), this.population[triggerid].getSigma());
            /*System.out.println("Path found: " + this.population[triggerid].getPath() + " with cost: " + this.population[triggerid].getSigma());*/
        } else {
            path.add(0);
        }
        return path;
    }
}
   
