package com.halaesus.kabasuji.builder.entity;



/**
 * 
 */
public class NumberToBoardMove {

    /**
     * Default constructor
     */
    public NumberToBoardMove() {
    }

    /**
     * 
     */
    ReleaseNumber number;

    /**
     * 
     */
    AbstractBoard board;

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