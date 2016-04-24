package com.halaesus.kabasuji.builder.entity;



/**
 * 
 */
public class AbstractBoard {

    /**
     * Default constructor
     */
    public AbstractBoard() {
    }

    /**
     * 
     */
    Piece pieces;

    /**
     * 
     */
    BoardSquare[] boardSquares;




    /**
     * @param BoardSquare[144] boardSquares
     */
    public AbstractBoard(BoardSquare[] boardSquares) {
        this.boardSquares = boardSquares;
    }

    /**
     * @param Piece p
     */
    public boolean doesCollide(Piece p) {
        // TODO implement here
    	return false; // stub
    }

    /**
     * @param Piece p
     */
    public boolean isOutsideBounds(Piece p) {
        // TODO implement here
    	return false; // stub
    }

}