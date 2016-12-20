package core.states;

import java.awt.Color;
import java.awt.Graphics;

import core.Game;
import graphics.Assets;

public class EndState extends State{

	public EndState(Game game) {
		super(game);
	}

	@Override
	public void update() {
		if (game.getKeyboardManager().enter) {
			game.setGameState(new GameState(game, 1));
			StateManager.setState(game.getGameState());
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.background,0,0,null);
		g.setColor(Color.WHITE);
		if (game.isVictorious()) g.drawString("WIN!", 300, 300);
		else g.drawString("LOSE!", 300, 300);
		g.drawString("Press ENTER to start new game", 300, 315);
	}

}
