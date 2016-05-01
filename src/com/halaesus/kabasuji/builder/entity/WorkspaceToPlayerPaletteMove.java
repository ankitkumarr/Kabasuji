package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;


public class WorkspaceToPlayerPaletteMove implements IMove {

	@Override
    public boolean doMove(AbstractLevel level) {
        if (!isValid(level)) return false;
    	level.getPieceBeingDragged().getParentHexomino().changeCount(+1);
        return true;
    }
   
	@Override
    public boolean isValid(AbstractLevel level) {
        if (!level.isDraggingActive()) return false;
        
        return true;
    }
   
	@Override
    public boolean undoMove(AbstractLevel level) {
    	level.getPieceBeingDragged().getParentHexomino().changeCount(-1);
        return true;
    }

	@Override
    public boolean redoMove(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }
}

