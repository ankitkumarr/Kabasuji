package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.Hexomino;
import com.halaesus.kabasuji.shared.entity.Piece;

public class ClickPieceInPalette implements MouseListener {
    Hexomino hexomino;
    AbstractBuilderView builderView;

    public ClickPieceInPalette(Hexomino hexomino, AbstractBuilderView builderView) {
        this.hexomino = hexomino;
        this.builderView = builderView;
    }

    @Override
	public void mouseClicked(MouseEvent e) {
			// Get the piece clicked on
			Piece clickOnPiece = new Piece(hexomino.getPiece());
			clickOnPiece.centerPiece(); // Center this piece
			// Add the piece to the LevelView
			this.builderView.setPieceInWorkspace(clickOnPiece);
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