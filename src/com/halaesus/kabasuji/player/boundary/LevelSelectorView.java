package com.halaesus.kabasuji.player.boundary;

import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.halaesus.kabasuji.player.entity.LevelData;
import com.halaesus.kabasuji.player.entity.LevelSelector;

@SuppressWarnings("serial")
public class LevelSelectorView extends JPanel {

	LinkedList<LevelThumbnailView> levelViews;
	LevelSelector levelSelector;
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
		exitProgram = new JButton("Exit Game");
		int ctr = 0;
		// For each level, create a LevelThumbnailView
		for( Iterator<LevelData> iter = levelSelector.getLevels().getIterator(); iter.hasNext(); ) {
			LevelData levelData = iter.next(); // Get the next object
			// Create a new LevelThumbnailView
			LevelThumbnailView toAdd = new LevelThumbnailView(levelData);
			toAdd.setBounds(10 + (150*ctr), 10, 140, 140);
			ctr++;
			//TODO: levelViews.add();
			// Place the LevelThumbnailView now
			add(toAdd);
			//add(levelViews.getLast());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its job
		// Render the background image
		g.drawImage(levelSelector.getBackgroundImage().getScaledInstance(1280, -1, Image. SCALE_SMOOTH), 0, 0, null);
	}

}