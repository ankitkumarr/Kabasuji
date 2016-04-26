/**
 * 
 */
package com.halaesus.kabasuji.shared;

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
	AbstractBoard generateBoard() {
		return new LightningBoard(squares);
	}

}
