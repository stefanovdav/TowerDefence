package td.scenes;

import td.game.Game;

public class GameScene {
	private final Game game;

	public GameScene(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

}
