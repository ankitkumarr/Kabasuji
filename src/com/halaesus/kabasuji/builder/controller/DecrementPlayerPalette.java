package com.halaesus.kabasuji.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.builder.entity.DecrementPlayerPaletteMove;
import com.halaesus.kabasuji.shared.entity.Hexomino;

public class DecrementPlayerPalette implements ActionListener {

    Hexomino hex;
    AbstractBuilderView levelView;
	
	public DecrementPlayerPalette(Hexomino h, AbstractBuilderView l) {
		hex = h;
		levelView = l;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DecrementPlayerPaletteMove move = new DecrementPlayerPaletteMove(hex);
		if (move.isValid(levelView.getLevel())) {
			move.doMove(levelView.getLevel());
			levelView.updatePlayerPaletteView();
		}
	}
}
