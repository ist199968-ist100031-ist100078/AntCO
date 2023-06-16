package antco;
import java.util.ArrayList;
/**
 * Public Colony interface for simulator access
 * @since 06-jun-2023
 */
public interface IColony {
    /**
     * Public translator getter for graph getter
     * @param hashedge hashed edge of nodes i and j
     * @see antco.Colony#unHash
     * @see antco.Colony#Hash
     * @return cost of hashed edge
     * @since 05-Jun-2023
     */
    int getCost(int hashedge);

    /** Public translator getter for current Ant position in edge format
     * @see antco.Colony#Hash
     * @param id ID of Ant to get current position from
     * @return current position in edge format
     */
    int getIdEdge(int id);

    /** Public Trigger for Ant movement
     * @param triggerid Id of Ant to trigger movement sequence
     * @return path of a hamilton cycle or 0 if not yet found a hamilton cycle
     * @since 05-Jun-2023
     */
    ArrayList<Integer> triggerAntMovement(int triggerid);
    /**
     * Public trigger for Pheromone decay
     * @param hashededge edge in which pheromone must decay
     * @return If value of pheromone in edge is positive
     * @since 05-Jun-2023
     */
    boolean triggerPheromoneDecay(int hashededge);
    /** getter for path in BestPath object list in index i
     * @param i index of desired Bestpath to get path
     * @return path of Bestpath in index i
     * @see antco.BestPath
     */
    ArrayList<Integer> getBestPath(int i);

    /** getter for cost in BestPath object list in index i
     * @param i index of desired Bestpath to get cost
     * @return cost of Bestpath in index i
     * @see antco.BestPath
     */
    int getBestCost(int i);
}
