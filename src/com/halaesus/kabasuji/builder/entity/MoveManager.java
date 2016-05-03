package com.halaesus.kabasuji.builder.entity;


import java.util.*;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * @author Corey Dixon
 */
public class MoveManager {
	
    Stack<IMove> moves;
    Stack<IMove> undoneMoves;
    AbstractLevel level;
	
	private static MoveManager inst;

    private MoveManager() {
    	moves = new Stack<IMove>();
    	undoneMoves = new Stack<IMove>();
    }
    
    public static MoveManager instance() {
    	if(inst == null)
    		inst = new MoveManager();
    	return inst;
    }
    
    public static void reset() {
    	inst = new MoveManager();
    }
    
    public static void pushMove(IMove move) {
    	instance().moves.add(move);
    	instance().undoneMoves = new Stack<IMove>();
    	System.out.println(move + " pushed");
    }
    
    public static void undo(AbstractLevel level) {
    	if (instance().moves.isEmpty()) return;
    	
    	IMove move = instance().moves.pop();
    	move.undoMove();
    	instance().undoneMoves.push(move);
    	System.out.println(move + " undo");
    }
    
    public static void redo(AbstractLevel level) {
    	if (instance().undoneMoves.isEmpty()) return;
    	
    	IMove move = instance().undoneMoves.pop();
    	move.redoMove();
    	instance().moves.push(move);
    	System.out.println(move + " redo");
    }

}
