package com.halaesus.kabasuji.player.entity;

public class PieceSquare extends AbstractSquare {
   
    Piece parentPiece;
    int row;
	int col;

    public PieceSquare(boolean active, int col, int row) {
        super(active); // Let the super do its job
        // Save this information
        this.row = row;
        this.col = col;
    }
    
    public PieceSquare(int col, int row) {
        super(true); // Let the super do its job
        // Save this information
        this.row = row;
        this.col = col;
    }
    
    public void setParentPiece(Piece parentPiece) {
		this.parentPiece = parentPiece;
	}

	public Piece getParentPiece() {
		return parentPiece;
	}	
	
    public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	// TODO: Remove these setters
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}

}