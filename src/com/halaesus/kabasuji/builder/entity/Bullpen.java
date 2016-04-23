package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.entity.Hexomino;
import com.halaesus.kabasuji.builder.entity.Palette;
import com.halaesus.kabasuji.builder.entity.Piece;
import com.halaesus.kabasuji.builder.entity.Workspace;
import com.halaesus.kabasuji.utils.BuilderPieceGenerator;


/**
 * 
 */
public class Bullpen {

    /**
     * Default constructor
     * 
     */
	
	 Palette palette;

	Workspace workspace;
    public Bullpen() {
    	
    	this.workspace = new Workspace();
    	// TODO: All the count to the hexominoes; For now its random stuff
    	Hexomino[] hexominoes = new Hexomino[35];
    	for(int i = 0; i < 35; i++) {
    		Piece hexominoPiece = new Piece (BuilderPieceGenerator.pieces[i]);
    		// Now, add it to the Hexominoes class
    		hexominoes[i] = new Hexomino(i, hexominoPiece, this.workspace);
    	}
    	// Set the palette up with the hexominoes
    	this.palette = new Palette(hexominoes);
    }
    
    public Palette getPalette(){
    	return palette;
    }
    
    public Workspace getWorkspace() {
    	return workspace;
    }

}