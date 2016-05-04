package com.halaesus.kabasuji.builder.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.NumberToBoardMove;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * Controller to drag a release number on to the board
 */
public class DragNumberToBoardMove implements MouseListener, MouseMotionListener {
	/** The level model to update */
	ReleaseLevel level;
	/** The level view to update */
	ReleaseBuilderView builderView;

	/** Associate the given model and view with this controller
	 * @param level Model
	 * @param builderView View
	 */
	public DragNumberToBoardMove(ReleaseLevel level, ReleaseBuilderView builderView) {
		this.level = level;
		this.builderView = builderView;
	}

	/** Pick up a number if user clicked on one */
	@Override
	public void mousePressed(MouseEvent e) {
		int desiredKey = InputEvent.BUTTON1_MASK | InputEvent.CTRL_MASK;
		if (((e.getModifiers() & desiredKey) == desiredKey)) {
			return;
		}

		Point mouseClickLocation = new Point(e.getX(), e.getY());
		ReleaseNumber numBeingdragged;

		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 6; j++) {
				Rectangle num = this.builderView.getReleaseNumberRectangle(i, j);
				if (num.contains(mouseClickLocation)) {
					this.level.setDraggingActive(true);
					this.level.setDragSource(ReleaseLevel.DRAG_SOURCE_NUMBERBAR);
					numBeingdragged = this.level.getNumberBar().getNumbers()[i - 1][j - 1];
					this.level.setnumberBeingDragged(new ReleaseNumber(numBeingdragged.getValue(),
							numBeingdragged.getColor(), numBeingdragged.getCol(), numBeingdragged.getRow()));
					this.level.setTopPointOfDraggingPiece(new Point(num.x, num.y));
					this.level.setDraggingDistToPointX(e.getX() - num.x);
					this.level.setDraggingDistToPointY(e.getY() - num.y);
				}
			}
		}
		this.builderView.repaint();

		if (this.level.getnumberBeingDragged() == null) {
			return;
		}
	}

	/** Move the number around */
	@Override
	public void mouseDragged(MouseEvent e) {
		if ((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
			return;
		}
		if (this.level.isDraggingActive() && this.level.getDragSource() == ReleaseLevel.DRAG_SOURCE_NUMBERBAR) {
			// Form the new point to draw the Piece
			this.level.setTopPointOfDraggingPiece(new Point(e.getX() - this.level.getDraggingDistToPointX(),
					e.getY() - this.level.getDraggingDistToPointY()));
			// Force the view to repaint
			this.builderView.repaint();
		}
	}

	/** Drop the number on the board */
	@Override
	public void mouseReleased(MouseEvent e) {
		if ((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
			return;
		}
		if (this.level.isDraggingActive() && this.level.getDragSource() == ReleaseLevel.DRAG_SOURCE_NUMBERBAR) {
			NumberToBoardMove theMove = new NumberToBoardMove(this.level, this.builderView);

			if (theMove.isValid()) {
				theMove.setOriginalNumber(null);
				if (theMove.doMove()) {
					MoveManager.pushMove(theMove);
				}
			}
			level.setDraggingActive(false);
			level.setDraggingDistToPointX(-1);
			level.setDraggingDistToPointY(-1);
			level.setnumberBeingDragged(null);
			level.setTopPointOfDraggingPiece(null);
			// Force a LevelView repaint
			this.builderView.repaint();
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
