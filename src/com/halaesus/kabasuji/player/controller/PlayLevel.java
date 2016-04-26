package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.Application;
import com.halaesus.kabasuji.shared.AbstractLevel;

public class PlayLevel implements MouseListener {
	
	AbstractLevel theLevel;
    Application application;

    public PlayLevel(AbstractLevel level, Application application) {
        this.theLevel = level;
        this.application = application;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		application.showLevel(theLevel); // Show the required level on the GUI now
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