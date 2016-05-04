package com.halaesus.kabasuji.builder.entity;

/**
 * Interface to generalize all moves
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public interface IMove {
	/**
	 * Do a move
	 * @return if move is done
	 */
    public boolean doMove();
    
    /**
     * check if move is valid
     * @return if move was valid
     */
    public boolean isValid();
    
    /**
     * Undo a move
     * @return if undo successful
     */
    public boolean undoMove();
    
    /**
     * Redo a move
     * @return if redo successful
     */
    public boolean redoMove();
}
