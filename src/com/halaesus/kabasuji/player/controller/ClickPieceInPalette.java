package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.entity.Hexomino;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * Controller to put a piece into the workspace
 */
public class ClickPieceInPalette implements MouseListener {
	/** View to update */
    AbstractLevelView levelView;
    /** Hexomino to add */
    Hexomino hexomino;
    
    /**
     * Associate the given model and view with this controller
     * @param hexomino Model
     * @param levelView View
     */
    public ClickPieceInPalette(Hexomino hexomino, AbstractLevelView levelView) {
    	this.levelView = levelView;
        this.hexomino = hexomino;
    }

    /**
     * Add the requested piece to the workspace
     */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Check its count
		if( hexomino.getCount() > 0 ) {
			// Get the piece clicked on
			Piece clickOnPiece = new Piece(hexomino.getPiece());
			clickOnPiece.centerPiece(); // Center this piece
			// Add the piece to the LevelView
			this.levelView.setPieceInWorkspace(clickOnPiece);
		}
	}

    /** Not needed */
	@Override
	public void mouseEntered(MouseEvent e) {}

    /** Not needed */
	@Override
	public void mouseExited(MouseEvent e) {}

    /** Not needed */
	@Override
	public void mousePressed(MouseEvent e) {}

    /** Not needed */
	@Override
	public void mouseReleased(MouseEvent e) {}
}
