package graph;
/**Interface extension for acessing Graphs known to be Weighted
 * @since 03-Jun-2023*/
public interface IWeightedGraph extends IGraph {

	int getCost(int a, int b);
	int getTotWeight();
}
