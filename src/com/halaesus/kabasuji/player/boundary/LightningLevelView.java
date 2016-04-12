package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import com.halaesus.kabasuji.player.entity.LightningLevel;
import com.halaesus.kabasuji.utils.JLabelHelper;

@SuppressWarnings("serial")
public class LightningLevelView extends AbstractLevelView {

	JLabel lightningModeLabel;
	JLabel timeRemaining;
	LightningLevel level;

	public LightningLevelView(Application anApplication, LightningLevel aLevel) {
		super(anApplication); // Let the super initialize itself
		// Set up Lightning Specific Layout Stuff
		setupLevelTypeLabel();
	}

	private void setupLevelTypeLabel() {
		// Create the label
		lightningModeLabel = new JLabel("Lightning Mode");
		lightningModeLabel.setBounds(145, 10, 350, 60);
		lightningModeLabel.setForeground(Color.ORANGE);
		lightningModeLabel.setFont(new Font(lightningModeLabel.getFont().getName(), Font.BOLD, lightningModeLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(lightningModeLabel);
		// Add it to the GUI
		add(lightningModeLabel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}