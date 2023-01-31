package td.game;

public enum GameStates {
	PLAYING, MENU, SETTINGS, EDIT, GAME_OVER;
	public static GameStates gameStates = MENU;
	public static void setGameStates(GameStates gameState){
		gameStates = gameState;
	}

}
