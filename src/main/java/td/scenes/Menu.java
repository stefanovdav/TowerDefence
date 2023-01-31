package td.scenes;

import td.game.Game;
import td.helper.LoadSave;
import td.ui.MyButton;

import java.awt.*;
import java.util.Random;

import static td.game.GameStates.*;

public class Menu extends GameScene implements SceneMethods {
	private final Random rand;
	private MyButton bPlaying, bSettings, bQuit, bEdit;

	public Menu(Game game) {
		super(game);
		rand = new Random();
		initButtons();
	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 310;
		int yOffset = 100;

		bPlaying = new MyButton("Play", x, y, w, h);
		bEdit = new MyButton("Edit", x, y + yOffset, w, h);
		bSettings = new MyButton("Settings", x, y + yOffset * 2, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);
	}

	@Override
	public void render(Graphics g) {
		drawButtons(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bPlaying.getBounds().contains(x, y)) {
			setGameStates(PLAYING);
		} else if (bSettings.getBounds().contains(x, y)) {
			setGameStates(SETTINGS);
		} else if (bQuit.getBounds().contains(x, y)) {
			System.exit(0);
		} else if (bEdit.getBounds().contains(x, y)) {
			setGameStates(EDIT);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		resetButtons();
		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		} else if (bEdit.getBounds().contains(x, y)) {
			bEdit.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		} else if (bEdit.getBounds().contains(x, y)) {
			bEdit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	@Override
	public void mouseDragged(int x, int y) {

	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
		bEdit.resetBooleans();
	}

	private void drawButtons(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0,0, 640, 800);

		g.drawImage(LoadSave.getLogo(), 28, 20, null);

		g.setFont(new Font("Butt", Font.BOLD, 20));
		bPlaying.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
		bEdit.draw(g);
	}
}
