package com.halaesus.kabasuji.builder.entity;


import java.util.*;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * This manager manages the moves and controls the undo and redo functionality
 * @author Corey Dixon
 */
public class MoveManager {
	
	/**
	 * Stores the stack of moves
	 */
    Stack<IMove> moves;
    
    /**
     * Stores the stack of undoneMoves
     */
    Stack<IMove> undoneMoves;
    
    /**
     * Stores the information of the level
     */
    AbstractLevel level;
	
    /**
     * An instance of MoveManager
     */
	private static MoveManager inst;

	/**
	 * Makes the first instance of Singleton MoveManager
	 */
    private MoveManager() {
    	moves = new Stack<IMove>();
    	undoneMoves = new Stack<IMove>();
    }
    
    /**
     * Returns the instance of MoveManager
     * @return
     */
    public static MoveManager instance() {
    	if(inst == null)
    		inst = new MoveManager();
    	return inst;
    }
    
    /**
     * Resets the instance
     */
    public static void reset() {
    	inst = new MoveManager();
    }
    
    /**
     * pushes a move to the movemanager
     * @param move
     */
    public static void pushMove(IMove move) {
    	instance().moves.add(move);
    	instance().undoneMoves = new Stack<IMove>();
    }
    
    /**
     * undoes the most recent move
     * @param level
     */
    public static void undo(AbstractLevel level) {
    	if (instance().moves.isEmpty()) return;
    	
    	IMove move = instance().moves.pop();
    	move.undoMove();
    	instance().undoneMoves.push(move);
    }
    
    /**
     * redoes the most recent move
     * @param level
     */
    public static void redo(AbstractLevel level) {
    	if (instance().undoneMoves.isEmpty()) return;
    	
    	IMove move = instance().undoneMoves.pop();
    	move.redoMove();
    	instance().moves.push(move);
    }

}
