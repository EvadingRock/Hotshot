import Precomputation.Trig;

import Basic.*;
import hsa2.GraphicsConsole;
import Graphics.Mesh;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;

public class Camera {
	private ArrayList<Mesh> currentMeshes;
	ArrayList<Triangle> toDisplay;
	Vector pos;
	Vector dir;
	Heading heading;

	public Camera() {
		currentMeshes = new ArrayList<Mesh>();
		toDisplay = new ArrayList<Triangle>();
		pos = new Vector(0, 0, 0);
		dir = new Vector(1, 0, 0);
		heading = new Heading(0, 0);
	}

	/**
	 * gets the x and y coordinates of where any particular point would map onto the camera plain
	 * made from my shoddy math
	 * if bugs are here, refer to the physical papers of the math while debugging
	 * 
	 * @param obj		vector for a points position
	 * @return			will return null if point if behind player,
	 * 					otherwise, just the newly mapped point
	 */
	private Point getCameraPlanePoint(Vector obj) {
		Vector dis = obj.subtract(pos);
		double n = dir.dot(dis); // scaler to map object to camera plane

		if (n < 0) return null; // if object is behind player

		double invDeterminant = Trig.csc(heading.yRot) * Trig.sec(heading.xRot);
		Vector pointOnPlane = new Vector((n * dis.x) - dir.x, (n * dis.y) - dir.y, 0);

		// matrix multiplication
		double x = invDeterminant * ((Trig.cos(heading.xRot) * pointOnPlane.x) + (Trig.sin(heading.xRot) * Trig.cos(heading.yRot) * pointOnPlane.y));
		double y = invDeterminant * (Trig.sin(heading.yRot) * pointOnPlane.y);

		return new Point(x, y, n);
	}

	/**
	 * updates the camera plane based on the current values of the player
	 * @param player	the current player
	 */
	public void updateCamera(Player player) {
		pos = player.pos;
		dir = player.dir;
		heading = player.heading;
	}

	/**
	 * adds the triangles of the mesh to be displayed
	 * @param mesh		mesh to add
	 */
	private void meshToTriangles(Mesh mesh) {
		if (mesh.points.length < 2) return; // if no triangles can be made
		
		Point point1 = getCameraPlanePoint(mesh.points[0]);
		Point point2 = getCameraPlanePoint(mesh.points[1]);

		for (int i = 2; i < mesh.points.length; i++) {
			Point pointNew = getCameraPlanePoint(mesh.points[i]);

			if (point1 != null && point2 != null && pointNew != null) {
				toDisplay.add(new Triangle(point1, point2, pointNew)); // add triangle
			}
			
			// update points
			point1 = point2;
			point2 = pointNew;
		}
	}

	public void addMesh(Mesh mesh) {
		currentMeshes.add(mesh);
	}

	public void sendMeshes() {
		toDisplay = new ArrayList<Triangle>();
		for (Mesh mesh : currentMeshes) {
			meshToTriangles(mesh);
		}
	}

	public void processMeshes() {
		for (int i = 0; i < toDisplay.size() - 1; i++) {
			if (toDisplay.get(i).v1.n > toDisplay.get(i).v1.n) { // if the first triangle is farther then the next
				Collections.swap(toDisplay, i, i + 1);
			}
		}
	}

	public void drawMeshes(GraphicsConsole gc) {
		gc.setStroke(1);

		for (int i = toDisplay.size() - 1; i >= 0; i--) {
			Polygon tri = new Polygon(
				new int[] {(int) toDisplay.get(i).v1.x, (int) toDisplay.get(i).v2.x, (int) toDisplay.get(i).v3.x}, 
				new int[] {(int) toDisplay.get(i).v1.y, (int) toDisplay.get(i).v2.y, (int) toDisplay.get(i).v3.y}, 3);

			gc.setColor(Color.BLACK);
			gc.drawPolygon(tri);

			int col = (int) (255 / toDisplay.get(i).v1.n);
			gc.setColor(new Color(col, col, col));
			gc.fillPolygon(tri);
		}

	}

	/**
	 * getter for the sorted triangle array list
	 * @return	toDisplay
	 */
	public ArrayList<Triangle> getTriangles() {
		toDisplay.sort((a, b) -> {
			if (a.v1.n > b.v1.n) return 1;
			if (a.v1.n < b.v1.n) return -1;
			else return 0;
		});
		return toDisplay;
	}
}
