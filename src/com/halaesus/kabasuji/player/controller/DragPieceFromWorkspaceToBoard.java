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
		// Get all the Piece ke PieceSquares and find the tightest rectangle around the PieceSquares
		Piece thePiece = level.getLevelBullpen().getWorkspace().getPiece();
		PieceSquare[] squares = thePiece.getPieceSquares();
		
		int xMin = squares[0].getCol();
		int xMax = squares[0].getCol();
		int yMin = squares[0].getRow();
		int yMax = squares[0].getRow();
		
		for (PieceSquare s: squares){
			if (s.getCol() < xMin) xMin = s.getCol();
			if (s.getCol() > xMax) xMax = s.getCol();
			if (s.getRow() < yMin) yMin = s.getRow();
			if (s.getRow() > yMax) yMax = s.getRow();				
		}
		// Solve for the Tightest Rect Top Left Point
		Rectangle topLeftSolver = this.levelView.getBullpenWorkspacePieceRectangle(yMin, xMin);
		int tlX = topLeftSolver.x;
		int tlY = topLeftSolver.y;
		Point topLeftPoint = new Point(tlX, tlY);
		// See if the click lies on one of the PieceSquares
		for( PieceSquare aPieceSquare : squares ) {
			// Find this PieceSquare Rectangle
			Rectangle rect = this.levelView.getBullpenWorkspacePieceRectangle(aPieceSquare.getRow(), aPieceSquare.getCol());
			// If the click is on this rectangle, do further processing:
			if( rect.contains(e.getX(), e.getY()) ) {
				// Set necessary dragging stuff
				this.level.setDraggingActive(true);
				this.level.setDraggingDistToPointX((e.getX() - topLeftPoint.x) * 51 / 38);
				this.level.setDraggingDistToPointY((e.getY() - topLeftPoint.y) * 51 / 38);
				this.level.setPieceBeingDragged(new Piece(thePiece.getColor(), thePiece.getOriginalPieceSquares()));
				// Remove piece from Workspace
				this.level.getLevelBullpen().getWorkspace().addPiece(null);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Update start off the 6x6 Matrix point
		this.level.setTopPointOfMatrix(new Point(e.getX() - this.level.getDraggingDistToPointX(),
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