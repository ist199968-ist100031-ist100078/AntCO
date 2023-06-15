package graph;

import java.util.ArrayList;

public interface IGraph {

	void addNode();
	int rmvNode();
	void clear();
	ArrayList<Integer> nodeAdj(int a);
	int getNumNodes();
	int getNumEdges();
	void displayMat();
}
