package com.halaesus.kabasuji.builder.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.BoardToBoardMove;
import com.halaesus.kabasuji.builder.entity.BoardToBullpenMove;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.utils.BuilderPieceHelper;

/**
 * Controller to handle dragging pieces already on the board
 */
public class DragPieceFromBoard implements MouseListener, MouseMotionListener {
	/** Our view to update */
    AbstractBuilderView levelView;
    /** The piece that was clicked on */
	Piece originalPiece;
	/** The level model */
    AbstractLevel level;

    /** Associate the given model and view with this controller
     * @param theLevel Model
     * @param levelView View
     */
    public DragPieceFromBoard(AbstractLevel theLevel, AbstractBuilderView levelView) {
        // Save the information
    	this.levelView = levelView;
        this.level = theLevel;
    }
    
    /** Pick up the piece from the board */
	@Override
	public void mousePressed(MouseEvent e) {
		
		// only allow left click
		if (!SwingUtilities.isLeftMouseButton(e))
			return;
		if((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
			return;
		}
			
		// See if we should be handing this drag or not
		Rectangle overallBoardRectangle = new Rectangle(this.levelView.getBoardPieceRectangle(0, 0).x, 
				                                        this.levelView.getBoardPieceRectangle(0, 0).y,
				                                        12 * 53, 12 * 53);
		Point mouseClickLocation = new Point(e.getX(), e.getY());
		
		if( !overallBoardRectangle.contains(mouseClickLocation) )
			return; // We shouldn't be handling this move
		
		// Go over each Piece and then their PieceSquares to determine where the click happened
		Iterator<Piece> boardPieces = this.level.getBoard().getPiecesIt();
		boolean exit = false; // To keep track if the iteration loop should be quit or not
		for( ; boardPieces.hasNext() && !exit; ) {
			Piece aPiece = new Piece(boardPieces.next()); // Get the next Piece
			// Go over each of the PieceSquares
			for( PieceSquare aPieceSquare : aPiece.getPieceSquares() ) {
				// See if the click landed on this PieceSquare
				Rectangle pieceSquareRect = this.levelView.getBoardPieceRectangle(aPieceSquare.getRow(), aPieceSquare.getCol());
				if( pieceSquareRect.contains(mouseClickLocation) ) {
					// The click landed on this location
					try {
						boardPieces.remove(); // Remove this piece from the Board
					} catch(IllegalStateException exp) { /* For some reason we couldn't remove th piece from the Board */ }
					// Backup the Original Board PieceSquares
					
					// remove any possible hints from underlying board squares because piece is being moved
					for (PieceSquare s : aPiece.getPieceSquares()) {
						this.level.getBoard().addHint(s.getRow(), s.getCol(), -1); // remove hint
					}
					
					// Save the original piece
					originalPiece = new Piece(aPiece);
					
					// Solve for tightest bounding rectangle around the PieceSquares
					int xMin = aPiece.getPieceSquares()[0].getCol();
					int yMin = aPiece.getPieceSquares()[0].getRow();
					
					for( PieceSquare s: aPiece.getPieceSquares() ){
						if (s.getCol() < xMin) xMin = s.getCol();
						if (s.getRow() < yMin) yMin = s.getRow();				
					}
					// Calculate Top_Left Rectangle Square
					Rectangle topLeftPieceRect = this.levelView.getBoardPieceRectangle(yMin, xMin);
					
					// Inform the Model
					this.level.setDraggingActive(true);
					this.level.setDragSource(AbstractLevel.DRAG_SOURCE_BOARD);
					this.level.setPieceBeingDragged(new Piece(aPiece.getColorID(), aPiece.pushTopLeft(), aPiece.getParentHexomino()));
					this.level.setTopPointOfDraggingPiece(new Point(topLeftPieceRect.x, topLeftPieceRect.y));
					this.level.setDraggingDistToPointX(e.getX() - topLeftPieceRect.x);
					this.level.setDraggingDistToPointY(e.getY() - topLeftPieceRect.y);
					
					// Force the LevelView to repaint
					this.levelView.repaint();
					
					// We've handled the mousePress, so exit the loop
					exit = true;
					break; // Bug fix right here
				}
			}
		}
	}

	/** Move the Piece around */
	@Override
	public void mouseDragged(MouseEvent e) {
		if((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
			return;
		}
		// Only if the dragging is active, make the necessary changes
		if( this.level.isDraggingActive() &&
			this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_BOARD ) {
			
			// Form the new point to draw the Piece
			this.level.setTopPointOfDraggingPiece(new Point(e.getX() - this.level.getDraggingDistToPointX(), 
					                                        e.getY() - this.level.getDraggingDistToPointY()));
			
			// Check if the Piece is in the Bullpen Bounds
			// STEP 1: Calculate tightest rectangle around PieceSquares
			PieceSquare[] squares = this.level.getPieceBeingDragged().getPieceSquares();
			int xMax = squares[0].getCol();
			int yMax = squares[0].getRow();
			
			for( PieceSquare s: squares ){
				if (s.getCol() > xMax)
					xMax = s.getCol(); // We found a new max, save it
				if (s.getRow() > yMax)
					yMax = s.getRow(); // We found a new max, save it
			}
			
			Rectangle tighestPieceRectangle = new Rectangle(this.level.getTopPointOfDraggingPiece().x, 
					                                        this.level.getTopPointOfDraggingPiece().y, 
					                                        (xMax + 1) * 53, 
					                                        (yMax + 1) * 53);
			
			// STEP 2: Now check if the piece is in the Bullpen Bounds and make the necessary settings
			if( this.levelView.getBullpenBounds().contains(tighestPieceRectangle) )
				level.setPieceOverBullpen(true); // The piece is on the Bullpen Bounds
			else
				level.setPieceOverBullpen(false); // The piece is not on the Bullpen Bounds
			
			// Force the view to repaint
			this.levelView.repaint();
			
		}
	}

	/** Drop the piece and spawn the appropriate move */
	@Override
	public void mouseReleased(MouseEvent e) {
		if((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
			return;
		}
		// Stop the drag if it was happening
		if( this.level.isDraggingActive() &&
			this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_BOARD ) {
			
			Piece pieceDragged = this.level.getPieceBeingDragged();
			Point topPiecePoint = this.level.getTopPointOfDraggingPiece();
			
			// Check where was the Piece Dropped
			// STEP 1: Calculate tightest rectangle around PieceSquares
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
			
			// STEP 2: Calculate the Board Rectangle
			Rectangle overallBoardRectangle = new Rectangle(this.levelView.getBoardPieceRectangle(0, 0).x, 
					                                        this.levelView.getBoardPieceRectangle(0, 0).y, 
					                                        12 * this.levelView.getBoardPieceRectangle(0, 0).width, 
					                                        12 * this.levelView.getBoardPieceRectangle(0, 0).height);
			
			// STEP 3: Calculate the Bullpen Rectangle
			Rectangle bullpenRectangle = this.levelView.getBullpenBounds();
			
			// STEP 4: Check where was it dropped
			if( overallBoardRectangle.contains(tighestPieceRectangle) ) {
				
				Piece snappedPiece = BuilderPieceHelper.snapToNearestBoardSquare(level, this.levelView);
				
				// It was dropped on the board itself; Spawn off the move
				BoardToBoardMove theMove = new BoardToBoardMove(this.level, this.levelView);
				// Now, attempt the move
				if( theMove.isValid() ) {
					// The move is valid; Set the original piece
					theMove.setOriginalPiece(originalPiece);
					// Perform the move and let the underlying board know about this
					if (theMove.doMove())
						MoveManager.pushMove(theMove);
					Piece finalPiece = snappedPiece;
					// Check if the Final Piece location is different from the original location or not
					boolean locationChanged = false;
					for(int idx = 0; idx < this.originalPiece.getPieceSquares().length; idx++)
						if( ( this.originalPiece.getPieceSquares()[idx].getRow() != finalPiece.getPieceSquares()[idx].getRow() ) ||
							( this.originalPiece.getPieceSquares()[idx].getCol() != finalPiece.getPieceSquares()[idx].getCol() ) )
							locationChanged = true;
					// Only if the location changed, inform the board of this move to have happened
					if( locationChanged == true )
						this.level.boardPieceUpdated(this.originalPiece.getPieceSquares(), finalPiece);
				} else {
					// The move wasn't performed. Put the piece back to its original place
					this.level.getBoard().addPiece(new Piece(originalPiece));
					originalPiece = null; // Remove old piece
				}
			} else if( bullpenRectangle.contains(tighestPieceRectangle) ) {
				// It was dropped on the Bullpen; Spawn off the move
				BoardToBullpenMove theMove = new BoardToBullpenMove(this.level, this.levelView);
				// Save the original Piece now
				theMove.setOriginalPiece(originalPiece);
				// Now attempt the move
				if( theMove.isValid() ) {
					// The move is valid; Perform the move and let the underlying board know about this
					if (theMove.doMove())
						MoveManager.pushMove(theMove);
					this.level.boardPieceRemoved(level.getPieceBeingDragged());
				} else {
					// The move wasn't performed :( Put the piece back to where it was picked from
					this.level.getBoard().addPiece(new Piece(originalPiece));
					originalPiece = null; // Remove old piece
				}
			} else {
				// It wasn't dropped on a valid location. Place the piece back to its source
				// Add the original piece back to the board
				this.level.getBoard().addPiece(new Piece(originalPiece));
				originalPiece = null; // Remove old piece
			}
			// Nonetheless, stop the drag
			level.setDraggingActive(false);
			level.setPieceOverBullpen(false);
			level.setDraggingDistToPointX(-1);
			level.setDraggingDistToPointY(-1);
			level.setPieceBeingDragged(null);
			level.setTopPointOfDraggingPiece(null);
			// Refresh the GUI
			this.levelView.repaint();
			levelView.updatePlayerPaletteView();
		}
	}

	/** Not needed */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseExited(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseMoved(MouseEvent e) {}
}
