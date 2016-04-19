package com.halaesus.kabasuji.player.entity;

public class AbstractBoard {

	BoardSquare[] squares;
	Piece pieces;

	public AbstractBoard(BoardSquare[] square) {
		// this.squares = square;
		
		// TODO remove this when we actually pass in board squares from a file
		// BoardSquares
		BoardSquare[] boardSquares = new BoardSquare[144];
		for (int i = 0; i < 144; i++) {
			boardSquares[i] = new BoardSquare(true);
		}
		this.squares = boardSquares;

	}
    
    public boolean isActive(int row, int col) {
    	return squares[(row * 12) + col].isActive();
    }

    public boolean doesCollide(Piece p) {
    	return false;
        // TODO implement here
    }

    public boolean isOutsideBounds(Piece p) {
    	return false;
        // TODO implement here
    }
    
    public AbstractBoard getBoard(){
    	return this;    	
    }

}