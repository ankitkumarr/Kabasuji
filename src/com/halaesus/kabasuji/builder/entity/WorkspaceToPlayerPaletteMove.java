package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * Move class to perform Workspace To Player palette move by moving a piece
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class WorkspaceToPlayerPaletteMove extends PlayerPaletteMove {

	/**
	 * Stores the information of the level
	 */
	private AbstractLevel theLevel;

	/**
	 * Creates the instance to perform the workspace player palette move
	 * @param theLevel
	 * @param thePiece
	 */
	public WorkspaceToPlayerPaletteMove(AbstractLevel theLevel, Piece thePiece) {
		this.theLevel = theLevel;
		setPiece(thePiece);
	}

	/**
	 * Checks if the move is valid
	 */
	@Override
    public boolean isValid() {
        return theLevel.isDraggingActive();
    }

	/**
	 * Performs the move
	 */
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

	/**
	 * Undoes the move
	 */
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

	/**
	 * Redo the move
	 */
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
