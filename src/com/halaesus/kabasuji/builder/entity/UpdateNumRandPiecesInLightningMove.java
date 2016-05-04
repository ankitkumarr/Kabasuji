package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * Move class to update the number of Random pieces in the LightningLevel
 * @author Ankit Kumar
 *
 */
public class UpdateNumRandPiecesInLightningMove extends GameSettingChangeMove {

	/**
	 * Stores the number of original pieces set in the lightning level
	 */
	int originalNumPieces;
	
	/**
	 * Stores the final number of random pieces
	 */
	int finalNumPieces;
	
	/**
	 * creates an instance of the move class to update the number of random pieces
	 * @param theLevel
	 * @param theBuilderView
	 */
	public UpdateNumRandPiecesInLightningMove(LightningLevel theLevel, LightningBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	/**
	 * checks if the move is valid
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
		LightningBuilderView theBuilderView = (LightningBuilderView)this.theBuilderView;
		LightningLevel theLightningLevel = (LightningLevel)this.theLevel;
		// Now save the stuff
		theLightningLevel.setRandomMoves(Integer.parseInt(theBuilderView.getRandPiecesValue()));
		theBuilderView.setMovesLabel(Integer.toString(theLightningLevel.getNumRandomPieces()));
		// Set the final values
		setFinalNumPieces(Integer.parseInt(theBuilderView.getRandPiecesValue()));
		// We did the move, so:
		return true;
	}

	/**
	 * Undoes the move
	 */
	@Override
	public boolean undoMove() {
		LightningBuilderView theBuilderView = (LightningBuilderView)this.theBuilderView;
		LightningLevel theLightningLevel = (LightningLevel)this.theLevel;
		// Now save the stuff
		theLightningLevel.setRandomMoves(getOriginalNumPieces());
		theBuilderView.setMovesLabel(String.valueOf(getOriginalNumPieces()));
		// We did the move, so:
		return true;
	}

	/**
	 * Redo the move
	 */
	@Override
	public boolean redoMove() {
		LightningBuilderView theBuilderView = (LightningBuilderView)this.theBuilderView;
		LightningLevel theLightningLevel = (LightningLevel)this.theLevel;
		// Now save the stuff
		theLightningLevel.setRandomMoves(getFinalNumPieces());
		theBuilderView.setMovesLabel(String.valueOf(getFinalNumPieces()));
		// We did the move, so:
		return true;
	}

	/**
	 * gets the original number of pieces in the lightning level
	 * @return
	 */
	// Some Getters and Setters
	public int getOriginalNumPieces() {
		return originalNumPieces;
	}

	/**
	 * Sets the original number of pieces in the lightning level
	 * @param originalNumPieces
	 */
	public void setOriginalNumPieces(int originalNumPieces) {
		this.originalNumPieces = originalNumPieces;
	}

	/**
	 * gets the number of pieces finally set in the lightning level
	 * @return
	 */
	private int getFinalNumPieces() {
		return finalNumPieces;
	}

	/**
	 * sets the final number of lightning moves
	 * @param finalNumPieces
	 */
	private void setFinalNumPieces(int finalNumPieces) {
		this.finalNumPieces = finalNumPieces;
	}

}
