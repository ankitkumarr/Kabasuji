package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.UpdateNumRandPiecesInLightningMove;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class SetNumRandPiecesLightning implements MouseListener {

    LightningBuilderView builderView;
    LightningLevel level;

    public SetNumRandPiecesLightning(LightningBuilderView builderView, LightningLevel level) {
        this.builderView = builderView;
        this.level = level;
    }

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

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

}