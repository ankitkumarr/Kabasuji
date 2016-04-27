package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.player.entity.LevelSelector;
import com.halaesus.kabasuji.player.entity.PlayerProgress;

public class Model {
	
	SplashModel splashModel;
	PlayerProgress playerProgress;
	LevelSelector levelSelector;
		
	public Model(PlayerProgress playerProgress) {
		splashModel = new SplashModel();
		this.playerProgress = playerProgress;
		levelSelector = new LevelSelector(playerProgress);
	}
	
	public Model() {
		splashModel = new SplashModel();
		this.playerProgress = new PlayerProgress();
		levelSelector = new LevelSelector(playerProgress);
	}

	public SplashModel getSplashModel() {
		return splashModel;
	}
	
	public LevelSelector getLevelSelectorModel() {
		return levelSelector;
	}

	public PlayerProgress getPlayerProgress() {
		return playerProgress;
	}
	
}
