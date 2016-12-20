package gameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import graphics.Assets;

public class Enemy extends DynamicObject{

	public Enemy(float x, float y) {
		super(x, y);
		yMove = DEFAULT_SPEED-1;
		sprite = Assets.enemy;
		bounds = new Rectangle(0,0,sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void update() {
		randomMovement();
		move();
		cooldown--;
	}

	private void randomMovement() {
		Random rand = new Random();
		if (rand.nextInt(10000)%15 == 0) {
			if (rand.nextInt(100)%2 == 0)
				xMove = -DEFAULT_SPEED+1;
			else xMove = DEFAULT_SPEED-1;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
	}

	public boolean checkCollisionWithLaser(ArrayList<Laser> laserShots) {
		for (Laser laserShot : laserShots) {
			if (!this.isDead && laserShot.getBounds().intersects(this.getBounds())) 
				this.setDead(true);
		}
		return false;
	}

}
