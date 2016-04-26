package com.halaesus.kabasuji.shared;

/**
 * 
 * @author Corey Dixon
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