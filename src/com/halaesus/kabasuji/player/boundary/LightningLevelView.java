package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.entity.LightningLevel;
import com.halaesus.kabasuji.utils.JLabelHelper;

@SuppressWarnings("serial")
public class LightningLevelView extends AbstractLevelView {

	LightningLevel level;
	
	JLabel lightningModeLabel;
	JLabel timeRemaining;

	public LightningLevelView(Application anApplication, LightningLevel aLevel) {
		super(anApplication, aLevel); // Let the super initialize itself
		// Set up Lightning Specific Layout Stuff
		setupLevelTypeLabel();
		setupTimeRemainingLabel();
	}

	private void setupLevelTypeLabel() {
		// Create the label	
		lightningModeLabel = new JLabel("Lightning Mode");
		lightningModeLabel.setBounds(100, 10, 350, 60);
		lightningModeLabel.setForeground(Color.ORANGE);
		lightningModeLabel.setFont(new Font(lightningModeLabel.getFont().getName(), Font.BOLD, lightningModeLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(lightningModeLabel);
		// Add it to the GUI
		add(lightningModeLabel);
	}
	
	private void setupTimeRemainingLabel() {
		// Calculate some values
		int seconds = 10; // TODO: level.getTimeLeft() % 60;
		int minutes = 1; // TODO: level.getTimeLeft() / 60;
		// Create the label	
		if( minutes == 0 )
			timeRemaining = new JLabel(String.valueOf(seconds).concat("s"), SwingConstants.RIGHT);
		else
			timeRemaining = new JLabel(String.valueOf(minutes).concat("m ").concat(String.valueOf(seconds).concat("s")), SwingConstants.RIGHT);
		timeRemaining.setBounds(470, 10, 150, 60);
		timeRemaining.setForeground(Color.ORANGE);
		timeRemaining.setFont(new Font(timeRemaining.getFont().getName(), Font.BOLD, timeRemaining.getFont().getSize()));
		timeRemaining.setOpaque(false); // Transparent background
		JLabelHelper.resizeTextBasedOnAvailableSize(timeRemaining); // One-time resizing of the font
		// Add it to the GUI
		add(timeRemaining);
		timeRemaining.repaint(); // Render the changes
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Let the super do its painting first
	}

}