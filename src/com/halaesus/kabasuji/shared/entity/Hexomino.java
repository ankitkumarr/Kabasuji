package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu), Anthony Panetta
 *
 */
public class Hexomino implements Serializable {
	
	private static final long serialVersionUID = -2917029974885842756L;
	Piece piece;
    int count;

	public Hexomino(int count, Piece piece) {
        // Save the stuff
		this.piece = piece;
		this.count = count;
		// Setup Parent Piece relationship in here
		for(int i = 0; i < this.piece.getPieceSquares().length; i++)
			this.piece.getPieceSquares()[i].setParentPiece(this.piece);
    }
	
	public Piece getPiece() {
		return piece;
	}

    public int getCount() {
		return count;
	}
    
    public void setCount(int count) {
    	this.count = count;
    }

    public void changeCount(int change) {
    	count += change;
    }
}
