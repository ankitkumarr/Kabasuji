package com.halaesus.kabasuji.player.boundary;

import java.awt.image.BufferedImage;
import javax.swing.JButton;

import com.halaesus.kabasuji.player.entity.LevelData;

/**
 * 
 */
public class LevelThumbnailView extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770472682015033163L;

	/**
	 * Default constructor
	 */
	public LevelThumbnailView() {
	}

	/**
	 * 
	 */
	LevelData level;

	/**
	 * 
	 */
	BufferedImage[] stars;

	/**
	 * @param LevelData
	 *            level
	 */
	public LevelThumbnailView(LevelData level) {
		// TODO implement here
	}

}