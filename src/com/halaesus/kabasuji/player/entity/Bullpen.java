package com.halaesus.kabasuji.player.entity;

import com.halaesus.kabasuji.utils.PieceGenerator;

public class Bullpen {

    Workspace workspace;
    Palette palette;
	
    public Bullpen() {
    	this.workspace = new Workspace();
    	// TODO: All the hexominoes; For now its random stuff
    	Hexomino[] hexominoes = new Hexomino[35];
    	for(int i = 0; i < 35; i++)
    		hexominoes[i] = new Hexomino(i, PieceGenerator.pieces[i], this.workspace);
    	this.palette = new Palette(hexominoes);
    }

	public Workspace getWorkspace() {
		return workspace;
	}

	public Palette getPalette() {
		return palette;
	}    

}