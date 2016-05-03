package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class PieceMove implements IMove {

	AbstractLevel theLevel;
	AbstractBuilderView theBuilderView;
	// Piece Persistence
	Piece finalPiece;
	Piece originalPiece;
	
	public PieceMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		// Save the stuff
		this.theLevel = theLevel;
		this.theBuilderView = theBuilderView;
	}

	public Piece getFinalPiece() {
		return finalPiece;
	}

	public void setFinalPiece(Piece finalPiece) {
		this.finalPiece = finalPiece;
	}

	public Piece getOriginalPiece() {
		return originalPiece;
	}

	public void setOriginalPiece(Piece originalPiece) {
		this.originalPiece = originalPiece;
	}

}
