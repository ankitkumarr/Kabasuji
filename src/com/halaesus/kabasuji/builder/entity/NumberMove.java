package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * @author Ankit Kumar, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public abstract class NumberMove implements IMove {

	AbstractLevel theLevel;
	AbstractBuilderView theBuilderView;
	// ReleaseNumber Persistence
	ReleaseNumber originalNumber;
	ReleaseNumber finalNumber;
	
	public NumberMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		// Save the stuff
		this.theLevel = theLevel;
		this.theBuilderView = theBuilderView;
	}

	public ReleaseNumber getOriginalNumber() {
		return originalNumber;
	}

	public void setOriginalNumber(ReleaseNumber originalNumber) {
		this.originalNumber = originalNumber;
	}

	public ReleaseNumber getFinalNumber() {
		return finalNumber;
	}

	public void setFinalNumber(ReleaseNumber finalNumber) {
		this.finalNumber = finalNumber;
	}

}
