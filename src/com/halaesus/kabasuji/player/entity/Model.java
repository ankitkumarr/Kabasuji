package com.halaesus.kabasuji.player.entity;

public class Model {
	
	SplashModel splashModel;
	PlayerProgress playerProgress;
	LevelSelector levelSelector;
		
	public Model() {
		splashModel = new SplashModel();
		playerProgress = new PlayerProgress(); // TODO: Read from a file
		levelSelector = new LevelSelector(playerProgress);
	}

	public SplashModel getSplashModel() {
		return splashModel;
	}
	
	public LevelSelector getLevelSelectorModel() {
		return levelSelector;
	}
	
}
