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
	int levelIndex;
	String levelType;
	
	public AbstractLevelMemento(int levelIndex, String levelType) {
		this.levelIndex = levelIndex;
		palette = new PaletteMemento();
		this.levelType = levelType;
	}
	
	public AbstractLevelMemento(AbstractBoardMemento board,
			PaletteMemento palette, int levelIndex, String levelType) {
		this.board = board;
		this.palette = palette;
		this.levelIndex = levelIndex;
		this.levelType = levelType;
	}
	
	public AbstractBoardMemento getBoardMemento() {
		return board;
	}
	
	public PaletteMemento getPaletteMemento() {
		return palette;
	}
	
	public int getLevelIndex(){
		return this.levelIndex;	
	}
	
	public String getLevelType(){
		return this.levelType;	
	}
	
	public abstract AbstractLevel generateLevel();
}
