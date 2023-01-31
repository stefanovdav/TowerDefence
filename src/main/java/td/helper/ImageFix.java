package td.helper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFix {

	public static BufferedImage getRotImg(BufferedImage img, int rotAngle) {
		int w = img.getWidth();
		int h = img.getHeight();

		BufferedImage newImage = new BufferedImage(w, h, img.getType());
		Graphics2D g2d = newImage.createGraphics();

		g2d.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();

		return newImage;
	}

	public static BufferedImage buildImage(BufferedImage[] images) {
		int w = images[0].getWidth();
		int h = images[0].getHeight();

		BufferedImage newImage = new BufferedImage(w, h, images[0].getType());
		Graphics2D g2d = newImage.createGraphics();

		for (BufferedImage i : images) {
			g2d.drawImage(i, 0, 0, null);
		}
		g2d.dispose();
		return newImage;
	}

	public static BufferedImage getBuildRotImage(BufferedImage[] images, int rotAngle, int rotIndex){
		images[rotIndex] = getRotImg(images[rotIndex],rotAngle);
		return buildImage(images);
	}
}
