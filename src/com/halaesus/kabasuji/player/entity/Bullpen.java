package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

import com.halaesus.kabasuji.utils.PieceGenerator;

public class Bullpen implements Serializable {


	private static final long serialVersionUID = 1578699169144445639L;
	Workspace workspace;
    Palette palette;
    
	
    public Bullpen() {
    	this.workspace = new Workspace();
    	// TODO: All the count to the hexominoes; For now its random stuff
    	Hexomino[] hexominoes = new Hexomino[35];
    	for(int i = 0; i < 35; i++) {
    		Piece hexominoPiece = new Piece(PieceGenerator.pieces[i]);
    		// Now, add it to the Hexominoes class
    		hexominoes[i] = new Hexomino(i, hexominoPiece, this.workspace);
    		// Set reference to the Hexomino
    		hexominoes[i].getPiece().setParentHexomino(hexominoes[i]);
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