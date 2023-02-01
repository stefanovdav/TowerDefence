package td.ui;

import java.awt.*;

public class MyButton {
	public int x, y, width, height;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver;
	private boolean mousePressed;
	private final int id;

	public MyButton(String text, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.id = -1;
		initBounds();
	}

	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.id = id;
		initBounds();
	}

	public void draw(Graphics g) {
		//Body
		drawBody(g);

		//Border
		drawBorder(g);


		//Text
		drawText(g);
	}

	private void drawBorder(Graphics g) {
		g.setColor(Color.green);
		g.drawRect(x, y, width, height);
		if (mousePressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}

	}

	private void drawBody(Graphics g) {
		if (mouseOver) {
			g.setColor(Color.gray);
		} else {
			g.setColor(Color.blue);
		}
		g.fillRect(x, y, width, height);
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	private void drawText(Graphics g) {
		int textWidth = g.getFontMetrics().stringWidth(text);
		int textHeight = g.getFontMetrics().getHeight();
		g.drawString(text, x - textWidth / 2 + width / 2, y + textHeight / 2 + height / 2);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void resetBooleans() {
		this.mousePressed = false;
		this.mouseOver = false;
	}

	public int getId() {
		return id;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setText(String text) {
		this.text = text;
	}
}
