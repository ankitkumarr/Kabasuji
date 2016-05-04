package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.player.entity.LevelSelector;
import com.halaesus.kabasuji.player.entity.PlayerProgress;

/**
 * Represents the main Model class of the KabaSuji app.
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class Model {
	
	/** The Splash Screen Model */
	SplashModel splashModel;
	
	/** The Player Progress Data Structure */
	PlayerProgress playerProgress;
	
	/** Level Selector Model to help the Player choose the Levels to play */
	LevelSelector levelSelector;
	
	/**
	 * Instantiates a Model class with a given PlayerProgress
	 * @param playerProgress the PlayerProgress to construct the Model class with
	 */
	public Model(PlayerProgress playerProgress) {
		splashModel = new SplashModel();
		this.playerProgress = playerProgress;
		levelSelector = new LevelSelector(playerProgress);
	}
	
	/**
	 * Constructs a Model class
	 */
	public Model() {
		splashModel = new SplashModel();
		this.playerProgress = new PlayerProgress();
		levelSelector = new LevelSelector(playerProgress);
	}

	/**
	 * Returns the Splash Screen Model
	 * @return
	 */
	public SplashModel getSplashModel() {
		return splashModel;
	}
	
	/**
	 * Returns the Level Selector Screen Model
	 * @return
	 */
	public LevelSelector getLevelSelectorModel() {
		return levelSelector;
	}

	/**
	 * Returns the Model that tracks a Player's Progress
	 * @return
	 */
	public PlayerProgress getPlayerProgress() {
		return playerProgress;
	}
	
}
