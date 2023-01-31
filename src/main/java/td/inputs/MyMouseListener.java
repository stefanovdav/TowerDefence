package td.inputs;

import td.game.Game;
import td.game.GameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
	private Game game;

	public MyMouseListener(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == e.BUTTON1) {
			switch (GameStates.gameStates) {
				case MENU -> game.getMenu().mouseClicked(e.getX(), e.getY());
				case PLAYING -> game.getPlaying().mouseClicked(e.getX(), e.getY());
				case SETTINGS -> game.getSettings().mouseClicked(e.getX(), e.getY());
				case EDIT -> game.getEditing().mouseClicked(e.getX(), e.getY());
				case GAME_OVER -> game.getGameOver().mouseClicked(e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameStates.gameStates) {
			case MENU -> game.getMenu().mousePressed(e.getX(), e.getY());
			case PLAYING -> game.getPlaying().mousePressed(e.getX(), e.getY());
			case SETTINGS -> game.getSettings().mousePressed(e.getX(), e.getY());
			case EDIT -> game.getEditing().mousePressed(e.getX(), e.getY());
			case GAME_OVER -> game.getGameOver().mouseClicked(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameStates) {
			case MENU -> game.getMenu().mouseReleased(e.getX(), e.getY());
			case PLAYING -> game.getPlaying().mouseReleased(e.getX(), e.getY());
			case SETTINGS -> game.getSettings().mouseReleased(e.getX(), e.getY());
			case EDIT -> game.getEditing().mouseReleased(e.getX(), e.getY());
			case GAME_OVER -> game.getGameOver().mouseClicked(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (GameStates.gameStates) {
			case MENU -> game.getMenu().mouseDragged(e.getX(), e.getY());
			case PLAYING -> game.getPlaying().mouseDragged(e.getX(), e.getY());
			case SETTINGS -> game.getSettings().mouseDragged(e.getX(), e.getY());
			case EDIT -> game.getEditing().mouseDragged(e.getX(), e.getY());
			case GAME_OVER -> game.getGameOver().mouseClicked(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameStates) {
			case MENU -> game.getMenu().mouseMoved(e.getX(), e.getY());
			case PLAYING -> game.getPlaying().mouseMoved(e.getX(), e.getY());
			case SETTINGS -> game.getSettings().mouseMoved(e.getX(), e.getY());
			case EDIT -> game.getEditing().mouseMoved(e.getX(), e.getY());
			case GAME_OVER -> game.getGameOver().mouseClicked(e.getX(), e.getY());
		}
	}
}
