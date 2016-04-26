package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.Hexomino;
import com.halaesus.kabasuji.shared.Piece;

public class ClickPieceInPalette implements ActionListener {
    Hexomino hexomino;
    AbstractBuilderView builderView;

    public ClickPieceInPalette(Hexomino hexomino, AbstractBuilderView builderView) {
        this.hexomino = hexomino;
        this.builderView = builderView;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Piece clickOnPiece = new Piece(hexomino.getPiece());
		clickOnPiece.centerPiece();
		this.builderView.setPieceInWorkspace(clickOnPiece);
	}
}
