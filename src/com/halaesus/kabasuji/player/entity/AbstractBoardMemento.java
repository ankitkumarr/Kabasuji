/**
 * 
 */
package com.halaesus.kabasuji.player.entity;

import java.io.Serializable;

/**
 * @author Corey Dixon
 *
 */
public abstract class AbstractBoardMemento implements Serializable {
	
	private static final long serialVersionUID = 5436199349310710599L;
	BoardSquare[][] squares;
	
	AbstractBoardMemento(BoardSquare[][] squares) {
		this.squares = squares;
	}
	
	abstract AbstractBoard generateBoard();
}
