package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.entity.LevelData;
import com.halaesus.kabasuji.player.entity.LevelSelector;
import com.halaesus.kabasuji.utils.JLabelHelper;

@SuppressWarnings("serial")
public class LevelSelectorView extends JPanel {

	LinkedList<LevelThumbnailView> levelViews;
	LevelSelector levelSelector;
	JLabel levelSelectorLabel;
	JButton exitProgram;

	public LevelSelectorView(LevelSelector levelSelector) {
		this.levelSelector = levelSelector;
		// Set the bounds for this JPanel
		setBounds(0, 0, 1280, 720);
		setLayout(null);
		// Initialize
		levelViews = new LinkedList<LevelThumbnailView>();
		// Initialize GUI elements
		initialize();
	}

	private void initialize() {		
		// For each level, create a LevelThumbnailView
		int levelThumbnailColumn = 0; // To keep track of the locations of each of the LevelThumbnailViews
		int levelThumbnailRow = 0; // To keep track of the locations of each of the LevelThumbnailViews
		int index = 0; // To keep track of the number of Levels put on the GUI
		// Iterate over for each Level now
		for( Iterator<LevelData> iter = levelSelector.getLevelIterator(); iter.hasNext(); ) {
			LevelData levelData = iter.next(); // Get the next object
			// Create a new LevelThumbnailView
			// TODO: Check if the LevelThumbnailView constructor is allowed to take the stars achieved as well 
			LevelThumbnailView toAdd = new LevelThumbnailView(levelData, levelSelector.getPlayerStarsAchieved(index));
			toAdd.setBounds(310 + (140 * levelThumbnailColumn++), 160 + (120 * levelThumbnailRow), 100, 100);
			levelViews.add(toAdd);
			// Place the LevelThumbnailView now
			add(toAdd);
			// Change levelThumbnailRow if necessary
			if( (levelThumbnailColumn != 0) && (levelThumbnailColumn % 5 == 0) ) {
				levelThumbnailColumn = 0;
				levelThumbnailRow++;
			}
			// Increment index
			index++;
		}
		// Add the JLabel as well
		levelSelectorLabel = new JLabel("Level Selector", SwingConstants.CENTER);
		levelSelectorLabel.setBounds(10, 10, 1260, 80);
		levelSelectorLabel.setForeground(Color.ORANGE);
		JLabelHelper.resizeTextBasedOnAvailableSize(levelSelectorLabel);
		add(levelSelectorLabel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its job
		// Render the background image
		g.drawImage(levelSelector.getBackgroundImage().getScaledInstance(1280, -1, Image. SCALE_SMOOTH), 0, 0, null);
	}

}