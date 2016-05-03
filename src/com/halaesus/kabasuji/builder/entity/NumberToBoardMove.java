package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;
import com.halaesus.kabasuji.utils.ReleaseNumberHelper;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class NumberToBoardMove extends NumberMove {
	
	public NumberToBoardMove(AbstractLevel theLevel, ReleaseBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	@Override
	public boolean isValid() {
		assert( theLevel.isDraggingActive() == true ); // We can only be called if a drag is in action
    	// Take Board Bounds and see if the Piece is within those bounds or not
    	Point topPiecePoint = theLevel.getTopPointOfDraggingPiece();
    	
    	// Calculate tightest rectangle around PieceSquares
		Rectangle NumberRectangle = new Rectangle(topPiecePoint.x, 
		                                          topPiecePoint.y, 
		                                          53, 
		                                          53);
		// Calculate the Board Rectangle
		Rectangle overallBoardRectangle = new Rectangle(this.theBuilderView.getBoardPieceRectangle(0, 0).x - 25, 
				                                        this.theBuilderView.getBoardPieceRectangle(0, 0).y - 25, 
				                                        13 * this.theBuilderView.getBoardPieceRectangle(0, 0).width, 
				                                        13 * this.theBuilderView.getBoardPieceRectangle(0, 0).height);
		
		// See if Piece is within Board Bounds
		if( !overallBoardRectangle.contains(NumberRectangle) )
			return false; // Definitely not a valid move
		
		ReleaseNumber newNumberDragged = ReleaseNumberHelper.snapToNearestBoardSquare((ReleaseLevel)theLevel, (ReleaseBuilderView)theBuilderView);
		if( newNumberDragged == null )
			return false; // Failed to snap to the Board and hence, not a valid move **/

		// Check for collisions and out of bounds
		ReleaseBoard rb = (ReleaseBoard)theLevel.getBoard();
		if(rb.doesCollide(newNumberDragged))
			return false;		
		if(rb.isOutsideBounds(newNumberDragged))
			return false;
		
		return true;
	}

	@Override
	public boolean doMove() {
		if( theLevel.isDraggingActive() == false )
    		return false; // An active drag needs to be in place for this function to be called
    	if( isValid() == false )
    		return false; // Also, the move should be valid for this function to be called
    	// We can do the move, so proceed to do it
    	ReleaseNumber getNumber = ReleaseNumberHelper.snapToNearestBoardSquare((ReleaseLevel)theLevel, (ReleaseBuilderView)theBuilderView);
    	ReleaseBoard rb = (ReleaseBoard)theLevel.getBoard();
    	rb.addNumber(getNumber);
    	// The move was successful, so:
		return true;
	}

	@Override
	public boolean undoMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean redoMove() {
		// TODO Auto-generated method stub
		return false;
	}

}