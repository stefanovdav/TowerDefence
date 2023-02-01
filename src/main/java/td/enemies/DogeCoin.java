package td.enemies;

import td.managers.EnemyManager;

import static td.helper.Constants.Enemies.DOGECOIN;

public class DogeCoin extends Enemy {
	public DogeCoin(float x, float y, int id, EnemyManager enemyManager) {
		super(id, x, y, DOGECOIN, enemyManager);
	}
}
