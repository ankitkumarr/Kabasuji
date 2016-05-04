package com.halaesus.kabasuji.player.tests;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;

import junit.framework.TestCase;

// TODO make this for KabaSuji ? - Brian KD

public abstract class PlayerTestCase extends TestCase {
	public MouseEvent createPressed (AbstractLevelView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	public MouseEvent createDragged (AbstractLevelView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_DRAGGED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createRightClick (AbstractLevelView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createReleased (AbstractLevelView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_RELEASED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createClicked (AbstractLevelView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createDoubleClicked (AbstractLevelView game, int dx, int dy) {
		MouseEvent me = new MouseEvent(new JLabel(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				dx, dy, 0, false);
		return me;
	}
}
