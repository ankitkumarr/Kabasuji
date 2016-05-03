package com.halaesus.kabasuji.player.tests;

import java.awt.event.MouseEvent;

import junit.framework.TestCase;

// TODO make this for KabaSuji ? - Brian KD

public abstract class PlayerTestCase extends TestCase {
/*	*//** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. *//*
	public MouseEvent createPressed (AbstractBuilderView builderView, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	*//** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. *//*
	public MouseEvent createRightClick (Solitaire game, Widget view, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, true);
		return me;
	}
	
	*//** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. *//*
	public MouseEvent createReleased (Solitaire game, Widget view, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_RELEASED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	*//** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. *//*
	public MouseEvent createClicked (Solitaire game, Widget view, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 1, false);
		return me;
	}
	
	*//** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. *//*
	public MouseEvent createDoubleClicked (Solitaire game, Widget view, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 2, false);
		return me;
	}*/
}
