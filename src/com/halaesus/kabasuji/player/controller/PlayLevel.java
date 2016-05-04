package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * Controller to launch a specific level in the KabaSuji Player
 */
public class PlayLevel implements MouseListener {
	/** Level model to load */
	AbstractLevel theLevel;
	/** View to update */
    Application application;

    /**
     * Associates the given model and view with this controller
     * @param level Model
     * @param application View
     */
    public PlayLevel(AbstractLevel level, Application application) {
        this.theLevel = level;
        this.application = application;
    }

    /** Show the level */
	@Override
	public void mouseClicked(MouseEvent e) {
		application.showLevel(theLevel); // Show the required level on the GUI now
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
