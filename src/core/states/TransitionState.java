package core.states;

import java.awt.Color;
import java.awt.Graphics;

import core.Game;
import graphics.Assets;

public class TransitionState extends State{

	public TransitionState(Game game) {
		super(game);
	}

	@Override
	public void update() {
		if (game.getKeyboardManager().enter) {
			int nextLevel = ((GameState) game.getGameState()).getLevelNumber()+1;
			game.setGameState(new GameState(game, nextLevel));
			StateManager.setState(game.getGameState());
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.background,0,0,null);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to load next level!", 300, 300);
	}

}
