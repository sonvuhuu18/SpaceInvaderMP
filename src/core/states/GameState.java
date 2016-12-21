package core.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import core.Game;
import gameObject.Enemy;
import gameObject.Laser;
import gameObject.Meteor;
import gameObject.Player;
import graphics.Assets;
import levels.Level;

public class GameState extends State{
	
	private int backgroundY;
    private int bgMotion;
    private int bgMotionSec;

	private Player player;
    private ArrayList<Laser> laserShots;
    private ArrayList<Laser> hostileLaserShots;
    private ArrayList<Enemy> enemies;
    private ArrayList<Meteor> meteors;
    
    private Level level;
    private int currentLength = -1, levelNumber;
	
	public GameState(Game game, int levelNumber) {
		super(game);
		this.levelNumber = levelNumber;
		initLevel();
		bgMotion    = Assets.background.getHeight(null);
        bgMotionSec = 0;
        backgroundY = 0;
	}
	
	private void initLevel() {
		level = new Level("res/levels/level"+levelNumber+".txt");
		player = new Player(game, 350, 600-80);
		laserShots = new ArrayList<Laser>();
		hostileLaserShots = new ArrayList<Laser>();
		enemies = new ArrayList<Enemy>();
		meteors = new ArrayList<Meteor>();
		
	}

	private void backgroundMovement(){
        bgMotion    -= 1;
        bgMotionSec += 1;
        backgroundY += 1;
    }
	
	private void drawBackground(Graphics g){
        if ((backgroundY - 0) % (Assets.background.getHeight(null) * 2) == 0){
            bgMotionSec = 0;
        } else if ((backgroundY - Assets.background.getHeight(null)) % (Assets.background.getHeight(null) * 2) == 0){
            bgMotion = (Assets.background.getHeight(null) * 2);
        }
        g.drawImage(Assets.background, 0, Assets.background.getHeight(null) - bgMotion, null);
        if (backgroundY > 0){
            g.drawImage(Assets.background, 0, -(Assets.background.getHeight(null) - bgMotionSec), null);
        }
    }

	@Override
	public void update() {
		backgroundMovement();
		currentLength++;
		if (currentLength >= level.getLength()) {
			if (levelNumber < 2) 
				StateManager.setState(game.getTransitionState());
			else {
				game.setFinished(true);
				game.setVictorious(true);
				StateManager.setState(game.getEndState());
			}
		}
		spawnObject(currentLength);
		
		player.update();
		if (player.isFired() && player.getCooldown() <= 0) {
			laserShots.add(new Laser(player.getX()+44, player.getY(), Laser.FRIENDLY));
			player.setCooldown(Laser.FRIENDLY_LASER_DELAY);
		}
		
		for (Enemy enemy : enemies) {
			enemy.update();
			if (enemy.checkCollisionWithLaser(laserShots)) enemy.setDead(true);
			if (!enemy.isDead() && enemy.getCooldown()<=0) {
				hostileLaserShots.add(new Laser(enemy.getX()+41, enemy.getY(), Laser.HOSTILE));
				enemy.setCooldown(Laser.HOSTILE_LASER_DELAY);
			}
		}
		
		for (Meteor meteor : meteors) {
			meteor.update();
		}

		Iterator<Laser> iter = laserShots.iterator();
		while (iter.hasNext()) {
			Laser laserShot = iter.next();
			laserShot.update();
			if (laserShot.getY() < 0) iter.remove();
		}
		
		iter = hostileLaserShots.iterator();
		while (iter.hasNext()) {
			Laser laserShot = iter.next();
			laserShot.update();
			if (laserShot.getY() > 600) iter.remove();
		}

		if (player.checkCollisionWithEnemiesOrLaser(enemies, hostileLaserShots, meteors)) {
			player.setSprite(Assets.dead);
			game.setVictorious(false);
			game.setFinished(true);
			StateManager.setState(game.getEndState());
		}
	}

	private void spawnObject(int currentLength) {
		int numOfEnemies = level.getNumOfEnemies();
		int numOfMeteors = level.getNumOfMeteors();
		ArrayList<Integer> enemiesSpawnTimes = level.getEnemiesSpawnTimes();
		ArrayList<Integer> meteorsSpawnTimes = level.getMeteorsSpawnTimes();
		
		Random rand = new Random();
		for (int i = 0; i < numOfEnemies; i++) {
			if (currentLength == enemiesSpawnTimes.get(i)) 
				enemies.add(new Enemy(rand.nextInt(800), 0));
		}
		for (int i = 0; i < numOfMeteors; i++) {
			if (currentLength == meteorsSpawnTimes.get(i))
				meteors.add(new Meteor(rand.nextInt(800), 0));
		}
	}

	@Override
	public void render(Graphics g) {
		drawBackground(g);
		g.setColor(Color.WHITE);
		g.drawString("Level "+levelNumber, 10, 17);
		g.drawString("Distance: "+currentLength/10+"/"+level.getLength()/10, 10, 17+15);
		player.render(g);
		
		for (Enemy enemy : enemies) {
			if (!enemy.isDead()) enemy.render(g);
		}
		
		for (Meteor meteor : meteors) {
			meteor.render(g);
		}
		
		for (Laser laserShot : laserShots) {
			laserShot.render(g);
		}
		for (Laser laserShot : hostileLaserShots) {
			laserShot.render(g);
		}
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

}
