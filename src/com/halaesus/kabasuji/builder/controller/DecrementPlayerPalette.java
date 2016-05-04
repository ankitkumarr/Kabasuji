package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.DecrementPlayerPaletteMove;
import com.halaesus.kabasuji.builder.entity.MoveManager;
import com.halaesus.kabasuji.shared.entity.Hexomino;

/**
 * Controller to decrement the number of a certain piece
 * available to the player
 */
public class DecrementPlayerPalette implements ActionListener {
	/** Hexomino to decrement */
    Hexomino hex;
    /** View to update */
    AbstractBuilderView levelView;
	
    /**
     * Associate the given model and view with ths controller
     * @param h Hexomino model
     * @param l View for the level
     */
	public DecrementPlayerPalette(Hexomino h, AbstractBuilderView l) {
		hex = h;
		levelView = l;
	}
	
	/** Perform the move and update the UI */
	@Override
	public void actionPerformed(ActionEvent e) {
		DecrementPlayerPaletteMove move = new DecrementPlayerPaletteMove(hex);
		if (move.isValid()) {
			if (move.doMove())
				MoveManager.pushMove(move);
			levelView.updatePlayerPaletteView();
		}
	}
}
