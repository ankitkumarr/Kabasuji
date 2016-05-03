package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;

public class WorkspaceToPlayerPaletteMove implements IMove {

	Piece piece;
	
	public WorkspaceToPlayerPaletteMove(Piece p) {
		piece = p;
	}
	
    public boolean doMove(AbstractLevel level) {
        if (!isValid(level)) return false;
    	piece.getParentHexomino().changeCount(+1);
        return true;
    }
   
    public boolean isValid(AbstractLevel level) {
        if (!level.isDraggingActive()) return false;
        
        return true;
    }
   
    public boolean undoMove(AbstractLevel level) {
    	piece.getParentHexomino().changeCount(-1);
        return true;
    }

    public boolean redoMove(AbstractLevel level) {
    	piece.getParentHexomino().changeCount(+1);
    	return true;
    }

	@Override
	public boolean doMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean undoMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean redoMove() {
		// TODO Auto-generated method stub
		return false;
	}
}
