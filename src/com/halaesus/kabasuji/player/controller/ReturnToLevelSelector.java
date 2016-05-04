package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * Controller for returning to the level selector screen
 * after completing a level
 */
public class ReturnToLevelSelector implements MouseListener {
	/** View to update */
    Application application;
    /** Model to update */
    AbstractLevel theLevel;

    /**
     * Associates the given model and view with this controller
     * @param application View
     * @param theLevel Model
     */
    public ReturnToLevelSelector(Application application, AbstractLevel theLevel) {
        this.application = application;
        this.theLevel = theLevel;
    }

    /**
     * Update the model and return to the level selector screen
     */
	@Override
	public void mouseClicked(MouseEvent e) { 
		// update the stars earned in the player progress
		application.getMasterModel().getPlayerProgress().updateStarsEarned(theLevel.getLevelIndex(),
				theLevel.getStarsAchieved());
		// refresh the levelSelector Model too (coupled code)
		application.getMasterModel().getLevelSelectorModel().updateStars();

		application.showLevelSelector();
	}

	/** Not needed */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseExited(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mousePressed(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseReleased(MouseEvent e) {}
}
