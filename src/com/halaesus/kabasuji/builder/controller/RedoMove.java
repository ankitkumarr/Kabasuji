package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * @author Anthony Panetta
 */
public class RedoMove implements ActionListener {
	
	AbstractLevel level;
	AbstractBuilderView view;

    /**
     * Constructor
     */
    public RedoMove(AbstractLevel l, AbstractBuilderView abv) {
    	level = l;
    	view = abv;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		MoveManager.redo(level);
		view.updatePlayerPaletteView();
	}
}
