package com.halaesus.kabasuji.shared.entity;

import com.halaesus.kabasuji.utils.PieceGenerator;

/**
 * Represents a Palette with 35 hexomino Pieces, as a part of the Player Bullpen
 * <p>
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public class Palette {

	/** Represents the Hexominos that are shown on the Player Bullpen */
	Hexomino[] hexominoes;
    
	/**
	 * Construct a Player Palette with the given Piece Counts
	 * @param pieceCount
	 */
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
    
    /**
     * Construct a Player Palette with the given array of Hexominos
     * @param hexs
     */
    public Palette(Hexomino[] hexs) {
        this.hexominoes = new Hexomino[hexs.length];
        // Make a true copy of the array
        for(int idx = 0; idx < hexs.length; idx++)
        	this.hexominoes[idx] = hexs[idx];
    }
    
    /**
     * A copy constructor of Palette which instantiates another Palette with same (yet new instances) of the Hexominos
     * @param anotherPalette
     */
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

    /**
     * Get a Hexomino with a given index
     * @param idx
     * @return
     */
	public Hexomino getHexomino(int idx) {
    	return hexominoes[idx];
    }
	
	/**
	 * Generate PaletteMemento for the Palette
	 * @return
	 */
	public PaletteMemento generateMemento() {
		return new PaletteMemento(hexominoes);
	}

}