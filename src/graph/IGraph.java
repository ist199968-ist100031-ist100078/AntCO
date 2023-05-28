package graph;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public interface IGraph {

	int getCost(int a, int b);
	int getCost(Vertex a, Vertex b);
	List<Vertex> nodeAdj(Vertex a);
	List<Vertex> nodeAdj(int a);
	void generate(int maxCost);
	public void readFromFile(Scanner reader) throws FileNotFoundException;
	void displayMat();
	
}
