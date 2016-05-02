package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * @author Anthony Panetta
 */
public class UndoMove implements ActionListener {

	AbstractLevel level;
	AbstractBuilderView view;
	
	public UndoMove(AbstractLevel l, AbstractBuilderView abv) {
    	level = l;
    	view = abv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MoveManager.undo(level);
		view.updatePlayerPaletteView();
//		view.repaint();
	}
}
