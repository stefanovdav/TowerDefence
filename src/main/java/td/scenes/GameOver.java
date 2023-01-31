package td.scenes;

import td.game.Game;
import td.helper.LoadSave;
import td.ui.MyButton;

import java.awt.*;

import static td.game.GameStates.*;

public class GameOver extends GameScene implements SceneMethods {

	private MyButton bMenu, bQuit;

	public GameOver(Game game) {
		super(game);
		initButtons();
	}

	public void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 500;
		int yOffset = 100;

		bMenu = new MyButton("Menu", x, y, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset, w, h);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0,0, 640, 800);

		g.drawImage(LoadSave.getLogo(), 28, 20, null);

		g.setColor(Color.red);
		g.setFont(new Font("DaviTitle", Font.BOLD, 80));
		g.drawString("Game Over", 120, 400);

		g.setFont(new Font("Butt", Font.BOLD, 20));
		bMenu.draw(g);
		bQuit.draw(g);

	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			setGameStates(MENU);
		} else if (bQuit.getBounds().contains(x, y)) {
			System.exit(0);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bQuit.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {

	}
}
