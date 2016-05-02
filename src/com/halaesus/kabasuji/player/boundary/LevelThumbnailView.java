package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.entity.LevelData;
import com.halaesus.kabasuji.player.entity.LevelThumbnail;
import com.halaesus.kabasuji.shared.entity.SplashModel;

@SuppressWarnings("serial")
public class LevelThumbnailView extends JPanel {

	protected boolean mouseHovered;
	
	LevelThumbnail levelThumbnail;
	LevelData levelData;
	BufferedImage[] stars;
	Image levelTypeImageSmall;
	Image levelTypeImageBig;
	JLabel levelNumberLabel;
	int starsAchieved;

	public LevelThumbnailView(LevelThumbnail theThumbnail) {
		this.levelThumbnail = theThumbnail;
		// Extract the fields
		this.levelData = levelThumbnail.getLevelData();
		this.starsAchieved = levelThumbnail.getStarsEarned();
		// Set up the basic GUI layouting stuff
		setLayout(null); // We will place all the things on the GUI by ourselves
		setOpaque(false); // We are looking for a transparent background
		// Initialize
		initialize();
		// Set up Mouse Hover Listener
		setupMouseHoverListener();
	}

	private void initialize() {
		// Initialize the required puzzle image
		levelTypeImageBig = null;
		try {
			if( starsAchieved == -1 ) {
				levelTypeImageSmall = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/levelLockedThumbnail.png")).getScaledInstance(60, 60, Image. SCALE_SMOOTH);
				levelTypeImageBig = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/levelLockedThumbnail.png")).getScaledInstance(90, 90, Image. SCALE_SMOOTH);
			} else {
				if( levelData.getLevelType().equals("Lightning") ) {
					levelTypeImageSmall = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/lightningThumbnail.png")).getScaledInstance(60, 60, Image. SCALE_SMOOTH);
					levelTypeImageBig = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/lightningThumbnail.png")).getScaledInstance(90, 90, Image. SCALE_SMOOTH);
				} else if( levelData.getLevelType().equals("Puzzle") ) {
					levelTypeImageSmall = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/puzzleThumbnail.png")).getScaledInstance(60, 60, Image. SCALE_SMOOTH);
					levelTypeImageBig = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/puzzleThumbnail.png")).getScaledInstance(90, 90, Image. SCALE_SMOOTH);
				} else if( levelData.getLevelType().equals("Release") ) {
					levelTypeImageSmall = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/releaseThumbnail.png")).getScaledInstance(60, 60, Image. SCALE_SMOOTH);
					levelTypeImageBig = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/releaseThumbnail.png")).getScaledInstance(90, 90, Image. SCALE_SMOOTH);
				}
			}
		} catch(IOException e) {
			// We cannot do anything :(
		}
		// Place the Level Number in the Thumbnail
		levelNumberLabel = new JLabel(String.valueOf(levelData.getLevelIndex() + 1), SwingConstants.CENTER);
		levelNumberLabel.setBounds(0, 0, 100, 100);
		// Locked based color scheme
		if( starsAchieved == -1 )
			levelNumberLabel.setForeground(Color.LIGHT_GRAY);
		else
			levelNumberLabel.setForeground(Color.WHITE);
		levelNumberLabel.setFont(levelNumberLabel.getFont().deriveFont(48.0f));
		//JLabelHelper.resizeTextBasedOnAvailableSize(levelNumberLabel);
		add(levelNumberLabel);
		// Initialize the star images as well
		stars = new BufferedImage[3];
		// Do case wise
		try {
			if( starsAchieved == 0 ) {
				stars[0] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starShadow.png"));
				stars[1] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starShadow.png"));
				stars[2] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starShadow.png"));
			} else if( starsAchieved == 1 ) {
				stars[0] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starGold.png"));
				stars[1] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starShadow.png"));
				stars[2] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starShadow.png"));
			} else if( starsAchieved == 2 ) {
				stars[0] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starGold.png"));
				stars[1] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starGold.png"));
				stars[2] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starShadow.png"));
			} else if( starsAchieved == 3 ) {
				stars[0] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starGold.png"));
				stars[1] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starGold.png"));
				stars[2] = ImageIO.read(SplashModel.class.getResourceAsStream("/resources/starGold.png"));
			}
		} catch(IOException e) {
			// Do nothing :(
		}
	}

	private void setupMouseHoverListener() {
		// Set up Feedback Listener
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) { /* Do nothing */ }
			@Override
			public void mousePressed(MouseEvent e) { /* Do nothing */ }
			@Override
			public void mouseClicked(MouseEvent e) { /* Do nothing */ }
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(mouseHovered) {
					mouseHovered = false; // The mouse is no longer on this View
					LevelThumbnailView.this.repaint(); // Force a repaint
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!mouseHovered) {
					mouseHovered = true; // The mouse is on this View
					LevelThumbnailView.this.repaint(); // Force a repaint
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its stuff
		// Paint the levelTypeImage
		if(levelTypeImageBig != null)
			if(mouseHovered)
				g.drawImage(levelTypeImageBig, 5, 5, null);
			else
				g.drawImage(levelTypeImageSmall, 20, 20, null);
		// Also paint the stars
		for(int i = 0; i < 3; i++)
			if(stars[i] != null)
				g.drawImage(stars[i].getScaledInstance(30, 30, Image. SCALE_SMOOTH), 5 + (30 * i), 70, null);
	}

}