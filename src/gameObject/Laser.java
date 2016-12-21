package gameObject;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Assets;

public class Laser extends DynamicObject{
	public static final int FRIENDLY_LASER_DELAY = 20;
	public static final int HOSTILE_LASER_DELAY = 60;
	public static final int FRIENDLY = 0;
	public static final int HOSTILE = 1;
	private int type;
	
	public Laser(float x, float y, int type) {
		super(x, y);
		this.type = type;
		if (type == FRIENDLY) {
			yMove = -DEFAULT_SPEED-5;
			sprite = Assets.laserFriendly;
		}
		else {
			yMove = DEFAULT_SPEED+5;
			sprite = Assets.laserHostile;
		}
		bounds = new Rectangle(0,0,sprite.getWidth(), sprite.getHeight());
	}
	
	@Override
	public void update() {
		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
	}

}
