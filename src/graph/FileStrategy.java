package graph;

import java.util.NoSuchElementException;
import java.util.Scanner;
/** FileStrategy class for graph generation
 */
public class FileStrategy implements GenerationStrategy {
	/** Public Constructor
	 */
	public FileStrategy() {
		
	}
	/**Public method for graph generation from file
	 * @param G Graph mainframe to be generated in
	 * @param o Input file
	 */
	public void generate(Graph G, Object o) {
        //Read adj matrix
        int cost;
        Scanner reader = (Scanner)o;
       try {
           for (int i = 0; i < G.getNumNodes(); i++) {
               for (int j = 0; j < G.getNumNodes(); j++) {
                   cost = reader.nextInt();
                   if (i != j && cost != 0 && G.getCost(Vertex.getInstance(i + 1), Vertex.getInstance(j + 1)) == 0)
                       G.addEdge(Vertex.getInstance(i + 1), Vertex.getInstance(j + 1), cost);
               }
           }
       } catch (NoSuchElementException e) {
           System.out.println("Not enough parameters in the file: " + e);
           reader.close();
           System.exit(1);
       }
        reader.close();
	}
}
