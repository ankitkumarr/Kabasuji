package com.halaesus.kabasuji.builder.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.Set;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.builder.entity.*;
import com.halaesus.kabasuji.player.moves.BoardToBoardMove;
import com.halaesus.kabasuji.player.moves.BoardToBullpenMove;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;
import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * 
 */
public class DragNumberFromBoardToNumberBarMove implements MouseListener, MouseMotionListener {

   
 
	ReleaseLevel level;
	ReleaseBuilderView builderView;
	
	private ReleaseNumber sourceNumber;
	
    public DragNumberFromBoardToNumberBarMove(ReleaseBuilderView builderView, ReleaseLevel level) {
        this.builderView = builderView;
        this.level = level;
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		
		int desiredKey = e.BUTTON1_MASK | e.CTRL_MASK;
		if(!((e.getModifiers() & e.CTRL_MASK) == e.CTRL_MASK)) {
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
					ReleaseNumber beingDragged = this.level.getnumberBeingDragged();
					Rectangle tighestPieceRectangle = new Rectangle(this.level.getTopPointOfDraggingPiece().x, 
							                                        this.level.getTopPointOfDraggingPiece().y, 
							                                        53, 
							                                        53);
					
					// STEP 2: Now check if the piece is in the Bullpen Bounds and make the necessary settings
					if( this.builderView.getReleaseNumberBarBounds().contains(tighestPieceRectangle) )
						level.setNumberOverNumberBar(true); // The number is on the numberbar Bounds
					else
						level.setNumberOverNumberBar(false); // The number is not on the numberbar Bounds
					
					// Force the view to repaint
					this.builderView.repaint();
					
				}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		// See if we should be handing this drag or not
		int desiredKey = e.BUTTON1_MASK | e.CTRL_MASK;
		if(!((e.getModifiers() & desiredKey) == desiredKey)) {
			return;
		}
				Rectangle overallBoardRectangle = new Rectangle(this.builderView.getBoardPieceRectangle(0, 0).x, 
						                                        this.builderView.getBoardPieceRectangle(0, 0).y,
						                                        12 * 53, 12 * 53);
				Point mouseClickLocation = new Point(e.getX(), e.getY());
				
				if( !overallBoardRectangle.contains(mouseClickLocation) )
					return; // We shouldn't be handling this move
				
				// Go over each Piece and then their PieceSquares to determine where the click happened
				ReleaseBoard rb = (ReleaseBoard) this.level.getBoard();
				Set<ReleaseNumber> boardNumbers = rb.getReleaseNumbers();
				boolean exit = false; // To keep track if the iteration loop should be quit or not
				for( ReleaseNumber theNumber : boardNumbers ) {
					
						Rectangle numberSquareRect = this.builderView.getBoardPieceRectangle(theNumber.getRow(), theNumber.getCol());
						if( numberSquareRect.contains(mouseClickLocation) ) {
							// The click landed on this location
							try {
								boardNumbers.remove(theNumber); // Remove this number from the Board
							} catch(IllegalStateException exp) { /* For some reason we couldn't remove th piece from the Board */ }
							
							
							// Inform the Model
							this.level.setDraggingActive(true);
							this.sourceNumber = theNumber;
							this.level.setDragSource(AbstractLevel.DRAG_SOURCE_BOARD);
							this.level.setnumberBeingDragged(new ReleaseNumber (theNumber.getValue(), theNumber.getColor(), theNumber.getCol(), theNumber.getRow()));
							this.level.setTopPointOfDraggingPiece(new Point(numberSquareRect.x, numberSquareRect.y));
							this.level.setDraggingDistToPointX(e.getX() - numberSquareRect.x);
							this.level.setDraggingDistToPointY(e.getY() - numberSquareRect.y);
							
							// Force the LevelView to repaint
							this.builderView.repaint();
							
							// We've handled the mousePress, so exit the loop
							exit = true;
							break; // Exit the For Each Loop
						}
					}
				}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		// Stop the drag if it was happening
		int desiredKey = e.BUTTON1_MASK | e.CTRL_MASK;
		if(!((e.getModifiers() & e.CTRL_MASK) == e.CTRL_MASK)) {
			return;
		}
				if( this.level.isDraggingActive() &&
					this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_BOARD ) {
					
					ReleaseNumber numberDragged = this.level.getnumberBeingDragged();
					Point topPiecePoint = this.level.getTopPointOfDraggingPiece();
					
					Rectangle tighestNumberBarRectangle = new Rectangle(topPiecePoint.x, 
							                                        topPiecePoint.y, 
							                                        53, 
							                                        53);
					
					// STEP 2: Calculate the Board Rectangle
					
					Rectangle overallBoardRectangle = new Rectangle(320, 80, 12*53, 12*53);
					// STEP 3: Calculate the NumberBar Rectangle
					Rectangle numberBarRectangle = this.builderView.getReleaseNumberBarBounds();

					
					// STEP 4: Check where was it dropped
					if( overallBoardRectangle.contains(tighestNumberBarRectangle) ) {
						NumberToBoardMove theMove = new NumberToBoardMove(this.level, this.builderView);
						if( theMove.isValid() ) {
							
							theMove.setOriginalNumber(sourceNumber);
							if (theMove.doMove()) {
								MoveManager.pushMove(theMove);
							} 
						}
						}
					 else if( numberBarRectangle.contains(tighestNumberBarRectangle) ) {
						NumberFromBoardToNumberBarMove theMove = new NumberFromBoardToNumberBarMove(this.level, this.builderView);
						if (theMove.isValid()) {

							theMove.setOriginalNumber(sourceNumber);
							if (theMove.doMove()) {
								MoveManager.pushMove(theMove);
							}
						}
					} 
					 
					 else {
						 
						 ((ReleaseBoard)this.level.getBoard()).addNumber(new ReleaseNumber (this.level.getnumberBeingDragged().getValue(), this.level.getnumberBeingDragged().getColor(), this.level.getnumberBeingDragged().getCol(), 
								 this.level.getnumberBeingDragged().getRow()));
					}
					// Nonetheless, stop the drag
					level.setDraggingActive(false);
					level.setNumberOverNumberBar(false);
					level.setDraggingDistToPointX(-1);
					level.setDraggingDistToPointY(-1);
					level.setnumberBeingDragged(null);
					level.setTopPointOfDraggingPiece(null);
					// Force a LevelView repaint
					
				}
				this.builderView.repaint();
	}

 

}