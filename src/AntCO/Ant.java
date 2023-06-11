package AntCO;

import java.util.ArrayList;
import java.util.Random;

/* Name: Ant
description: Ant class for Ant Colony Optimization
Date added: 04 Jun 2023
Last modified: 04 Jun 2023
*/
public class Ant {
    Colony antcolony;
    int id;
    ArrayList<Integer> path;
    int pathlength;
    int maxvertex;
    int start;
    int current;
    int next;
    int sigma; /*sum of all weights*/
    int weight;


    /*Constructor*/
    public Ant(int max, int start, int id, Colony antcolony) {
        this.id = id;
        this.maxvertex = max;
        this.start = start;
        this.antcolony = antcolony;
        this.path = new ArrayList<>();
        this.sigma = 0;
        this.weight = 0;
        this.current = start;
        this.next = -1;
        this.pathlength = 0;
        this.path.add(start);
    }

    /* Name: move
    input: none
    output: none
    description: move the ant to the next vertex and update stored path
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
    public boolean move() {
        boolean hamilton = false;
        for (int p : path) {
            System.out.println("path: " + p);
        }
        this.next = this.selectNext(this.current);
        while(this.path.get(this.path.size()-1) == this.next){ //Caso em que o next é um nó repetido
            this.next = this.selectNext(this.next);
        }
        this.path.add(this.next);
        this.sigma += this.antcolony.graph.getCost(this.current, this.next);
        this.current = this.next;
        if (this.current == this.start && this.path.size() == this.maxvertex+1) {
            hamilton = true;
        }
        return hamilton;
    }

    public int selectNext(int current) { /*TODO: reset path if all adjacent were already visited*/
        int next = -1;
        double aux = 0;
        float alpha = this.antcolony.alpha;
        float beta = this.antcolony.beta;
        double abs_prob = 0;
        ArrayList<Integer> possible = this.antcolony.getAdj(current);


        //Remove all already visited nodes from adjacency list
        for (Integer n : this.path) {
            for (Integer p : possible) {
                if (p.equals(n - 1)) {
                    possible.remove(possible.indexOf(p));
                    break;
                }
            }
        }

        ArrayList<Double> prob_arr = new ArrayList<>();
        double sum = 0;
        Random rand = new Random();
        aux = rand.nextDouble(); //random number for probabilities

        if (possible.size() > 0) {
            for (int p : possible) {
                //Cijk
                abs_prob = (alpha + this.antcolony.pheromone.getFvalue(current - 1, p));
                abs_prob /= (beta + this.antcolony.graph.getCost(current, p + 1));
                prob_arr.add(abs_prob + sum);

                sum += abs_prob; //Ci
            }

            for (double p : prob_arr) {
                next++;
                if (Double.compare(aux, p / sum) < 0) {
                    next = possible.get(next) + 1;
                    break;
                }
            }
        } else { //If there are no not yet visited nodes in adjacency list
            possible = this.antcolony.getAdj(current);
            if (this.path.size() == this.maxvertex && possible.contains(this.start-1)) {
                next = this.start;
            } else {
                sum = possible.size();
                for (int i = 1; i <= (int) sum; i++) {
                    if (aux < i / sum) {
                        next = possible.get(i - 1) + 1;
                        break;
                    }
                }
                int idx;
                idx = this.path.indexOf(next);
                System.out.println(idx);;
                int p_anterior = this.path.get(idx);
                for(Integer p : path.subList(idx + 1, path.size())) {
                    this.sigma -= this.antcolony.graph.getCost(p_anterior, p);
                    p_anterior = p;
                }
                this.path.subList(idx + 1, this.path.size()).clear(); //remove all elements after idx
            }
        }

        System.out.println("next is " + next);
        return next;
    }

    /* Name: pheromonize
    input: none
    output: none
    description: pheromonize all edges in the stored path
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
    public void pheromonize() {
        int i;
        for (int p : path.subList(0, path.size() - 1)){
            i = path.indexOf(p) + 1;
            antcolony.pheromone.incrementFvalue(p-1, path.get(i)-1, antcolony.gamma, sigma, antcolony.graph.getCost(i, i + 1));
        }
    }

    /* Name: reset
    input: none
    output: none
    description: reset the ant to its initial state
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
    public void reset() {
        this.pathlength = 0;
        this.sigma = 0;
        this.weight = 0;
        this.current = this.start;
        this.next = -1;
        path.clear();
        path.add(start);

    }

    /* Name: redirect
    input: none
    output: none
    description: reset the ant to its initial state
    Date added: 04 Jun 2023
    Last modified: 04 Jun 2023
    */
     /*   public void redirect(int next) {
            int[] newpath = new int[this.maxvertex]; //I think this is not needed as it should overwrite over the previous path
            int newpathlength = -1; //using this variable
            int newsigma=0;
            for(int i = 0; i < this.pathlength; i++){
                newpathlength++;
                newpath[i] = this.path[i];
                if(this.path[i] == next)
                    break;
                newsigma += this.antcolony.graph.getCost(this.path[i]+1, this.path[i+1]+1);

            }
            this.path = newpath;
            this.pathlength = newpathlength;
            this.sigma = newsigma;
        }*/

}