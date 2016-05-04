package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * Abstract Move Class to move a piece
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class PieceMove implements IMove {

	/**
	 * The level contains the information of the level
	 */
	AbstractLevel theLevel;
	
	/**
	 * The information of the view
	 */
	AbstractBuilderView theBuilderView;
	// Piece Persistence
	/**
	 * The destination piece that was moved
	 */
	Piece finalPiece;
	
	/**
	 * The originalPiece that was moved
	 */
	Piece originalPiece;
	
	/**
	 * Creates an instance of the PieceMove class
	 * @param theLevel
	 * @param theBuilderView
	 */
	public PieceMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		// Save the stuff
		this.theLevel = theLevel;
		this.theBuilderView = theBuilderView;
	}

	/**
	 * Getter to get the destination piece
	 * @return the destination pieces
	 */
	public Piece getFinalPiece() {
		return finalPiece;
	}

	/**
	 * Setter to set the final piece
	 * @param finalPiece
	 */
	public void setFinalPiece(Piece finalPiece) {
		this.finalPiece = finalPiece;
	}

	/**
	 * getter to get the original piece
	 * @return the original piece
	 */
	public Piece getOriginalPiece() {
		return originalPiece;
	}

	/**
	 * setter to set the original piece
	 * @param originalPiece
	 */
	public void setOriginalPiece(Piece originalPiece) {
		this.originalPiece = originalPiece;
	}

}
