package Precomputation;
/*
 * Precomputed.java
 * by Caden
 * 
 * file started: Monday, March 24, 2025
 * 
 * file to help for precomputed functions
/*/

public class Precomputed {
	static int size = 1000;
	static double[] lTsin, lTcos, lTtan, lTcsc, lTsec, lTcot;
	private static int qSize = size / 4;

	static {
		// trig precomputation
		double change = (2 * Math.PI) / size;
		double x = 0;

		lTsin = new double[size];
		lTcos = new double[size];
		lTtan = new double[size];
		lTcsc = new double[size];
		lTsec = new double[size];
		lTcot = new double[size];

		// repeat calculations per quadrent
		for (int i = 0; i < qSize; i++) {
			assignTrigQuadrants(lTsin, i, Math.sin(x), new int[] {1, 1, -1, -1});
			assignTrigQuadrants(lTcos, i, Math.cos(x), new int[] {1, -1, -1, 1});

			assignTrigQuadrants(lTsec, i, 1 / lTsin[i]);
			assignTrigQuadrants(lTcsc, i, 1 / lTcos[i]);

			assignTrigQuadrants(lTtan, i, lTsin[i] * lTcsc[i]);
			assignTrigQuadrants(lTcot, i, lTcos[i] * lTsec[i]);

			// update x
			x += change;
		}
	}

	/**
	 * assign trig values to there respective quadrants
	 * signs are a parameter
	 * 
	 * @param table		precomp table
	 * @param i			starting index
	 * @param value		value to assign
	 * @param signs		int[] with 4 values, -1 or 1 for each quadrant
	 */
	private static void assignTrigQuadrants(double[] table, int i, double value, int[] signs) {
		table[i] = signs[0] * value;
		table[i + qSize] = signs[1] * value;
		table[i + 2 * qSize] = signs[2] * value;
		table[i + 3 * qSize] = signs[3] * value;
	}


	/**
	 * assign trig values to there respective quadrants
	 * signs are not a parameter
	 * 
	 * @param table		precomp table
	 * @param i			starting index
	 * @param value		value to assign
	 */
	private static void assignTrigQuadrants(double[] table, int i, double value) {
		table[i] = value;
		table[i + qSize] = value;
		table[i + 2 * qSize] = value;
		table[i + 3 * qSize] = value;
	}
}
