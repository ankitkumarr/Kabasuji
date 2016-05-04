package com.halaesus.kabasuji.builder.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Set;

import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.builder.entity.*;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseBoard;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * Controller to handle dragging a release number off of the board
 */
public class DragNumberFromBoardToNumberBarMove implements MouseListener, MouseMotionListener {
	/** Level model to update */
	ReleaseLevel level;
	/** View to update */
	ReleaseBuilderView builderView;

	/** The ReleaseNumber being moved */
	private ReleaseNumber sourceNumber;

	/** Associate the given view and model with this controller
	 * @param builderView View
	 * @param level Model
	 */
	public DragNumberFromBoardToNumberBarMove(ReleaseBuilderView builderView, ReleaseLevel level) {
		this.builderView = builderView;
		this.level = level;
	}

	/** Pick up the number if appropriate */
	@Override
	public void mousePressed(MouseEvent e) {
		// See if we should be handing this drag or not
		int desiredKey = InputEvent.BUTTON1_MASK | InputEvent.CTRL_MASK;
		if (!((e.getModifiers() & desiredKey) == desiredKey)) {
			return;
		}
		Rectangle overallBoardRectangle = new Rectangle(this.builderView.getBoardPieceRectangle(0, 0).x,
				this.builderView.getBoardPieceRectangle(0, 0).y, 12 * 53, 12 * 53);
		Point mouseClickLocation = new Point(e.getX(), e.getY());

		if (!overallBoardRectangle.contains(mouseClickLocation))
			return; // We shouldn't be handling this move

		// Go over each Piece and then their PieceSquares to determine where the
		// click happened
		ReleaseBoard rb = (ReleaseBoard) this.level.getBoard();
		Set<ReleaseNumber> boardNumbers = rb.getReleaseNumbers();
		for (ReleaseNumber theNumber : boardNumbers) {

			Rectangle numberSquareRect = this.builderView.getBoardPieceRectangle(theNumber.getRow(),
					theNumber.getCol());
			if (numberSquareRect.contains(mouseClickLocation)) {
				// The click landed on this location
				try {
					boardNumbers.remove(theNumber); // Remove this number from
													// the Board
				} catch (IllegalStateException exp) {
					/*
					 * For some reason we couldn't remove th piece from the
					 * Board
					 */ }

				// Inform the Model
				this.level.setDraggingActive(true);
				this.sourceNumber = theNumber;
				this.level.setDragSource(AbstractLevel.DRAG_SOURCE_BOARD);
				this.level.setnumberBeingDragged(new ReleaseNumber(theNumber.getValue(), theNumber.getColor(),
						theNumber.getCol(), theNumber.getRow()));
				this.level.setTopPointOfDraggingPiece(new Point(numberSquareRect.x, numberSquareRect.y));
				this.level.setDraggingDistToPointX(e.getX() - numberSquareRect.x);
				this.level.setDraggingDistToPointY(e.getY() - numberSquareRect.y);

				// Force the LevelView to repaint
				this.builderView.repaint();

				break; // Exit the For Each Loop
			}
		}
	}

	/** Move the number around */
	@Override
	public void mouseDragged(MouseEvent e) {

		if (!((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK)) {
			return;
		}
		// Only if the dragging is active, make the necessary changes
		if (this.level.isDraggingActive() && this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_BOARD) {

			// Form the new point to draw the Piece
			this.level.setTopPointOfDraggingPiece(new Point(e.getX() - this.level.getDraggingDistToPointX(),
					e.getY() - this.level.getDraggingDistToPointY()));

			this.level.getnumberBeingDragged();
			Rectangle tighestPieceRectangle = new Rectangle(this.level.getTopPointOfDraggingPiece().x,
					this.level.getTopPointOfDraggingPiece().y, 53, 53);

			// STEP 2: Now check if the piece is in the Bullpen Bounds and make
			// the necessary settings
			if (this.builderView.getReleaseNumberBarBounds().contains(tighestPieceRectangle))
				level.setNumberOverNumberBar(true); // The number is on the
													// numberbar Bounds
			else
				level.setNumberOverNumberBar(false); // The number is not on the
														// numberbar Bounds

			// Force the view to repaint
			this.builderView.repaint();

		}
	}

	/** Drop the number and perform move based on where it was dropped */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (!((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK)) {
			return;
		}
		if (this.level.isDraggingActive() && this.level.getDragSource() == AbstractLevel.DRAG_SOURCE_BOARD) {

			this.level.getnumberBeingDragged();
			Point topPiecePoint = this.level.getTopPointOfDraggingPiece();

			Rectangle tighestNumberBarRectangle = new Rectangle(topPiecePoint.x, topPiecePoint.y, 53, 53);

			// STEP 2: Calculate the Board Rectangle

			Rectangle overallBoardRectangle = new Rectangle(320, 80, 12 * 53, 12 * 53);
			// STEP 3: Calculate the NumberBar Rectangle
			Rectangle numberBarRectangle = this.builderView.getReleaseNumberBarBounds();

			// STEP 4: Check where was it dropped
			if (overallBoardRectangle.contains(tighestNumberBarRectangle)) {
				NumberToBoardMove theMove = new NumberToBoardMove(this.level, this.builderView);
				if (theMove.isValid()) {

					theMove.setOriginalNumber(sourceNumber);
					if (theMove.doMove()) {
						MoveManager.pushMove(theMove);
					}
				}
			} else if (numberBarRectangle.contains(tighestNumberBarRectangle)) {
				NumberFromBoardToNumberBarMove theMove = new NumberFromBoardToNumberBarMove(this.level,
						this.builderView);
				if (theMove.isValid()) {

					theMove.setOriginalNumber(sourceNumber);
					if (theMove.doMove()) {
						MoveManager.pushMove(theMove);
					}
				}
			}
			else {
				((ReleaseBoard) this.level.getBoard()).addNumber(new ReleaseNumber(
						this.level.getnumberBeingDragged().getValue(), this.level.getnumberBeingDragged().getColor(),
						this.level.getnumberBeingDragged().getCol(), this.level.getnumberBeingDragged().getRow()));
			}
			// Nonetheless, stop the drag
			level.setDraggingActive(false);
			level.setNumberOverNumberBar(false);
			level.setDraggingDistToPointX(-1);
			level.setDraggingDistToPointY(-1);
			level.setnumberBeingDragged(null);
			level.setTopPointOfDraggingPiece(null);
		}
		this.builderView.repaint();
	}

	/** Not needed */
	@Override
	public void mouseMoved(MouseEvent e) {}

	/** Not needed */
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	/** Not needed */
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	/** Not needed */
	@Override
	public void mouseExited(MouseEvent arg0) {}
}
