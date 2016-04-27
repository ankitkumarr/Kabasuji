package com.halaesus.kabasuji.player.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.halaesus.kabasuji.shared.entity.SplashModel;

public class LevelSelector {

	ArrayList<LevelThumbnail> thumbnails;
	PlayerProgress playerProgress;
	BufferedImage backgroundImage;
	LevelList levels;

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
			// Create a new LevelThumbnail Model and add it to the ArrayList
			thumbnails.add(new LevelThumbnail(iterator.next(), 0));
			//thumbnails.add(new LevelThumbnail(iterator.next(), starsEarned.get(idx)));
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

	public LevelList getLevelList(){
		return this.levels;
	}
}