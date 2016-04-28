package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.utils.PieceGenerator;

/**
 * 
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 *
 */
public class Palette {

	Hexomino[] hexominoes;
    
    public Palette(int[] pieceCount) {
    	hexominoes = new Hexomino[35];
    	for(int i = 0; i < 35; i++) {
    		Piece hexominoPiece = new Piece(PieceGenerator.pieces[i]);
    		// Now, add it to the Hexominoes class
    		hexominoes[i] = new Hexomino(pieceCount[i], hexominoPiece);
    		// Set reference to the Hexomino
    		hexominoes[i].getPiece().setParentHexomino(hexominoes[i]);
    	}
    }

    public Palette(Hexomino[] hexs) {
    	// TODO: Remove this one
        this.hexominoes = new Hexomino[hexs.length];
        // Make a true copy of the array
        for(int idx = 0; idx < hexs.length; idx++)
        	this.hexominoes[idx] = hexs[idx];
    }
    
    public Palette(Palette anotherPalette) {
    	// Initialize
    	hexominoes = new Hexomino[35];
    	// Set it up
    	for(int i = 0; i < 35; i++) {
    		Piece hexominoPiece = new Piece(PieceGenerator.pieces[i]);
    		// Now, add it to the Hexominoes class
    		hexominoes[i] = new Hexomino(anotherPalette.getHexomino(i).count, hexominoPiece);
    		// Set reference to the Hexomino
    		hexominoes[i].getPiece().setParentHexomino(hexominoes[i]);
    	}
    }

	public Hexomino getHexomino(int idx) {
    	return hexominoes[idx];
    }
	
	public PaletteMemento generateMemento() {
		return new PaletteMemento(hexominoes);
	}

}