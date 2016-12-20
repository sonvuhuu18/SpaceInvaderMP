package gameObject;


public abstract class DynamicObject extends GameObject{
	
	protected float xMove, yMove;
	protected boolean isDead = false;
	protected int cooldown = 0;
	
	public DynamicObject(float x, float y) {
		super(x, y);
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		moveX();
		moveY();
	}
	
	protected void moveY() {
		y+=yMove;
	}

	protected void moveX() {
		x+=xMove;
	}

	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

}
