package td.game;

import java.awt.*;

public class Render {
	private Game game;

	public Render(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {
		switch (GameState.gameState) {
			case MENU -> {
				game.getMenu().render(g);
			}
			case PLAYING -> {
				game.getPlaying().render(g);
			}
			case SETTINGS -> {
				game.getSettings().render(g);
			}
			case EDIT -> {
				game.getEditing().render(g);
			}
			case GAME_OVER -> {
				game.getGameOver().render(g);
			}
		}
	}
}
