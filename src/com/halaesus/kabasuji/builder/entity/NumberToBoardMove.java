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
 * Move class to Perform the Number to Board Move
 * @author Ankit Kumar, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class NumberToBoardMove extends NumberMove {
	
	/**
	 * create an instance of the class to perform the move
	 */
	public NumberToBoardMove(AbstractLevel theLevel, ReleaseBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	/**
	 * Checks if the move is valid 
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

	/**
	 * Do a move
	 * @return if move is done
	 */
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
    	//setFinalNumber(new ReleaseNumber(getNumber.getValue(), getNumber.getColor(), getNumber.getCol(),getNumber.getRow()));
    	setFinalNumber(getNumber);

		return true;
	}

	 /**
     * Undo a move
     * @return if undo successful
     */
	@Override
	public boolean undoMove() {
		ReleaseBoard rb = (ReleaseBoard)theLevel.getBoard();
		rb.removeNumber(getFinalNumber());
	if (getOriginalNumber()!=null) {
		rb.addNumber(getOriginalNumber());
	}
		return true;
	}

	 /**
     * Redo a move
     * @return if redo successful
     */
	@Override
	public boolean redoMove() {
		ReleaseBoard rb = (ReleaseBoard)theLevel.getBoard();
		rb.addNumber(getFinalNumber());
		rb.removeNumber(getOriginalNumber());
		return true;
	}

}