package td.managers;

import td.enemies.Enemy;
import td.helper.LoadSave;
import td.helper.tdUtils;
import td.objects.Tower;
import td.scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TowerManager {
	private final Playing playing;
	private BufferedImage[] towerImgs;
	private final List<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;


	public TowerManager(Playing playing) {
		this.playing = playing;
		loadTowerImgs();
	}

	private void loadTowerImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		towerImgs = new BufferedImage[8];
		towerImgs[0] = atlas.getSubimage(9 * 32, 32, 32, 32);
		towerImgs[1] = atlas.getSubimage(8 * 32, 32, 32, 32);
		towerImgs[2] = atlas.getSubimage(32, 3 * 32, 32, 32);
		towerImgs[3] = atlas.getSubimage(2 * 32, 3 * 32, 32, 32);
		towerImgs[4] = atlas.getSubimage(3 * 32, 3 * 32, 32, 32);
		towerImgs[5] = atlas.getSubimage(4 * 32, 3 * 32, 32, 32);
		towerImgs[6] = atlas.getSubimage(5 * 32, 3 * 32, 32, 32);
		towerImgs[7] = atlas.getSubimage(6 * 32, 3 * 32, 32, 32);
	}

	public void draw(Graphics g) {
		for (Tower t : towers) {
			if (t.getTier() == 1) {
				g.drawImage(towerImgs[t.getTowerType() * 2], t.getX(), t.getY(), null);
			} else {
				int index = t.getTowerType() * 2 + 1;
				g.drawImage(towerImgs[index], t.getX(), t.getY(), null);
			}
		}
	}

	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
	}

	public void update() {
		for (Tower t : towers) {
			t.update();
			attackCloseEnemy(t);
		}
	}

	public void attackCloseEnemy(Tower t) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive()) {
				if (isEnemyInRange(t, e)) {
					if (t.isCooldownOver()) {
						playing.shootEnemy(t, e);
						t.resetCooldown();
					}
				}
			}
		}
	}

	private boolean isEnemyInRange(Tower t, Enemy e) {
		int range = tdUtils.getHypoDistance(t.getX(), e.getX(), t.getY(), e.getY());
		return range < t.getRange();
	}

	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}


	public Tower getTowerAt(int x, int y) {
		for (Tower t : towers) {
			if (t.getX() == x && t.getY() == y) {
				return t;
			}
		}
		return null;
	}

	public void removeTower(Tower displayedTower) {
		for (int i = 0; i < towers.size(); i++) {
			if (towers.get(i).getId() == displayedTower.getId()) {
				towers.remove(i);
			}
		}
	}

	public void upgradeTower(Tower displayedTower) {
		for (Tower t : towers) {
			if (t.getId() == displayedTower.getId()) {
				t.upgrade();
			}
		}
	}
}
