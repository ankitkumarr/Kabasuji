/**
 * 
 */
package com.halaesus.kabasuji.shared;

import java.io.Serializable;

/**
 * @author Corey Dixon
 *
 */
public abstract class AbstractLevelMemento implements Serializable{
	
	private static final long serialVersionUID = 2031176011519301247L;
	AbstractBoardMemento board;
	PaletteMemento palette;
	
	public AbstractLevelMemento() {
		palette = new PaletteMemento();
	}
	
	public AbstractLevelMemento(AbstractBoardMemento board, PaletteMemento palette) {
		this.board = board;
		this.palette = palette;
	}
	
	public abstract AbstractLevel generateLevel();
}
