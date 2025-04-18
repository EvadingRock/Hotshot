package Graphics;

import Basic.*;

public class Mesh {
	public Vector[] points;
	public String id;

	public Mesh(Vector start, Vector dimensions) {
		Vector[] vertices = new Vector[] {
			start,
			start.add(dimensions.x, 0, 0),
			start.add(0, dimensions.y, 0),
			start.add(0, 0, dimensions.z),
			start.add(dimensions.x, dimensions.y, 0),
			start.add(0, dimensions.y, dimensions.z),
			start.add(dimensions.x, 0, dimensions.z),
			start.add(dimensions)
		};

		points = new Vector[] {
			vertices[0],
			vertices[1],
			vertices[2],
			vertices[4],
			vertices[5],
			vertices[7],
			vertices[4],
			vertices[6],
			vertices[1],
			vertices[0],
			vertices[3],
			vertices[2],
			vertices[5],
			vertices[3],
			vertices[6],
			vertices[7],
		};
	}

	
}
