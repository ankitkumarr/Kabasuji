package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.PuzzleBuilderView;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;

/**
 * 
 */
public class SetNumMovePuzzle implements MouseListener {

  
    PuzzleBuilderView builderView;
    PuzzleLevel level;

    public SetNumMovePuzzle(PuzzleBuilderView builderView, PuzzleLevel level) {
        this.builderView = builderView;
        this.level = level;
    }

    /**
     * @param MouseEvent e
     */
    public void mouseClicked(MouseEvent e) {
        this.level.setallowedMoves(Integer.parseInt(this.builderView.getmovesValue()));
        this.builderView.setmovesLabel((this.builderView.getmovesValue()));
        this.builderView.repaint();
    }

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}