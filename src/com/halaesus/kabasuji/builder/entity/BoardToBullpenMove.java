package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;

/**
 * Move Class to handle Board To bullpen move of pieces
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */

public class BoardToBullpenMove extends PieceMove {

    public BoardToBullpenMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

    /**
     * Check if the move to be done is valid or not
     */
    @Override
    public boolean isValid() {
		assert(theLevel.isDraggingActive() == true); // We can only be called if a drag is in action
		// Take Board Bounds and see if the Piece is within those bounds or not
		Piece pieceDragged = theLevel.getPieceBeingDragged();
		Point topPiecePoint = theLevel.getTopPointOfDraggingPiece();
		
		// Calculate tightest rectangle around PieceSquares
		PieceSquare[] squares = pieceDragged.getPieceSquares();
		int xMax = squares[0].getCol();
		int yMax = squares[0].getRow();
		
		for( PieceSquare s: squares ){
			if (s.getCol() > xMax)
				xMax = s.getCol(); // We found a new max, save it
			if (s.getRow() > yMax)
				yMax = s.getRow(); // We found a new max, save it
		}
		
		Rectangle tighestPieceRectangle = new Rectangle(topPiecePoint.x, 
				                                        topPiecePoint.y, 
				                                        (xMax + 1) * 53, 
				                                        (yMax + 1) * 53);
		
		// If the piece is not in the Bullpen rectangle, it is not a valid move
		if( !this.theBuilderView.getBullpenBounds().contains(tighestPieceRectangle) )
			return false; // This move cannot take place
		
		// Else, the move and removal of the piece from the Board is valid, so:
		return true;
    }

    /**
     * Performs the move
     */
    @Override
    public boolean doMove() {
		if (!this.theLevel.isDraggingActive())
			return false; // An active drag needs to be in place for this function to be called
		if (!isValid())
			return false; // Also, the move should be valid for this function to be called
		
		// Remove the piece from the board and return
		// STEP 1: Mark original BoardSquares as inactive
		for( PieceSquare aPieceSquare : getOriginalPiece().getPieceSquares() ) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 2: Decrement Bullpen Count for the respective piece
		if(theLevel.getPieceBeingDragged().getParentHexomino().getCount() > 0)
			theLevel.getPieceBeingDragged().getParentHexomino().changeCount(-1);
		// STEP 3: Set the final Piece
		setFinalPiece(null); // The Piece was removed so it has to be null
		// The move was successful, so:
		return true;
    }

    /**
	 * Undoes the move
	 */
    @Override
	public boolean undoMove() {
    	// STEP 1: Add the Piece back to the Board
    	theLevel.getBoard().addPiece(getOriginalPiece());
    	// STEP 2: Mark the underlying BoardSquares as active
		for (PieceSquare aPieceSquare : getOriginalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
		}
		// STEP 3: Increment Bullpen Count for the respective piece
		getOriginalPiece().getParentHexomino().changeCount(1);
		// The undo was successful, so:
		return true;
	}

    /**
	 * Redoes the move
	 */
	@Override
	public boolean redoMove() {
		// STEP 1: Remove the Piece from the Board
		theLevel.getBoard().getPieces().remove(getOriginalPiece());
		// STEP 2: Mark the underlying BoardSquares as inactive
		for (PieceSquare aPieceSquare : getOriginalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 3: Decrement Bullpen Count for the respective Piece
		if(getOriginalPiece().getParentHexomino().getCount() > 0)
			getOriginalPiece().getParentHexomino().changeCount(-1);
		// The redo was successful, so:
		return true;
	}
}
