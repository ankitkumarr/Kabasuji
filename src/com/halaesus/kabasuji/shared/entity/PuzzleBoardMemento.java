/**
 * 
 */
package com.halaesus.kabasuji.shared.entity;

/**
 * @author Corey Dixon
 *
 */
public class PuzzleBoardMemento extends AbstractBoardMemento {

	private static final long serialVersionUID = -403476375991445415L;
	
	public PuzzleBoardMemento() {
		super();
	}

	public PuzzleBoardMemento(BoardSquare[][] squares) {
		super(squares);
	}

	@Override
	AbstractBoard generateBoard() {
		return new PuzzleBoard(squares);
	}

}
