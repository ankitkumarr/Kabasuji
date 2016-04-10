package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.entity.LevelData;
import com.halaesus.kabasuji.player.entity.SplashModel;

@SuppressWarnings("serial")
public class LevelThumbnailView extends JPanel {

	LevelData levelData;
	BufferedImage[] stars;
	BufferedImage levelTypeImage;
	JLabel levelNumberLabel;
	int starsAchieved;

	public LevelThumbnailView(LevelData levelData, int starsAchieved) {
		this.levelData = levelData;
		this.starsAchieved = starsAchieved;
		// Set up the basic GUI layouting stuff
		setLayout(null); // We will place all the things on the GUI by ourselves
		// Initialize
		initialize();
	}

	private void initialize() {
		// Initialize the required puzzle image
		levelTypeImage = null;
		// TODO: Figure out if the level is locked and the number of stars the user has achieved
		try {
			if( starsAchieved == -1 )
				levelTypeImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/levelLockedThumbnail.png"));
			else
				if( levelData.getLevelType().equals("Lightning") )
					levelTypeImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/lightningThumbnail.png"));
				else if( levelData.getLevelType().equals("Puzzle") )
					levelTypeImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/puzzleThumbnail.png"));
				else if( levelData.getLevelType().equals("Release") )
					levelTypeImage = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/releaseThumbnail.png"));
		} catch(IOException e) {
			// We cannot do anything :(
		}
		// Place the Level Number in the Thumbnail
		levelNumberLabel = new JLabel(String.valueOf(levelData.getLevelIndex()), SwingConstants.CENTER);
		levelNumberLabel.setBounds(0, 0, 100, 100);
		levelNumberLabel.setForeground(Color.RED);
		levelNumberLabel.setFont(levelNumberLabel.getFont().deriveFont(48.0f));
		//JLabelHelper.resizeTextBasedOnAvailableSize(levelNumberLabel);
		add(levelNumberLabel);
		// Initialize the star images as well
		stars = new BufferedImage[3];
		stars[0] = null; stars[1] = null; stars[2] = null;
		// TODO: Based on the number of stars the user has achieved, render these
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Paint the levelTypeImage
		if(levelTypeImage != null)
			g.drawImage(levelTypeImage.getScaledInstance(80, 80, Image. SCALE_SMOOTH), 10, 10, null);
		// Also paint the stars
		for(int i = 0; i < 3; i++)
			if(stars[i] != null)
				g.drawImage(stars[i].getScaledInstance(30, 30, Image. SCALE_SMOOTH), 5 + (30 * i), 70, null);
	}

}