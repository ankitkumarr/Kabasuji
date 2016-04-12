package com.halaesus.kabasuji.player.entity;


public class PieceSquare extends AbstractSquare {
   
    Piece parentPiece;
    boolean active;
    int row;
    int col;

    public PieceSquare(boolean active, int row, int col) {
        this.active = active;
        this.row = row;
        this.col = col;
    }
    
    public void setParentPiece(Piece parentPiece) {
		this.parentPiece = parentPiece;
	}

	public Piece getParentPiece() {
		return parentPiece;
	}	

}