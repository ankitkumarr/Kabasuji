/**
 * 
 */
package com.halaesus.kabasuji.shared;

import java.io.Serializable;

/**
 * @author Corey Dixon
 *
 */
public abstract class AbstractBoardMemento implements Serializable {
	
	private static final long serialVersionUID = 5436199349310710599L;
	BoardSquare[][] squares;
	
	public AbstractBoardMemento() {
		squares = new BoardSquare[12][12];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				squares[i][j] = new BoardSquare(true);
			}
		}
	}
	
	public AbstractBoardMemento(BoardSquare[][] squares) {
		this.squares = squares;
	}
	
	abstract AbstractBoard generateBoard();
}
