package td.helper;

public class Constants {
	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}

	public static class Enemies {
		public static final int DOGECOIN = 0;
		public static final int BINANCECOIN = 1;
		public static final int ETHEREUM = 2;
		public static final int BITCOIN = 3;

		public static float getSpeed(int enemyType) {
			switch (enemyType) {
				case DOGECOIN -> {
					return 0.45f;
				}
				case BINANCECOIN -> {
					return 0.65f;
				}
				case ETHEREUM -> {
					return 0.33f;
				}
				case BITCOIN -> {
					return 0.69f;
				}
			}
			return 0;
		}

		public static int getStartingHealth(int enemyType) {
			switch (enemyType) {
				case DOGECOIN -> {
					return 150;
				}
				case BINANCECOIN -> {
					return 200;
				}
				case ETHEREUM -> {
					return 500;
				}
				case BITCOIN -> {
					return 600;
				}
			}
			return 0;
		}

		public static int getReward(int enemyType) {
			switch (enemyType) {
				case DOGECOIN -> {
					return 3;
				}
				case BINANCECOIN -> {
					return 4;
				}
				case ETHEREUM -> {
					return 7;
				}
				case BITCOIN -> {
					return 10;
				}
			}
			return 0;
		}
	}

	public static class Towers {
		public static final int ELON = 0;
		public static final int WARREN = 1;
		public static final int SAM = 2;
		public static final int SZ = 3;

		public static String getName(int towerType) {
			switch (towerType) {
				case ELON -> {
					return "Elon Musk";
				}
				case WARREN -> {
					return "Warren Buffet";
				}
				case SAM -> {
					return "SBF";
				}
				case SZ -> {
					return "Changpeng Zhao";
				}
			}
			return "";
		}

		public static int getStartDmg(int towerType) {
			switch (towerType) {
				case ELON -> {
					return 20;
				}
				case WARREN -> {
					return 30;
				}
				case SAM -> {
					return 5;
				}
				case SZ -> {
					return 10;
				}
			}
			return 0;
		}

		public static float getStartRange(int towerType) {
			switch (towerType) {
				case ELON -> {
					return 100;
				}
				case WARREN -> {
					return 70;
				}
				case SAM -> {
					return 110;
				}
				case SZ -> {
					return 120;
				}
			}
			return 0;
		}

		public static int getTowerCost(int towerType) {
			switch (towerType) {
				case ELON -> {
					return 50;
				}
				case WARREN -> {
					return 70;
				}
				case SAM -> {
					return 40;
				}
				case SZ -> {
					return 80;
				}
			}
			return 0;
		}

		public static float getShootCooldown(int towerType) {
			switch (towerType) {
				case ELON -> {
					return 18;
				}
				case WARREN -> {
					return 21;
				}
				case SAM -> {
					return 15;
				}
				case SZ -> {
					return 11;
				}
			}
			return 0;
		}
	}

	public static class Projectiles {
		public static final int TWEET = 0;
		public static final int DOLLAR = 1;
		public static final int FTX = 2;
		public static final int SZWEAPON = 3;

		public static float getSpeed(int type) {
			switch (type) {
				case TWEET -> {
					return 6f;
				}
				case DOLLAR -> {
					return 5f;
				}
				case FTX -> {
					return 6.5f;
				}
				case SZWEAPON -> {
					return 5.5f;
				}
			}
			return 0;
		}
	}
}
