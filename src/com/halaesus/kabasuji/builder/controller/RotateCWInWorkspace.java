package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.Workspace;

/**
 * Rotates the piece in the workspace 90 degrees clockwise
 */
public class RotateCWInWorkspace implements ActionListener{
	/** The workspace holding a piece */
    Workspace workspace;
    /** The view we will update */
    AbstractBuilderView builderView;

    /** Associate the given workspace and view with this controller
     * @param workspace Model
     * @param builderView View
     */
    public RotateCWInWorkspace(Workspace workspace, AbstractBuilderView builderView) {
        this.workspace = workspace;
        this.builderView = builderView;
    }

    /** Perform the rotation and update */
	@Override
	public void actionPerformed(ActionEvent e) {
		Piece piece = workspace.getPiece();
		if (piece != null) {
			piece.rotateCW();
			piece.centerPiece();
			this.builderView.repaint(); 
		}
	}
}
