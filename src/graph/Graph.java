package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Graph implements IWeightedGraph {

    private int nodes;
    private List<Edge> edges;
    private List<List<Vertex>> adjs;

    public Graph(int n) {
        this.nodes = n;
        edges = new ArrayList<>();
        adjs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Vertex> aux = new ArrayList<>();
            adjs.add(i, aux);
        }
    }

    public void addNode() {
        List<Vertex> aux = new ArrayList<>();
        adjs.add(this.nodes, aux);
        this.nodes++;
    }

    public int rmvNode() {
        adjs.remove(this.nodes - 1);
        this.nodes--;
        return this.nodes + 1;
    }

    public int getNumNodes() {
        return this.nodes;
    }

    public int getNumEdges() {
        return this.edges.size();
    }

    public void clear() {
        edges.clear();
        adjs.clear();
        for (int i = 0; i < this.nodes; i++) {
            List<Vertex> aux = new ArrayList<>();
            adjs.add(i, aux);
        }
    }

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

    private void sortAdj(int idx) {
        Collections.sort(adjs.get(idx));
    }

    public void addEdge(Vertex a, Vertex b) {
        if (this.getCost(a, b) == 0) {
            edges.add(new Edge(a, b));
            addAdj(a, b);
        }
    }

    public void addEdge(Vertex a, Vertex b, int cost) {
        edges.add(new Edge(a, b, cost));
        addAdj(a, b);
    }

    public int getCost(int a, int b) {
        Vertex va = Vertex.getInstance(a);
        Vertex vb = Vertex.getInstance(b);

        return getCost(va, vb);
    }

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

    public List<Vertex> nodeAdj(Vertex a) {
        return adjs.get(a.getId() - 1);
    }

    public ArrayList<Integer> nodeAdj(int a) {

        ArrayList<Integer> al = new ArrayList<>();

        for (Vertex v : adjs.get(a - 1)) {
            al.add(v.getId());
        }
        return al;
    }

    //Displays All adjacencies
    public void displayGraph() {
        Vertex[] arr;
        System.out.println("Graph has " + this.nodes + " nodes.");
        for (Edge e : edges) {
            arr = e.get_nodes();
            System.out.println("Edge: [" + arr[0].getId() + " - " + arr[1].getId() + "] with cost " + e.get_weight());
        }
    }

    public void displayAdj(int node) {
        System.out.println("Node " + node + " is adjacent to:");
        for (Vertex aux : adjs.get(node - 1)) {
            System.out.print(aux.getId() + " - ");
        }
        System.out.println();
    }

    public void displayAdj() {
        for (int i = 0; i < nodes; i++)
            displayAdj(i + 1);
    }

    public void displayMat() {
        Vertex[] v = new Vertex[2];
        v[0] = Vertex.getInstance(1);
        v[1] = Vertex.getInstance(2);
        int cost;
        System.out.println();
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                v[0] = Vertex.getInstance(i + 1);
                v[1] = Vertex.getInstance(j + 1);
                cost = this.getCost(v[0], v[1]);
                System.out.print(" " + cost + " ");
            }
            System.out.println();
        }
    }

    private void sortEdges() {
        Collections.sort(edges);
    }

}