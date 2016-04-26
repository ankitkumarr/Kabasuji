package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.entity.Workspace;

public class RotateCCInWorkspace implements MouseListener  {

    AbstractLevelView levelView;
    Workspace workspace;
    
    public RotateCCInWorkspace(Workspace workspace, AbstractLevelView levelView) {
    	// Store them right away
    	this.levelView = levelView;
    	this.workspace = workspace;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// First validate the move
		if( this.workspace.getPiece() != null ) {
			// Perform the move
			this.workspace.getPiece().rotateCC(); // Perform the Rotation
			this.workspace.getPiece().centerPiece(); // Re-center the piece
			// Repaint the AbsLevelView
			this.levelView.repaint();
		}
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