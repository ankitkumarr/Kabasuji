package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.Application;

public class ReturnToLevelSelector implements MouseListener {

    Application application;

    public ReturnToLevelSelector(Application application) {
        this.application = application;
    }

	@Override
	public void mouseClicked(MouseEvent e) { 
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