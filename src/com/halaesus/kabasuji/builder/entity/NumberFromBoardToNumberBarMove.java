package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;

/**
 * Move class to move Number from Board to NumberBar
 * @author Ankit Kumar
 */
public class NumberFromBoardToNumberBarMove extends NumberMove {
   
	/**
	 * Creates an instance of Move class to perform the move
	 * @param theLevel
	 * @param theBuilderView
	 */
    public NumberFromBoardToNumberBarMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

    /**
     * Does the move
     */
	@Override
	public boolean doMove() {
		if( theLevel.isDraggingActive() == false )
    		return false;
		if( isValid() == false )
    		return false;
		
		ReleaseBoard rb = (ReleaseBoard)theLevel.getBoard();
		ReleaseLevel theRLevel = (ReleaseLevel) this.theLevel;
		rb.removeNumber(theRLevel.getnumberBeingDragged());
		setFinalNumber(null);
		return true;
	}

	/**
	 * Check if the move is valid
	 */
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
		ReleaseBuilderView rbv = (ReleaseBuilderView) this.theBuilderView;
		Rectangle numberBarRectangle = rbv.getReleaseNumberBarBounds();
		
		if( !numberBarRectangle.contains(NumberRectangle) )
			return false;
		return true;
	}

	/**
	 * Redoes the Move
	 */
	@Override
	public boolean redoMove() {
		ReleaseBoard rb = (ReleaseBoard)theLevel.getBoard();
		rb.removeNumber(getOriginalNumber());
		return true;
	}

	/**
	 * Undoes the Move
	 */
	@Override
	public boolean undoMove() {
		ReleaseBoard rb = (ReleaseBoard)theLevel.getBoard();
		rb.addNumber(getOriginalNumber());
		return true;
	}

}