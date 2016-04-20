package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import com.halaesus.kabasuji.player.entity.PuzzleLevel;
import com.halaesus.kabasuji.utils.JLabelHelper;

@SuppressWarnings("serial")
public class PuzzleLevelView extends AbstractLevelView {

	JLabel puzzleModeLabel;
	JLabel allowedMovesLabel;
	PuzzleLevel level;
	
	public PuzzleLevelView(Application anApplication, PuzzleLevel aLevel) {
		super(anApplication, aLevel); // Let the super do its stuff
		// Save the level
		level = aLevel;
		// Set up Puzzle Specific Layout Stuff
		setupLevelTypeLabel();
		setupAllowedMovesLabel();
	}

	private void setupLevelTypeLabel() {
		// Create the label
		puzzleModeLabel = new JLabel("Puzzle Mode");
		puzzleModeLabel.setBounds(125, 10, 350, 60);
		puzzleModeLabel.setForeground(Color.ORANGE);
		puzzleModeLabel.setFont(new Font(puzzleModeLabel.getFont().getName(),
				Font.BOLD, puzzleModeLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(puzzleModeLabel);
		// Add it to the GUI
		add(puzzleModeLabel);
	}
	
	private void setupAllowedMovesLabel() {
		// Create the label
		allowedMovesLabel = new JLabel("Moves: " + level.getMovesLeft());
		allowedMovesLabel.setBounds(500, 10, 350, 60);
		allowedMovesLabel.setForeground(Color.ORANGE);
		allowedMovesLabel.setFont(new Font(allowedMovesLabel.getFont().getName(),
				Font.BOLD, allowedMovesLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(allowedMovesLabel);
		// Add it to the GUI
		add(allowedMovesLabel);
	}

}