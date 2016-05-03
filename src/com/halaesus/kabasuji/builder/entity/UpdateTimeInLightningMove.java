package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class UpdateTimeInLightningMove extends GameSettingChangeMove {

	// Time persistence
	int originalMinutes;
	int originalSeconds;
	int finalMinutes;
	int finalSeconds;
	
	public UpdateTimeInLightningMove(LightningLevel theLevel, LightningBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	@Override
	public boolean isValid() {
		return true; // Such a move should always be valid
	}

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

	// Some getters and setters
	public int getOriginalMinutes() {
		return originalMinutes;
	}

	public void setOriginalMinutes(int originalMinutes) {
		this.originalMinutes = originalMinutes;
	}

	public int getOriginalSeconds() {
		return originalSeconds;
	}

	public void setOriginalSeconds(int originalSeconds) {
		this.originalSeconds = originalSeconds;
	}

	private int getFinalMinutes() {
		return finalMinutes;
	}

	private void setFinalMinutes(int finalMinutes) {
		this.finalMinutes = finalMinutes;
	}

	private int getFinalSeconds() {
		return finalSeconds;
	}

	private void setFinalSeconds(int finalSeconds) {
		this.finalSeconds = finalSeconds;
	}

}
