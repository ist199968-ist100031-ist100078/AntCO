package graph;

import java.util.ArrayList;
/**Interface for Graph access
 * @since 28-May-2023*/

public interface IGraph {
	/**Setup new node for filling adjacencies*/
	void addNode();
	/**Remove last node from list*/
	int rmvNode();
	/**Cleaner for Graph attribute*/
	void clear();
	/**Return adjacencies of a node in vertex/vertex format
	 * @param a target to return adjacencies from
	 * @return list of vertex adjacencies of a
	 */
	ArrayList<Integer> nodeAdj(int a);

	/**Getter for nodes atribute
	 * @return number of nodes in graph
	 */
	int getNumNodes();

	/**Getter for size of edge list
	 * @return number of edge in graph
	 */
	int getNumEdges();
	/** Print Graph in Adjacency Matrix representation in stdout*/
	void displayMat();
}
