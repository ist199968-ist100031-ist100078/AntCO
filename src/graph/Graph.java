package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**Graph class for Graph package
 * @see graph.Edge
 * @see graph.Vertex
 * @since 21-May-2023*/

public class Graph implements IWeightedGraph {
    /**number of nodes in graph*/
    private int nodes;
    /**Edges in graph*/
    private List<Edge> edges;
    /**Node adjacencies in list of lists representation*/
    private List<List<Vertex>> adjs;
    /**Public constructor for Graph class
     * @param n number of nodes in graph
     */
    public Graph(int n) {
        this.nodes = n;
        edges = new ArrayList<>();
        adjs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Vertex> aux = new ArrayList<>();
            adjs.add(i, aux);
        }
    }
     /**Setup new node for filling adjacencies
     */
    public void addNode() {
        List<Vertex> aux = new ArrayList<>();
        adjs.add(this.nodes, aux);
        this.nodes++;
    }
	/**Remove last node from list
	 * @return number of nodes in graph
	 */
    public int rmvNode() {
        adjs.remove(this.nodes - 1);
        this.nodes--;
        return this.nodes + 1;
    }
	/**Getter for nodes atribute
	 * @return number of nodes in graph
	 */
    public int getNumNodes() {
        return this.nodes;
    }
	/**Getter for size of edge list
	 * @return number of edge in graph
	 */
    public int getNumEdges() {
        return this.edges.size();
    }
	/**Cleaner for Graph attributes
	 */
    public void clear() {
        edges.clear();
        adjs.clear();
        for (int i = 0; i < this.nodes; i++) {
            List<Vertex> aux = new ArrayList<>();
            adjs.add(i, aux);
        }
    }
	/** Insert adjacency information in graph
	 * @param na a node connected to nb
	 * @param nb a node connected to na
	 */
    private void addAdj(Vertex na, Vertex nb) {
        int a = na.getId(), b = nb.getId();
        List<Vertex> la;
        la = adjs.get(a - 1);
        la.add(nb);
        List<Vertex> lb;
        lb = adjs.get(b - 1);
        lb.add(na);
        sortEdges();
        sortAdj(a - 1);
        sortAdj(b - 1);
    }

    /**Sorts adjacencies in order of ascending vertex number
     * @param idx index of node to have adjacencies sorted
     */
    private void sortAdj(int idx) {
        Collections.sort(adjs.get(idx));
    }
	/**  Insert edge information method for non-weighted graph
	 * @param a a node connected to nb
	 * @param b a node connected to na
	 */

    public void addEdge(Vertex a, Vertex b) {
        if (this.getCost(a, b) == 0) {
            edges.add(new Edge(a, b));
            addAdj(a, b);
        }
    }
	/** Insert edge information method for weighted graph
	 * @param a a node connected to b
	 * @param b a node connected to a
	 * @param cost weight of edge
	 */

    public void addEdge(Vertex a, Vertex b, int cost) {
        edges.add(new Edge(a, b, cost));
        addAdj(a, b);
    }
    /**Return cost of edge of two node  integer/integer 
     * @param a a node adjacent to b
     * @param b a node adjacent to a
     * @return cost of edge (a,b)
     */
    public int getCost(int a, int b) {
        Vertex va = Vertex.getInstance(a);
        Vertex vb = Vertex.getInstance(b);

        return getCost(va, vb);
    }

    /**Return adjacencies of a node in vertex/vertex format
     * @param a a node adjacent to b
     * @param b a node adjacent to a
     * @return cost of edge (a,b)
     */
    public int getCost(Vertex a, Vertex b) {
        Vertex[] arr;
        for (Edge e : edges) {
            arr = e.get_nodes();
            if ((arr[0].equals(a) && arr[1].equals(b) || (arr[0].equals(b) && arr[1].equals(a)))) {
                return e.get_weight();
            }
        }
        return 0;
    }
	/**Compute total weight of the graph
	 * @return total weight of graph*/
    public int getTotWeight() {
    	int w = 0;
    	for (Edge e: edges) {
    		w+= e.get_weight();
    	}
    	return w;
    }
    /**Return adjacencies of a node in vertex/vertex format
     * @param a target to return adjacencies from
     * @return list of vertex adjacencies of a
     */
    public List<Vertex> nodeAdj(Vertex a) {
        return adjs.get(a.getId() - 1);
    }

    /**Return adjacencies of a node in integer/integer format
     * @param a target to return adjacencies from
     * @return list of vertex adjacencies of a
     */

    public ArrayList<Integer> nodeAdj(int a) {

        ArrayList<Integer> al = new ArrayList<>();

        for (Vertex v : adjs.get(a - 1)) {
            al.add(v.getId());
        }
        return al;
    }

    /**Display graph in a verbose way in stdout
     * @deprecated substituted by displayMat() */
    @Deprecated
    public void displayGraph() {
        Vertex[] arr;
        System.out.println("Graph has " + this.nodes + " nodes.");
        for (Edge e : edges) {
            arr = e.get_nodes();
            System.out.println("Edge: [" + arr[0].getId() + " - " + arr[1].getId() + "] with cost " + e.get_weight());
        }
    }
	/**Display adjacencies of target node in a verbose way in stdout
	 * @param node target node to display adjacencies from
     * @deprecated subsrtituted by displayMat() */
    @Deprecated
    public void displayAdj(int node) {
        System.out.println("Node " + node + " is adjacent to:");
        for (Vertex aux : adjs.get(node - 1)) {
            System.out.print(aux.getId() + " - ");
        }
        System.out.println();
    }
	/**Display all adjacencies of graph in a verbose way in stdout
     * @deprecated substituted by displayMat() */
    @Deprecated
    public void displayAdj() {
        for (int i = 0; i < nodes; i++)
            displayAdj(i + 1);
    }
	/** Print Graph in Adjacency Matrix representation in stdout*/
    public void displayMat() {
        Vertex[] v = new Vertex[2];
        v[0] = Vertex.getInstance(1);
        v[1] = Vertex.getInstance(2);
        int cost;
        System.out.print("with graph:\n\t");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                v[0] = Vertex.getInstance(i + 1);
                v[1] = Vertex.getInstance(j + 1);
                cost = this.getCost(v[0], v[1]);
                System.out.print(" " + cost + " ");
            }
            System.out.print("\n\t");
        }
    }

    /**Sorts edges in order of ascending vertex number*/
    private void sortEdges() {
        Collections.sort(edges);
    }

}
