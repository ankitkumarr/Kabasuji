package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.UpdateTimeInLightningMove;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * Sets the time limit for a Lightning Level
 */
public class SetTimeLightning implements MouseListener {
	/** The view we are working with */
	LightningBuilderView builderView;
	/** The level model */
	LightningLevel level;

	/**
	 * Generates this controller with given view and model
	 * 
	 * @param builderView The view
	 * @param level The model
	 */
	public SetTimeLightning(LightningBuilderView builderView, LightningLevel level) {
		this.builderView = builderView;
		this.level = level;
	}

	/**
	 * Set the time limit in the model and update the UI
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// STEP 1: Make the necessary calculations
		int minutes = this.level.getMaxTime() / 60;
		int seconds = this.level.getMaxTime() % 60;
		// STEP 2: Spawn off the move
		UpdateTimeInLightningMove theMove = new UpdateTimeInLightningMove(level, builderView);
		theMove.setOriginalMinutes(minutes);
		theMove.setOriginalSeconds(seconds);
		// STEP 3: Perform the move
		if (theMove.isValid() && theMove.doMove())
			MoveManager.pushMove(theMove);
		// STEP 4: Force a level repaint
		this.builderView.repaint();
	}

	/** Not needed */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseExited(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseReleased(MouseEvent e) {}
}
