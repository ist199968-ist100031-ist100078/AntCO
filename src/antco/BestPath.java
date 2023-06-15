package antco;

import java.util.ArrayList;
import java.util.Objects;

/** BestPath class for Ant Colony Optimization, works as a container
@since 07-Jun-2023
*/

class BestPath {
    /**path contained*/
    private final ArrayList<Integer> path;
    /**cost of path contained*/
    private final int cost;

	/** Public Constructor for BestPath
	 * @param cost total cost of path
	 * @param path Node List description of path
	 */
    public BestPath(int cost, ArrayList<Integer> path) {
        this.cost = cost;
        this.path = path;
    }
	/** Public getter for path attribute
	 *@return Node List description of path
	 */
    public ArrayList<Integer> getPath() {
        return path;
    }
	/**Public getter for cost attribute
	 * @return total cost of path
	 */
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
