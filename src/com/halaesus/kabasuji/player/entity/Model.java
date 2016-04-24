package com.halaesus.kabasuji.player.entity;

public class Model {
	
	SplashModel splashModel;
	PlayerProgress playerProgress;
	LevelSelector levelSelector;
		
	public Model(PlayerProgress playerProgress) {
		this.splashModel = new SplashModel();
		this.playerProgress = playerProgress;// PlayerProgress(); // TODO: Read from a file
		this.levelSelector = new LevelSelector(playerProgress);
	}

	public SplashModel getSplashModel() {
		return splashModel;
	}
	
	public PlayerProgress getPlayerProgress(){
		return this.playerProgress;	
	}
	
	public LevelSelector getLevelSelectorModel() {
		return levelSelector;
	}
	
}
