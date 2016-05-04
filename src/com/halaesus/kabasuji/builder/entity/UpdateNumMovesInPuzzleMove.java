package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.PuzzleBuilderView;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;

/**
 * Move Class to Update the Number of moves in Puzzle
 * @author Ankit Kumar, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class UpdateNumMovesInPuzzleMove extends GameSettingChangeMove {

	/**
	 * The number of moves before the change
	 */
	int originalNumMoves;
	
	/**
	 * The number of moves after the change
	 */
	int finalNumMoves;
	
	/**
	 * Makes an instance of the UpdateNumMovesInPuzzleMove to perform the move
	 * @param theLevel
	 * @param theBuilderView
	 */
	public UpdateNumMovesInPuzzleMove(PuzzleLevel theLevel, PuzzleBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	/**
	 * Checks if the move to be performed is valid
	 */
	@Override
	public boolean isValid() {
		return true; // An update to the number of moves is always possible
	}

	/**
	 * Performs the move
	 */
	@Override
	public boolean doMove() {
		if( !isValid() )
			return false; // We cannot perform the move if it is not valid
		// Go onto perform the move
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

	/**
	 * Undoes the move
	 */
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

	/**
	 * Redo the move
	 */
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

	/**
	 * Getter function to get the original number moves
	 * @return
	 */
	// Some Getters and Setters
	public int getOriginalNumMoves() {
		return originalNumMoves;
	}

	/**
	 * Setter function to set the original Number of moves
	 * @param originalNumMoves
	 */
	public void setOriginalNumMoves(int originalNumMoves) {
		this.originalNumMoves = originalNumMoves;
	}

	/**
	 * gets the final number of moves
	 * @return
	 */
	private int getFinalNumMoves() {
		return finalNumMoves;
	}

	/**
	 * sets the final number of moves
	 * @param finalNumMoves
	 */
	private void setFinalNumMoves(int finalNumMoves) {
		this.finalNumMoves = finalNumMoves;
	}

}
