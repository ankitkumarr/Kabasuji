package com.halaesus.kabasuji.player.entity;

public class AbstractBoard {

	BoardSquare[] squares;
	Piece pieces;

    public AbstractBoard(BoardSquare[] square) {
        // TODO implement here
    }

    public boolean doesCollide(Piece p) {
    	return false;
        // TODO implement here
    }

    public boolean isOutsideBounds(Piece p) {
    	return false;
        // TODO implement here
    }

}