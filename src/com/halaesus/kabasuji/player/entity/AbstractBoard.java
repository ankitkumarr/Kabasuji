package com.halaesus.kabasuji.player.entity;
/**
 * 
 */
public class AbstractBoard {

	 Piece pieces;
	 BoardSquare[] squares;

    public AbstractBoard() {
    }

   
    public AbstractBoard(BoardSquare[] square) {
        // TODO implement here
    }

    /**
     * @param Piece p
     */
    public boolean doesCollide(Piece p) {
    	return false;
        // TODO implement here
    }

    /**
     * @param Piece p
     */
    public boolean isOutsideBounds(Piece p) {
    	return false;
        // TODO implement here
    }

}