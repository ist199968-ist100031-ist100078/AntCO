package graph;

import java.util.ArrayList;
import java.util.Objects;

/**Vertex class for Graph package, using a Singleton Pattern
 * @since 21-May-2023*/
public class Vertex implements Comparable<Vertex> {
	private int id;
	private static ArrayList<Vertex> V = new ArrayList<>();
	/**Private constructor for vertex
	 * @param id Identifier of node*/
	private Vertex(int id) {
		this.setId(id);
	}
	
	//Singleton
	/**Control instance of Vertex, constructor caller
	 * @param id identifier of node to be analyzed*/
	public static Vertex getInstance(int id) {
		for (Vertex v: V) {
			//If it's an already instanced ID
			if (v.id == id) {
				return v;
			}
		}
		//If it's  new ID
		Vertex v = new Vertex(id);
		V.add(v);
		return v;
		
	}
		/**Public getter of id parameter
		 * @return identifier of node
		 */
	public int getId() {
		return id;
	}
		/**Public setter of id parameter
		 * @param id identifier of node
		 */
	void setId(int id) {
		this.id = id;
	}

	/**Overridden method for checking equality between a present node and a generic Object
	 * @param o generic Object
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vertex vertex = (Vertex) o;
		return id == vertex.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public int compareTo(Vertex v1) {
		
		return Integer.compare(this.getId(), v1.getId());
	
	}
}
