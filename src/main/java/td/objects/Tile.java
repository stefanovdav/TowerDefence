package td.objects;

import java.awt.image.BufferedImage;

public class Tile {
	private final BufferedImage sprite;
	private final int id;
	private final int tileType;


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
