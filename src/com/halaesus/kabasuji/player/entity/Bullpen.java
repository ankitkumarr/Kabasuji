package com.halaesus.kabasuji.player.entity;

import com.halaesus.kabasuji.utils.PieceGenerator;

public class Bullpen {

    Workspace workspace;
    Palette palette;
	
    public Bullpen() {
    	this.workspace = new Workspace();
    	// TODO: All the count to the hexominoes; For now its random stuff
    	Hexomino[] hexominoes = new Hexomino[35];
    	for(int i = 0; i < 35; i++) {
    		Piece hexominoPiece = new Piece(PieceGenerator.pieces[i]);
    		hexominoPiece.centerPiece(); // Center the copy of the Hexomino Piece
    		// Now, add it to the Hexominoes class
    		hexominoes[i] = new Hexomino(i, hexominoPiece, this.workspace);
    	}
    	// Set the palette up with the hexominoes
    	this.palette = new Palette(hexominoes);
    }

	public Workspace getWorkspace() {
		return workspace;
	}

	public Palette getPalette() {
		return palette;
	}    

}