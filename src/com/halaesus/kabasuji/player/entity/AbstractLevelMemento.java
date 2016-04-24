/**
 * 
 */
package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

/**
 * @author Corey Dixon
 *
 */
public abstract class AbstractLevelMemento implements Serializable{
	
	private static final long serialVersionUID = 2031176011519301247L;
	AbstractBoardMemento board;
	Palette palette;
	
	
	AbstractLevelMemento(AbstractBoardMemento board, Palette palette) {
		this.board = board;
		this.palette = palette;
	}
	
	abstract AbstractLevel generateLevel();
}
