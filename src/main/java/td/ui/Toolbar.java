package td.ui;

import static td.game.GameStates.MENU;
import static td.game.GameStates.setGameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import td.helper.LoadSave;
import td.objects.Tile;
import td.scenes.Editing;

public class Toolbar extends Bar {
	private Editing editing;
	private MyButton bMenu, bSave;
	private MyButton bStart, bEnd;
	private BufferedImage bStartImg, bEndImg;
	private Tile selectedTile;
	private ArrayList<MyButton> tileButtons = new ArrayList<>();

	public Toolbar(int x, int y, int width, int height, Editing editing) {
		super(x, y, width, height);
		this.editing = editing;
		initImages();
		initButtons();
	}

	private void initImages() {
		bStartImg = LoadSave.getSpriteAtlas().getSubimage(9 * 32, 3 * 32, 32, 32);
		bEndImg = LoadSave.getImgFromRes("images/trading-212.png");
	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		bSave = new MyButton("Save", 2, 674, 100, 30);

		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);
		int i = 0;

		for (Tile tile : editing.getGame().getTileManager().tiles) {
			tileButtons.add(new MyButton("", xStart + xOffset * i, yStart, w, h, i));
			i++;
		}
		bStart = new MyButton("Start", xStart, yStart + xOffset, w, h, i++);
		bEnd = new MyButton("End", xStart + xOffset, yStart + xOffset, w, h, i);
	}

	private void saveLevel() {
		editing.saveLevel();
	}

	public void draw(Graphics g) {

		// Background
		g.setColor(Color.gray);
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bSave.draw(g);
		drawPathButton(g, bStart, bStartImg);
		drawPathButton(g, bEnd, bEndImg);
		drawTileButtons(g);
		drawSelectedTile(g);

	}

	private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
		g.drawImage(img, b.x, b.y, b.width, b.height, null);
		drawButtonFeedback(g, b);
	}

	private void drawSelectedTile(Graphics g) {

		if (selectedTile != null) {
			g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
			g.setColor(Color.RED);
			g.drawRect(550, 650, 50, 50);
		}

	}

	private void drawTileButtons(Graphics g) {
		for (MyButton b : tileButtons) {

			// Sprite
			g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);

			// MouseOver
			if (b.isMouseOver()) {
				g.setColor(Color.white);
			} else {
				g.setColor(Color.BLACK);
			}
			// Border
			g.drawRect(b.x, b.y, b.width, b.height);

			// MousePressed
			if (b.isMousePressed()) {
				g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
				g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
			}
		}
	}

	public BufferedImage getButtImg(int id) {
		return editing.getGame().getTileManager().getSprite(id);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			setGameStates(MENU);
		} else if (bSave.getBounds().contains(x, y)) {
			saveLevel();
		} else if (bStart.getBounds().contains(x, y)) {
			selectedTile = new Tile(bStartImg, -1, -1);
			editing.setSelectedTile(selectedTile);
		} else if (bEnd.getBounds().contains(x, y)) {
			selectedTile = new Tile(bEndImg, -2, -2);
			editing.setSelectedTile(selectedTile);
		} else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					selectedTile = editing.getGame().getTileManager().getTile(b.getId());
					editing.setSelectedTile(selectedTile);
					return;
				}
			}
		}

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		bStart.setMouseOver(false);
		bEnd.setMouseOver(false);
		for (MyButton b : tileButtons) {
			b.setMouseOver(false);
		}
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		} else if (bSave.getBounds().contains(x, y)) {
			bSave.setMouseOver(true);
		} else if (bStart.getBounds().contains(x, y)) {
			bStart.setMouseOver(true);
		} else if (bEnd.getBounds().contains(x, y)) {
			bEnd.setMouseOver(true);
		} else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}

	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else if (bSave.getBounds().contains(x, y)) {
			bSave.setMousePressed(true);
		} else if (bStart.getBounds().contains(x, y)) {
			bStart.setMousePressed(true);
		} else if (bEnd.getBounds().contains(x, y)) {
			bEnd.setMousePressed(true);
		} else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSave.resetBooleans();
		for (MyButton b : tileButtons) {
			b.resetBooleans();
		}
	}

	public BufferedImage getbStartImg() {
		return bStartImg;
	}

	public BufferedImage getbEndImg() {
		return bEndImg;
	}
}
