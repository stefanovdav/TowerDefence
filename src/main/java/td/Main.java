package td;

import td.game.Game;

public class Main {
	public static void main(String[] args) {
		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();
	}
}
