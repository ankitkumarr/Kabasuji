package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.player.boundary.ReleaseLevelView;
import com.halaesus.kabasuji.shared.entity.AbstractBoard;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;
import com.halaesus.kabasuji.utils.PieceHelper;
import com.halaesus.kabasuji.utils.ReleaseNumberHelper;

public class NumberToBoardMove implements IMove {

	ReleaseBuilderView levelView;
	
	public NumberToBoardMove(ReleaseBuilderView levelView) {
		this.levelView = levelView;
	}

	@Override
	public boolean doMove(AbstractLevel aLevel) {
		ReleaseLevel level = (ReleaseLevel) aLevel;
		if( level.isDraggingActive() == false )
    		return false; // An active drag needs to be in place for this function to be called
    	if( isValid(level) == false )
    		return false; // Also, the move should be valid for this function to be called

    	ReleaseNumber getNumber = ReleaseNumberHelper.snapToNearestBoardSquare(level, this.levelView);
    	ReleaseBoard rb = (ReleaseBoard) level.getBoard();
    	rb.addNumber(getNumber);
    	//TODO Now, snap to the board at the location and update the underlying board
    	// STEP 1: Find the squares to snap to
    	this.levelView.repaint();
		return true;
	}

	@Override
	public boolean undoMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean redoMove(AbstractLevel level) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid (AbstractLevel aLevel) {
		
		ReleaseLevel level = (ReleaseLevel) aLevel;
		assert( level.isDraggingActive() == true ); // We can only be called if a drag is in action
    	// Take Board Bounds and see if the Piece is within those bounds or not
    	ReleaseNumber numberDragged = level.getnumberBeingDragged();
    	Point topPiecePoint = level.getTopPointOfDraggingPiece();
    	
    	// Calculate tightest rectangle around PieceSquares
		
		Rectangle NumberRectangle = new Rectangle(topPiecePoint.x, 
				                                        topPiecePoint.y, 
				                                        53, 
				                                        53);
		// Calculate the Board Rectangle
		Rectangle overallBoardRectangle = new Rectangle(this.levelView.getBoardPieceRectangle(0, 0).x - 25, 
				                                        this.levelView.getBoardPieceRectangle(0, 0).y - 25, 
				                                        13 * this.levelView.getBoardPieceRectangle(0, 0).width, 
				                                        13 * this.levelView.getBoardPieceRectangle(0, 0).height);
		
		// See if Piece is within Board Bounds
		if( !overallBoardRectangle.contains(NumberRectangle) )
			return false; // Definitely not a valid move
		
		ReleaseNumber newNumberDragged = ReleaseNumberHelper.snapToNearestBoardSquare(level, this.levelView);
		if( newNumberDragged == null )
			return false; // Failed to snap to the Board and hence, not a valid move **/

		//TODO: Check if number overlaps
		ReleaseBoard rb = (ReleaseBoard) level.getBoard();
		if(rb.doesCollide(newNumberDragged)) {
			return false;
		}
		
		
		//TODO Check if Piece is outside Active Board Bounds
		//if( level.getBoard().isOutsideBounds(newPieceDragged))
		//	return false; // We are outside board active bounds and thus the drag cannot be completed
		
		return true;
	}

}