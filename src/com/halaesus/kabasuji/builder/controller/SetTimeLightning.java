package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.UpdateTimeInLightningMove;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class SetTimeLightning implements MouseListener {

    LightningBuilderView builderView;
    LightningLevel level;

    public SetTimeLightning(LightningBuilderView builderView, LightningLevel level) {
        this.builderView = builderView;
        this.level = level;
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// STEP 1: Make the necessary calculations
        int minutes = this.level.getMaxTime() / 60;
        int seconds = this.level.getMaxTime() % 60;
        // STEP 2: Spawn off the move
        UpdateTimeInLightningMove theMove = new UpdateTimeInLightningMove(level, builderView);
        theMove.setOriginalMinutes(minutes);
        theMove.setOriginalSeconds(seconds);
        // STEP 3: Perform the move
        if( theMove.isValid() && theMove.doMove() )
        	MoveManager.pushMove(theMove);
        // STEP 4: Force a level repaint
    	this.builderView.repaint();
		
	}

    @Override
    public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

}