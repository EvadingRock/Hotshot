/*
 * Player.java
 * by Caden
 * 
 * file started: Monday, March 24, 2025
 * 
 * player class
/*/

import Basic.*;

public class Player {
	Vector pos;
	Vector dir;
	Heading heading;
	double speed;

	public Player() {
		pos = new Vector(0, 0, 0);
		dir = new Vector(1, 0, 0);
		heading = new Heading(0, 0);
		speed = 1;
	}
}
