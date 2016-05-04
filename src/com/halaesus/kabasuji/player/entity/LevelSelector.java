package com.halaesus.kabasuji.player.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.halaesus.kabasuji.shared.entity.SplashModel;

/**
 * Models the Level Selector Screen for the KabaSuji player
 */
public class LevelSelector {
	/** Models for the thumbnails for each level */
	ArrayList<LevelThumbnail> thumbnails;
	/** The player's progression through the game */
	PlayerProgress playerProgress;
	/** The background picture */
	BufferedImage backgroundImage;
	/** Data about the levels */
	LevelList levels;

	/**
	 * Construct a LevelSelector model based on the given progress
	 * @param playerProgress The player's progression through the game
	 */
	public LevelSelector(PlayerProgress playerProgress) {
		this.playerProgress = playerProgress;
		// Grab the levels and the stars
		this.levels = this.playerProgress.levels;
		ArrayList<Integer> starsEarned = this.playerProgress.starsEarned;
		// Initialize some stuff
		thumbnails = new ArrayList<LevelThumbnail>();
		// Create a bunch of LevelThumbnails now
		int idx = 0;
		for( Iterator<LevelData> iterator = levels.getIterator(); iterator.hasNext(); idx++) {
			if (idx < starsEarned.size()){
				// Create a new LevelThumbnail Model and add it to the ArrayList
				thumbnails.add(new LevelThumbnail(iterator.next(), starsEarned.get(idx)));
			}
			else 
				thumbnails.add(new LevelThumbnail(iterator.next(), 0));
		}
		// Get the background image
		try {
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/bacground.jpg"));
		} catch (IOException e) {
			backgroundImage = null; // We don't have an image to show :(
		}
    }
	
	/**
	 * Put the most recent number of stars in each thumbnail
	 */
	public void updateStars(){
		thumbnails = new ArrayList<LevelThumbnail>();
		int idx = 0;
		for( Iterator<LevelData> iterator = levels.getIterator(); iterator.hasNext(); idx++) {
			// Create a new LevelThumbnail Model and add it to the ArrayList
			thumbnails.add(new LevelThumbnail(iterator.next(), playerProgress.starsEarned.get(idx)));
		}
	}
	
	/**
	 * Gets the list of thumbnails
	 * @return thumbnails
	 */
	public ArrayList<LevelThumbnail> getThumbnails() {
		return thumbnails;
	}

	/** 
	 * Gets the background image
	 * @return backgroundImage
	 */
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * Gets the LevelList
	 * @return levels
	 */
	public LevelList getLevelList(){
		return this.levels;
	}
}
