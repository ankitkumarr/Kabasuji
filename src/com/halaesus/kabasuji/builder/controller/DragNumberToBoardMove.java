package com.halaesus.kabasuji.builder.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.boundary.ReleaseBuilderView;
import com.halaesus.kabasuji.builder.entity.*;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseNumber;

/**
 * 
 */
public class DragNumberToBoardMove implements MouseListener, MouseMotionListener {

   
    /**
     * 
     */
    ReleaseLevel level;

    /**
     * 
     */
    ReleaseBuilderView builderView;

    /**
     * @param NumberToBoardMove move 
     * @param AbstractBuilderView builderView
     */
    public DragNumberToBoardMove(ReleaseLevel level, ReleaseBuilderView builderView) {
        this.level = level;
        this.builderView = builderView;
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/**
		Point mouseClickLocation = new Point(e.getX(), e.getY());
		System.out.println(mouseClickLocation);
		ReleaseNumber numBeingdragged;
		
		for(int i = 1; i <=3; i++) {
			for(int j = 1; j <= 6; j++ ){
				Rectangle num = this.builderView.getReleaseNumberRectangle(i, j);
				if (num.contains(mouseClickLocation)) {
					numBeingdragged = this.level.getNumberBar().getNumbers()[i-1][j-1];
					this.builderView.setnumberBeingDragged(new ReleaseNumber(numBeingdragged.getValue(), numBeingdragged.getColor(),
							numBeingdragged.getCol(), numBeingdragged.getRow()));
					System.out.println(i + " " + j);
				}
			}
		}
		
		if (this.builderView.getnumberBeingDragged() == null) {
			//oh well
			return;
		}
		**/
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

 

}