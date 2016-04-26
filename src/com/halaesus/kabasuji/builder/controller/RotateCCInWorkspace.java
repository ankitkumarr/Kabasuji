package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.Workspace;

public class RotateCCInWorkspace implements ActionListener  {
    Workspace workspace;
    AbstractBuilderView builderView;

    public RotateCCInWorkspace(Workspace workspace, AbstractBuilderView builderView) {
    	this.workspace = workspace;
    	this.builderView = builderView;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Piece piece = workspace.getPiece();
		if (piece != null) {
			piece.rotateCC();
			piece.centerPiece();
			this.builderView.repaint();
		}
	}
}
