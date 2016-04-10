package com.halaesus.kabasuji.player.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LevelSelector {
  
	PlayerProgress playerProgress;
	BufferedImage backgroundImage;
	LevelList levels;

	public LevelSelector(PlayerProgress playerProgress) {
		this.playerProgress = playerProgress;
		// Grab the levels
		this.levels = this.playerProgress.levels;
		// Get the background image
		try {
			backgroundImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/bacground.jpg"));
		} catch (IOException e) {
			backgroundImage = null; // We don't have an image to show :(
		}
    }
	
	public LevelList getLevels() {
		return levels;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

}