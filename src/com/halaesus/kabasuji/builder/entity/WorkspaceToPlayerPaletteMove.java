package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;

public class WorkspaceToPlayerPaletteMove extends PlayerPaletteMove {

	private AbstractLevel theLevel;

	public WorkspaceToPlayerPaletteMove(AbstractLevel theLevel, Piece thePiece) {
		this.theLevel = theLevel;
		setPiece(thePiece);
	}

	@Override
    public boolean isValid() {
        return theLevel.isDraggingActive();
    }

	@Override
    public boolean doMove() {
    	// Check if the move is valid or not
        if (!isValid()) 
        	return false;
        // If the move is valid, increase the count of the respective Hexomino by one
        getPiece().getParentHexomino().changeCount(+1);
    	// As the move was successful, so:
        return true;
    }

	@Override
    public boolean undoMove() {
    	// Decrease the count of the respective Hexomino by one
		getPiece().getParentHexomino().changeCount(-1);
    	// Add the Piece back to the Workspace
    	theLevel.getLevelBullpen().getWorkspace().addPiece(new Piece(getPiece()));
    	theLevel.getLevelBullpen().getWorkspace().getPiece().centerPiece();
    	// As the undo was successful, so:
        return true;
    }

	@Override
    public boolean redoMove() {
    	// Increase the count of the respective Hexomino by one
		getPiece().getParentHexomino().changeCount(+1);
    	// Remove the Piece from the Workspace
    	theLevel.getLevelBullpen().getWorkspace().addPiece(null);
    	// As the redo was successful, so:
    	return true;
    }

}
