package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * 
 */
public class SetNumRandPiecesLightning implements MouseListener {

    /**
     * 
     */
    LightningBuilderView builderView;

    /**
     * 
     */
    LightningLevel level;

    /**
     * @param LightningBuilderView builderView 
     * @param LightningLevel level
     */
    public SetNumRandPiecesLightning(LightningBuilderView builderView, LightningLevel level) {
        this.builderView = builderView;
        this.level = level;
    }

    /**
     * @param MouseEvent e
     */
  
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		this.level.setRandomMoves(Integer.parseInt(this.builderView.getRandPiecesValue()));
    	this.builderView.setmovesLabel(Integer.toString(this.level.getRandomMoves()));
    	this.builderView.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}