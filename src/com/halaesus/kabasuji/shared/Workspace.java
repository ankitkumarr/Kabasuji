package com.halaesus.kabasuji.shared;

public class Workspace {

	Piece piece;
	
    public Workspace() {
    	// TODO
    }

    public void removePiece() {
		piece = null; // Remove the piece from the Board
    }

    public void addPiece(Piece p) {
        piece = p; // Set the piece on the Board
    }
    
    public Piece getPiece() {
    	return piece;
    }

	public boolean pieceExists() {
		return (piece != null);
	}

}