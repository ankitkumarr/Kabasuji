package com.halaesus.kabasuji.player.entity;

public class Workspace {

	Piece piece;
	
    public Workspace() {
    	// TODO
    }

    public Piece removePiece() {
    	Piece temp = piece; // Stores a temporary piece
		piece = null; // Remove the piece from the Board
		return temp; // Return this piece
    }

    public void addPiece(Piece p) {
        piece = p; // Set the piece on the Board
    }

}