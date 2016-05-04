package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * Undoes the last move in the move manager
 * @author Anthony Panetta
 */
public class UndoMove implements ActionListener {
	/** The level on which the moves take place */
	AbstractLevel level;
	/** The view holding the level */
	AbstractBuilderView view;
	
	/**
	 * Generate UndoMove controller from given model and view
	 * @param l Level in the builder
	 * @param abv View in the builder
	 */
	public UndoMove(AbstractLevel l, AbstractBuilderView abv) {
    	level = l;
    	view = abv;
	}
	
	/**
	 * Undoes the last move and updates the UI.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MoveManager.undo(level);
		view.updatePlayerPaletteView();
	}
}
