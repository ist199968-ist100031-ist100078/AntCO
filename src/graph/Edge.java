package graph;
public class Edge implements Comparable<Edge>{
	/**Weight of edge*/
	private int weight;
	/**A Node A adjacent to B, that composes the edge*/
	private Vertex na;
	/**A Node B adjacent to A, that composes the edge */
	private Vertex nb;
	
	/** Public getter for weight attribute
	 * @return weight of edge
	 */
	int get_weight() {
		return weight;
	}
	
	/** Public getter for vertex attributes
	 * @return adjacent nodes that composite the edge
	 */
	Vertex[] get_nodes() {
		return new Vertex[]{na, nb};
	}
	/** Private Constructor like method for Edge class
	 * @param a a node
	 * @param b a node adjacent to a
	 * @param w the weight of the adjacency between a and b (weight of edge)
	 */
	private void buildEdge(Vertex a, Vertex b, int w) {
		if (a.getId() < b.getId()) {
			this.na = a;
			this.nb = b;
		}else {this.na = b;
			this.nb = a;
		}
		this.weight = w;
	}

	/** Public Constructor for Edge class
	 * @param a a node
	 * @param b a node adjacent to a
	 * @param w the weight of the adjacency between a and b (weight of edge)
	 */

	public Edge(Vertex a, Vertex b, int w) {buildEdge(a, b, w);}
	
	/** Public Constructor for Edge class with defaulting for edge weight
	 * @param a a node
	 * @param b a node adjacent to a
	 */

	public Edge(Vertex a, Vertex b) {buildEdge(a, b, 1);}
	
	/**
	 * @param e1 a edge
	 * @return
	 */
	public int compareTo(Edge e1) {
		Integer c = Integer.compare(this.na.getId(), e1.get_nodes()[0].getId());
		if (c.equals(0)) {
			return Integer.compare(this.nb.getId(), e1.get_nodes()[1].getId());
		}
		return c;
	}
}
