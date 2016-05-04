package com.halaesus.kabasuji.player.entity;

/**
 * Models the thumbnails used to represent levels on
 * the level selector screen
 */
public class LevelThumbnail {
	/** Data about the level */
	LevelData theLevel;
	/** Number of stars to display on the thumbnail */
	int starsEarned;
	
	/**
	 * Constructs model with given data and the given number of stars
	 * @param theLevel Data about the level
	 * @param starsEarned Number of stars earned
	 */
	public LevelThumbnail(LevelData theLevel, int starsEarned) {
		this.theLevel = theLevel;
		this.starsEarned = starsEarned;
	}

	/**
	 * Gets the LevelData associated with this thumbnail
	 * @return theLevel
	 */
	public LevelData getLevelData() {
		return theLevel;
	}

	/**
	 * Gets the number of stars earned associated with this thumbnail
	 * @return starsEarned
	 */
	public int getStarsEarned() {
		return starsEarned;
	}
}
