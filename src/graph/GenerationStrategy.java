package graph;
/**Interface for Graph Generation
 * @see graph.FileStrategy
 * @see graph.RandomStrategy
 * @since 03-Jun-2023 */
public interface GenerationStrategy {
	/**Graph generator from Object to Graph
	 *@param G graph object to generate into
	 @param o generic Object to generate graph from
	  * */
	void generate(Graph G, Object o);
}
