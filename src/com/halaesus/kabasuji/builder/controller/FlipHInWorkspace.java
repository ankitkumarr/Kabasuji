package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.Workspace;
import com.halaesus.kabasuji.builder.entity.Piece;

public class FlipHInWorkspace implements ActionListener {
    Piece piece;
    AbstractBuilderView builderView;

    public FlipHInWorkspace(Workspace workspace, AbstractBuilderView builderView) {
        this.piece = workspace.getPiece();
        this.builderView = builderView;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (piece != null) {
			piece.flipH();
			piece.centerPiece();
			this.builderView.repaint();
		}
	}
}
