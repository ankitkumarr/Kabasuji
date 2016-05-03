package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;

public class BoardToBullpenMove implements IMove {

	AbstractBuilderView levelView;
	Piece originalPiece;
	PieceSquare[] originalPieceSquares;

    public BoardToBullpenMove(AbstractBuilderView levelView, Piece originalPiece, PieceSquare[] originalPieceSquares) {
    	this.levelView = levelView;
    	this.originalPiece = originalPiece;
    	this.originalPieceSquares = originalPieceSquares;
    }

    public boolean isValid(AbstractLevel level) {
		assert( level.isDraggingActive() == true ); // We can only be called if a drag is in action
		// Take Board Bounds and see if the Piece is within those bounds or not
		Piece pieceDragged = level.getPieceBeingDragged();
		Point topPiecePoint = level.getTopPointOfDraggingPiece();
		
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
		if( !this.levelView.getBullpenBounds().contains(tighestPieceRectangle) )
			return false; // This move cannot take place
		
		// Else, the move and removal of the piece from the Board is valid, so:
		return true;
    }

    public boolean doMove(AbstractLevel level) {
		if (!level.isDraggingActive())
			return false; // An active drag needs to be in place for this function to be called
		if (!isValid(level))
			return false; // Also, the move should be valid for this function to be called
    	
		// Remove the piece from the board and return
		// STEP 1: Mark original BoardSquares as inactive
		for( PieceSquare aPieceSquare : originalPieceSquares ) {
			level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 2: Decrement Bullpen Count for the respective piece
		if (level.getPieceBeingDragged().getParentHexomino().getCount() > 0)
			level.getPieceBeingDragged().getParentHexomino().changeCount(-1);
		
		// The move was successful, so:
		return true;
    }

	public boolean undoMove(AbstractLevel level) {
		/**
		
		// STEP 1: Mark original BoardSquares as active
		for( PieceSquare aPieceSquare : originalPieceSquares ) {
			level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
		}
		// STEP 2: Increment Bullpen Count for the respective piece
		originalPiece.getParentHexomino().changeCount(+1);
		
		// The move was successful, so:
		return true;
		
		*/
		
		return false;
	}

	
	public boolean redoMove(AbstractLevel level) {
		 /**
		// STEP 1: Mark original BoardSquares as inactive
		for( PieceSquare aPieceSquare : originalPiece.getPieceSquares() ) {
			level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 2: Decrement Bullpen Count for the respective piece
		if (originalPiece.getParentHexomino().getCount() > 0)
			originalPiece.getParentHexomino().changeCount(-1);
		
		// The move was successful, so:
		return true;
		*/
		
		return false;
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
