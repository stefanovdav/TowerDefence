package td.inputs;

import td.game.Game;
import td.game.GameStates;

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
		if(GameStates.gameStates == GameStates.PLAYING){
			game.getPlaying().keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
