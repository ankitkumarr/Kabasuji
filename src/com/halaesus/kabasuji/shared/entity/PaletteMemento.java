package com.halaesus.kabasuji.shared.entity;

import java.io.Serializable;

/**
 * Represents the Memento class for the Palette Class
 * <p>
 * @author Corey Dixon
 */
public class PaletteMemento implements Serializable {
	
	private static final long serialVersionUID = -760916650968848895L;
	
	/** Holds the Piece Count of each of the Hexominos in the Palette */
	int[] pieceCount;

	/**
	 * Initializes a Memento for the <code>Palette</code> class
	 */
	public PaletteMemento() {
		pieceCount = new int[35];
	}
	
	/**
	 * Initializes a Memento for the <code>Palette</code> class with the given Hexomino counts
	 * @param hexominoes
	 */
	public PaletteMemento(Hexomino[] hexominoes) {
		pieceCount = new int[35];
		for(int i = 0; i < 35; i++) {
			pieceCount[i] = hexominoes[i].count;
		}
	}
	
	/**
	 * Generates a new instance of <code>Palette</code> with the correct Hexomino Piece Count and ordered Hexominos
	 * @return
	 */
	public Palette generatePalette() {
		return new Palette(pieceCount);
	}

}
