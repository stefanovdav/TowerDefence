package td.scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import td.helper.LoadSave;
import td.game.Game;
import td.objects.PathPoint;
import td.objects.Tile;
import td.ui.Toolbar;

import static td.helper.Constants.Tiles.ROAD_TILE;

public class Editing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private Tile selectedTile;
	private int mouseX, mouseY;
	private int lastTileX, lastTileY, lastTileId;
	private boolean drawSelect;
	private final Toolbar toolbar;
	private PathPoint start, end;

	public Editing(Game game) {
		super(game);
		loadDefaultLevel();
		toolbar = new Toolbar(0, 640, 640, 160, this);
	}

	private void loadDefaultLevel() {
		lvl = LoadSave.getLevelData("new_level");
		List<PathPoint> points = LoadSave.getLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}

	@Override
	public void render(Graphics g) {
		drawLevel(g);
		toolbar.draw(g);
		drawSelectedTile(g);
		drawPathPoints(g);
	}

	private void drawPathPoints(Graphics g) {
		if (start != null) {
			g.drawImage(toolbar.getbStartImg(), start.getXCord() * 32, start.getYCord() * 32, 32, 32, null);
		}
		if (end != null) {
			g.drawImage(toolbar.getbEndImg(), end.getXCord() * 32, end.getYCord() * 32, 32, 32, null);

		}

	}

	private void drawLevel(Graphics g) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}

	private BufferedImage getSprite(int spriteID) {
		return getGame().getTileManager().getSprite(spriteID);
	}

	private void drawSelectedTile(Graphics g) {
		if (selectedTile != null && drawSelect) {
			g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
		}

	}

	public void saveLevel() {
		LoadSave.saveLevel("new_level", lvl, start, end);
		this.getGame().getPlaying().setLevel(lvl, start, end);
	}

	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
		drawSelect = true;
	}

	private void changeTile(int x, int y) {
		if (selectedTile != null) {
			int tileX = x / 32;
			int tileY = y / 32;

			if (selectedTile.getId() >= 0) {

				if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId()) {
					return;
				}

				lastTileX = tileX;
				lastTileY = tileY;
				lastTileId = selectedTile.getId();

				lvl[tileY][tileX] = selectedTile.getId();
			} else {
				int id = lvl[tileY][tileX];
				if (getGame().getTileManager().getTile(id).getTileType() == ROAD_TILE) {
					if (selectedTile.getId() == -1) {
						start  = new PathPoint(tileX, tileY);
					} else {
						end = new PathPoint(tileX, tileY);
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640) {
			toolbar.mouseClicked(x, y);
		} else {
			changeTile(mouseX, mouseY);
		}

	}

	@Override
	public void mouseMoved(int x, int y) {

		if (y >= 640) {
			toolbar.mouseMoved(x, y);
			drawSelect = false;
		} else {
			drawSelect = true;
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}

	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640) {
			toolbar.mousePressed(x, y);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		toolbar.mouseReleased(x, y);

	}

	@Override
	public void mouseDragged(int x, int y) {
		if (y >= 640) {
		} else {
			changeTile(x, y);
		}
	}
}
