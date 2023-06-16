package graph;
/**Interface extension for acessing Graphs known to be Weighted
 * @since 03-Jun-2023*/
public interface IWeightedGraph extends IGraph {
	/**Return cost of edge of two node  integer/integer
	 * @param a a node adjacent to b
	 * @param b a node adjacent to a
	 * @return cost of edge (a,b)
	 */
	int getCost(int a, int b);

	/**Compute total weight of the graph
	 * @return total weight of graph*/
	int getTotWeight();

}
