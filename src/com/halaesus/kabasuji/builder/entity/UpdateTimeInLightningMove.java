package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * Move class to update the time in the lightning level
 * @author Ankit Kumar, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class UpdateTimeInLightningMove extends GameSettingChangeMove {

	/**
	 * The original minutes before change
	 */
	int originalMinutes;
	
	/**
	 * Original seconds before change
	 */
	int originalSeconds;
	
	/**
	 * Final minutes after change
	 */
	int finalMinutes;
	
	/**
	 * final seconds after change
	 */
	int finalSeconds;
	
	/**
	 * Creates an instance of the class to perform the move
	 * @param theLevel
	 * @param theBuilderView
	 */
	public UpdateTimeInLightningMove(LightningLevel theLevel, LightningBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}
	
	/**
	 * checks if the move is valid 
	 */
	@Override
	public boolean isValid() {
		return true; // Such a move should always be valid
	}

	/**
	 * Performs the move
	 */
	@Override
	public boolean doMove() {
		if( !isValid() )
			return false; // We cannot perform the move if it is not valid
		// Go onto perform the move
		LightningBuilderView theBuilderView = (LightningBuilderView)this.theBuilderView;
		LightningLevel theLightningLevel = (LightningLevel)this.theLevel;
		// Now save the stuff
		theLightningLevel.setMaxTime( ( Integer.valueOf(theBuilderView.getMinutesValue()) * 60 ) + Integer.valueOf(theBuilderView.getSecondsValue()) );
		theBuilderView.setMinutesLabel( theBuilderView.getMinutesValue() );
		theBuilderView.setSecondsLabel( theBuilderView.getSecondsValue() );
		// Set the final values
		setFinalMinutes(Integer.parseInt(theBuilderView.getMinutesValue()));
		setFinalSeconds(Integer.parseInt(theBuilderView.getSecondsValue()));
		// We did the move, so:
		return true;
	}

	/**
	 * Undoes the move
	 */
	@Override
	public boolean undoMove() {
		LightningBuilderView theBuilderView = (LightningBuilderView)this.theBuilderView;
		LightningLevel theLightningLevel = (LightningLevel)this.theLevel;
		// Now save the stuff
		theLightningLevel.setMaxTime( ( getOriginalMinutes() * 60 ) + getOriginalSeconds() );
		theBuilderView.setMinutesLabel(Integer.toString( getOriginalMinutes() ));
		theBuilderView.setSecondsLabel(Integer.toString( getOriginalSeconds() ));
		// We did the undo, so:
		return true;
	}

	/**
	 * Redoes the move
	 */
	@Override
	public boolean redoMove() {
		LightningBuilderView theBuilderView = (LightningBuilderView)this.theBuilderView;
		LightningLevel theLightningLevel = (LightningLevel)this.theLevel;
		// Now save the stuff
		theLightningLevel.setMaxTime( ( getFinalMinutes() * 60 ) + getFinalSeconds() );
		theBuilderView.setMinutesLabel(Integer.toString( getFinalMinutes() ));
		theBuilderView.setSecondsLabel(Integer.toString( getFinalSeconds() ));
		// We did the redo, so:
		return true;
	}

	/**
	 * getter function to get the original number of minutes
	 * @return
	 */
	public int getOriginalMinutes() {
		return originalMinutes;
	}

	/**
	 * setter function to set the original minutes
	 * @param originalMinutes
	 */
	public void setOriginalMinutes(int originalMinutes) {
		this.originalMinutes = originalMinutes;
	}

	/**
	 * Getter to get the original seconds
	 * @return
	 */
	public int getOriginalSeconds() {
		return originalSeconds;
	}

	/**
	 * setter to set the original seconds
	 * @param originalSeconds
	 */
	public void setOriginalSeconds(int originalSeconds) {
		this.originalSeconds = originalSeconds;
	}

	/**
	 * getter to get the final minutes
	 * @return
	 */
	private int getFinalMinutes() {
		return finalMinutes;
	}

	/**
	 * setter to set the final minutes
	 * @param finalMinutes
	 */
	private void setFinalMinutes(int finalMinutes) {
		this.finalMinutes = finalMinutes;
	}

	/**
	 * getter to get the final seconds
	 * @return
	 */
	private int getFinalSeconds() {
		return finalSeconds;
	}

	/**
	 * setter to set the final seconds
	 * @param finalSeconds
	 */
	private void setFinalSeconds(int finalSeconds) {
		this.finalSeconds = finalSeconds;
	}

}
