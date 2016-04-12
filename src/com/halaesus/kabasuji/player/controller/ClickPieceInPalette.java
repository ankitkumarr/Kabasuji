package com.halaesus.kabasuji.player.controller;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.halaesus.kabasuji.player.boundary.AbstractLevelView;
import com.halaesus.kabasuji.player.entity.Hexomino;

public class ClickPieceInPalette implements MouseListener {

    Hexomino hexomino;
    AbstractLevelView levelView;
    
    public ClickPieceInPalette(Hexomino hexomino, AbstractLevelView levelView) {
    	this.levelView = levelView;
        this.hexomino = hexomino;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked Hexomino #" + hexomino.getCount()); // TODO
	}

	@Override
	public void mouseEntered(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseExited(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mousePressed(MouseEvent e) { /* Nothing to do */ }

	@Override
	public void mouseReleased(MouseEvent e) { /* Nothing to do */ }

}