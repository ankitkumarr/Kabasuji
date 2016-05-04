package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.utils.BuilderPieceHelper;

/**
 * Move class to handle Board to Board Moves
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class BoardToBoardMove extends PieceMove {

	/**
	 * Construct an instance of BoardToBoardMove for completing a move
	 * @param theLevel is the Level to be handled
	 * @param theBuilderView The view to be updated after the move
	 */
	public BoardToBoardMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	/**
	 * Checks if the Move to be done is valid or not
	 */
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

		Rectangle tighestPieceRectangle = new Rectangle(topPiecePoint.x, topPiecePoint.y, (xMax + 1) * 53,
				(yMax + 1) * 53);
		// Calculate the Board Rectangle
		Rectangle overallBoardRectangle = new Rectangle(theBuilderView.getBoardPieceRectangle(0, 0).x,
				                                        theBuilderView.getBoardPieceRectangle(0, 0).y,
				                                        12 * theBuilderView.getBoardPieceRectangle(0, 0).width,
				                                        12 * theBuilderView.getBoardPieceRectangle(0, 0).height);

		// If the piece is not in the rectangle, it is not a valid move
		if (!overallBoardRectangle.contains(tighestPieceRectangle))
			return false; // This move cannot take place

		// Get the Piece that will Snap to the Board
		Piece newPieceDragged = BuilderPieceHelper.snapToNearestBoardSquare(theLevel, theBuilderView);
		if (newPieceDragged == null)
			return false; // Failed to snap to the Board and hence, not a valid move

		// Check if Piece overlaps
		if (theLevel.getBoard().doesCollide(newPieceDragged))
			return false; // We clash with some other piece and thus cannot complete the drag

		// Finally, the move was valid, so:
		return true;
	}

	/**
	 * Performs the move
	 */
	@Override
	public boolean doMove() {
		if (theLevel.isDraggingActive() == false)
			return false; // An active drag needs to be in place for this function to be called
		if (isValid() == false)
			return false; // Also, the move should be valid for this function to be called
		
		// Now, snap to the board at the location and update the underlying board
		// STEP 1: Find the squares to snap to
		Piece snappedPiece = BuilderPieceHelper.snapToNearestBoardSquare(theLevel, theBuilderView);
		// Check if snap was successful or not
		if (snappedPiece == null)
			return false; // We failed to snap to the board and hence the move wasn't completed
		else
			theLevel.getBoard().addPiece(snappedPiece); // Add the snapped Piece to the board
		// STEP 2: Mark original BoardSquares as inactive
		for (PieceSquare aPieceSquare : getOriginalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 3: Mark underlying BoardSquares as active
		for (PieceSquare aPieceSquare : snappedPiece.getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
		}
		// STEP 4: Save the Piece
		setFinalPiece(new Piece(snappedPiece));
		// The move was successful, so:
		return true;
	}

	/**
	 * Undoes the move
	 */
	@Override
	public boolean undoMove() {
		// STEP 1: Remove the final Piece that was placed and replace it with the original piece
		theLevel.getBoard().getPieces().remove(getFinalPiece());
		theLevel.getBoard().addPiece(getOriginalPiece());
		// STEP 2: Mark old BoardSquares as inactive
		for (PieceSquare aPieceSquare : getFinalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 3: Mark original BoardSquares as active
		for (PieceSquare aPieceSquare : getOriginalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
		}
		// The move was successful, so:
		return true;
	}

	/**
	 * Redoes the move
	 */
	@Override
	public boolean redoMove() {
		// STEP 1: Check if there is a final piece
		if( getFinalPiece() == null )
			return false; // We cannot redo the move
		// STEP 2: Remove the original piece and add the final piece to the board
		theLevel.getBoard().addPiece(getFinalPiece());
		theLevel.getBoard().getPieces().remove(getOriginalPiece());
		// STEP 3: Mark original BoardSquares as inactive
		for (PieceSquare aPieceSquare : getOriginalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(false);
		}
		// STEP 4: Mark final BoardSquares as active
		for (PieceSquare aPieceSquare : getFinalPiece().getPieceSquares()) {
			theLevel.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
		}
		// The move was successful, so:
		return true;
	}
}
