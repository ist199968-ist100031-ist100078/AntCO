package graph2;

import java.util.Scanner;

public class FileStrategy implements GenerationStrategy {
	
	public FileStrategy() {
		
	}
	
	public void generate(Graph G, Object o) {
        //Read adj matrix
        int cost;
        Scanner reader = (Scanner)o;
        
        for (int i = 0; i < G.getNumNodes(); i++) {
            for (int j = 0; j < G.getNumNodes(); j++) {
                cost = reader.nextInt();
                if (i != j && cost != 0 && G.getCost(Vertex.getInstance(i+1), Vertex.getInstance(j+1)) == 0 )
                    G.addEdge(Vertex.getInstance(i+1), Vertex.getInstance(j+1), cost);
            }
        }
        reader.close();
	}
}
