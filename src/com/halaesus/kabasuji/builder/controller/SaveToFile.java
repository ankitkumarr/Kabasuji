package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.player.entity.LevelList;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.memento.AbstractLevelMemento;

public class SaveToFile implements ActionListener{
	AbstractBuilderView builderView;
		
    public SaveToFile(AbstractBuilderView builderView) {
    	this.builderView = builderView;
    }
    
    public void savelLevel() {
    	LevelList levelList = LevelList.loadList();
		AbstractLevelMemento memento = builderView.getLevel().generateMemento();
		levelList.overwriteLevel(memento, builderView.getLevel().getLevelIndex());
		levelList.saveList();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		savelLevel();
	}
}

