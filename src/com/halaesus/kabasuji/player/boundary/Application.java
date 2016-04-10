package com.halaesus.kabasuji.player.boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.halaesus.kabasuji.player.entity.Model;

public class Application extends JFrame {
	
	JPanel currentView;
	Model masterModel;
	
	SplashView splashView;
	LevelSelectorView levelSelectorView;

	public Application(Model masterModel) {
		setTitle("Halaesus Kabasuji");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setResizable(false); // Cannot rescale the window
		setLocationRelativeTo(null); // Center in screen
		
		this.masterModel = masterModel;
		// Initialize
		splashView = new SplashView(masterModel.getSplashModel());
		levelSelectorView = new LevelSelectorView();
		// Show Splash Screen
		showSplashScreen();
		
	}
	
	public void showSplashScreen() {
		currentView = splashView; // Our current view is the Splash Screen
		setContentPane(currentView); // Show it on the UI
//		setUndecorated(true); // Get rid of the window border
	}

	public void showLevelSelector() {
		currentView = levelSelectorView;
		setContentPane(currentView);
//		setUndecorated(false);
		repaint();
	}

}