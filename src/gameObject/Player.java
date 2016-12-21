package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import core.Game;
import graphics.Assets;

public class Player extends DynamicObject{
	private Game game;
	private boolean isFired;

	public Player(Game game, float x, float y) {
		super(x, y);
		this.game = game;
		isFired = false;
		sprite = Assets.playerOne;
		bounds = new Rectangle(0,0,sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void update() {
		getInput();
		move();
		cooldown--;
	}
	@Override
	public void move() {
		float tempX = x + xMove;
		float tempY = y + yMove;
		if (tempX >= 0 && tempX <=800-99) 
			super.moveX();
		if (tempY >= 0 && tempY <= 600-75)
			super.moveY();
	}
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if (game.getKeyboardManager().up)
			yMove = -speed;
		if (game.getKeyboardManager().down)
			yMove = speed;
		if (game.getKeyboardManager().left)
			xMove = -speed;
		if (game.getKeyboardManager().right)
			xMove = speed;
		isFired = game.getKeyboardManager().fire;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (int) x, (int) y, null);
	}

	public boolean isFired() {
		return isFired;
	}

	public void setFired(boolean isFired) {
		this.isFired = isFired;
	}

	public boolean checkCollisionWithEnemiesOrLaser(ArrayList<Enemy> enemies, ArrayList<Laser> hostileLaserShots, ArrayList<Meteor> meteors) {
		for (Enemy enemy : enemies) {
			if (!enemy.isDead && enemy.getBounds().intersects(this.getBounds())) return true;
		}
		for (Meteor meteor : meteors) {
			if (meteor.getBounds().intersects(this.getBounds())) return true;
		}
		for (Laser laserShot : hostileLaserShots) {
			if (laserShot.getBounds().intersects(this.getBounds())) return true;
		}
		return false;
	}

}
