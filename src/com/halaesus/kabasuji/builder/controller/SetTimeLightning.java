package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.LightningBuilderView;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

/**
 * 
 */
public class SetTimeLightning implements MouseListener {

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
    public SetTimeLightning(LightningBuilderView builderView, LightningLevel level) {
        this.builderView = builderView;
        this.level = level;
    }

    /**
     * @param MouseEvent e
     */
    public void mouseClicked(MouseEvent e) {
    	
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
		this.level.setMaxTime(((Integer.parseInt(this.builderView.getMinutesValue()))*60) +
    			(Integer.parseInt(this.builderView.getSecondsValue())));
		//System.out.println("Check = " + this.level.getMaxTime());
    	//this.level.setRandomMoves(Integer.parseInt(this.builderView.getRandPiecesValue()));
    	//this.builderView.setmovesLabel(Integer.toString(this.level.getRandomMoves()));
    	this.builderView.setMinutesLabel(Integer.toString((this.level.getMaxTime())/60));
		//this.builderView.setMinutesLabel(Integer.toString((250)));
    	this.builderView.setSecondsLabel(Integer.toString((this.level.getMaxTime())%60));
    	this.builderView.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}