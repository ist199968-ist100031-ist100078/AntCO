package graph;

public interface IWeightedGraph extends IGraph {

	public int getCost(int a, int b);
	public int getTotWeight();
}
