package com.halaesus.kabasuji.player.moves;

import java.awt.Point;
import java.awt.Rectangle;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.player.moves.IMove;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;
import com.halaesus.kabasuji.utils.PieceHelper;

/**
 * Represents the movement of a Piece from the Workspace to the Player Board
 * <p>
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class WorkspaceToBoardMove implements IMove {
	
	/** The Level View where the changes should appear */
	AbstractLevelView levelView;

	/**
	 * Creates the move class and is associated with a LevelView to be updated
	 * @param levelView
	 */
    public WorkspaceToBoardMove(AbstractLevelView levelView) {
    	this.levelView = levelView;
    }
    
    /**
     * Verifies that the WorkspaceToBoardMove is valid
     * @param level the AbstractLevel where the move will be validated
     */
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
		Rectangle overallBoardRectangle = new Rectangle(this.levelView.getBoardPieceRectangle(0, 0).x - 25, 
				                                        this.levelView.getBoardPieceRectangle(0, 0).y - 25, 
				                                        13 * this.levelView.getBoardPieceRectangle(0, 0).width, 
				                                        13 * this.levelView.getBoardPieceRectangle(0, 0).height);
		
		// See if Piece is within Board Bounds
		if( !overallBoardRectangle.contains(tighestPieceRectangle) )
			return false; // Definitely not a valid move
		
		// Get the Piece that will Snap to the Board
		Piece newPieceDragged = PieceHelper.snapToNearestBoardSquare(level, this.levelView);
		if( newPieceDragged == null )
			return false; // Failed to snap to the Board and hence, not a valid move

		// Check if Piece overlaps
		if( level.getBoard().doesCollide(newPieceDragged) )
			return false; // We clash with some other piece and thus cannot complete the drag
		
		// Check if Piece is outside Active Board Bounds
		if( level.getBoard().isOutsideBounds(newPieceDragged) )
			return false; // We are outside board active bounds and thus the drag cannot be completed
		
		// Check if the level is a PuzzleLevel. If is is a PuzzleLevel, then check the number of moves left
		if( level.getLevelType().toUpperCase().equals("PUZZLE") && (((PuzzleLevel)level).getMovesLeft() == 0) )
			return false; // No more moves can be performed :(
		
		// Finally, the move was valid, so:
		return true;
    }

    /**
     * Perform the move on the given AbstractLevel
     */
    public Piece doMove(AbstractLevel level, PieceSquare[] originalPieceSquares) {
    	if( level.isDraggingActive() == false )
    		return null; // An active drag needs to be in place for this function to be called
    	if( isValid(level) == false )
    		return null; // Also, the move should be valid for this function to be called
    	// Now, snap to the board at the location and update the underlying board
    	// STEP 1: Find the squares to snap to
		Piece snappedPiece = PieceHelper.snapToNearestBoardSquare(level, this.levelView);
		if( snappedPiece == null )
			return null; // We failed to snap to the board and hence the move wasn't completed
		else
			level.getBoard().addPiece(snappedPiece); // Add the snapped Piece to the board
    	// STEP 2: Decrement Bullpen Count for the respective piece
    	level.getPieceBeingDragged().getParentHexomino().setCount(level.getPieceBeingDragged().getParentHexomino().getCount() - 1);
		// STEP 3: Mark underlying BoardSquares as filled
		for( PieceSquare aPieceSquare : snappedPiece.getPieceSquares() ) {
			level.getBoard().getSquares()[aPieceSquare.getRow()][aPieceSquare.getCol()].setFilled(true);
		}
		
		// The move was successful, so:
		return snappedPiece;
    }

}