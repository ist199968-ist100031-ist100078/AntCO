package graph;

public class Edge {

    private int weight;
    private Vertex na, nb;

    int get_weight() {
        return weight;
    }

    Vertex[] get_nodes() {
        return new Vertex[]{na, nb};
    }

    private void buildEdge(Vertex a, Vertex b, int w) {
        if (a.getId() < b.getId()) {
            this.na = a;
            this.nb = b;
        } else {
            this.na = b;
            this.nb = a;
        }
        this.weight = w;
    }

    public Edge(Vertex a, Vertex b, int w) {
        buildEdge(a, b, w);
    }

    public Edge(Vertex a, Vertex b) {
        buildEdge(a, b, 0);
    }
}
