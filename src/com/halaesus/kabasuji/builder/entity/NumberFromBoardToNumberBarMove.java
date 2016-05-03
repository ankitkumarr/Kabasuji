package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class NumberFromBoardToNumberBarMove extends NumberMove {
   
	
    public NumberFromBoardToNumberBarMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	@Override
	public boolean doMove() {
		if( theLevel.isDraggingActive() == false )
    		return false;
		if( isValid() == false )
    		return false;
		
		ReleaseBoard rb = (ReleaseBoard)theLevel.getBoard();
		ReleaseLevel theRLevel = (ReleaseLevel) this.theLevel;
		rb.removeNumber(theRLevel.getnumberBeingDragged());
		return true;
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
		ReleaseBuilderView rbv = (ReleaseBuilderView) this.theBuilderView;
		Rectangle numberBarRectangle = rbv.getReleaseNumberBarBounds();
		
		if( !numberBarRectangle.contains(NumberRectangle) )
			return false;
		return true;
	}

	@Override
	public boolean redoMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean undoMove() {
		// TODO Auto-generated method stub
		return false;
	}

}