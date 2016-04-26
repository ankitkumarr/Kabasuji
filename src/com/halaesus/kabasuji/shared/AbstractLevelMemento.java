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
	Palette palette;
	
	public AbstractLevelMemento() {
		palette = new Palette();
	}
	
	public AbstractLevelMemento(AbstractBoardMemento board, Palette palette) {
		this.board = board;
		this.palette = palette;
	}
	
	public abstract AbstractLevel generateLevel();
}
