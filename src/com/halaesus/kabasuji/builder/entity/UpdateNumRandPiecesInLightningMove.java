package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

public class UpdateNumRandPiecesInLightningMove extends GameSettingChangeMove {

	int originalNumPieces;
	int finalNumPieces;
	
	public UpdateNumRandPiecesInLightningMove(LightningLevel theLevel, LightningBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	@Override
	public boolean isValid() {
		return true; // An update to the number of moves is always possible
	}

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

	// Some Getters and Setters
	public int getOriginalNumPieces() {
		return originalNumPieces;
	}

	public void setOriginalNumPieces(int originalNumPieces) {
		this.originalNumPieces = originalNumPieces;
	}

	private int getFinalNumPieces() {
		return finalNumPieces;
	}

	private void setFinalNumPieces(int finalNumPieces) {
		this.finalNumPieces = finalNumPieces;
	}

}
