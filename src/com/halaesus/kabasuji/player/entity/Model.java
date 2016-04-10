package com.halaesus.kabasuji.player.entity;

public class Model {
	
	SplashModel splashModel;
	PlayerProgress playerProgress;
	LevelSelector levelSelector;
	
	public Model() {
		splashModel = new SplashModel();
		playerProgress = new PlayerProgress();
		levelSelector = new LevelSelector(playerProgress);
	}

	public SplashModel getSplashModel() {
		return splashModel;
	}
	
}
