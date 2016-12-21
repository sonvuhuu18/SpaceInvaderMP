package gameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {
	public static final float DEFAULT_SPEED = 3;
	
	protected float x,y;
	protected float speed;
	protected Rectangle bounds;
	protected BufferedImage sprite;
	
	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
		this.speed = DEFAULT_SPEED;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x+bounds.x, (int)y+bounds.y, bounds.width, bounds.height);
	}


	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
}
