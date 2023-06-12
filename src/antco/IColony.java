package antco;
import java.util.ArrayList;

public interface IColony {
    int getCost(int hashedge);
    int getIdEdge(int id);
    ArrayList<Integer> triggerAntMovement(int triggerid);
    boolean triggerPheromoneDecay(int hashededge);
    ArrayList<Integer> getBestPath(int i);
    int getBestCost(int i);
}
