package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.*;

/**
 * 
 */
public class FlipVInWorkspace implements MouseListener {

  
    Workspace workspace;
    AbstractBuilderView builderView;

   
    public FlipVInWorkspace(Workspace workspace, AbstractBuilderView builderView) {
        this.workspace = workspace;
        this.builderView = builderView;
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		// First run a check
				if( this.workspace.getPiece() != null ) {
					// Perform the move
					this.workspace.getPiece().flipV(); // Perform the Flip
					this.workspace.getPiece().centerPiece(); // Re-center the piece
					// Repaint the AbsLevelView
					this.builderView.repaint();
				}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
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