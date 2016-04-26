package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.utils.PieceGenerator;

/**
 * 
 * @author Corey Dixon
 *
 */
public class Palette {

	Hexomino[] hexominoes;
	
    public Palette() {
    	hexominoes = new Hexomino[35];
    	for(int i = 0; i < 35; i++) {
    		Piece hexominoPiece = new Piece(PieceGenerator.pieces[i]);
    		// Now, add it to the Hexominoes class
    		hexominoes[i] = new Hexomino(0, hexominoPiece);
    		// Set reference to the Hexomino
    		hexominoes[i].getPiece().setParentHexomino(hexominoes[i]);
    	}
	}
    
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
        this.hexominoes = hexs;
    }

	public Hexomino getHexomino(int idx) {
    	return hexominoes[idx];
    }
	
	public PaletteMemento generateMemento() {
		return new PaletteMemento(hexominoes);
	}

}