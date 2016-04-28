package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.utils.PieceGenerator;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class Bullpen {

    Workspace workspace;
    Palette palette;
	
    public Bullpen() {
    	this.workspace = Workspace.resetInstance();
    	// TODO: All the count to the hexominoes; For now its random stuff
    	Hexomino[] hexominoes = new Hexomino[35];
    	for(int i = 0; i < 35; i++) {
    		Piece hexominoPiece = new Piece(PieceGenerator.pieces[i]);
    		// Now, add it to the Hexominoes class
    		hexominoes[i] = new Hexomino(i, hexominoPiece);
    		// Set reference to the Hexomino
    		hexominoes[i].getPiece().setParentHexomino(hexominoes[i]);
    	}
    	// Set the palette up with the hexominoes
    	this.palette = new Palette(hexominoes); // TODO: Remove this line and the corresponding constructor
    }
    
    public Bullpen(Palette palette) {
    	workspace = Workspace.resetInstance();
    	this.palette = palette;
    }
    
    public Bullpen(Bullpen anotherBullpen) {
    	this(); // Create a new Bullpen
    	// Initialize the fields
    	this.workspace = Workspace.instance();
    	this.palette = new Palette(anotherBullpen.palette);
    }

	public Workspace getWorkspace() {
		return workspace;
	}

	public Palette getPalette() {
		return palette;
	}    

}