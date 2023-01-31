package td.managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import td.helper.LoadSave;
import td.helper.ImageFix;
import td.objects.Tile;

import static td.helper.Constants.Tiles.*;

public class TileManager {

	public Tile GRASS, WATER, ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R;
	private BufferedImage atlas;
	public ArrayList<Tile> tiles = new ArrayList<>();
	public ArrayList<Tile> roadS = new ArrayList<>();
	public ArrayList<Tile> roadC = new ArrayList<>();

	public TileManager() {

		loadAtlas();
		createTiles();

	}

	private void createTiles() {

		int id = 0;
		tiles.add(GRASS = new Tile(getSprite(9, 0), id++, GRASS_TILE));
		tiles.add(WATER = new Tile(getSprite(0, 0), id++, WATER_TILE));
		roadS.add(ROAD_LR = new Tile(getSprite(8, 0), id++, ROAD_TILE));
		roadS.add(ROAD_TB = new Tile(ImageFix.getRotImg(getSprite(8, 0), 90), id++, ROAD_TILE));
		roadC.add(ROAD_B_TO_R = new Tile(getSprite(7, 0), id++, ROAD_TILE));
		roadC.add(ROAD_L_TO_B = new Tile(ImageFix.getRotImg(getSprite(7, 0), 90), id++, ROAD_TILE));
		roadC.add(ROAD_L_TO_T = new Tile(ImageFix.getRotImg(getSprite(7, 0), 180), id++, ROAD_TILE));
		roadC.add(ROAD_T_TO_R = new Tile(ImageFix.getRotImg(getSprite(7, 0), 270), id++, ROAD_TILE));
		tiles.addAll(roadS);
		tiles.addAll(roadC);
	}

	private BufferedImage[] getImages(int firstX, int firstY, int secondX, int secondY) {

		return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX, secondY)};
	}

	private void loadAtlas() {
		atlas = LoadSave.getSpriteAtlas();
	}

	public Tile getTile(int id) {
		return tiles.get(id);
	}

	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
	}

	public ArrayList<Tile> getRoadS() {
		return roadS;
	}

	public ArrayList<Tile> getRoadC() {
		return roadC;
	}

	public int[][] getTypeArr() {
		int[][] idArr = LoadSave.getLevelData("new_level");
		int[][] typeArr = new int[idArr.length][idArr[0].length];

		for (int j = 0; j < idArr.length; j++) {
			for (int i = 0; i < idArr[j].length; i++) {
				int id = idArr[j][i];
				typeArr[j][i] = tiles.get(id).getTileType();
			}
		}

		return typeArr;

	}
}
