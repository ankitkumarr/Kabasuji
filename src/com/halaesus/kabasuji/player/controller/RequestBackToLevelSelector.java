package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

public class RequestBackToLevelSelector implements MouseListener {

	AbstractLevel theLevel;
	AbstractLevelView theLevelView;

    public RequestBackToLevelSelector(AbstractLevel level, AbstractLevelView theLevelView) {
        this.theLevel = level;
        this.theLevelView = theLevelView;
    }

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

	@Override
	public void mouseEntered(MouseEvent arg0) { /* Do nothing */ }

	@Override
	public void mouseExited(MouseEvent arg0) { /* Do nothing */ }

	@Override
	public void mousePressed(MouseEvent arg0) { /* Do nothing */ }

	@Override
	public void mouseReleased(MouseEvent arg0) { /* Do nothing */ }

}
