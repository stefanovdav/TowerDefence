package td.game;

import td.helper.LoadSave;
import td.managers.TileManager;
import td.scenes.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Game extends JFrame implements Runnable {
	public GameScreen gameScreen;
	private int updates;
	private long lastTimeUPS;
	private Thread gameThread;
	private final double FPS = 120.0;
	private static final double UPS = 50.0;
	private TileManager tileManager;

	//Classes
	private Render render;
	private Menu menu;
	private Settings settings;
	private Playing playing;
	private Editing editing;
	private GameOver gameOver;


	public Game() {
		tileManager = new TileManager();
		initClasses();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(gameScreen);
		createDefaultLevel();

		pack();
		setVisible(true);
	}

	private void initClasses() {
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		settings = new Settings(this);
		playing = new Playing(this);
		editing = new Editing(this);
		gameOver = new GameOver(this);
	}

	private void createDefaultLevel() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 400; i++) {
			list.add(0);
		}
		LoadSave.createLevel("new_Level", list);
	}

	private void callUPS() {
		if (System.currentTimeMillis() - lastTimeUPS >= 1000) {
			System.out.println("UPS: " + updates);
			updates = 0;
			lastTimeUPS = System.currentTimeMillis();
		}
	}

	private void updateGame() {
		switch (GameStates.gameStates) {
			case MENU -> System.out.print("");
			case PLAYING -> playing.update();
			case SETTINGS -> System.out.print("");
			case EDIT -> System.out.print("");
		}
	}

	public void start() {
		gameThread = new Thread(this) {};
		gameThread.start();
	}


	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int updates = 0;
		int frames = 0;
		long now;

		while (true) {
			now = System.nanoTime();
			//Render
			if (now - lastFrame >= timePerFrame) {
				lastFrame = now;
				repaint();
				frames++;
			}
			if (now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = now;
				updates++;
			}
			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
	}

	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Settings getSettings() {
		return settings;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Editing getEditing() {
		return editing;
	}

	public TileManager getTileManager() {
		return tileManager;
	}

	public static double getUPS() {
		return UPS;
	}

	public GameOver getGameOver() {
		return gameOver;
	}
}