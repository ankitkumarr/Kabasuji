package com.halaesus.kabasuji.player.entity;

import com.halaesus.kabasuji.utils.PieceGenerator;

/**
 * 
 * @author Corey Dixon
 *
 */
public class Bullpen {

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
    
    public Bullpen(Palette palette) {
    	workspace = new Workspace();
    	this.palette = palette;
    }

	public Workspace getWorkspace() {
		return workspace;
	}

	public Palette getPalette() {
		return palette;
	}    

}