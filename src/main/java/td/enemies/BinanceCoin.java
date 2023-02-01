package td.enemies;

import td.managers.EnemyManager;

import static td.helper.Constants.Enemies.BINANCECOIN;

public class BinanceCoin extends Enemy {
	public BinanceCoin(float x, float y, int id, EnemyManager enemyManager) {
		super(id, x, y, BINANCECOIN, enemyManager);
	}
}
