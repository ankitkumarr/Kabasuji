/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import java.io.Serializable;
import java.util.ArrayList;

import com.halaesus.kabasuji.shared.entity.AbstractBoard;
import com.halaesus.kabasuji.shared.entity.BoardSquare;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * @author Corey Dixon
 *
 */
public abstract class AbstractBoardMemento implements Serializable {
	
	private static final long serialVersionUID = 5436199349310710599L;
	BoardSquare[][] squares;
	ArrayList<Piece> pieces;
	
	public AbstractBoardMemento() {
		squares = new BoardSquare[12][12];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				squares[i][j] = new BoardSquare(true);
				squares[i][j].setActive(false);
			}
		}
		pieces = new ArrayList<Piece>();
	}
	
	public AbstractBoardMemento(BoardSquare[][] squares, ArrayList<Piece> pieces) {
		this.squares = squares;
		this.pieces = pieces;
	}
	
	public abstract AbstractBoard generateBoard();
}
