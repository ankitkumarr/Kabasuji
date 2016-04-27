/**
 * 
 */
package com.halaesus.kabasuji.shared.memento;

import com.halaesus.kabasuji.shared.entity.AbstractBoard;
import com.halaesus.kabasuji.shared.entity.BoardSquare;
import com.halaesus.kabasuji.shared.entity.LightningBoard;

/**
 * @author Corey Dixon
 *
 */
public class LightningBoardMemento extends AbstractBoardMemento {
	
	public LightningBoardMemento() {
		super();
	}

	public LightningBoardMemento(BoardSquare[][] squares) {
		super(squares);
	}

	private static final long serialVersionUID = -5266883898767735058L;

	@Override
	public AbstractBoard generateBoard() {
		return new LightningBoard(squares);
	}

}
