package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;

public class WorkspaceToPlayerPaletteMove {

    public boolean doMove(AbstractLevel level) {
        if (!isValid(level)) return false;
    	level.getPieceBeingDragged().getParentHexomino().changeCount(+1);
        return true;
    }
   
    public boolean isValid(AbstractLevel level) {
        if (!level.isDraggingActive()) return false;
        
        return true;
    }
   
    public boolean undo(AbstractLevel level) {
    	level.getPieceBeingDragged().getParentHexomino().changeCount(-1);
        return true;
    }

    public boolean redo(AbstractLevel level) {
        // TODO implement here
    	return false; // stub
    }
}
