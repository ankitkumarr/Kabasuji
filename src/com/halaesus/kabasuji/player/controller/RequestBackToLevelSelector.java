package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * Controller for when the player wants to quit the level
 * early and return to the level selector screen
 */
public class RequestBackToLevelSelector implements MouseListener {
	/** Level model */
	AbstractLevel theLevel;
	/** View to update */
	AbstractLevelView theLevelView;

	/**
	 * Associates the given model and view with this controller
	 * @param level Model
	 * @param theLevelView View
	 */
    public RequestBackToLevelSelector(AbstractLevel level, AbstractLevelView theLevelView) {
        this.theLevel = level;
        this.theLevelView = theLevelView;
    }

    /**
     * Update the model and show the level completion screen
     */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Change the state of Level Completition shown
		if( !theLevel.isLevelCompletedShown() ) {
			theLevel.setLevelCompletedShown(true); // The Level Completition message will now be shown
			theLevel.setLevelCompletionStatus(AbstractLevel.LEVEL_COMPLETION_QUIT_LEVEL); // The user requested to quit the game
		}
		// Force the AbstractLevel to repaint
		theLevelView.repaint(); // Force the repaint
	}

	/** Not needed */
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	/** Not needed */
	@Override
	public void mouseExited(MouseEvent arg0) {}

	/** Not needed */
	@Override
	public void mousePressed(MouseEvent arg0) {}

	/** Not needed */
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
