package td.enemies;

import td.managers.EnemyManager;

import static td.helper.Constants.Enemies.BITCOIN;

public class Bitcoin extends Enemy{
	public Bitcoin(float x, float y, int id, EnemyManager enemyManager) {
		super(x, y, id, BITCOIN, enemyManager);
	}
}
