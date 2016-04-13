package com.halaesus.kabasuji.builder.boundary;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

public class ImageLoader extends SwingWorker<Void, Object> {
	
	static String path = "/resources/";
	static String[] files = {"save.png", "open.png", "undo.png", "redo.png", "flipHorizontal.png",
			"flipVertical.png", "rotateCW.png", "rotateCC.png", "playerBackground.jpg",
			"bullpenWindow.jpg", "paletteWindow.jpg", "board.jpg"};
	
	@Override
	protected Void doInBackground() throws Exception {
		HashMap<String, BufferedImage> map = Application.instance().getAllImages();
		for (int i = 0; i < files.length; i++) {
			map.put(files[i], ImageIO.read(getClass().getResource(path + files[i])));
		}
		for (int i = 1; i <= 35; i++) {
			map.put(i + ".jpg", ImageIO.read(getClass().getResource(path + i + ".jpg")));
		}
		return null;
	}

	protected void done() {
		Application.instance().showSplashScreen(0);
	}
}
