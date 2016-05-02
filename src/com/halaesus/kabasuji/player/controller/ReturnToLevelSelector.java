package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

public class ReturnToLevelSelector implements MouseListener {

    Application application;
    AbstractLevel theLevel;

    public ReturnToLevelSelector(Application application, AbstractLevel theLevel) {
        this.application = application;
        this.theLevel = theLevel;
    }

	@Override
	public void mouseClicked(MouseEvent e) { 
		// update the stars earned in the player progress
		application.getMasterModel().getPlayerProgress().updateStarsEarned(theLevel.getLevelIndex(),
				theLevel.getStarsAchieved());
		// refresh the levelSelector Model too (coupled code)
		application.getMasterModel().getLevelSelectorModel().updateStars();

		application.showLevelSelector();
	}

	@Override
	public void mouseEntered(MouseEvent e) { /* Do nothing */ }

	@Override
	public void mouseExited(MouseEvent e) { /* Do nothing */ }

	@Override
	public void mousePressed(MouseEvent e) { /* Do nothing */ }

	@Override
	public void mouseReleased(MouseEvent e) { /* Do nothing */ }

}