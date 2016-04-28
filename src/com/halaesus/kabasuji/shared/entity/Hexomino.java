package com.halaesus.kabasuji.shared.entity;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class Hexomino {

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

}