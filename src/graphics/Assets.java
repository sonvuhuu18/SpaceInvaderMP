package graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage playerOne, playerTwo, enemy, laserFriendly, laserHostile, meteor, background, dead;
	public static void init() {
		playerOne = ImageLoader.loadImage("/textures/playerShip1_blue.png");
		playerTwo = ImageLoader.loadImage("/textures/playerShip1_green.png");
		enemy = ImageLoader.loadImage("/textures/enemyBlack1.png");
		laserFriendly = ImageLoader.loadImage("/textures/laserBlue02.png");
		laserHostile = ImageLoader.loadImage("/textures/laserRed04.png");
		meteor = ImageLoader.loadImage("/textures/meteorBrown_big1.png");
		background = ImageLoader.loadImage("/textures/background.jpg");
		dead = ImageLoader.loadImage("/textures/playerShip1_damage3.png");
	}
	
}
