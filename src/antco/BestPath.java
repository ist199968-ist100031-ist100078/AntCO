package antco;

import java.util.ArrayList;
import java.util.Objects;
/*Name: BestPath
description: BestPath class for Ant Colony Optimization
Date added: 07 Jun 2023
*/

class BestPath {
    private ArrayList<Integer> path;
    private int cost;

    public BestPath(int cost, ArrayList<Integer> path) {
        this.cost = cost;
        this.path = path;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BestPath bestPath = (BestPath) o;
        return cost == bestPath.cost && Objects.equals(path, bestPath.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, cost);
    }
}
