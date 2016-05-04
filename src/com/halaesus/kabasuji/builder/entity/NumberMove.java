package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * Abstract Move class to manage the moves
 * @author Ankit Kumar, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class NumberMove implements IMove {

	/**
	 * The information of the level for moves to be done
	 */
	AbstractLevel theLevel;
	
	/**
	 * The view of the level where the move is performed
	 */
	AbstractBuilderView theBuilderView;
	// ReleaseNumber Persistence
	/**
	 * The number which was moved original
	 */
	ReleaseNumber originalNumber;
	
	/**
	 * The destination number
	 */
	ReleaseNumber finalNumber;
	
	/**
	 * The instance for a move in number
	 * @param theLevel
	 * @param theBuilderView
	 */
	public NumberMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		// Save the stuff
		this.theLevel = theLevel;
		this.theBuilderView = theBuilderView;
	}

	/**
	 * Getter to get the ReleaseNumber
	 * @return
	 */
	public ReleaseNumber getOriginalNumber() {
		return originalNumber;
	}

	/**
	 * Setter to set the original number that was moved
	 * @param originalNumber
	 */
	public void setOriginalNumber(ReleaseNumber originalNumber) {
		this.originalNumber = originalNumber;
	}

	/**
	 * Getter for the final Number
	 * @return
	 */
	public ReleaseNumber getFinalNumber() {
		return finalNumber;
	}

	/*
	 * Setter for the final Number
	 */
	public void setFinalNumber(ReleaseNumber finalNumber) {
		this.finalNumber = finalNumber;
	}

}
