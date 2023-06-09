package graph2;

import java.util.ArrayList;

public interface IGraph {

	public void addNode();
	public int rmvNode();
	public void clear();
	public ArrayList<Integer> nodeAdj(int a);
	public int getNumNodes();
	public void displayMat();
	
}
