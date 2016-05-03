package com.halaesus.kabasuji.builder.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.builder.entity.NumberToBoardMove;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * @author Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class DragNumberToBoardMove implements MouseListener, MouseMotionListener {
   
    ReleaseLevel level;
    ReleaseBuilderView builderView;

    public DragNumberToBoardMove(ReleaseLevel level, ReleaseBuilderView builderView) {
        this.level = level;
        this.builderView = builderView;
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int desiredKey = e.BUTTON1_MASK | e.CTRL_MASK;
		if(((e.getModifiers() & desiredKey) == desiredKey)) {
			return;
		}
		
		Point mouseClickLocation = new Point(e.getX(), e.getY());
		//System.out.println(mouseClickLocation);
		ReleaseNumber numBeingdragged;
		
		for(int i = 1; i <=3; i++) {
			for(int j = 1; j <= 6; j++ ){
				Rectangle num = this.builderView.getReleaseNumberRectangle(i, j);
				if (num.contains(mouseClickLocation)) {
					this.level.setDraggingActive(true);
					this.level.setDragSource(ReleaseLevel.DRAG_SOURCE_NUMBERBAR);
					numBeingdragged = this.level.getNumberBar().getNumbers()[i-1][j-1];
					this.level.setnumberBeingDragged(new ReleaseNumber(numBeingdragged.getValue(), numBeingdragged.getColor(),
							numBeingdragged.getCol(), numBeingdragged.getRow()));
					this.level.setTopPointOfDraggingPiece(new Point(num.x, num.y));
					this.level.setDraggingDistToPointX(e.getX() - num.x);
					this.level.setDraggingDistToPointY(e.getY() - num.y);
				}
			}
		}
		this.builderView.repaint();
		
		if (this.level.getnumberBeingDragged() == null) {
			//oh well
			return;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int desiredKey = e.BUTTON1_MASK | e.CTRL_MASK;
		if((e.getModifiers() & e.CTRL_MASK) == e.CTRL_MASK) {
			return;
		}
		if( this.level.isDraggingActive() &&
				this.level.getDragSource() == ReleaseLevel.DRAG_SOURCE_NUMBERBAR ) {
			NumberToBoardMove theMove = new NumberToBoardMove(this.level, this.builderView);

			if( theMove.isValid() ) {
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

	@Override
	public void mouseDragged(MouseEvent e) {
		
		int desiredKey = e.BUTTON1_MASK | e.CTRL_MASK;
		if((e.getModifiers() & e.CTRL_MASK) == e.CTRL_MASK) {
			return;
		}
		if( this.level.isDraggingActive() &&
			this.level.getDragSource() == ReleaseLevel.DRAG_SOURCE_NUMBERBAR) {
			// Form the new point to draw the Piece
			this.level.setTopPointOfDraggingPiece(new Point(e.getX() - this.level.getDraggingDistToPointX(), 
					                                        e.getY() - this.level.getDraggingDistToPointY()));
			// Force the view to repaint
			this.builderView.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}