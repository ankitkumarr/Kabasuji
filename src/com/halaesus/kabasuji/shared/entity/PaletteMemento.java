/**
 * 
 */
package com.halaesus.kabasuji.shared;

import java.io.Serializable;

/**
 * @author Corey Dixon
 *
 */
public class PaletteMemento implements Serializable {
	
	private static final long serialVersionUID = -760916650968848895L;
	int[] pieceCount;

	public PaletteMemento() {
		pieceCount = new int[35];
	}
	
	public PaletteMemento(Hexomino[] hexominoes) {
		pieceCount = new int[35];
		for(int i = 0; i < 35; i++) {
			pieceCount[i] = hexominoes[i].count;
		}
	}
	
	public Palette generatePalette() {
		return new Palette(pieceCount);
	}

}
