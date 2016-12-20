package gameObject;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Assets;

public class Meteor extends DynamicObject{

	public Meteor(float x, float y) {
		super(x, y);
		yMove = DEFAULT_SPEED-1;
		sprite = Assets.meteor;
		bounds = new Rectangle(0,0,sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void update() {
		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (int) x, (int) y, null);
	}

}
