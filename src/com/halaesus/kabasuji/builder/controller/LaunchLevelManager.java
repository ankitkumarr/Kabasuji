package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.Application;
import com.halaesus.kabasuji.builder.boundary.LevelManagerDialog;

/**
 * Launches the Level Manager Dialog box
 */
public class LaunchLevelManager implements ActionListener{
	/**
	 * Launch the LevelManagerDialog and show the splash screen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		LevelManagerDialog.main(null);
		Application.instance().showSplashScreen();
	}
}
