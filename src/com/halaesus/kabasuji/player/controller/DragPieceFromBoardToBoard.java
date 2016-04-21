package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.player.entity.AbstractLevel;

public class DragPieceFromBoardToBoard implements MouseListener, MouseMotionListener {

    AbstractLevelView levelView;
    AbstractLevel level;

    public DragPieceFromBoardToBoard(AbstractLevel theLevel, AbstractLevelView levelView) {
        // Save the information
    	this.levelView = levelView;
        this.level = theLevel;
    }
    
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseEntered(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseExited(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseMoved(MouseEvent e) { /* Nothing to do */ }

}