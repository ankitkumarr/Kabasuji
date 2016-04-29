package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class BoardSquare extends AbstractSquare implements Serializable{

	private static final long serialVersionUID = -3708147432756262459L;
	
	boolean filled;
    int hint;

    public BoardSquare(boolean active) {
        super(active); // Let the super do its job
        // By default, there is no hint on the board
        hint = -1;
    }
    
    public BoardSquare(BoardSquare anotherSquare) {
    	this(anotherSquare.active); // Call the constructor
    	// Fill out the other fields
    	this.filled = anotherSquare.filled;
    	this.hint = anotherSquare.hint;
    }

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public int getHint() {
		return hint;
	}

	public void setHint(int hint) {
		this.hint = hint;
	}

}