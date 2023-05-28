package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Graph {

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

    public void rmvNode() {
        adjs.remove(this.nodes - 1);
        this.nodes--;
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
        List<Vertex> l;
        l = adjs.get(idx);
        int sz = l.size(), i = 0, j, m = 0;
        Vertex min = new Vertex(0);

        for (Vertex a1 : l) {
            min.setId(a1.getId());
            j = 0;
            for (Vertex a2 : l.subList(i, sz)) {
                if (a2.lessthan(min)) {
                    min.setId(a2.getId());
                    m = j;
                } else {
                    m = i;
                }
                j++;
            }
            Collections.swap(l, i, m);
            i++;
        }
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

    public int getCost(Vertex a, Vertex b) {
        int sz = edges.size();
        Vertex[] arr;
        Edge e;
        for (int i = 0; i < sz; i++) {
            e = edges.get(i);
            arr = e.get_nodes();
            if ((arr[0].equals(a) && arr[1].equals(b) || (arr[0].equals(b) && arr[1].equals(a)))) {
                return e.get_weight();
            }
        }
        return 0;
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
        List<Vertex> aux;
        aux = adjs.get(node - 1);
        int sz = aux.size();
        System.out.println("Node " + node + " is adjacent to:");
        for (int i = 0; i < sz; i++) {
            System.out.print(aux.get(i).getId());
            if (i < sz - 2)
                System.out.print(", ");
            else if (i < sz - 1)
                System.out.print(" and ");
        }
        System.out.println();
    }

    public void displayAdj() {
        for (int i = 0; i < nodes; i++)
            displayAdj(i + 1);
    }

    public void displayMat() {
        Vertex[] v = new Vertex[2];
        v[0] = new Vertex(1);
        v[1] = new Vertex(2);
        int cost;
        System.out.println();
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                v[0].setId(i + 1);
                v[1].setId(j + 1);
                cost = this.getCost(v[0], v[1]);
                System.out.print(" " + cost + " ");
            }
            System.out.println();
        }
    }

    private void sortEdges() {
        int sz = edges.size(), i = 0, j, min;
        Vertex[] a1, a2;
        for (Edge e1 : edges) {
            a1 = e1.get_nodes();
            min = i;
            j = 0;
            for (Edge e2 : edges.subList(i + 1, sz)) {
                a2 = e2.get_nodes();
                //e1 comes first in list
                if (a1[0].lessthan(a2[0]) || (a1[0].equals(a2[0]) && a1[1].lessthan(a2[1]))) {
                    min = i;
                } else {
                    min = j;
                    a1[0] = a2[0];
                    a1[1] = a2[1];
                }
                j++;
            }
            Collections.swap(edges, i, min);
            i++;
        }
    }

    public void generate(int maxCost) {
        Random rand = new Random();  //Fazer singleton neste rand?
        Vertex[] v = new Vertex[this.nodes];
        for (int i = 0; i < this.nodes; i++)
            v[i] = new Vertex(i + 1);

        //Creates a Ring Graph which automatically has a Hamilatonian Cycle
        for (int i = 0; i < this.nodes - 1; i++) {
            this.addEdge(v[i], v[i + 1], rand.nextInt(maxCost) + 1);
        }
        this.addEdge(v[0], v[this.nodes - 1], rand.nextInt(maxCost) + 1);

        //Add up to n(n-1)/2 - n edges, where n is the number of nodes
        int bound = this.nodes * (this.nodes - 1) / 2 - this.nodes;
        if (bound > 0) {
            bound = rand.nextInt(bound+1); //number of extra edges to add
            int n1, n2, retries = 0;
            //Add extra edges in new places
            for (int i = 0; i < bound; i++) {
                n1 = rand.nextInt(1, this.nodes+1);
                n2 = rand.nextInt(1, this.nodes+1);
                if (n1 != n2 && this.getCost(v[n1 - 1], v[n2 - 1]) == 0) {
                    this.addEdge(v[n1 - 1], v[n2 - 1], (rand.nextInt(maxCost)+ 1));
                } else {
                    i--;
                    //retries are used to discard some new edges that are hard to find
                    //fastens up the process
                    retries++;
                    if (retries == 3) {
                        retries = 0;
                        bound--;
                    }
                }
            }
        }
    }


    public void readFromFile(Scanner reader) throws FileNotFoundException {
        //Read adj matrix
        int cost;
        Vertex[] v = new Vertex[nodes];
        for (int i = 0; i < nodes; i++)
            v[i] = new Vertex(i + 1);

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                cost = reader.nextInt();
                if (i != j && cost != 0 && this.getCost(v[i], v[j]) == 0 )
                    this.addEdge(v[i], v[j], cost);
            }
        }
        reader.close();
    }


    public static void main(String args[]) {
        String[] params = new String[]{"-r", "-f"};
        if (args.length < 2 || (!args[0].equals(params[0]) && !args[0].equals(params[1])) || (args[0].equals(params[0]) && args.length != 12)) {
            System.out.println("ERROR - Incorrect Arguments");
            return;
        }
        int numNodes = 0, maxWeight = 0, nest = 0;
        float[] inParams = new float[8];//alpha, beta, delta, eta, ro, gamma, nu, tau

        Graph G;

        if (args[0].equals(params[0])) {
            numNodes = Integer.parseInt(args[1]);
            maxWeight = Integer.parseInt(args[2]);
            nest = Integer.parseInt(args[3]);
            for (int i = 0; i < 8; i++) {
                inParams[i] = Float.parseFloat(args[i + 4]);
            }
            G = new Graph(numNodes);

            G.generate(maxWeight);
            G.displayGraph();
            G.displayAdj();
            G.displayMat();
        } else {
            try {
                File f = new File(args[1]);
                Scanner reader = new Scanner(f);

                //Read Int parameters
                numNodes = reader.nextInt();
                nest = reader.nextInt();
                System.out.println(numNodes + " " + nest);

                //Read float parameters
                try {
                    for (int i = 0; i < 8; i++) {
                        inParams[i] = reader.nextFloat();
                        System.out.println(inParams[i]);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Float has wrong format: " + e + "\n Format should be 'x,y'");
                    reader.close();
                    return;
                }
                G = new Graph(numNodes);
                G.readFromFile(reader);
            } catch (FileNotFoundException e) {
                System.out.println("Exception: " + e);
                return;
            }
            System.out.println("Graph: ");
            G.displayMat();
            G.displayAdj();
        }
    }
}
