package levels;

import java.awt.Graphics;
import java.util.ArrayList;

import gameObject.Enemy;
import gameObject.Laser;
import gameObject.Meteor;
import gameObject.Player;

public class Level {

    private int length, numOfEnemies, numOfMeteors;
    private ArrayList<Integer> enemiesSpawnTimes, meteorsSpawnTimes;
    
    public Level(String path) {
    	loadLevel(path);
    }
    
    public void update() {
    	
    }
    
    public void render(Graphics g) {
    	
    }

	private void loadLevel(String path) {
		String file = LevelLoader.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		length = LevelLoader.parseInt(tokens[0]);
		
		numOfEnemies = LevelLoader.parseInt(tokens[1]);
		enemiesSpawnTimes = new ArrayList<>();
		int index = 2;
		for (int i = 0; i < numOfEnemies; i++) {
			enemiesSpawnTimes.add(LevelLoader.parseInt(tokens[index+i]));
		}
		
		index+=numOfEnemies;
		numOfMeteors = LevelLoader.parseInt(tokens[index]);
		index++;
		meteorsSpawnTimes = new ArrayList<>();
		for (int i = 0; i < numOfMeteors; i++) {
			meteorsSpawnTimes.add(LevelLoader.parseInt(tokens[index+i]));
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getNumOfEnemies() {
		return numOfEnemies;
	}

	public void setNumOfEnemies(int numOfEnemies) {
		this.numOfEnemies = numOfEnemies;
	}

	public int getNumOfMeteors() {
		return numOfMeteors;
	}

	public void setNumOfMeteors(int numOfMeteors) {
		this.numOfMeteors = numOfMeteors;
	}

	public ArrayList<Integer> getEnemiesSpawnTimes() {
		return enemiesSpawnTimes;
	}

	public void setEnemiesSpawnTimes(ArrayList<Integer> enemiesSpawnTimes) {
		this.enemiesSpawnTimes = enemiesSpawnTimes;
	}

	public ArrayList<Integer> getMeteorsSpawnTimes() {
		return meteorsSpawnTimes;
	}

	public void setMeteorsSpawnTimes(ArrayList<Integer> meteorsSpawnTimes) {
		this.meteorsSpawnTimes = meteorsSpawnTimes;
	}
	
}
