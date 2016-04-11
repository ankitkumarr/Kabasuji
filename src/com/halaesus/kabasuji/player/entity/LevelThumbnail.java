package com.halaesus.kabasuji.player.entity;

public class LevelThumbnail {

	LevelData theLevel;
	int starsEarned;
	
	public LevelThumbnail(LevelData theLevel, int starsEarned) {
		this.theLevel = theLevel;
		this.starsEarned = starsEarned;
	}

	public LevelData getLevelData() {
		return theLevel;
	}

	public int getStarsEarned() {
		return starsEarned;
	}
	
}