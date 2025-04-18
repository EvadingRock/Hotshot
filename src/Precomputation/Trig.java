package Precomputation;
/*
 * Trig.java
 * by Caden
 * 
 * file started: Monday, March 24, 2025
 * 
 * store for various precomputed trig functions
/*/

import static Basic.Constants.*;

public class Trig {
	private static double indexScaler = Precomputed.size / TAU;

	public static double sin(double x) {
		return Precomputed.lTsin[getTrigValue(x)];
	}

	public static double cos(double x) {
		return Precomputed.lTcos[getTrigValue(x)];
	}

	public static double tan(double x) {
		return Precomputed.lTtan[getTrigValue(x)];
	}

	public static double csc(double x) {
		return Precomputed.lTcsc[getTrigValue(x)];
	}

	public static double sec(double x) {
		return Precomputed.lTsec[getTrigValue(x)];
	}

	public static double cot(double x) {
		return Precomputed.lTcot[getTrigValue(x)];
	}


	private static int getTrigValue(double x) {
		double mod = x % TAU;
		if (mod < 0) mod += TAU;

		return (int) (mod * indexScaler);
	}
}
