package com.halaesus.kabasuji.player.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class LevelSelector {

	ArrayList<LevelThumbnail> thumbnails;
	PlayerProgress playerProgress;
	BufferedImage backgroundImage;

	public LevelSelector(PlayerProgress playerProgress) {
		this.playerProgress = playerProgress;
		// Grab the levels and the stars
		LevelList levels = this.playerProgress.levelList;
		ArrayList<Integer> starsEarned = this.playerProgress.starsEarned;
		// Initialize some stuff
		thumbnails = new ArrayList<LevelThumbnail>();
		// Create a bunch of LevelThumbnails now
		int idx = 0;
		for( Iterator<LevelData> iterator = levels.getIterator(); iterator.hasNext(); idx++) {
			// Create a new LevelThumbnail Model and add it to the ArrayList

			// TODO This is a hacky fix to serious problem -BrianKD
			// by default say no stars
			// because what if there are more levels than there are stars (user created more levels)
			// then we get a runtime exception
			int stars = -1;
			if (idx < starsEarned.size() ) stars = starsEarned.get(idx);
			if (idx < 3) stars = 0; // the first three levels should always be playable
			thumbnails.add(new LevelThumbnail(iterator.next(), stars));
		}
		// Get the background image
		try {
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/bacground.jpg"));
		} catch (IOException e) {
			backgroundImage = null; // We don't have an image to show :(
		}
    }
	
	public ArrayList<LevelThumbnail> getThumbnails() {
		return thumbnails;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

}