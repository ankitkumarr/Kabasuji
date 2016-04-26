package com.halaesus.kabasuji.shared;

import java.io.Serializable;

import com.halaesus.kabasuji.utils.PieceGenerator;

/**
 * 
 * @author Corey Dixon
 *
 */
public class Palette implements Serializable{

	private static final long serialVersionUID = 6907929958555099610L;
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

    public Palette(Hexomino[] hexs) {
        this.hexominoes = hexs;
    }

	public Hexomino getHexomino(int idx) {
    	return hexominoes[idx];
    }

}