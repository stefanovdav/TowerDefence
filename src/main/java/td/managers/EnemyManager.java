package td.managers;

import td.enemies.*;
import td.helper.LoadSave;
import td.helper.tdUtils;
import td.objects.PathPoint;
import td.scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static td.helper.Constants.Direction.*;
import static td.helper.Constants.Enemies.*;

public class EnemyManager {
	private final Playing playing;
	private final BufferedImage[] enemyImgs;
	private final List<Enemy> enemies = new ArrayList<>();
	//	private float speed;
	private final PathPoint start;
	private final PathPoint end;
	private int[][] roadDirArr;

	public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
		this.playing = playing;
		this.start = start;
		this.end = end;
		enemyImgs = new BufferedImage[4];
		loadEnemyImages();
		loadRoadDirArr();

	}

	public void addEnemy(int enemyType) {
		int x = start.getXCord() * 32;
		int y = start.getYCord() * 32;

		switch (enemyType) {
			case DOGECOIN -> enemies.add(new DogeCoin(x, y, 0, this));
			case BINANCECOIN -> enemies.add(new BinanceCoin(x, y, 0, this));
			case ETHEREUM -> enemies.add(new Ethereum(x, y, 0, this));
			case BITCOIN -> enemies.add(new Bitcoin(x, y, 0, this));
		}
	}

	private void loadEnemyImages() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		enemyImgs[0] = atlas.getSubimage(7 * 32, 32, 32, 32);
		enemyImgs[1] = atlas.getSubimage(7 * 32, 2 * 32, 32, 32);
		enemyImgs[2] = atlas.getSubimage(8 * 32, 2 * 32, 32, 32);
		enemyImgs[3] = atlas.getSubimage(9 * 32, 2 * 32, 32, 32);
	}

	public void update() {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				updateEnemyMoveNew(e);
			}
		}
	}

	private void loadRoadDirArr() {
		roadDirArr = tdUtils.GetRoadDirArr(playing.getGame().getTileManager().getTypeArr(), start, end);
	}

	private void updateEnemyMoveNew(Enemy e) {
		PathPoint currTile = getEnemyTile(e);
		int dir = roadDirArr[currTile.getYCord()][currTile.getXCord()];

		e.move(getSpeed(e.getEnemyType()), dir);

		PathPoint newTile = getEnemyTile(e);

		if (!isTilesTheSame(currTile, newTile)) {
			if (isTilesTheSame(newTile, end)) {
				e.kill();
				playing.removeALife();
			}
			int newDir = roadDirArr[newTile.getYCord()][newTile.getXCord()];
			if (newDir != dir) {
				e.setPos(newTile.getXCord() * 32, newTile.getYCord() * 32);
				e.setLastDir(newDir);
			}
		}

	}

	private PathPoint getEnemyTile(Enemy e) {

		return switch (e.getLastDir()) {
			case LEFT -> new PathPoint((int) ((e.getX() + 31) / 32), (int) (e.getY() / 32));
			case UP -> new PathPoint((int) (e.getX() / 32), (int) ((e.getY() + 31) / 32));
			default -> new PathPoint((int) (e.getX() / 32), (int) (e.getY() / 32));
		};

	}

	public void spawnEnemy(int nextEnemy) {
		addEnemy(nextEnemy);
	}

	private boolean isTilesTheSame(PathPoint currTile, PathPoint newTile) {
		if (currTile.getXCord() == newTile.getXCord()) {
			return currTile.getYCord() == newTile.getYCord();
		}
		return false;
	}

/*
 private boolean isAtEnd(Enemy e) {
 	return (int) e.getX() / 32 == end.getXCord() && (int) e.getY() / 32 == end.getYCord();

 }

 private int getTileType(int x, int y) {
 	return playing.getTileType(x, y);
 }
 private float getSpeedAndWidth(int dir, int enemyType) {
 	if (dir == LEFT) {
 		return -getSpeed(enemyType);
 	} else if (dir == RIGHT) {
 		return getSpeed(enemyType) + 32;
 	} else {
 		return 0;
 	}
 }

 private float getSpeedAndHeight(int dir, int enemyType) {
 	if (dir == UP) {
 		return -getSpeed(enemyType);
 	} else if (dir == DOWN) {
 		return getSpeed(enemyType) + 32;
 	} else {
 		return 0;
 	}
 }
*/

	public void draw(Graphics g) {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				drawEnemy(e, g);
				drawEnemyHealthBar(e, g);
			}
		}

	}

	private void drawEnemyHealthBar(Enemy e, Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) e.getX() + 16 - (getNewHealthBarWidth(e) / 2), (int) e.getY() - 8, getNewHealthBarWidth(e), 3);
	}

	private int getNewHealthBarWidth(Enemy e) {
		int healthBarWidth = 20;
		return (int) (healthBarWidth * e.getHealthBarPercentage());
	}

	private void drawEnemy(Enemy e, Graphics g) {
		g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public int getAliveEnemiesNum() {
		int size = 0;
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				size++;
			}
		}
		return size;
	}

	public void rewardPlayer(int enemyType) {
		playing.rewardEnemy(enemyType);
	}
}