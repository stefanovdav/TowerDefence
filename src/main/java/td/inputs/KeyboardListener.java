package td.inputs;

import td.game.Game;
import td.game.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	Game game;
	public KeyboardListener(Game game) {
		this.game = game;
	}
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(GameState.gameState == GameState.PLAYING){
			game.getPlaying().keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
