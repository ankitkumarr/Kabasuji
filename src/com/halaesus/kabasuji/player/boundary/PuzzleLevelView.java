package com.halaesus.kabasuji.player.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.halaesus.kabasuji.player.controller.DragPieceFromBoard;
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
    	
    	// Also the user should be able to move pieces in Puzzle Level
		// Add Listener for DragPieceFromBoard
		DragPieceFromBoard dragFromBoard = new DragPieceFromBoard(this.level, PuzzleLevelView.this);
		addMouseListener(dragFromBoard);
		addMouseMotionListener(dragFromBoard);
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
		allowedMovesLabel.setBounds(320 + 53 * 12, 90, 324, 60);
		allowedMovesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		allowedMovesLabel.setForeground(Color.ORANGE);
		allowedMovesLabel.setFont(new Font(allowedMovesLabel.getFont().getName(),
				Font.BOLD, allowedMovesLabel.getFont().getSize()));
		JLabelHelper.resizeTextBasedOnAvailableSize(allowedMovesLabel);
		// Add it to the GUI
		add(allowedMovesLabel);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// Fix the Allowed Moves Label if necessary
		// TODO: See what to do if the user ran out of moves
		if( Integer.valueOf(this.allowedMovesLabel.getText().substring(7)) != this.level.getMovesLeft() ) {
			this.allowedMovesLabel.setText("Moves: " + this.level.getMovesLeft()); // Refresh the view
			JLabelHelper.resizeTextBasedOnAvailableSize(allowedMovesLabel);
		}
		// Let the super do its painting now
		super.paintComponent(g);
	}

}