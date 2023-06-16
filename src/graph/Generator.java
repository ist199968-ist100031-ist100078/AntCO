package graph;

/** Public class Generator, strategy Pattern for Graph Generation
 * @see graph.FileStrategy
 * @see graph.RandomStrategy
 * @since 03-Jun-2023 */

public class Generator {
	/**Strategy for Graph Generation*/
	private GenerationStrategy strategy;
	/**Public constructor for Generator class*/
	public Generator() {
			
	}
	/**Public setter for generation strategy
	 * @param gs Generation Strategy for Graph Generation*/
	public void setGenerationStrat(GenerationStrategy gs) {
		this.strategy = gs;
	}
	/**Graph generator from Object to Graph
	 *@param G graph object to generate into
	 @param o generic Object to generate graph from
	 * */	
	public void generate(Graph G, Object o) {
		strategy.generate(G, o);
	}
	
}
