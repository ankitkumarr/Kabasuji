package com.halaesus.kabasuji.builder.controller;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 
 */
public class ClickPieceInPalette implements MouseListener {

    /**
     * 
     */
    Hexomino hexomino;

    /**
     * 
     */
    AbstractBuilderView builderView;

    /**
     * @param Hexomino hexomino 
     * @param AbstractBuilderView builderView
     */
    public ClickPieceInPalette(Hexomino hexomino, AbstractBuilderView builderView) {
        this.hexomino = hexomino;
        this.builderView = builderView;
    }

    /**
     * @param MouseEvent e
     */
    
    //TODO: Not done yet
    public void mouseClicked(MouseEvent e) {
        
    	// Get the piece clicked on
		Piece clickOnPiece = new Piece(hexomino.getPiece());
		clickOnPiece.centerPiece(); // Center this piece
		// Add the piece to the LevelView
		this.builderView.setPieceInWorkspace(clickOnPiece);
		
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