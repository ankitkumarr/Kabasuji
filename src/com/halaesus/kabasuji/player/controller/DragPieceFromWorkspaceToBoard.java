package com.halaesus.kabasuji.player.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.player.entity.AbstractLevel;
import com.halaesus.kabasuji.player.entity.Piece;
import com.halaesus.kabasuji.player.entity.PieceSquare;
import com.halaesus.kabasuji.player.entity.WorkspaceToBoardMove;

public class DragPieceFromWorkspaceToBoard implements MouseListener, MouseMotionListener{

    AbstractLevelView levelView;
	AbstractLevel level;
	
    public DragPieceFromWorkspaceToBoard(AbstractLevel theLevel, AbstractLevelView levelView) {
    	// Save the information
    	this.level = theLevel;
    	this.levelView = levelView;
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// If there is no piece to drag, do nothing
		if( level.getLevelBullpen().getWorkspace().getPiece() == null )
			return;
		// Go over all the PieceSquares of the Workspace Piece and see if a click took place there
		Piece thePiece = level.getLevelBullpen().getWorkspace().getPiece();
		PieceSquare[] squares = thePiece.getPieceSquares();
		
		for( PieceSquare aPieceSquare : squares ) {
			// Get the Rectangle for this PieceSquare
			Rectangle pieceSquareRect = this.levelView.getBullpenWorkspacePieceRectangle(aPieceSquare.getRow(), aPieceSquare.getCol());
			// See if the click is in the Rectangle
			if( pieceSquareRect.contains(new Point(e.getX(), e.getY())) ) {
				// Okay. The click was on this PieceSquare
				// Solve for tightest bounding rectangle around the PieceSquares
				int xMin = squares[0].getCol();
				int yMin = squares[0].getRow();
				
				for( PieceSquare s: squares ){
					if (s.getCol() < xMin) xMin = s.getCol();
					if (s.getRow() < yMin) yMin = s.getRow();				
				}
				// Calculate Top_Left Rectangle Square
				Rectangle topLeftPieceRect = this.levelView.getBullpenWorkspacePieceRectangle(yMin, xMin);
				// Inform the Model
				this.level.setDraggingActive(true);
				this.level.setDragSource(AbstractLevel.DRAG_SOURCE_WORKSPACE);
				this.level.setPieceBeingDragged(new Piece(thePiece.getColor(), thePiece.pushTopLeft(), thePiece.getParentHexomino()));
				this.level.setTopPointOfDraggingPiece(new Point(topLeftPieceRect.x, topLeftPieceRect.y));
				this.level.setDraggingDistToPointX(e.getX() - topLeftPieceRect.x);
				this.level.setDraggingDistToPointY(e.getY() - topLeftPieceRect.y);
				// Remove piece from Workspace
				this.level.getLevelBullpen().getWorkspace().addPiece(null);
				// Force the LevelView to repaint
				this.levelView.repaint();
				// Break out of the loop
				break;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Only if the dragging is active, make the necessary changes
		if( this.level.isDraggingActive() &&
			this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_WORKSPACE ) {
			// Form the new point to draw the Piece
			this.level.setTopPointOfDraggingPiece(new Point(e.getX() - this.level.getDraggingDistToPointX(), 
					                                        e.getY() - this.level.getDraggingDistToPointY()));
			// Force the view to repaint
			this.levelView.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Stop the drag if it was happening
		if( this.level.isDraggingActive() &&
			this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_WORKSPACE ) {
			// Create the move
			WorkspaceToBoardMove theMove = new WorkspaceToBoardMove(this.levelView);
			// Now attempt the move
			if( theMove.isValid(this.level) ) {
				// The move is valid; Perform the move and let the underlying board know about this
				level.newPieceDropped(theMove.doMove(this.level));
			} else {
				// The move wasn't performed; Bring the piece back to the workspace
				this.level.getLevelBullpen().getWorkspace().addPiece(level.getPieceBeingDragged());
				this.level.getLevelBullpen().getWorkspace().getPiece().centerPiece(); // Center it in the Workspace
			}
			// Stop the drag
			level.setDraggingActive(false);
			level.setPieceOverBullpen(false);
			level.setDraggingDistToPointX(-1);
			level.setDraggingDistToPointY(-1);
			level.setPieceBeingDragged(null);
			level.setTopPointOfDraggingPiece(null);
			// Force a LevelView repaint
			this.levelView.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseClicked(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseEntered(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseExited(MouseEvent e) { /* Nothing to do */ }

}