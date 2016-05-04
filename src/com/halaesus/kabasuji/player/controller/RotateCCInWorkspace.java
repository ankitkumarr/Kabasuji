package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.entity.Workspace;

/**
 * Rotates the piece in the workspace 90 degrees counterclockwise
 */
public class RotateCCInWorkspace implements MouseListener  {
	/** The view we will update */
    AbstractLevelView levelView;
    /** The workspace holding a piece */
    Workspace workspace;
    
    /**
     * Associate the given workspace and view with this controller
     * @param workspace Model
     * @param levelView View
     */
    public RotateCCInWorkspace(Workspace workspace, AbstractLevelView levelView) {
    	// Store them right away
    	this.levelView = levelView;
    	this.workspace = workspace;
    }

    /** Perform the rotation and update */
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
