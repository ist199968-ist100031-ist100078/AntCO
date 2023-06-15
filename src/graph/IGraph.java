package graph;

import java.util.ArrayList;
/**Interface for Graph access
 * @since 28-May-2023*/

public interface IGraph {

	void addNode();
	int rmvNode();
	void clear();
	ArrayList<Integer> nodeAdj(int a);
	int getNumNodes();
	int getNumEdges();
	void displayMat();
}
