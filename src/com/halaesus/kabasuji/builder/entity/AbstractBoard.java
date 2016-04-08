package com.halaesus.kabasuji.builder.entity;


import java.util.*;

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
     * 
     */
    public void AbstractBoard() {
        // TODO implement here
    }

    /**
     * @param BoardSquare[144] boardSquares
     */
    public void AbstractBoard(BoardSquare[] boardSquares) {
        // TODO implement here
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