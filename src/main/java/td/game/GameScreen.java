package td.game;

import td.inputs.KeyboardListener;
import td.inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
	private Game game;
	private Dimension size;
	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;


	public GameScreen(Game game) {
		this.game = game;
		setPanelSize();
	}

	private void setPanelSize() {
		size = new Dimension(640, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}
	public void initInputs() {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new KeyboardListener(game);

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);

		requestFocus();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.getRender().render(g);
	}
}
