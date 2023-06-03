package graph;

//Strategy Pattern for Graph Generation - Context
public class Generator {

	private GenerationStrategy strategy;
	
	public Generator() {
			
	}
	
	public void setGenerationStrat(GenerationStrategy gs) {
		this.strategy = gs;
	}
	
	public void generate(Graph G, Object o) {
		strategy.generate(G, o);
	}
	
}
