package com.halaesus.kabasuji.player.boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.halaesus.kabasuji.player.entity.Model;

@SuppressWarnings("serial")
public class Application extends JFrame {
	
	JPanel currentView;
	Model masterModel;
	
	SplashView splashView;
	LevelSelectorView levelSelectorView;

	public Application(Model masterModel) {
		// Basic GUI Stuff
		setTitle("Kabasuji by Team Halaesus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setResizable(false); // Cannot rescale the window
		setLocationRelativeTo(null); // Center in screen
		
		this.masterModel = masterModel;
		// Initialize
		splashView = new SplashView(masterModel.getSplashModel());
		levelSelectorView = new LevelSelectorView(masterModel.getLevelSelectorModel());
		// Show Splash Screen
		showSplashScreen();
		
	}
	
	public void showSplashScreen() {
		currentView = splashView; // Our current view is the Splash Screen
		setContentPane(currentView); // Show it on the UI
	}

	public void showLevelSelector() {
		currentView = levelSelectorView; // Our current view is the Level Selector Screen
		setContentPane(currentView); // Show it on the UI
		repaint(); // Refresh the UI
	}

}