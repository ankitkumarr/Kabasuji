package com.halaesus.kabasuji.player.entity;

public class Hexomino {

    Workspace workspace;
    Piece piece;
    int count;

	public Hexomino(int count, Piece piece, Workspace workspace) {
        // Save the stuff
		this.workspace = workspace;
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

}