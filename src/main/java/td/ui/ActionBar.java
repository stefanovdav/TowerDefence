package td.ui;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import td.helper.Constants;
import td.objects.Tower;
import td.scenes.Playing;

import static td.game.GameState.*;

public class ActionBar extends Bar {

	private final Playing playing;
	private MyButton bMenu, bSell, bUpgrade, bPause;
	private ArrayList<MyButton> bTower = new ArrayList<>();
	private Tower displayedTower;
	private int gold = 100;
	private boolean showCost;
	private int towerType;
	private int lives = 3;

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		new DecimalFormat("0.0");
		initButtons();
	}

	public void draw(Graphics g) {

		// Background
		g.setColor(Color.darkGray);
		g.fillRect(x, y, width, height);

		drawButtons(g);
		drawDisplayedTower(g);
		drawWaveInfo(g);
		drawGoldAmount(g);
		if (showCost) {
			drawTowerPrice(g);
		}
		g.setColor(Color.green);
		g.drawString("Lives: " + getLives(), 215, 725);
	}

	private void drawTowerPrice(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(110, 740, 175, 50);
		g.setColor(Color.green);
		g.drawRect(110, 740, 175, 50);
		g.drawString(Constants.Towers.getName(towerType), 120, 760);
		g.drawString("Price: " + Constants.Towers.getTowerCost(towerType), 120, 780);

		if (Constants.Towers.getTowerCost(towerType) > gold) {
			g.setColor(Color.red);
			g.drawString("No money", 310, 725);
		}
	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 5, 650, 100, 30);
		bPause = new MyButton("Pause", 5, 690, 100, 30);

		bTower = new ArrayList<>();
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);
		for (int i = 0; i < 4; i++) {
			bTower.add(new MyButton("", xStart + xOffset * i, yStart, w, h, i));
		}
		bSell = new MyButton("Sell", 420, 702, 50, 30);
		bUpgrade = new MyButton("Upgrade", 530, 702, 70, 30);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bPause.draw(g);
		int i = 0;
		for (MyButton b : bTower) {
			if (b.isMouseOver()) {
				g.setColor(Color.lightGray);
			} else {
				g.setColor(Color.blue);
			}
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getTowerManager().getTowerImgs()[i], b.x, b.y, b.width, b.height, null);
			i += 2;
			drawButtonFeedback(g, b);
		}
	}

	private void drawGoldAmount(Graphics g) {
		g.drawString("Gold: " + gold, 110, 725);
	}

	private void drawWaveInfo(Graphics g) {
		g.setColor(Color.green);
		g.setFont(new Font("DaviTSans", Font.BOLD, 20));
		drawEnemiesLeft(g);
		drawWavesLeft(g);
	}

	private void drawWavesLeft(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex() + 1;
		int size = playing.getWaveManager().getWaves().size();
		g.drawString("Wave: " + current + " / " + size, 425, 760);
	}

	private void drawEnemiesLeft(Graphics g) {
		int remaining = playing.getEnemyManger().getAliveEnemiesNum();
		g.drawString("Enemies left: " + remaining, 425, 780);
	}

	private void drawDisplayedTower(Graphics g) {
		if (displayedTower != null) {

			g.setColor(Color.BLUE);
			g.fillRect(410, 645, 220, 95);
			g.setColor(Color.GREEN);
			g.drawRect(410, 645, 220, 95);
			g.drawRect(420, 650, 50, 50);
			g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType() * 2], 420, 650, 50, 50, null);
			g.setColor(Color.GREEN);
			g.setFont(new Font("DavidNewRoman", Font.BOLD, 15));
			g.drawString(Constants.Towers.getName(displayedTower.getTowerType()), 490, 660);
			g.drawString("ID: " + displayedTower.getId(), 490, 675);
			g.drawString("TIER: " + displayedTower.getTier(), 560, 675);

			drawSelectedTowerBorder(g);
			drawDisplayedTowerRange(g);

			//buttons
			bSell.draw(g);
			drawButtonFeedback(g, bSell);

			if (displayedTower.getTier() < 3 && gold >= getTowerUpgradePrice(displayedTower)) {
				bUpgrade.draw(g);
				drawButtonFeedback(g, bUpgrade);
			}

			if (bSell.isMouseOver()) {
				g.setColor(Color.red);
				g.drawString("Sell for " + getTowerSellPrice(displayedTower), 480, 695);
			} else if (bUpgrade.isMouseOver() && gold >= getTowerUpgradePrice(displayedTower)) {
				g.setColor(Color.green);
				g.drawString("Upgrade for " + getTowerUpgradePrice(displayedTower), 480, 695);
			}

		}
	}

	private int getTowerUpgradePrice(Tower displayedTower) {
		return (int) (Constants.Towers.getTowerCost(displayedTower.getTowerType()) * 0.5f);
	}

	private int getTowerSellPrice(Tower displayedTower) {
		int upgradeCost = (displayedTower.getTier() - 1) * getTowerUpgradePrice(displayedTower) / 2;
		return Constants.Towers.getTowerCost(displayedTower.getTowerType()) * 2 / 3 + upgradeCost;
	}

	private void drawDisplayedTowerRange(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawOval(displayedTower.getX() + 16 - (int) (displayedTower.getRange()), displayedTower.getY() + 16 - (int) (displayedTower.getRange()), (int) displayedTower.getRange() * 2, (int) displayedTower.getRange() * 2);
	}

	private void drawSelectedTowerBorder(Graphics g) {
		g.setColor(Color.green);
		g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			setGameState(MENU);
		} else if (bPause.getBounds().contains(x, y)) {
			togglePause();
		} else {
			if (displayedTower != null) {
				if (bSell.getBounds().contains(x, y)) {
					sellTower();
					return;
				} else if (bUpgrade.getBounds().contains(x, y) && displayedTower.getTier() < 3 && gold >= getTowerUpgradePrice(displayedTower)) {
					bUpgrade.getBounds().contains(x, y);
					playing.upgradeTower(displayedTower);
					gold -= getTowerUpgradePrice(displayedTower);
					return;
				}
			}
			for (MyButton b : bTower) {
				if (b.getBounds().contains(x, y)) {
					if (this.gold >= Constants.Towers.getTowerCost(b.getId())) {
						Tower selectedTower = new Tower(0, 0, -1, b.getId());
						playing.setSelectedTower(selectedTower);
						return;
					}
				}
			}
		}
	}

	private void togglePause() {
		playing.setGamePaused(!playing.isGamePaused());

		if (playing.isGamePaused()) {
			bPause.setText("Unpause");
		} else {
			bPause.setText("Pause");
		}
	}


	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bPause.setMouseOver(false);
		showCost = false;
		bUpgrade.setMouseOver(false);
		bSell.setMouseOver(false);
		for (MyButton b : bTower) {
			b.setMouseOver(false);
		}

		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		} else if (bPause.getBounds().contains(x, y)) {
			bPause.setMouseOver(true);
		} else {
			if (displayedTower != null) {
				if (bSell.getBounds().contains(x, y)) {
					bSell.setMouseOver(true);
					return;
				} else if (bUpgrade.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
					bUpgrade.getBounds().contains(x, y);
					bUpgrade.setMouseOver(true);
					return;
				}
			}
			for (MyButton b : bTower) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					showCost = true;
					towerType = b.getId();
				}
			}
		}
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else if (bPause.getBounds().contains(x, y)) {
			bPause.setMousePressed(true);
		} else {
			if (displayedTower != null) {
				if (bSell.getBounds().contains(x, y)) {
					bSell.setMousePressed(true);
					return;
				} else if (bUpgrade.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
					bUpgrade.getBounds().contains(x, y);
					bUpgrade.setMousePressed(true);
					return;
				}
			}
			for (MyButton b : bTower) {
				if (b.getBounds().contains(x, y)) {
					b.resetBooleans();
				}
			}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bPause.resetBooleans();
		for (MyButton b : bTower) {
			b.resetBooleans();
		}
		bUpgrade.resetBooleans();
		bSell.resetBooleans();
	}

	public void displayTower(Tower t) {
		displayedTower = t;
	}

	public void payForTower(int towerType) {
		this.gold -= Constants.Towers.getTowerCost(towerType);
	}

	public void addGold(int reward) {
		this.gold += reward;
	}

	private void sellTower() {
		gold += Constants.Towers.getTowerCost(displayedTower.getTowerType()) * 2 / 3;
		int upgradeCost = (displayedTower.getTier() - 1) * getTowerUpgradePrice(displayedTower) / 2;
		gold += upgradeCost;
		playing.removeTower(displayedTower);
	}

	public int getLives() {
		return lives;
	}

	public void removeALife() {
		lives--;
		if (lives < 1) {
			setGameState(GAME_OVER);
		}
	}

}
