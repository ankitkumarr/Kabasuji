package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.*;

/**
 * 
 */
public class RotateCCInWorkspace implements MouseListener  {

    /**
     * 
     */
    Workspace workspace;

    /**
     * 
     */
    AbstractBuilderView builder;

    /**
     * @param Workspace workspace 
     * @param AbstractBuilderView builderView
     */
    public RotateCCInWorkspace(Workspace workspace, AbstractBuilderView builderView) {
       this.workspace= workspace;
       this.builder = builderView;
    }

    /**
     * @param ActionEvent e
     */
    public void mouseClicked(MouseEvent e) {
		// First validate the move
		if( this.workspace.getPiece() != null ) {
			// Perform the move
			this.workspace.getPiece().rotateCC(); // Perform the Rotation
			this.workspace.getPiece().centerPiece(); // Re-center the piece
			// Repaint the AbsLevelView
			this.builder.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}