package td.enemies;

import td.helper.Constants;
import td.managers.EnemyManager;

import java.awt.*;

import static td.helper.Constants.Direction.*;

public abstract class Enemy {
	protected float x, y;
	protected int health, maxHealth;
	protected Rectangle bounds;
	protected int id;
	protected int enemyType;
	protected int lastDir;
	protected boolean alive;
	protected EnemyManager enemyManager;

	public Enemy(float x, float y, int id, int enemyType, EnemyManager enemyManager) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.enemyType = enemyType;
		this.enemyManager = enemyManager;
		bounds = new Rectangle((int) x, (int) y, 32, 32);
		lastDir = -1;
		setStartingHealth();
		alive = true;
	}

	public void move(float speed, int dir) {
		lastDir = dir;
		switch (dir) {
			case LEFT -> {
				this.x -= speed;
			}
			case UP -> {
				this.y -= speed;
			}
			case RIGHT -> {
				this.x += speed;
			}
			case DOWN -> {
				this.y += speed;
			}
		}
		updateBounds();
	}

	private void updateBounds() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}

	private void setStartingHealth() {
		health = Constants.Enemies.getStartingHealth(enemyType);
		maxHealth = health;
	}

	public float getHealthBarPercentage() {
		return (float) health / (float) maxHealth;
	}

	public void hurt(int dmg) {
		this.health -= dmg;
		if (health <= 0) {
			kill();
			enemyManager.rewardPlayer(enemyType);
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getHealth() {
		return health;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getId() {
		return id;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public int getLastDir() {
		return lastDir;
	}

	public void setPos(int xCord, int yCord) {
		this.x = x;
		this.y = y;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setLastDir(int newDir) {
		lastDir = newDir;
	}

	public void kill() {
		alive = false;
	}
}
