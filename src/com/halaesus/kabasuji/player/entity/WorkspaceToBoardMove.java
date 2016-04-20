package com.halaesus.kabasuji.player.entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;

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
		
		// Check if Piece overlaps
		if( level.getBoard().doesCollide(pieceDragged) )
			return false; // We clash with some other piece and thus cannot complete the drag
		
		// Check if Piece is outside Active Board Bounds
		if( level.getBoard().isOutsideBounds(pieceDragged) )
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
    	Point pushPoint = new Point(level.getTopPointOfDraggingPiece().x + 25, 
    			                    level.getTopPointOfDraggingPiece().y + 25);
    	boolean exit = false; // To keep track if the loop should exit
		for(int r = 0; r < 12 && !exit; r++) {
			for(int c = 0; c < 12 && !exit; c++) {
				Rectangle boardRectangle = this.levelView.getBoardPieceRectangle(r, c);
				// See if point lies there
				if( boardRectangle.contains(pushPoint) ) {
					Piece pieceDragged = level.getPieceBeingDragged();
					PieceSquare[] pieceSquares = Arrays.copyOf(pieceDragged.getPieceSquares(), pieceDragged.getPieceSquares().length);
					// Go over each PieceSquare now
					for(int ctr = 0; ctr < pieceSquares.length; ctr++) {
						// Update the row and col value
						pieceSquares[ctr].col += c; // Add the board Column to the base Column
						pieceSquares[ctr].row += r; // Add the board Row to the base Row
					}
					// Push the new piece to the board
					// TODO: Remove transparency
					level.getBoard().pieces.add(new Piece(pieceDragged.getColor(), pieceSquares));
					// Finally, we're done painting, so exit the loop
					exit = true;
				}
			}
		}
		// The move was successful, so:
		return true;
    }

}