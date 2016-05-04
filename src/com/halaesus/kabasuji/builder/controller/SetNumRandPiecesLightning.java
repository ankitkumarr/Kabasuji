package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.UpdateNumRandPiecesInLightningMove;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * Set the number of random pieces given in a lightning level
 */
public class SetNumRandPiecesLightning implements MouseListener {
	/** The view we will have to update */
    LightningBuilderView builderView;
    /** The level model */
    LightningLevel level;

    /** 
     * Associate the given model and view with this controller
     * @param builderView The view
     * @param level The model
     */
    public SetNumRandPiecesLightning(LightningBuilderView builderView, LightningLevel level) {
        this.builderView = builderView;
        this.level = level;
    }

    /**
     * Update the model and refresh the UI
     */
	@Override
	public void mousePressed(MouseEvent e) {
        // STEP 1: Spawn off the move
    	UpdateNumRandPiecesInLightningMove theMove = new UpdateNumRandPiecesInLightningMove(level, builderView);
    	theMove.setOriginalNumPieces(level.getNumRandomPieces());
    	// STEP 2: Perform the move
    	if( theMove.isValid() && theMove.doMove() )
    		MoveManager.pushMove(theMove); // Add the move to the Stack of Moves
    	// STEP 3: Refresh the GUI
    	this.builderView.repaint();
	}

	/** Not needed */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseExited(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseReleased(MouseEvent e) {}
}
