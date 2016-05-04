package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.player.entity.LevelList;
import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;

/**
 * Save the level being built to a file that can be opened 
 * by the player
 */
public class SaveToFile implements ActionListener{
	/** View where the level is being built */
	AbstractBuilderView builderView;
		
	/**
	 * Associates the given view with this controller
	 * @param builderView The View
	 */
    public SaveToFile(AbstractBuilderView builderView) {
    	this.builderView = builderView;
    }
    
    /**
     * Generates mementos from the level being built and saves them to disk
     */
    public void saveLevel() {
    	LevelList levelList = LevelList.loadList();
		AbstractLevelMemento memento = builderView.getLevel().generateMemento();
		levelList.overwriteLevel(memento, builderView.getLevel().getLevelIndex());
		levelList.saveList();
    }

    /**
     * Save the level being built
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		saveLevel();
	}
}
