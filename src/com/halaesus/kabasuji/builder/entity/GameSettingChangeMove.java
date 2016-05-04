package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class GameSettingChangeMove implements IMove {

	/**
	 * Level for the information
	 */
	AbstractLevel theLevel;
	
	/**
	 * BuilderView to update the setting
	 */
	AbstractBuilderView theBuilderView;
	
	/**
	 * Contruct an instance of GameSettingChangeMove when a game setting has been changed
	 * @param theLevel
	 * @param theBuilderView
	 */
	public GameSettingChangeMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		// Save the stuff
		this.theLevel = theLevel;
		this.theBuilderView = theBuilderView;
	}
	
}
