package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

public class Hexomino implements Serializable{

	private static final long serialVersionUID = 985952431306602018L;
	Workspace workspace;
    Piece piece;
    int count;

    public Hexomino(){}
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
    
    public void setCount(int count) {
    	this.count = count;
    }

}