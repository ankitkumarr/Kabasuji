package com.halaesus.kabasuji.builder.entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.BoardSquare;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.utils.BuilderPieceHelper;

public class WorkspaceToBoardMove implements IMove {

	AbstractBuilderView levelView;
	Piece pieceBeingMoved;
	Piece snappedPiece;
	Vector<Point> boardSquares;

	public WorkspaceToBoardMove(AbstractBuilderView levelView, Piece pieceBeingMoved, Piece snappedPiece) {
		this.levelView = levelView;
		this.pieceBeingMoved = pieceBeingMoved;
		this.snappedPiece = snappedPiece;
		boardSquares = new Vector<Point>();
	}

	@Override
	public boolean isValid(AbstractLevel level) {
		assert (level.isDraggingActive() == true); // We can only be called if a
													// drag is in action
		// Take Board Bounds and see if the Piece is within those bounds or not
		Piece pieceDragged = level.getPieceBeingDragged();
		Point topPiecePoint = level.getTopPointOfDraggingPiece();
		
		if (snappedPiece == null)
			return false;

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
		Rectangle overallBoardRectangle = new Rectangle(this.levelView.getBoardPieceRectangle(0, 0).x,
				this.levelView.getBoardPieceRectangle(0, 0).y, 12 * this.levelView.getBoardPieceRectangle(0, 0).width,
				12 * this.levelView.getBoardPieceRectangle(0, 0).height);

		// See if Piece is within Board Bounds
		if (!overallBoardRectangle.contains(tighestPieceRectangle))
			return false; // Definitely not a valid move

		// Get the Piece that will Snap to the Board
		Piece newPieceDragged = BuilderPieceHelper.snapToNearestBoardSquare(level, this.levelView);
		if (newPieceDragged == null)
			return false; // Failed to snap to the Board and hence, not a valid
							// move

		// Check if Piece overlaps
		if (level.getBoard().doesCollide(newPieceDragged))
			return false; // We clash with some other piece and thus cannot
							// complete the drag

		// Check if Piece is outside Active Board Bounds
		// if( level.getBoard().isOutsideBounds(newPieceDragged) )
		// return false; // We are outside board active bounds and thus the drag
		// cannot be completed

		// Finally, the move was valid, so:
		return true;
	}

	@Override
	public boolean doMove(AbstractLevel level) {
		// STEP 2: Increment Bullpen Count for the respective piece
		pieceBeingMoved.getParentHexomino().changeCount(1);
		// STEP 3: Mark underlying BoardSquares as active
		for (PieceSquare aPieceSquare : snappedPiece.getPieceSquares()) {
			if (!level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].isActive()) {
				boardSquares.add(new Point(aPieceSquare.getRow(), aPieceSquare.getCol()));
				level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setActive(true);
			}
		}

		// The move was successful, so:
		level.getBoard().addPiece(snappedPiece);
		return true;
	}

	@Override
	public boolean undoMove(AbstractLevel level) {
		/**
		
		BoardSquare[][] squares = level.getBoard().getSquares();

		for (Point p : boardSquares) {
			squares[p.x][p.y].setActive(false);
		}
		pieceBeingMoved.getParentHexomino().changeCount(-1);
		return true;
		
		*/
		
		return false;
	}

	@Override
	public boolean redoMove(AbstractLevel level) {
		/**
		
		BoardSquare[][] squares = level.getBoard().getSquares();

		for (Point p : boardSquares) {
			squares[p.x][p.y].setActive(true);
		}
		pieceBeingMoved.getParentHexomino().changeCount(1);
		return true;
		
		*/
		
		return false;
	}

}