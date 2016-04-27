package com.halaesus.kabasuji.player.boundary;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.LightningLevel;
import com.halaesus.kabasuji.shared.entity.Model;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;

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
		setResizable(false); // Cannot rescale the window
		// Set the size of the window
		// REFERNECE: http://stackoverflow.com/a/5916271
		setPreferredSize(new Dimension(1280, 720)); // Force the window to be 1280x720
		pack(); // Force Swing to respect it
		// Now, calculate the difference and set the new size
		setPreferredSize(new Dimension(1280 + (1280 - getContentPane().getSize().width), 
				                       720 + (720 - getContentPane().getSize().height)));
		pack(); // Force Swing to resize
		// Center in screen
		setLocationRelativeTo(null); // Center in screen
		// Save the Master Model given to us
		this.masterModel = masterModel;
		// Initialize
		splashView = new SplashView(masterModel.getSplashModel());
		levelSelectorView = new LevelSelectorView(masterModel.getLevelSelectorModel(), Application.this);
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
	
	public void showLevel(AbstractLevel theLevel) {
		// TODO: Fix preserve of level stuff when the same level is shown
		// Check the Level being passed to us
		if( theLevel.getLevelType().toUpperCase().equals("LIGHTNING") ) {
			currentView = new LightningLevelView(Application.this, (LightningLevel)theLevel);
			((LightningLevelView)currentView).performContentPaneShownActions(); // Perform actions to reset the board
		} else if( theLevel.getLevelType().toUpperCase().equals("RELEASE") )
			currentView = new ReleaseLevelView(Application.this, (ReleaseLevel)theLevel);
		else if( theLevel.getLevelType().toUpperCase().equals("PUZZLE") )
			currentView = new PuzzleLevelView(Application.this, (PuzzleLevel)theLevel);
		else
			return; 
		// Show it on the GUI
		setContentPane(currentView); // Show it on the UI
		repaint(); // Refresh the UI
	}

}