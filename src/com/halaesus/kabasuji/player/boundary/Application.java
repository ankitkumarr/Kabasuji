package com.halaesus.kabasuji.player.boundary;

import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.LightningLevel;
import com.halaesus.kabasuji.shared.entity.Model;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;

/**
 * Represents the top-level Applciation
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
@SuppressWarnings("serial")
public class Application extends JFrame {
	
	/** Holds the currently shown JPanel */
	JPanel currentView;
	
	/** Holds the master model that gives all the backing data */
	Model masterModel;
	
	/** Holds the SplashView Model that is necessary to show the Splash Screen */
	SplashView splashView;
	
	/** Holds the LevelSelectorView that is necessary to show the Level Selector Screen */ 
	LevelSelectorView levelSelectorView;

	/**
	 * Instantiates a top-level application with the necessary backing Model data
	 * @param masterModel
	 */
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
	
	/**
	 * Replaces the current screen to show the Splash Screen
	 */
	public void showSplashScreen() {
		currentView = splashView; // Our current view is the Splash Screen
		setContentPane(currentView); // Show it on the UI
	}

	/**
	 * Replaces the current screen with the LevelSelector Screen
	 */
	public void showLevelSelector() {
		levelSelectorView = new LevelSelectorView(masterModel.getLevelSelectorModel(), Application.this);
		currentView = levelSelectorView; // Our current view is the Level Selector Screen
		setContentPane(currentView); // Show it on the UI
		repaint(); // Refresh the UI
	}
	
	/**
	 * Replaces the current screen with one of the levels requested to be shown
	 * @param theLevel AbstractLevel that has to be shown
	 */
	public void showLevel(AbstractLevel theLevel) {
		// Check the Level being passed to us
		if( theLevel.getLevelType().toUpperCase().equals("LIGHTNING") )
			currentView = new LightningLevelView(Application.this, new LightningLevel((LightningLevel)theLevel));
		else if( theLevel.getLevelType().toUpperCase().equals("RELEASE") )
			currentView = new ReleaseLevelView(Application.this, new ReleaseLevel((ReleaseLevel)theLevel));
		else if( theLevel.getLevelType().toUpperCase().equals("PUZZLE") )
			currentView = new PuzzleLevelView(Application.this, new PuzzleLevel((PuzzleLevel)theLevel));
		else
			return; 
		// Show it on the GUI
		setContentPane(currentView); // Show it on the UI
		repaint(); // Refresh the UI
	}
	
	/**
	 * Returns the backing Master Model
	 * @return
	 */
	public Model getMasterModel(){
		return this.masterModel;
	}

}