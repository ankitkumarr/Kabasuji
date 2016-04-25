package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.entity.PieceSquare;

/**
 * 
 */
public class PieceSquare extends AbstractSquare {

    
    public PieceSquare(int col, int row) {
        super(true); // Let the super do its job
        // Save this information
        this.row = row;
        this.col = col;
    }
    
    public PieceSquare(PieceSquare toCopy) {
    	this(toCopy.active, toCopy.col, toCopy.row);
    }
    

    Piece parentPiece;

    int row;

    int col;

    public PieceSquare(boolean activem, int row, int col) {
        super(activem);
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
    	return row;
    }
    
    public int getCol() {
    	return col;
    }

	public Piece getParentPiece() {
		return parentPiece;
	}

	public void setParentPiece(Piece parentPiece) {
		this.parentPiece = parentPiece;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

}