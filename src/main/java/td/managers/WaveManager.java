package td.managers;

import td.events.Wave;
import td.game.Game;
import td.scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveManager {
	private Playing playing;
	private List<Wave> waves = new ArrayList();
	private int getEnemySpawnTickLimit = 60 * 1;
	private int enemySpawnTick = getEnemySpawnTickLimit;
	private int enemyIndex, waveIndex;
	private boolean waveStartTimer;
	private int waveTickLimit = (int) Game.getUPS() * 15;
	private int waveTick = 0;
	private boolean waveTickTimerOver;


	public WaveManager(Playing playing) {
		this.playing = playing;
		createWaves();
	}

	public void update() {
		if (enemySpawnTick < getEnemySpawnTickLimit) {
			enemySpawnTick++;
		}

		if (waveStartTimer) {
			waveTick++;
			if (waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}
	}

	public void startWaveTimer() {
		waveStartTimer = true;
	}

	public void increaseWaveIndex() {
		waveIndex++;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}

	public boolean isWaveTimerOver() {
		return waveTickTimerOver;
	}

	public int getNextEnemy() {
		enemySpawnTick = 0;
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}

	public boolean isTimeForNewEnemy() {
		return enemySpawnTick >= getEnemySpawnTickLimit;
	}

	private void createWaves() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 0, 0, 0, 0, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 3, 0, 0, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 3, 2, 3, 2, 1))));
	}

	public List<Wave> getWaves() {
		return waves;
	}

	public boolean isWaveEmpty() {
		if (waveIndex == waves.size()) {
			return false;
		} else {
			return waves.get(waveIndex).getEnemyList().size() <= enemyIndex;
		}
	}

	public boolean isThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	public float getWaveTimeLeft() {
		return (float) (waveTickLimit - waveTick) / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}
}
