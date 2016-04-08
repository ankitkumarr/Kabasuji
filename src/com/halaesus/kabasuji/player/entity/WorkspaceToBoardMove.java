package com.halaesus.kabasuji.player.entity;

/**
 * 
 */
public class WorkspaceToBoardMove {

    /**
     * Default constructor
     */
    public WorkspaceToBoardMove() {
    }

    /**
     * 
     */
    AbstractBoard board;

    /**
     * 
     */
    Bullpen bullpen;

    /**
     * 
     */
    Piece pieceDragged;

    /**
     * @param AbstractLevel level
     */
    public boolean doMove( AbstractLevel level) {
    	return false;
        // TODO implement here
    }

    /**
     * @param AbstractLevel level
     */
    public boolean isValid( AbstractLevel level) {
    	return false;
        // TODO implement here
    }

}