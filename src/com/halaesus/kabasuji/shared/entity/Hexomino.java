package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

/**
 * A basic representation for a KabaSuji Hexomino
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu), Anthony Panetta
 */
public class Hexomino implements Serializable {
	
	private static final long serialVersionUID = -2917029974885842756L;
	
	/** Holds the Piece that the Hexomino represents */
	Piece piece;
	/** The number of such Pieces that are available for the user to play with */
    int count;

    /**
     * Instantiates a Hexomino with a certain Piece Count to represent a certain Piece
     * @param count  the number of Pieces available for the user to play
     * @param piece  the Piece which the Hexomino will represent
     */
	public Hexomino(int count, Piece piece) {
        // Save the stuff
		this.piece = piece;
		this.count = count;
		// Setup Parent Piece relationship in here
		for(int i = 0; i < this.piece.getPieceSquares().length; i++)
			this.piece.getPieceSquares()[i].setParentPiece(this.piece);
    }
	
	/**
	 * Returns the Piece which the Hexomino represents
	 * @return
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Returns the respective Piece Count
	 * @return
	 */
    public int getCount() {
		return count;
	}
    
    /**
     * Sets the respective Hexomino count
     * @param count
     */
    public void setCount(int count) {
    	this.count = count;
    }

    /**
     * Changes the count of the Piece by a certain delta
     * @param change
     */
    public void changeCount(int change) {
    	count += change;
    }
}
