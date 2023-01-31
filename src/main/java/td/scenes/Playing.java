package td.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import td.enemies.Enemy;
import td.helper.Constants;
import td.helper.LoadSave;
import td.game.Game;
import td.managers.EnemyManager;
import td.managers.ProjectileManager;
import td.managers.TowerManager;
import td.managers.WaveManager;
import td.objects.PathPoint;
import td.objects.Tower;
import td.ui.ActionBar;

import static td.helper.Constants.Tiles.GRASS_TILE;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private ActionBar actionBar;
	private int mouseX, mouseY;
	private EnemyManager enemyManager;
	private PathPoint start, end;
	private TowerManager towerManager;
	private Tower selectedTower;
	private ProjectileManager projectileManager;
	private WaveManager waveManager;
	private boolean gamePaused = false;

	public Playing(Game game) {
		super(game);
		loadDefaultLevel();
		actionBar = new ActionBar(0, 640, 640, 160, this);
		enemyManager = new EnemyManager(this, start, end);
		towerManager = new TowerManager(this);
		projectileManager = new ProjectileManager(this);
		waveManager = new WaveManager(this);

	}

	public void setLevel(int[][] lvl, PathPoint start, PathPoint end) {
		this.lvl = lvl;
		this.start = start;
		this.end = end;
	}

	private void loadDefaultLevel() {
		lvl = LoadSave.getLevelData("new_level");
		List<PathPoint> points = LoadSave.getLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}

	public void setGamePaused (boolean gamePaused) {
		this.gamePaused = gamePaused;
	}


	@Override
	public void render(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
		actionBar.draw(g);
		enemyManager.draw(g);
		towerManager.draw(g);
		projectileManager.draw(g);
		drawSelectedTower(g);
		drawHighlight(g);
		drawWaveInfo(g);
	}

	private void drawWaveInfo(Graphics g) {

	}

	private void drawHighlight(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(mouseX, mouseY, 32, 32);
	}

	private void drawSelectedTower(Graphics g) {
		if (selectedTower != null) {
			g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType() * 2], mouseX, mouseY, null);
		}
	}

	public void update() {
		if (!gamePaused) {
			enemyManager.update();
			towerManager.update();
			projectileManager.update();
			waveManager.update();
			if (isAllEnemiesDead()) {
				if (waveManager.isThereMoreWaves()) {
					waveManager.startWaveTimer();
					if (waveManager.isWaveTimerOver()) {
						waveManager.increaseWaveIndex();
						enemyManager.getEnemies().clear();
						waveManager.resetEnemyIndex();
					}
				}
			}
			if (isTimeForNewEnemy()) {
				spawnEnemy();
			}
		}
	}


	private boolean isAllEnemiesDead() {
		if (waveManager.isWaveEmpty()) {
			for (Enemy e : enemyManager.getEnemies()) {
				if (e.isAlive()) {
					return false;
				}
			}
		}
		return true;
	}

	private void spawnEnemy() {
		enemyManager.spawnEnemy(waveManager.getNextEnemy());
	}

	private boolean isTimeForNewEnemy() {
		return !waveManager.isWaveEmpty() && waveManager.isTimeForNewEnemy();
	}

	public void addEnemy(int x, int y) {

	}

	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}

	private BufferedImage getSprite(int id) {
		return getGame().getTileManager().getSprite(id);
	}


	@Override
	public void mouseClicked(int x, int y) {

		if (y >= 640) {
			actionBar.mouseClicked(x, y);
		} else if (!isGamePaused()){
			if (selectedTower != null) {
				if (isTileGrass(mouseX, mouseY)) {
					if (getTowerAt(mouseX, mouseY) == null) {
						towerManager.addTower(selectedTower, mouseX, mouseY);
						removeGold(selectedTower.getTowerType());
						selectedTower = null;
					}
				}
			} else {
				Tower t = getTowerAt(mouseX, mouseY);
				actionBar.displayTower(t);
			}
		}
	}

	private void removeGold(int towerType) {
		actionBar.payForTower(towerType);
	}

	private boolean isTileGrass(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int tileType = getGame().getTileManager().getTile(id).getTileType();
		return tileType == GRASS_TILE;
	}

	public Tower getTowerAt(int x, int y) {
		return towerManager.getTowerAt(x, y);
	}

	@Override
	public void mouseMoved(int x, int y) {
			if (y >= 640) {
				actionBar.mouseMoved(x, y);
			} else if (!gamePaused) {
				mouseX = (x / 32) * 32;
				mouseY = (y / 32) * 32;
			}

	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640) {
			actionBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {

		actionBar.mouseReleased(x, y);

	}

	@Override
	public void mouseDragged(int x, int y) {
	}

	public int getTileType(int x, int y) {
		int xCord = x / 32;
		int yCord = y / 32;
		if (xCord < 0 || xCord > 19) {
			return 0;
		}
		if (yCord < 0 || yCord > 19) {
			return 0;
		}
		int id = lvl[y / 32][x / 32];
		return getGame().getTileManager().getTile(id).getTileType();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			selectedTower = null;
		}
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public void shootEnemy(Tower t, Enemy e) {
		projectileManager.newProjectile(t, e);
	}

	public EnemyManager getEnemyManger() {
		return enemyManager;
	}

	public WaveManager getWaveManager() {
		return waveManager;
	}

	public void rewardEnemy(int enemyType) {
		actionBar.addGold(Constants.Enemies.getReward(enemyType));
	}

	public void removeTower(Tower displayedTower) {
		towerManager.removeTower(displayedTower);
	}

	public void upgradeTower(Tower displayedTower) {
		towerManager.upgradeTower(displayedTower);
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void removeALife() {
		actionBar.removeALife();
	}
}