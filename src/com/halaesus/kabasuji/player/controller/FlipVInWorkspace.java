package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.entity.Workspace;

/**
 * Flips the piece in the workspace vertically
 */
public class FlipVInWorkspace implements MouseListener {
	/** The view we will update */
    AbstractLevelView levelView;
    /** The workspace holding a piece */
    Workspace workspace;

    /**
     * Associate the given workspace and view with this controller
     * @param workspace Model
     * @param levelView View
     */
    public FlipVInWorkspace(Workspace workspace, AbstractLevelView levelView) {
    	// Store them away
    	this.levelView = levelView;
    	this.workspace = workspace;
    }

    /** Perform the flip and update */
	@Override
	public void mouseClicked(MouseEvent e) {
		// First run a check
		if( this.workspace.getPiece() != null ) {
			// Perform the move
			this.workspace.getPiece().flipV(); // Perform the Flip
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
