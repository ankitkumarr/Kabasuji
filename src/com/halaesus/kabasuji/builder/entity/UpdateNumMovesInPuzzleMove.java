package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.PuzzleBuilderView;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class UpdateNumMovesInPuzzleMove extends GameSettingChangeMove {

	int originalNumMoves;
	int finalNumMoves;
	
	public UpdateNumMovesInPuzzleMove(PuzzleLevel theLevel, PuzzleBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	@Override
	public boolean isValid() {
		return true; // An update to the number of moves is always possible
	}

	@Override
	public boolean doMove() {
		PuzzleBuilderView theBuilderView = (PuzzleBuilderView)this.theBuilderView;
		PuzzleLevel thePuzzleLevel = (PuzzleLevel)this.theLevel;
		// Now save the stuff
		thePuzzleLevel.setAllowedMoves(Integer.parseInt(theBuilderView.getMovesValue()));
		theBuilderView.setMovesLabel(theBuilderView.getMovesValue());
		// Set the final values
		setFinalNumMoves(Integer.parseInt(theBuilderView.getMovesValue()));
		// We did the move, so:
		return true;
	}

	@Override
	public boolean undoMove() {
		PuzzleBuilderView theBuilderView = (PuzzleBuilderView)this.theBuilderView;
		PuzzleLevel thePuzzleLevel = (PuzzleLevel)this.theLevel;
		// Now save the stuff
		thePuzzleLevel.setAllowedMoves(getOriginalNumMoves());
		theBuilderView.setMovesLabel(String.valueOf(getOriginalNumMoves()));
		// We did the move, so:
		return true;
	}

	@Override
	public boolean redoMove() {
		PuzzleBuilderView theBuilderView = (PuzzleBuilderView)this.theBuilderView;
		PuzzleLevel thePuzzleLevel = (PuzzleLevel)this.theLevel;
		// Now save the stuff
		thePuzzleLevel.setAllowedMoves(getFinalNumMoves());
		theBuilderView.setMovesLabel(String.valueOf(getFinalNumMoves()));
		// We did the move, so:
		return true;
	}

	// Some Getters and Setters
	public int getOriginalNumMoves() {
		return originalNumMoves;
	}

	public void setOriginalNumMoves(int originalNumMoves) {
		this.originalNumMoves = originalNumMoves;
	}

	public int getFinalNumMoves() {
		return finalNumMoves;
	}

	public void setFinalNumMoves(int finalNumMoves) {
		this.finalNumMoves = finalNumMoves;
	}

}
