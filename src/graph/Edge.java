package graph;

public class Edge implements Comparable<Edge> {
	
	private int weight;
	private Vertex na, nb;
	
	int get_weight() {
		return weight;
	}
	
	Vertex[] get_nodes() {
		Vertex[] arr = {na, nb};
		return arr;
	}
	
	private void buildEdge(Vertex a, Vertex b, int w) {
		if (a.getId() < b.getId()) {
			this.na = a;
			this.nb = b;
		}
		else {
			this.na = b;
			this.nb = a;
		}
		this.weight = w;
	}
	
	public Edge(Vertex a, Vertex b, int w) {
		buildEdge(a, b, w);
	}
	
	public Edge(Vertex a, Vertex b) {
		buildEdge(a, b, 1);
	}
	
	public int compareTo(Edge e1) {		
		Integer c = Integer.compare(this.na.getId(), e1.get_nodes()[0].getId());
		if (c.equals(0)) {
			return Integer.compare(this.nb.getId(), e1.get_nodes()[1].getId());
		}
		return c;
	}
}
