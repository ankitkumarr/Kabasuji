package com.halaesus.kabasuji.player.boundary;

import com.halaesus.kabasuji.player.entity.AbstractBoard;

/**
 * 
 */
public class AbstractBoardView {

	/**
	 * Default constructor
	 */
	public AbstractBoardView() {
	}

	/**
	 * 
	 */
	AbstractBoard board;

	/**
	 * 
	 */
	PieceView[] pieces;

	/**
	 * 
	 */
	SquareView[] squares;

	/**
	 * @param AbstractBoard
	 *            board
	 */
	public AbstractBoardView(AbstractBoard board) {
		// TODO implement here
	}

}