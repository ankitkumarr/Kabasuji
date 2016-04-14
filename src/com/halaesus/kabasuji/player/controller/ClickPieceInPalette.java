package com.halaesus.kabasuji.player.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.player.entity.Hexomino;
import com.halaesus.kabasuji.player.entity.Piece;

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
			Piece clickOnPiece = hexomino.getPiece();
			// Add the piece to the LevelView
			levelView.setPieceInWorkspace(clickOnPiece);
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