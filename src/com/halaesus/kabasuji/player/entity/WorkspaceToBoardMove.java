package com.halaesus.kabasuji.player.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.utils.PieceHelper;

public class WorkspaceToBoardMove {
	
	// TODO: Newly added ones
	AbstractLevelView levelView;
	// TODO: Old ones
    AbstractBoard board;
    Piece pieceDragged;
    Bullpen bullpen;

    public WorkspaceToBoardMove(AbstractLevelView levelView, AbstractBoard theBoard, Piece pieceDragged, Bullpen theBullpen) {
    	this.levelView = levelView;
    	this.board = theBoard; // TODO: Check with team if needed
    	this.pieceDragged = pieceDragged;
    	this.bullpen = theBullpen;
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
		// Calculate the Board Rectangle
		Rectangle overallBoardRectangle = new Rectangle(this.levelView.getBoardPieceRectangle(0, 0).x, 
				                                        this.levelView.getBoardPieceRectangle(0, 0).y, 
				                                        12 * this.levelView.getBoardPieceRectangle(0, 0).width, 
				                                        12 * this.levelView.getBoardPieceRectangle(0, 0).height);
		
		// See if Piece is within Board Bounds
		if( !overallBoardRectangle.contains(tighestPieceRectangle) )
			return false; // Definitely not a valid move
		
		// Get the Piece that will Snap to the Board
		Piece newPieceDragged = PieceHelper.snapToNearestBoardSquare(level, this.levelView);
		if( newPieceDragged == null )
			return false; // Failed to snap to the Board and hence, not a valid move

		// Check if Piece overlaps
		if( level.getBoard().doesCollide(newPieceDragged) )
			return false; // We clash with some other piece and thus cannot complete the drag
		
		// Check if Piece is outside Active Board Bounds
		if( level.getBoard().isOutsideBounds(newPieceDragged) )
			return false; // We are outside board active bounds and thus the drag cannot be completed
		
		// Finally, the move was valid, so:
		return true;
    }

    public boolean doMove(AbstractLevel level) {
    	if( level.isDraggingActive() == false )
    		return false; // An active drag needs to be in place for this function to be called
    	if( isValid(level) == false )
    		return false; // Also, the move should be valid for this function to be called
    	// Now, snap to the board at the location and update the underlying board
    	// STEP 1: Decrement Bullpen Count for the respective piece
    	// TODO: Ask about ID Matching
    	// STEP 2: Find the squares to snap to
		Piece snappedPiece = PieceHelper.snapToNearestBoardSquare(level, this.levelView);
		if( snappedPiece == null )
			return false; // We failed to snap to the board and hence the move wasn't completed
		else
			level.getBoard().pieces.add(snappedPiece); // Add the snapped Piece to the board
		// The move was successful, so:
		return true;
    }

}