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
    }
	
	public Piece getPiece() {
		return piece;
	}

    public int getCount() {
		return count;
	}

}