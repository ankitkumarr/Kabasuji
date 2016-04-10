package com.halaesus.kabasuji.player.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class LevelSelector {

	ArrayList<Integer> starsAchieved;
	PlayerProgress playerProgress;
	BufferedImage backgroundImage;
	LevelList levels;

	public LevelSelector(PlayerProgress playerProgress) {
		this.playerProgress = playerProgress;
		// Grab the levels and the stars
		this.levels = this.playerProgress.levels;
		this.starsAchieved = this.playerProgress.starsEarned;
		// Get the background image
		try {
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/bacground.jpg"));
		} catch (IOException e) {
			backgroundImage = null; // We don't have an image to show :(
		}
    }
	
	public Iterator<LevelData> getLevelIterator() {
		return levels.getIterator();
	}
	
	public int getPlayerStarsAchieved(int idx) {
		return starsAchieved.get(idx);
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

}