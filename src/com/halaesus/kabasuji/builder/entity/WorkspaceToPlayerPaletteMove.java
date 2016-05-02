package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;


public class WorkspaceToPlayerPaletteMove implements IMove {

	Piece piece;
	
	public WorkspaceToPlayerPaletteMove(Piece p) {
		piece = p;
	}
	
	@Override
    public boolean doMove(AbstractLevel level) {
        if (!isValid(level)) return false;
    	piece.getParentHexomino().changeCount(+1);
        return true;
    }
   
	@Override
    public boolean isValid(AbstractLevel level) {
        if (!level.isDraggingActive()) return false;
        
        return true;
    }
   
	@Override
    public boolean undoMove(AbstractLevel level) {
    	piece.getParentHexomino().changeCount(-1);
        return true;
    }

	@Override
    public boolean redoMove(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }
}
