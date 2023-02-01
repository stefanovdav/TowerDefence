package td.helper;

import td.Main;
import td.objects.PathPoint;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LoadSave {

	public static BufferedImage getSpriteAtlas() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Objects.requireNonNull(Main.class.getResource("images/sprites3.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public static BufferedImage getLogo() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Objects.requireNonNull(Main.class.getResource("images/cryptoDefender.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public static BufferedImage getImgFromRes(String pathName) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Objects.requireNonNull(Main.class.getResource(pathName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public static void createFile() {
		File txtFile = new File("src/main/resources/td/testTextFile.txt");
		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<PathPoint> getLevelPathPoints(String name) {
		File lvlFile = new File("src/main/resources/td/" + name + ".txt");

		if (lvlFile.exists()) {
			List<Integer> list = readFromFile(lvlFile);
			List<PathPoint> points = new ArrayList<>();
			points.add(new PathPoint(list.get(400), list.get(401)));
			points.add(new PathPoint(list.get(402), list.get(403)));
			return points;
		} else {
			System.out.println("File: " + name + " does not exists! ");
			return null;
		}
	}

	public static void createLevel(String name, List<Integer> arr) {
		File newLevel = new File("src/main/resources/td/" + name + ".txt");
		if (newLevel.exists()) {
			System.out.println("File: " + name + " already exists!");
			return;
		} else {
			try {
				newLevel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			writeToFile(newLevel, arr, new PathPoint(0, 0), new PathPoint(0, 0));
		}
	}

	private static void writeToFile(File f, List<Integer> arr, PathPoint start, PathPoint end) {
		try {
			PrintWriter pw = new PrintWriter(f);
			for (Integer i : arr) {
				pw.println(i);
			}
			pw.println(start.getXCord());
			pw.println(start.getYCord());
			pw.println(end.getXCord());
			pw.println(end.getYCord());

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void saveLevel(String name, int[][] arr, PathPoint start, PathPoint end) {
		File levelFile = new File("src/main/resources/td/" + name + ".txt");

		if (levelFile.exists()) {
			writeToFile(levelFile, tdUtils.matricesToIntArrList(arr), start, end);
		} else {
			System.out.println("File: " + name + " does not exists! ");
			return;
		}
	}

	private static ArrayList<Integer> readFromFile(File file) {
		ArrayList<Integer> list = new ArrayList<>();

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}

			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static int[][] getLevelData(String name) {
		File lvlFile = new File("src/main/resources/td/" + name + ".txt");

		if (lvlFile.exists()) {
			ArrayList<Integer> list = readFromFile(lvlFile);
			return tdUtils.arrayListToIntMatrix(list, 20, 20);

		} else {
			System.out.println("File: " + name + " does not exists! ");
			return null;
		}

	}

	public static List<BufferedImage> getProjectiles() {
		List<BufferedImage> imgs = new ArrayList<>();
		try {
			BufferedImage tweet = ImageIO.read(Objects.requireNonNull(Main.class.getResource("images/twitterLogo.png")));
			imgs.add(tweet);

			BufferedImage dollar = ImageIO.read(Objects.requireNonNull(Main.class.getResource("images/BuffetDollar.png")));
			imgs.add(dollar);

			BufferedImage ftx = ImageIO.read(Objects.requireNonNull(Main.class.getResource("images/ftxLogo.png")));
			imgs.add(ftx);

			BufferedImage sz = ImageIO.read(Objects.requireNonNull(Main.class.getResource("images/ftxLogo.png")));
			imgs.add(ftx);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgs;
	}


}
