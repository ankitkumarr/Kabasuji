package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

/**
 * Represents a Square on an <code>AbstractBoard</code>
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class BoardSquare extends AbstractSquare implements Serializable {

	private static final long serialVersionUID = -3708147432756262459L;
	
	/** Boolean to keep track if the BoardSquare is filled with a Piece */
	boolean filled;
	/** Integer to track the color of the Piece whose hint is reflected by the BoardSquare. */
    int hint;

    /**
     * Construct an instance of <code>BoardSquare</code> with the given property, if it is active or not
     * @param active
     */
    public BoardSquare(boolean active) {
        super(active); // Let the super do its job
        // By default, there is no hint on the board
        hint = -1;
    }
    
    /**
     * A copy constructor for <code>BoardSquare</code> which instantiates a new <code>BoardSquare</code> with the
     * same attributes as the previous.
     * <p>
     * @param anotherSquare
     */
    public BoardSquare(BoardSquare anotherSquare) {
    	this(anotherSquare.active); // Call the constructor
    	// Fill out the other fields
    	this.filled = anotherSquare.filled;
    	this.hint = anotherSquare.hint;
    }

    /**
     * Returns if the <code>BoardSquare</code> is filled
     * @return
     */
	public boolean isFilled() {
		return filled;
	}

	/**
	 * Sets if the <code>BoardSquare</code> is filled
	 * @param filled
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	/**
	 * Returns the hint on the <code>BoardSquare</code>
	 * @return -1 if there is no hint on the <code>BoardSquare</code>
	 */
	public int getHint() {
		return hint;
	}

	/**
	 * Set the hint on the <code>BoardSquare</code>
	 * @param hint
	 */
	public void setHint(int hint) {
		this.hint = hint;
	}

}