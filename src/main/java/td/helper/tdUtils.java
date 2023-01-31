package td.helper;

import td.objects.PathPoint;

import java.util.ArrayList;
import java.util.List;

import static td.helper.Constants.Direction.*;
import static td.helper.Constants.Tiles.ROAD_TILE;

public class tdUtils {
	public static int[][] arrayListToIntMatrix(ArrayList<Integer> list, int x, int y) {
		int[][] arr = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int index = i * 20 + j;
				arr[i][j] = list.get(index);
			}
		}
		return arr;
	}

	public static List<Integer> matricesToIntArrList(int[][] arr) {
		List<Integer> list = new ArrayList();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				list.add(arr[i][j]);
			}
		}
		return list;
	}

	public static int getHypoDistance(float x1, float x2, float y1, float y2){
		float xDiff = Math.abs(x1 - x2);
		float yDiff = Math.abs(y1 - y2);
		return (int)Math.hypot(xDiff, yDiff);
	}

	public static int[][] GetRoadDirArr(int[][] lvlTypeArr, PathPoint start, PathPoint end) {
		int[][] roadDirArr = new int[lvlTypeArr.length][lvlTypeArr[0].length];

		PathPoint currTile = start;
		int lastDir = -1;

		while (!IsCurrSameAsEnd(currTile, end)) {
			PathPoint prevTile = currTile;
			currTile = GetNextRoadTile(prevTile, lastDir, lvlTypeArr);
			lastDir = GetDirFromPrevToCurr(prevTile, currTile);
			roadDirArr[prevTile.getYCord()][prevTile.getXCord()] = lastDir;
		}
		roadDirArr[end.getYCord()][end.getXCord()] = lastDir;
		return roadDirArr;
	}
	private static int GetDirFromPrevToCurr(PathPoint prevTile, PathPoint currTile) {
		// Up or down
		if (prevTile.getXCord() == currTile.getXCord()) {
			if (prevTile.getYCord() > currTile.getYCord())
				return UP;
			else
				return DOWN;
		} else {
			// Right or left
			if (prevTile.getXCord() > currTile.getXCord())
				return LEFT;
			else
				return RIGHT;
		}

	}

	private static PathPoint GetNextRoadTile(PathPoint prevTile, int lastDir, int[][] lvlTypeArr) {

		int testDir = lastDir;
		PathPoint testTile = GetTileInDir(prevTile, testDir, lastDir);

		while (!IsTileRoad(testTile, lvlTypeArr)) {
			testDir++;
			testDir %= 4;
			testTile = GetTileInDir(prevTile, testDir, lastDir);
		}

		return testTile;
	}

	private static boolean IsTileRoad(PathPoint testTile, int[][] lvlTypeArr) {
		if (testTile != null)
			if (testTile.getYCord() >= 0)
				if (testTile.getYCord() < lvlTypeArr.length)
					if (testTile.getXCord() >= 0)
						if (testTile.getXCord() < lvlTypeArr[0].length)
							if (lvlTypeArr[testTile.getYCord()][testTile.getXCord()] == ROAD_TILE)
								return true;

		return false;
	}

	private static PathPoint GetTileInDir(PathPoint prevTile, int testDir, int lastDir) {

		switch (testDir) {
			case LEFT:
				if (lastDir != RIGHT)
					return new PathPoint(prevTile.getXCord() - 1, prevTile.getYCord());
			case UP:
				if (lastDir != DOWN)
					return new PathPoint(prevTile.getXCord(), prevTile.getYCord() - 1);
			case RIGHT:
				if (lastDir != LEFT)
					return new PathPoint(prevTile.getXCord() + 1, prevTile.getYCord());
			case DOWN:
				if (lastDir != UP)
					return new PathPoint(prevTile.getXCord(), prevTile.getYCord() + 1);
		}

		return null;
	}

	private static boolean IsCurrSameAsEnd(PathPoint currTile, PathPoint end) {
		if (currTile.getXCord() == end.getXCord())
			if (currTile.getYCord() == end.getYCord())
				return true;
		return false;
	}

}
