package td.objects;

import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage sprite;
	private int id, tileType;


	public Tile(BufferedImage sprite, int id, int tileId) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileId;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public int getId() {
		return id;
	}

	public int getTileType() {
		return tileType;
	}
}
