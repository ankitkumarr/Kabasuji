package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.PuzzleBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.UpdateNumMovesInPuzzleMove;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;

/**
 * Sets the maximum number of moves for a puzzle level
 */
public class SetNumMovePuzzle implements MouseListener {
	/** The view associated with this level and controller */
    PuzzleBuilderView builderView;
    /** The level model */
    PuzzleLevel level;

    /** 
     * Associate the given view and model with this controller
     * @param builderView View
     * @param level Model
     */
    public SetNumMovePuzzle(PuzzleBuilderView builderView, PuzzleLevel level) {
        this.builderView = builderView;
        this.level = level;
    }

    /**
     * Update the model with the given number and refresh the UI
     */
    public void mouseClicked(MouseEvent e) {
        // STEP 1: Spawn off the move
    	UpdateNumMovesInPuzzleMove theMove = new UpdateNumMovesInPuzzleMove(level, builderView);
    	theMove.setOriginalNumMoves(level.getAllowedMoves());
    	// STEP 2: Perform the move
    	if( theMove.isValid() && theMove.doMove() )
    		MoveManager.pushMove(theMove); // Add the move to the Stack of Moves
    	// STEP 3: Refresh the GUI
        this.builderView.repaint();
    }

	/** Not needed */
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	/** Not needed */
	@Override
	public void mouseExited(MouseEvent arg0) {}

	/** Not needed */
	@Override
	public void mousePressed(MouseEvent arg0) {}

	/** Not needed */
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
