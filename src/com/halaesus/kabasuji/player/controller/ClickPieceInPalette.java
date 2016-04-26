package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.shared.Hexomino;
import com.halaesus.kabasuji.shared.Piece;

public class ClickPieceInPalette implements MouseListener {

    AbstractLevelView levelView;
    Hexomino hexomino;
    
    public ClickPieceInPalette(Hexomino hexomino, AbstractLevelView levelView) {
    	this.levelView = levelView;
        this.hexomino = hexomino;
    }

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

	@Override
	public void mouseEntered(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseExited(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mousePressed(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseReleased(MouseEvent e) { /* Nothing to do */ }

}