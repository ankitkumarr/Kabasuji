package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.utils.BuilderPieceHelper;

public class BoardToBoardMove implements IMove {

	AbstractBuilderView levelView;

    public BoardToBoardMove(AbstractBuilderView levelView) {
    	this.levelView = levelView;
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
		
		// If the piece is not in the rectangle, it is not a valid move
		if( !overallBoardRectangle.contains(tighestPieceRectangle) )
			return false; // This move cannot take place
    	
		// Get the Piece that will Snap to the Board
		Piece newPieceDragged = BuilderPieceHelper.snapToNearestBoardSquare(level, this.levelView);
		if( newPieceDragged == null )
			return false; // Failed to snap to the Board and hence, not a valid move

		// Check if Piece overlaps
		if( level.getBoard().doesCollide(newPieceDragged) )
			return false; // We clash with some other piece and thus cannot complete the drag
		
		// Check if Piece is outside Active Board Bounds
//		if( level.getBoard().isOutsideBounds(newPieceDragged) )
//			return false; // We are outside board active bounds and thus the drag cannot be completed
		
		// Finally, the move was valid, so:
		return true;
    }

    public Piece doMove(AbstractLevel level, PieceSquare[] originalPieceSquares) {
    	if( level.isDraggingActive() == false )
    		return null; // An active drag needs to be in place for this function to be called
    	if( isValid(level) == false )
    		return null; // Also, the move should be valid for this function to be called
    	// Now, snap to the board at the location and update the underlying board
    	// STEP 1: Find the squares to snap to
		Piece snappedPiece = BuilderPieceHelper.snapToNearestBoardSquare(level, this.levelView);
		if( snappedPiece == null )
			return null; // We failed to snap to the board and hence the move wasn't completed
		else
			level.getBoard().addPiece(snappedPiece); // Add the snapped Piece to the board
		// STEP 2: Mark original BoardSquares as not filled AND inactive
		for( PieceSquare aPieceSquare : originalPieceSquares ) {
			level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setFilled(false);
			level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 3: Mark underlying BoardSquares as filled AND active
		for( PieceSquare aPieceSquare : snappedPiece.getPieceSquares() ) {
			level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setFilled(true);
			level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
		}
		// The move was successful, so:
		return snappedPiece;
    }

	@Override
	public boolean undoMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}
}
