package antco;
import java.util.ArrayList;
/**
 * Public Colony interface for simulator access
 * @since 06-jun-2023
 */
public interface IColony {
    int getCost(int hashedge);
    int getIdEdge(int id);
    ArrayList<Integer> triggerAntMovement(int triggerid);
    boolean triggerPheromoneDecay(int hashededge);
    ArrayList<Integer> getBestPath(int i);
    int getBestCost(int i);
}
