/**
 * 
 */
package com.halaesus.kabasuji.player.entity;

import java.util.Set;

/**
 * @author Corey Dixon
 *
 */
public class ReleaseBoardMemento extends AbstractBoardMemento {

	private static final long serialVersionUID = -1440200991438024797L;
	Set<ReleaseNumber> numbers;
	
	ReleaseBoardMemento(BoardSquare[][] squares, Set<ReleaseNumber> numbers) {
		super(squares);
		this.numbers = numbers;
	}

	@Override
	AbstractBoard generateBoard() {
		return new ReleaseBoard(squares, numbers);
	}

}