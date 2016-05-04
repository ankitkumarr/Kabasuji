package com.halaesus.kabasuji.builder.boundary;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

/**
 * SwingWorker to load all required images from disk before
 * launching the Level Manager Dialog
 * 
 * @author apanetta
 *
 */
public class ImageLoader extends SwingWorker<Void, Object> {
	/** Path where we store the images */
	static String path = "/resources/";
	/** Names of files with unique names */
	static String[] files = {"save.png", "open.png", "undo.png", "redo.png", "flipHorizontal.png",
			"flipVertical.png", "rotateCW.png", "rotateCC.png", "gridWithBoard.jpg",
			"bullpenWindow.jpg", "paletteWindow.jpg", "trashcan_empty.png", "trashcan_full.png"};
	
	/**
	 * Loads all of the images from disk and stores them in the Application's collection
	 */
	@Override
	protected Void doInBackground() throws Exception {
		HashMap<String, BufferedImage> map = Application.instance().getAllImages();
		for (int i = 0; i < files.length; i++) {
			map.put(files[i], ImageIO.read(getClass().getResource(path + files[i])));
		}
		for (int i = 1; i <= 35; i++) {
			map.put(i + ".jpg", ImageIO.read(getClass().getResource(path + i + ".jpg")));
			map.put(i + "_disabled.jpg", ImageIO.read(getClass().getResource(path + i + "_disabled.jpg")));
		}
		return null;
	}

	/**
	 * Launch the level manager dialog when done
	 */
	protected void done() {
		LevelManagerDialog.main(null);
	}
}
