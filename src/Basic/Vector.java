package Basic;
/*
 * Vector.java
 * by Caden
 * 
 * file started: Monday, March 24, 2025
 * 
 * simple class for 3d position
/*/

public class Vector {
	public double x, y, z;

	/**
	 * empty constructor for Vector
	 */
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * contructor for setting position
	 * 
	 * @param x		x-cor
	 * @param y		y-cor
	 * @param z		z-cor
	 */
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * setter for position
	 * 
	 * @param x		x-cor
	 * @param y		y-cor
	 * @param z		z-cor
	 */
	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector add(Vector pos) {
		return new Vector(x + pos.x, y + pos.y, z + pos.z);
	}

	public Vector subtract(Vector pos) {
		return new Vector(x - pos.x, y - pos.y, z - pos.z);
	}

	public double dot(Vector pos) {
		return x * pos.x + y * pos.y + z * pos.z;
	}
	
	public Vector add(double x, double y, double z) {
		return new Vector(this.x + x, this.y + y, this.z + z);
	}
}
