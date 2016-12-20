package core;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import core.states.EndState;
import core.states.GameState;
import core.states.State;
import core.states.StateManager;
import core.states.TransitionState;
import graphics.Assets;
import input.KeyboardManager;

public class Game implements Runnable{
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private State gameState, endState, transitionState;
	
	private KeyboardManager keyboardManager;
	
	private boolean isVictorious = false;
	private boolean isFinished = false;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyboardManager = new KeyboardManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyboardManager);
		Assets.init();
		
		gameState = new GameState(this, 1);
		endState = new EndState(this);
		transitionState = new TransitionState(this);
		StateManager.setState(gameState);
	}
	
	private void update() {
		if (StateManager.getState() != null) 
			StateManager.getState().update();
		keyboardManager.update();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if (StateManager.getState() != null) 
			StateManager.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while (running) {
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			lastTime = now;
			
			if (delta >= 1) {
				update();
				render();
				delta--;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyboardManager getKeyboardManager() {
		return keyboardManager;
	}

	public boolean isVictorious() {
		return isVictorious;
	}

	public void setVictorious(boolean isVictorious) {
		this.isVictorious = isVictorious;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public State getGameState() {
		return gameState;
	}

	public State getEndState() {
		return endState;
	}

	public State getTransitionState() {
		return transitionState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

}
