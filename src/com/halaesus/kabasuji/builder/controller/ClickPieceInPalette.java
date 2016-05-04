package com.halaesus.kabasuji.builder.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.Hexomino;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * Controller to put a piece into the workspace
 */
public class ClickPieceInPalette implements MouseListener {
	/** Hexomino to add */
    Hexomino hexomino;
    /** View to update */
    AbstractBuilderView builderView;

    /**
     * Associate the given model and view with this controller
     * @param hexomino Hexomino model
     * @param builderView View
     */
    public ClickPieceInPalette(Hexomino hexomino, AbstractBuilderView builderView) {
        this.hexomino = hexomino;
        this.builderView = builderView;
    }

    /**
     * Add the requested piece to the workspace
     */
    @Override
	public void mouseClicked(MouseEvent e) {
			// Get the piece clicked on
			Piece clickOnPiece = new Piece(hexomino.getPiece());
			clickOnPiece.centerPiece(); // Center this piece
			// Add the piece to the LevelView
			this.builderView.setPieceInWorkspace(clickOnPiece);
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
