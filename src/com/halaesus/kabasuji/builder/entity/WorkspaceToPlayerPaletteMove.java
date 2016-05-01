package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Bullpen;
import com.halaesus.kabasuji.shared.entity.Palette;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;

public class WorkspaceToPlayerPaletteMove implements IMove {

	Palette playerPalette;
	Bullpen bullpen;
	Piece pieceDragged;

	@Override
	public Piece doMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean undoMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean redoMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}

}