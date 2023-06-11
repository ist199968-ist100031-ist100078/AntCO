package AntCO;

import java.util.ArrayList;
/*Name: BestPath
description: BestPath class for Ant Colony Optimization
Date added: 07 Jun 2023
*/

class BestPath {
    ArrayList<Integer> path;
    int cost;

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
}