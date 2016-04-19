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

public class DragPieceFromWorkspaceToBoard implements MouseListener, MouseMotionListener{

	AbstractLevel level;
    AbstractLevelView levelView;

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
				this.level.setPieceBeingDragged(new Piece(thePiece.getColor(), thePiece.pushTopLeft()));
				this.level.setTopPointOfDraggingPiece(new Point(topLeftPieceRect.x, topLeftPieceRect.y));
				this.level.setDraggingDistToPointX(e.getX() - topLeftPieceRect.x);
				this.level.setDraggingDistToPointY(e.getY() - topLeftPieceRect.y);
				// Remove piece from Workspace
				this.level.getLevelBullpen().getWorkspace().addPiece(null);
				// Break out of the loop
				break;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Form the new point to draw the Piece
		this.level.setTopPointOfDraggingPiece(new Point(e.getX() - this.level.getDraggingDistToPointX(), 
				                                        e.getY() - this.level.getDraggingDistToPointY()));
		// Force the view to repaint
		this.levelView.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Stop the drag if it was happening
		if( level.isDraggingActive() ) {
			// If a piece was being dragged, then:
			level.setDraggingActive(false);
			level.setPieceBeingDragged(null);
			// Check to snap to board and mutate the board
			// TODO
			// TODO: Create the move class to perform it as well
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