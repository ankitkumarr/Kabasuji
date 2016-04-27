/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import java.io.Serializable;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.PaletteMemento;

/**
 * @author Corey Dixon, Akshit (Axe) Soota (axe (at) wpi (dot) edu)
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
	
	public AbstractBoardMemento getBoardMemento() {
		return board;
	}
	
	public PaletteMemento getPaletteMemento() {
		return palette;
	}
	
	public abstract AbstractLevel generateLevel();
}
