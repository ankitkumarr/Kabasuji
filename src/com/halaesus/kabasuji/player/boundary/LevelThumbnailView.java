package com.halaesus.kabasuji.player.boundary;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.halaesus.kabasuji.player.entity.LevelData;

import javafx.scene.paint.Color;

public class LevelThumbnailView extends JPanel {

	LevelData levelData;
	BufferedImage[] stars;

	public LevelThumbnailView(LevelData levelData) {
		this.levelData = levelData;
		setBounds(0, 0, 100, 100);
		// Initialize
		initialize();
	}

	private void initialize() {
		JLabel levelText = new JLabel("Level#");
		add(levelText);
	}

}