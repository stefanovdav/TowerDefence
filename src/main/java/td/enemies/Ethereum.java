package td.enemies;

import td.managers.EnemyManager;

import static td.helper.Constants.Enemies.ETHEREUM;

public class Ethereum extends Enemy {
	public Ethereum(float x, float y, int id, EnemyManager enemyManager) {
		super(x, y, id, ETHEREUM, enemyManager);
	}
}
