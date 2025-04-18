/*
 * Game.java
 * by Caden
 * 
 * file started: Saturday, January 25, 2025
 * 
 * this is the main file of the game of hotshot
 * the game will be a 3d game in which you can hook to objects and swing
 * done with hsa2 graphics
/*/

import java.awt.Color;

import Basic.Vector;
import Graphics.Mesh;
import Precomputation.Trig;
import hsa2.GraphicsConsole;

public class Game {
	public static void main(String[] args) {
		new Game();
	}

	// graphics console
	static final int winW = 800, winH = 600;
	GraphicsConsole gc = new GraphicsConsole(winW, winH, "Hotshot");
	Player player = new Player();
	Camera camera = new Camera();

	// keys down
	boolean[] keysDown = new boolean[256];

	Game() {
		setup();

		while(true) {
			controls();
			// displayInterface();
			displayEnvironment();
			gc.sleep(10);
		}
	}

	private void setup() {
		// setup graphics console
		gc.setAntiAlias(false);
		gc.setLocationRelativeTo(null);
		gc.setBackgroundColor(new Color(0, 0, 0, 255));
		gc.clear();

		camera.addMesh(new Mesh(new Vector(2, -1, 1), new Vector(2, 2, -2)));
	}

	private void controls() {
		// move player (wasd)
		if (isKeyUpAndDown(65)) { // left
			player.pos.x += Trig.cos(player.heading.yRot);
			player.pos.y += Trig.sin(player.heading.yRot);
		}
      
		if (isKeyUpAndDown(68)) { // right
			player.pos.x -= Trig.sin(player.heading.yRot);
			player.pos.y += Trig.cos(player.heading.yRot);
		}
			
		if (isKeyUpAndDown(87)) { // up
			player.pos.x -= Trig.cos(player.heading.yRot);
			player.pos.y -= Trig.sin(player.heading.yRot);
		}
		
		if (isKeyUpAndDown(83)) { // down
			player.pos.x += Trig.sin(player.heading.yRot);
			player.pos.y -= Trig.cos(player.heading.yRot);
		}


		// turn player (arrows)
		if (isKeyUpAndDown(37)) { // left
			player.heading.yRot -= 0.1;
		}
      
		if (isKeyUpAndDown(39)) { // right
			player.heading.yRot += 0.1;
		}
			
		if (isKeyUpAndDown(38)) { // up
			player.heading.xRot -= 0.1;
		}
		
		if (isKeyUpAndDown(40)) { // down
			player.heading.xRot += 0.1;
		}
	}

	private void displayEnvironment() {
		camera.updateCamera(player);

		camera.sendMeshes();
		camera.processMeshes();
		camera.drawMeshes(gc);
	}

	/**
	 * checks if key has been released before press
	 * @param key		the key to check
	 * @return			if yes, true, else false
	 */
	private boolean isKeyUpAndDown(int key) {
		if (gc.isKeyDown(key)) {
			if (!keysDown[key]) {
				keysDown[key] = true;
				return true;
			}
			keysDown[key] = true;
		} else {
			keysDown[key] = false;
		}
		return false;
	}
}
