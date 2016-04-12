package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.util.*;

import javax.swing.JLabel;

import com.halaesus.kabasuji.player.entity.ReleaseLevel;
import com.halaesus.kabasuji.utils.JLabelHelper;

@SuppressWarnings("serial")
public class ReleaseLevelView extends AbstractLevelView {

	JLabel releaseModeLabel;
	NumberBarView numberBar;
	Set<JLabel> numbers;
	ReleaseLevel level;

	public ReleaseLevelView(Application anApplication, ReleaseLevel aLevel) {
		super(anApplication, aLevel);  // Let the super do its stuff
		// Set up Puzzle Specific Layout Stuff
		setupLevelTypeLabel();
	}

	private void setupLevelTypeLabel() {
		// Create the label
		releaseModeLabel = new JLabel("Release Mode");
		releaseModeLabel.setBounds(125, 10, 350, 60);
		releaseModeLabel.setForeground(Color.ORANGE);
		releaseModeLabel.setFont(new Font(releaseModeLabel.getFont().getName(), Font.BOLD, releaseModeLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(releaseModeLabel);
		// Add it to the GUI
		add(releaseModeLabel);
	}

}