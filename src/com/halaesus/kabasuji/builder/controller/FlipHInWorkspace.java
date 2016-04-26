package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.Workspace;
import com.halaesus.kabasuji.shared.Piece;

public class FlipHInWorkspace implements ActionListener {
    Workspace workspace;
    AbstractBuilderView builderView;

    public FlipHInWorkspace(Workspace workspace, AbstractBuilderView builderView) {
        this.workspace = workspace;
        this.builderView = builderView;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Piece piece = workspace.getPiece();
		if (piece != null) {
			piece.flipH();
			piece.centerPiece();
			this.builderView.repaint();
		}
	}
}
