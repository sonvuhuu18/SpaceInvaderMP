package launcher;

import core.Game;

public class Launcher {
	public static void main(String[] args) {
		Game game = new Game("Space Shooter", 800, 600);
		game.start();
	}
}
