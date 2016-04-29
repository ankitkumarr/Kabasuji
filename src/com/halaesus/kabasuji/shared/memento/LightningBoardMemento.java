/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import java.util.ArrayList;

import com.halaesus.kabasuji.shared.entity.AbstractBoard;
import com.halaesus.kabasuji.shared.entity.BoardSquare;
import com.halaesus.kabasuji.shared.entity.LightningBoard;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * @author Corey Dixon
 *
 */
public class LightningBoardMemento extends AbstractBoardMemento {
	
	public LightningBoardMemento() {
		super();
	}

	public LightningBoardMemento(BoardSquare[][] squares, ArrayList<Piece> pieces) {
		super(squares, pieces );
	}

	private static final long serialVersionUID = -5266883898767735058L;

	@Override
	public AbstractBoard generateBoard() {
		return new LightningBoard(squares, pieces);
	}

}
