package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.LevelManagerDialog;

public class LaunchLevelManager implements ActionListener{
	
		
    public LaunchLevelManager() {
    	
    }
    


	@Override
	public void actionPerformed(ActionEvent e) {
		LevelManagerDialog.main(null);
	}
}

