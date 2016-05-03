package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;
import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.utils.BuilderPieceHelper;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class WorkspaceToBoardMove extends PieceMove {

	public WorkspaceToBoardMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

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

		for (PieceSquare s : squares) {
			if (s.getCol() > xMax)
				xMax = s.getCol(); // We found a new max, save it
			if (s.getRow() > yMax)
				yMax = s.getRow(); // We found a new max, save it
		}

		Rectangle tighestPieceRectangle = new Rectangle(topPiecePoint.x, topPiecePoint.y, (xMax + 1) * 53, (yMax + 1) * 53);
		// Calculate the Board Rectangle
		Rectangle overallBoardRectangle = new Rectangle(this.theBuilderView.getBoardPieceRectangle(0, 0).x,
														this.theBuilderView.getBoardPieceRectangle(0, 0).y,
														12 * this.theBuilderView.getBoardPieceRectangle(0, 0).width,
														12 * this.theBuilderView.getBoardPieceRectangle(0, 0).height);

		// See if Piece is within Board Bounds
		if (!overallBoardRectangle.contains(tighestPieceRectangle))
			return false; // Definitely not a valid move

		// Get the Piece that will Snap to the Board
		Piece newPieceDragged = BuilderPieceHelper.snapToNearestBoardSquare(this.theLevel, this.theBuilderView);
		if (newPieceDragged == null)
			return false; // Failed to snap to the Board and hence, not a valid move

		// Check if Piece overlaps
		if (theLevel.getBoard().doesCollide(newPieceDragged))
			return false; // We clash with some other piece and thus cannotb complete the drag

		// Finally, the move was valid, so:
		return true;
	}

	@Override
	public boolean doMove() {
		if( theLevel.isDraggingActive() == false )
    		return false; // An active drag needs to be in place for this function to be called
    	if( isValid() == false )
    		return false; // Also, the move should be valid for this function to be called
    	// Now, snap to the board at the location and update the underlying board
    	// STEP 1: Find the squares to snap to
		Piece snappedPiece = BuilderPieceHelper.snapToNearestBoardSquare(theLevel, this.theBuilderView);
		if( snappedPiece == null )
			return false; // We failed to snap to the board and hence the move wasn't completed
		else
			theLevel.getBoard().addPiece(snappedPiece); // Add the snapped Piece to the board
		// STEP 2: Increment Bullpen Count for the respective piece
		theLevel.getPieceBeingDragged().getParentHexomino().changeCount(1);
		// STEP 3: Mark underlying BoardSquares as active
		for (PieceSquare aPieceSquare : snappedPiece.getPieceSquares()) {
			if (!theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].isActive())
				theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
		}
		// STEP 4: Save the Piece
		setFinalPiece(new Piece(snappedPiece));
		// The move was successful, so:
		return true;
	}

	@Override
	public boolean undoMove() {
		// STEP 1: Remove the piece from the board and add it to the Workspace
		theLevel.getBoard().getPieces().remove(getFinalPiece());
		theLevel.getLevelBullpen().getWorkspace().addPiece(getOriginalPiece());
		theLevel.getLevelBullpen().getWorkspace().getPiece().centerPiece(); // Center the Piece after adding it to the Workspace
		// STEP 2: Mark original BoardSquares as inactive
		for (PieceSquare aPieceSquare : getFinalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 3: Decrement Bullpen Count for the respective piece
		if (getFinalPiece().getParentHexomino().getCount() > 0)
			getFinalPiece().getParentHexomino().changeCount(-1);
		// The move was successful, so:
		return true;
	}

	@Override
	public boolean redoMove() {
		// STEP 1: Check if there is a final piece
		if( finalPiece == null )
			return false;
		// STEP 2: Remvoe the Piece from the Workspace and add it back to the board
		theLevel.getBoard().addPiece(finalPiece);
		theLevel.getLevelBullpen().getWorkspace().addPiece(null); // Remove the Piece from the Workspace
		// STEP 3: Mark the underlying BoardSquares as active
		for (PieceSquare aPieceSquare : getFinalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
		}
		// STEP 4: Increment Bullpen Count for the respective piece
		getOriginalPiece().getParentHexomino().changeCount(1);
		// The move was successful, so;
		return true;
	}

}