/**
 * 
 */
package com.halaesus.kabasuji.player.entity;

/**
 * @author Corey Dixon
 *
 */
public class LightningBoardMemento extends AbstractBoardMemento {

	LightningBoardMemento(BoardSquare[][] squares) {
		super(squares);
	}

	private static final long serialVersionUID = -5266883898767735058L;

	@Override
	AbstractBoard generateBoard() {
		return new LightningBoard(squares);
	}

}
