package com.halaesus.kabasuji.builder.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.WorkspaceToBoardMove;
import com.halaesus.kabasuji.builder.entity.WorkspaceToPlayerPaletteMove;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;

/**
 * Controller to handle dragging a piece out of the workspace
 */
public class DragPieceFromWorkspace implements MouseListener, MouseMotionListener{
	/** The view to update */
    AbstractBuilderView levelView;
    /** The piece being dragged */
    Piece originalPiece;
    /** The level model */
	AbstractLevel level;
	
	/**
	 * Associate the given model and view with this controller
	 * @param theLevel model
	 * @param levelView view
	 */
    public DragPieceFromWorkspace(AbstractLevel theLevel, AbstractBuilderView levelView) {
    	// Save the information
    	this.level = theLevel;
    	this.levelView = levelView;
    }

    /**
     * Initiate the drag if appropriate
     */
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
				this.level.setPieceBeingDragged(new Piece(thePiece.getColorID(), thePiece.pushTopLeft(), thePiece.getParentHexomino()));
				this.level.setTopPointOfDraggingPiece(new Point(topLeftPieceRect.x, topLeftPieceRect.y));
				this.level.setDraggingDistToPointX(e.getX() - topLeftPieceRect.x);
				this.level.setDraggingDistToPointY(e.getY() - topLeftPieceRect.y);
				// Save the workspace piece
				this.originalPiece = new Piece(this.level.getLevelBullpen().getWorkspace().getPiece());
				// Remove piece from Workspace
				this.level.getLevelBullpen().getWorkspace().addPiece(null);
				// Force the LevelView to repaint
				this.levelView.repaint();
				// Break out of the loop
				break;
			}
		}
	}

	/**
	 * Move the piece
	 */
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

	/**
	 * Drop the piece on a certain location and generate the appropriate
	 * move based on where it was dropped
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// Stop the drag if it was happening
		if( this.level.isDraggingActive() &&
			this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_WORKSPACE ) {
			// Create the move
			WorkspaceToBoardMove theMove = new WorkspaceToBoardMove(this.level, this.levelView);
			// Now attempt the move
			if( theMove.isValid() ) {
				// The move is valid; Add the original Piece
				theMove.setOriginalPiece(originalPiece);
				// Perform the move and let the underlying board know about this
				if (theMove.doMove())
					MoveManager.pushMove(theMove);
				//level.newPieceDropped();
			} else if (levelView.getPlayerPaletteFrame().getBounds().contains(e.getPoint())){ // mouse is released over the player palette
				WorkspaceToPlayerPaletteMove wtpMove = new WorkspaceToPlayerPaletteMove(this.level, this.level.getPieceBeingDragged());
				if (wtpMove.isValid()) {
					if (wtpMove.doMove())
						MoveManager.pushMove(wtpMove);
				}
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
			// Refresh the GUI
			this.levelView.repaint();
			levelView.updatePlayerPaletteView();
		}
	}

	/** Not needed */
	@Override
	public void mouseMoved(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseExited(MouseEvent e) {}
}
