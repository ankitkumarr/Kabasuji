package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.player.entity.Workspace;

public class RotateCWInWorkspace implements MouseListener {

    AbstractLevelView levelView;
    Workspace workspace;

    public RotateCWInWorkspace(Workspace workspace, AbstractLevelView levelView) {
        // Store them right away
    	this.levelView = levelView;
    	this.workspace = workspace;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO
	}

	@Override
	public void mouseEntered(MouseEvent e) { /* Do nothing :( */ }

	@Override
	public void mouseExited(MouseEvent e) { /* Do nothing :( */ }

	@Override
	public void mousePressed(MouseEvent e) { /* Do nothing :( */ }

	@Override
	public void mouseReleased(MouseEvent e) { /* Do nothing :( */ }

}