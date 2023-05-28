package graph;

import java.util.ArrayList;

public class Vertex {
	private int id;
	private static ArrayList<Vertex> V = new ArrayList<>();
	
	private Vertex(int id) {
		this.setId(id);
	}
	
	//Singleton
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	@Override
	public boolean equals(Object o) {
		Vertex v = (Vertex)o;
		
		if (this.id == v.getId())
			return true;
		return false;
	}
	
	public boolean lessthan(Object o) {
		Vertex v = (Vertex)o;
		
		if (this.id < v.getId())
			return true;
		return false;
	}
}
