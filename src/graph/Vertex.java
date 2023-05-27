package graph;

public class Vertex {
    private int id;

    public Vertex(int id) {
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        Vertex v = (Vertex) o;

        return this.id == v.getId();
    }

    public boolean lessthan(Object o) {
        Vertex v = (Vertex) o;

        return this.id < v.getId();
    }
}
