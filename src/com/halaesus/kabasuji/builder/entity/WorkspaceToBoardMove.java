package com.halaesus.kabasuji.builder.entity;


import java.util.*;

/**
 * 
 */
public class WorkspaceToBoardMove extends IMove {

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
    public boolean doMove(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }

    /**
     * @param AbstractLevel level
     */
    public boolean isValid(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }

    /**
     * @param AbstractLevel level
     */
    public boolean undo(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }

    /**
     * @param AbstractLevel level
     */
    public boolean redo(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }

}