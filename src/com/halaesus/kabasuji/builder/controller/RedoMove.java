package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * Redoes the last move undone in the move manager
 * @author Anthony Panetta
 */
public class RedoMove implements ActionListener {
	/** The level on which the moves take place */
	AbstractLevel level;
	/** The view holding the level */
	AbstractBuilderView view;

	/**
	 * Generate RedoMove controller from given model and view
	 * @param l Level in the builder
	 * @param abv View in the builder
	 */
    public RedoMove(AbstractLevel l, AbstractBuilderView abv) {
    	level = l;
    	view = abv;
    }

	/**
	 * Redoes the last move and updates the UI.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MoveManager.redo(level);
		view.updatePlayerPaletteView();
	}
}
