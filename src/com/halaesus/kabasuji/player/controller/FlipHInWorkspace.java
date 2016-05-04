package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.entity.Workspace;

/**
 * Flips the piece in the workspace horizontally
 */
public class FlipHInWorkspace implements MouseListener {
	/** The workspace holding a piece */
    Workspace workspace;
    /** The view we will update */
    AbstractLevelView levelView;

    /**
     * Associate the given workspace and view with this controller
     * @param workspace Model
     * @param levelView View
     */
    public FlipHInWorkspace(Workspace workspace, AbstractLevelView levelView) {
        // Store them away
    	this.levelView = levelView;
    	this.workspace = workspace;
    }

    /** Perform the flip and update */
	@Override
	public void mouseClicked(MouseEvent e) {
		// First validate the move
		if( this.workspace.getPiece() != null ) {
			// Perform the move
			this.workspace.getPiece().flipH(); // Perform the Flip
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
