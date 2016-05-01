package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Bullpen;
import com.halaesus.kabasuji.shared.entity.Palette;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;


public class WorkspaceToPlayerPaletteMove implements IMove {

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

