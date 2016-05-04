package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.controller.PlayLevel;
import com.halaesus.kabasuji.player.entity.LevelSelector;
import com.halaesus.kabasuji.player.entity.LevelThumbnail;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.utils.JLabelHelper;

/**
 * Represents the Level Selector Screen
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
@SuppressWarnings("serial")
public class LevelSelectorView extends JPanel {

	/** List of all the levels that are shown to the user on the Level Selection Screen */
	LinkedList<LevelThumbnailView> levelViews;
	
	/** Link to the LevelSelector Entity class */
	LevelSelector levelSelector;
	
	/** Label which says "Level Selector" on the GUI */
	JLabel levelSelectorLabel;
	
	/** Top-level Application */
	Application application;

	/**
	 * Instantiates a LevelSelectorView with the necessary entity and application class
	 * @param levelSelector
	 * @param application
	 */
	public LevelSelectorView(LevelSelector levelSelector, Application application) {
		this.levelSelector = levelSelector;
		this.application = application;
		// Set the bounds for this JPanel
		setBounds(0, 0, 1280, 720);
		setLayout(null);
		// Initialize
		levelViews = new LinkedList<LevelThumbnailView>();
		// Initialize GUI elements
		initialize();
	}

	/**
	 * Initialize the GUI for the Level Selector View
	 */
	private void initialize() {		
		// For each level, create a LevelThumbnailView
		int levelThumbnailColumn = 0; // To keep track of the locations of each of the LevelThumbnailViews
		int levelThumbnailRow = 0; // To keep track of the locations of each of the LevelThumbnailViews
		// Iterate over for each Level now
		for( LevelThumbnail levelThumbnail : levelSelector.getThumbnails() ) {
			// Create a new LevelThumbnailView
			LevelThumbnailView toAdd = new LevelThumbnailView(levelThumbnail);
			toAdd.setBounds(310 + (140 * levelThumbnailColumn++), 160 + (120 * levelThumbnailRow), 100, 100);
			//Check if the level is unlocked
			if (toAdd.starsAchieved != -1 ) {
				// Add a PlayLevel Controller on this LevelThumbnailView
				//toAdd.addMouseListener(new PlayLevel(levelThumbnail.getLevelData().produceLevel(), application));
				AbstractLevel level = levelSelector.getLevelList().loadLevel(levelThumbnail.getLevelData().getLevelIndex());
				//System.out.println(level.getLevelIndex() + " " + level.getLevelType());
				toAdd.addMouseListener(new PlayLevel(level, application));
			}
			// Now add it to the ArrayList<LevelThumbnail>
			levelViews.add(toAdd);
			// Place the LevelThumbnailView now
			add(toAdd);
			// Change levelThumbnailRow if necessary
			if( (levelThumbnailColumn != 0) && (levelThumbnailColumn % 5 == 0) ) {
				levelThumbnailColumn = 0;
				levelThumbnailRow++;
			}
		}
		// Add the JLabel as well
		levelSelectorLabel = new JLabel("Level Selector", SwingConstants.CENTER);
		levelSelectorLabel.setBounds(10, 10, 1260, 80);
		levelSelectorLabel.setForeground(Color.ORANGE);
		JLabelHelper.resizeTextBasedOnAvailableSize(levelSelectorLabel);
		add(levelSelectorLabel);
	}

	/**
	 * Override the paintComponent(g) to draw the Level Selector Background Image
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its job
		// Render the background image
		g.drawImage(levelSelector.getBackgroundImage().getScaledInstance(1280, -1, Image. SCALE_SMOOTH), 0, 0, null);
	}

}