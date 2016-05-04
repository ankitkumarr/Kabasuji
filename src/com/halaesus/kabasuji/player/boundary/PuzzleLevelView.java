package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.controller.DragPieceFromBoard;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.utils.JLabelHelper;

/**
 * Represents a PuzzleLevelView which is a type of an AbstractLevelView
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
@SuppressWarnings("serial")
public class PuzzleLevelView extends AbstractLevelView {

	/** Label indicating that level being played is a Puzzle Level */
	JLabel puzzleModeLabel;
	
	/** Label which shows the number of allowed moves left for the user */
	JLabel allowedMovesLabel;
	
	/** Keeps track of the PuzzleLevel that is being played */
	PuzzleLevel level;
	
	/**
	 * Instantiates a PuzzleLevel with the necessary top-level application and PuzzleLevel as the model element
	 * @param anApplication
	 * @param aLevel
	 */
	public PuzzleLevelView(Application anApplication, PuzzleLevel aLevel) {
		super(anApplication, aLevel); // Let the super do its stuff
		// Save the level
		level = aLevel;
		// Set up Puzzle Specific Layout Stuff
		setupLevelTypeLabel();
		setupAllowedMovesLabel();
    	
    	// Also the user should be able to move pieces in Puzzle Level
		// Add Listener for DragPieceFromBoard
		DragPieceFromBoard dragFromBoard = new DragPieceFromBoard(this.level, PuzzleLevelView.this);
		addMouseListener(dragFromBoard);
		addMouseMotionListener(dragFromBoard);
	}

	/**
	 * Sets up to show the Level Type in the Top-Bar
	 */
	private void setupLevelTypeLabel() {
		// Create the label
		puzzleModeLabel = new JLabel("Puzzle Mode");
		puzzleModeLabel.setBounds(125, 10, 350, 60);
		puzzleModeLabel.setForeground(Color.ORANGE);
		puzzleModeLabel.setFont(new Font(puzzleModeLabel.getFont().getName(), Font.BOLD, puzzleModeLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(puzzleModeLabel);
		// Add it to the GUI
		add(puzzleModeLabel);
	}
	
	/**
	 * Sets up the label to show the number of remaining moves
	 */
	private void setupAllowedMovesLabel() {
		// Create the label
		allowedMovesLabel = new JLabel("Moves: " + level.getMovesLeft());
		allowedMovesLabel.setBounds(320 + 53 * 12, 90, 324, 60);
		allowedMovesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		allowedMovesLabel.setForeground(Color.ORANGE);
		allowedMovesLabel.setFont(new Font(allowedMovesLabel.getFont().getName(),
				Font.BOLD, allowedMovesLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(allowedMovesLabel);
		// Add it to the GUI
		add(allowedMovesLabel);
	}
	
	/**
	 * Overrides paintComponent(g) method to reflect any changes made to the Moves Left Label
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// Fix the Allowed Moves Label if necessary
		if( Integer.valueOf(this.allowedMovesLabel.getText().substring(7)) != this.level.getMovesLeft() ) {
			this.allowedMovesLabel.setText("Moves: " + this.level.getMovesLeft()); // Refresh the view
			JLabelHelper.resizeTextBasedOnAvailableSize(allowedMovesLabel);
		}
		// Let the super do its painting now
		super.paintComponent(g);
	}

}