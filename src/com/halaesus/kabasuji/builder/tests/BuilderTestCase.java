package com.halaesus.kabasuji.builder.tests;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;

import junit.framework.TestCase;

// TODO make this for KabaSuji ? - Brian KD

public abstract class BuilderTestCase extends TestCase {
	public MouseEvent createPressed (AbstractBuilderView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	public MouseEvent createDragged (AbstractBuilderView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_DRAGGED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createRightClick (AbstractBuilderView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createReleased (AbstractBuilderView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_RELEASED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createClicked (AbstractBuilderView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createDoubleClicked (AbstractBuilderView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
}
