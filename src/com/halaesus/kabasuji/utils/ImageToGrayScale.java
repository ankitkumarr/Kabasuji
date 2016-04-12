package com.halaesus.kabasuji.utils;

//credit to http://www.tutorialspoint.com/java_dip/grayscale_conversion.html
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;

public class ImageToGrayScale {

	BufferedImage image;
	String filePath;
	int width;
	int height;

	public ImageToGrayScale() {
		for (int k = 1; k <= 35; k++) {
			try {
				File input = new File("src/resources/" + k + ".jpg");

				image = ImageIO.read(input);
				width = image.getWidth();
				height = image.getHeight();

				for (int i = 0; i < height; i++) {

					for (int j = 0; j < width; j++) {

						Color c = new Color(image.getRGB(j, i));
						int red = (int) (c.getRed() * 0.299);
						int green = (int) (c.getGreen() * 0.587);
						int blue = (int) (c.getBlue() * 0.114);
						Color newColor = new Color(red + green + blue,

								red + green + blue, red + green + blue);

						image.setRGB(j, i, newColor.getRGB());
					}
				}

				File ouptut = new File("src/resources/" + k + "_disabled.jpg");
				ImageIO.write(image, "jpg", ouptut);

			} catch (Exception e) {
			}
		}
	}

	static public void main(String args[]) throws Exception {
		ImageToGrayScale obj = new ImageToGrayScale();
	}
}
